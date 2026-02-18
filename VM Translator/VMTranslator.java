import parser.Parser;
import codewriter.CodeWriter;
import utils.CommandType;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class VMTranslator {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: java VMTranslator <file.vm | directory>");
            System.exit(1);
        }

        // compute output file robustly
        Path src = Paths.get(args[0]);
        File outFile;
        File[] vmFiles;

        if (Files.isDirectory(src)) {
            File dir = src.toFile();
            vmFiles = dir.listFiles((d, name) -> name.endsWith(".vm"));
            if (vmFiles == null || vmFiles.length == 0) {
                System.err.println("No .vm files found in directory: " + src);
                System.exit(1);
            }
            Arrays.sort(vmFiles, Comparator.comparing(File::getName));
            outFile = src.resolve(src.getFileName().toString() + ".asm").toFile();
        } else {
            if (!src.toString().endsWith(".vm")) {
                System.err.println("Input must be .vm file or directory");
                System.exit(1);
            }
            vmFiles = new File[] { src.toFile() };
            // handle null parent by using current directory if necessary
            Path parent = src.getParent();
            if (parent == null) parent = Paths.get(".");   // default to current directory
            String asmName = src.getFileName().toString().replaceAll("\\.vm$", ".asm");
            outFile = parent.resolve(asmName).toFile();
        }

        CodeWriter writer = new CodeWriter(outFile);
        try {
            for (File vmFile : vmFiles) {
                writer.setFileName(vmFile.getName());
                Parser parser = new Parser(vmFile);
                while (parser.hasMoreCommands()) {
                    parser.advance();
                    CommandType type = parser.commandType();

                    switch (type) {
                        case C_ARITHMETIC:
                            writer.writeArithmetic(parser.arg1());
                            break;
                        case C_PUSH:
                        case C_POP:
                            writer.writePushPop(type, parser.arg1(), parser.arg2());
                            break;
                        case C_LABEL:
                            writer.writeLabel(parser.arg1());
                            break;
                        case C_GOTO:
                            writer.writeGoto(parser.arg1());
                            break;
                        case C_IF:
                            writer.writeIf(parser.arg1());
                            break;
                        case C_FUNCTION:
                            writer.writeFunction(parser.arg1(), parser.arg2());
                            break;
                        case C_CALL:
                            writer.writeCall(parser.arg1(), parser.arg2());
                            break;
                        case C_RETURN:
                            writer.writeReturn();
                            break;
                        default:
                            break;
                    }
                }
            }
        } finally {
            writer.close();
        }

        System.out.println("Translated to: " + outFile.getAbsolutePath());
    }
}
