package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import util.InstructionType;

// Reads assembly commands, parses them, and provides access to their components
public class Parser {
    private ArrayList<String> commands;
    private String currentCommand;
    private int currentIndex;

    // Opens the input file and gets ready to parse it
    public Parser(String inputFile) throws IOException {
        commands = new ArrayList<>();
        currentIndex = -1;
        currentCommand = null;
        loadAndCleanFile(inputFile);
    }

    // Loads file and removes whitespace and comments
    private void loadAndCleanFile(String inputFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;

        while ((line = reader.readLine()) != null) {
            String cleanedLine = cleanLine(line);
            if (!cleanedLine.isEmpty()) {
                commands.add(cleanedLine);
            }
        }
        reader.close();
    }

    // Removes comments and whitespace from a line
    private String cleanLine(String line) {
        // Remove comments (everything after //)
        int commentIndex = line.indexOf("//");
        if (commentIndex != -1) {
            line = line.substring(0, commentIndex);
        }
        // Remove all whitespace
        return line.replaceAll("\\s+", "");
    }

    // Are there more commands in the input?
    public boolean hasMoreCommands() {
        return currentIndex < commands.size() - 1;
    }

    // Reads the next command from input and makes it the current command
    public void advance() {
        if (hasMoreCommands()) {
            currentIndex++;
            currentCommand = commands.get(currentIndex);
        }
    }

    // Returns the type of the current command
    public InstructionType commandType() {
        if (currentCommand == null) {
            return null;
        }

        if (currentCommand.startsWith("@")) {
            return InstructionType.A_COMMAND;
        } else if (currentCommand.startsWith("(")) {
            return InstructionType.L_COMMAND;
        } else {
            return InstructionType.C_COMMAND;
        }
    }

    // Returns the symbol or decimal of the current command (@Xxx or (Xxx))
    public String symbol() {
        InstructionType type = commandType();

        if (type == InstructionType.A_COMMAND) {
            // Remove @ and return the rest
            return currentCommand.substring(1);
        } else if (type == InstructionType.L_COMMAND) {
            // Remove ( and ) and return what's inside
            return currentCommand.substring(1, currentCommand.length() - 1);
        }
        return null;
    }

    // Returns the dest mnemonic in the current C-command
    public String dest() {
        if (commandType() != InstructionType.C_COMMAND) {
            return null;
        }

        // Check if there's an = sign
        int equalIndex = currentCommand.indexOf("=");
        if (equalIndex != -1) {
            // Everything before = is dest
            return currentCommand.substring(0, equalIndex);
        }
        // No dest part
        return "null";
    }

    // Returns the comp mnemonic in the current C-command
    public String comp() {
        if (commandType() != InstructionType.C_COMMAND) {
            return null;
        }

        int equalIndex = currentCommand.indexOf("=");
        int semiIndex = currentCommand.indexOf(";");

        // comp is between = and ; (or from = to end, or from start to ;)
        if (equalIndex != -1 && semiIndex != -1) {
            // dest=comp;jump
            return currentCommand.substring(equalIndex + 1, semiIndex);
        } else if (equalIndex != -1) {
            // dest=comp
            return currentCommand.substring(equalIndex + 1);
        } else if (semiIndex != -1) {
            // comp;jump
            return currentCommand.substring(0, semiIndex);
        }
        // This should never happen for valid C-commands
        return "null";
    }

    // Returns the jump mnemonic in the current C-command
    public String jump() {
        if (commandType() != InstructionType.C_COMMAND) {
            return null;
        }

        // Check if there's a ; sign
        int semiIndex = currentCommand.indexOf(";");
        if (semiIndex != -1) {
            // Everything after ; is jump
            return currentCommand.substring(semiIndex + 1);
        }
        // No jump part
        return "null";
    }
}