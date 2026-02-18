import parser.Parser;
import code.Code;
import symbolTable.SymbolTable;
import util.InstructionType;
import java.io.FileWriter;
import java.io.IOException;

// Main assembler class that translates Nova assembly code to binary
public class NovaAssembler {

    public static void main(String[] args) {
        // Check if input file is provided
        if (args.length == 0) {
            System.out.println("Usage: java NovaAssembler Prog.asm");
            return;
        }

        String inputFile = args[0];
        String outputFile = inputFile.replace(".asm", ".nova");

        try {
            // Initialize symbol table with predefined symbols
            SymbolTable symbolTable = new SymbolTable();
            initializePredefinedSymbols(symbolTable);

            // First pass: scan for labels and add them to symbol table
            firstPass(inputFile, symbolTable);

            // Second pass: translate assembly to binary
            secondPass(inputFile, outputFile, symbolTable);

            System.out.println("Assembly completed successfully!");
            System.out.println("Output written to: " + outputFile);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Add predefined symbols to symbol table
    private static void initializePredefinedSymbols(SymbolTable symbolTable) {
        // Virtual registers
        symbolTable.addEntry("SP", 0);
        symbolTable.addEntry("LCL", 1);
        symbolTable.addEntry("ARG", 2);
        symbolTable.addEntry("THIS", 3);
        symbolTable.addEntry("THAT", 4);

        // Registers R0-R15
        for (int i = 0; i <= 15; i++) {
            symbolTable.addEntry("R" + i, i);
        }

        // I/O pointers
        symbolTable.addEntry("SCREEN", 16384);
        symbolTable.addEntry("KBD", 24576);
    }

    // First pass: find all label declarations and add to symbol table
    private static void firstPass(String inputFile, SymbolTable symbolTable) throws IOException {
        Parser parser = new Parser(inputFile);
        int romAddress = 0;

        while (parser.hasMoreCommands()) {
            parser.advance();
            InstructionType commandType = parser.commandType();

            if (commandType == InstructionType.L_COMMAND) {
                // Label declaration - add to symbol table with current ROM address
                String label = parser.symbol();
                symbolTable.addEntry(label, romAddress);
            } else {
                // A-command or C-command - increment ROM address
                romAddress++;
            }
        }
    }

    // Second pass: translate all commands to binary
    private static void secondPass(String inputFile, String outputFile,
                                   SymbolTable symbolTable) throws IOException {
        Parser parser = new Parser(inputFile);
        Code code = new Code();
        FileWriter writer = new FileWriter(outputFile);

        int nextVariableAddress = 16;

        while (parser.hasMoreCommands()) {
            parser.advance();
            InstructionType commandType = parser.commandType();
            String binaryInstruction = "";

            if (commandType == InstructionType.A_COMMAND) {
                // Translate A-instruction
                String symbol = parser.symbol();
                int value;

                if (isNumeric(symbol)) {
                    value = Integer.parseInt(symbol);
                } else {
                    if (symbolTable.contains(symbol)) {
                        value = symbolTable.getAddress(symbol);
                    } else {
                        symbolTable.addEntry(symbol, nextVariableAddress);
                        value = nextVariableAddress;
                        nextVariableAddress++;
                    }
                }

                if (value < 0 || value > 32767) {
                    throw new IllegalArgumentException(
                            "Address out of range: " + value + ". Must be between 0 and 32767."
                    );
                }

                binaryInstruction = "0" + String.format("%15s",
                        Integer.toBinaryString(value)).replace(' ', '0');

            } else if (commandType == InstructionType.C_COMMAND) {
                String destBits = code.dest(parser.dest());
                String compBits = code.comp(parser.comp());
                String jumpBits = code.jump(parser.jump());

                binaryInstruction = "111" + compBits + destBits + jumpBits;
            }

            if (commandType != InstructionType.L_COMMAND) {
                writer.write(binaryInstruction + "\n");
            }
        }

        writer.close();
    }

    // Helper method to check if a string is numeric
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
