import tokenizer.LumaTokenizer;
import parser.CompilationEngine;
import symboltable.SymbolTable;
import vm.VMWriter;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class LumaCompiler {

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            System.err.println("Usage: java LumaCompiler <file.luma | directory>");
            System.exit(1);
        }

        Path src = Paths.get(args[0]);
        File[] lumaFiles;

        // Identify input type
        if (Files.isDirectory(src)) {
            File dir = src.toFile();
            lumaFiles = dir.listFiles((d, name) -> name.endsWith(".luma"));

            if (lumaFiles == null || lumaFiles.length == 0) {
                System.err.println("No .luma files found in directory: " + src);
                System.exit(1);
            }

            // Sort for predictable compilation order
            Arrays.sort(lumaFiles, Comparator.comparing(File::getName));

        } else {
            // Single file mode
            if (!src.toString().endsWith(".luma")) {
                System.err.println("Input must be .luma file or directory");
                System.exit(1);
            }
            lumaFiles = new File[]{ src.toFile() };
        }

        // Compile each .luma file → .vm file
        for (File lumaFile : lumaFiles) {
            compileLumaFile(lumaFile);
        }

        System.out.println("Compilation complete!");
    }

    private static void compileLumaFile(File lumaFile) throws IOException {
        String path = lumaFile.getAbsolutePath();

        // Output = same name but .vm
        String outPath = path.substring(0, path.lastIndexOf(".")) + ".vm";
        File outFile = new File(outPath);

        // Init modules
        LumaTokenizer tokenizer = new LumaTokenizer(lumaFile.getAbsolutePath());
        VMWriter vmWriter = new VMWriter(outFile);
        SymbolTable symbolTable = new SymbolTable();

        // Compilation engine generates VM code
        CompilationEngine engine = new CompilationEngine(tokenizer, vmWriter, symbolTable);

        engine.compileClass();
        vmWriter.close();

        System.out.println("Compiled: " + lumaFile.getName() + " → " + outFile.getName());
    }
}
