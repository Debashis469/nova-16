package vm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * VMWriter
 * --------
 * A clean, modular writer for emitting VM commands.
 * Used by the CompilationEngine to output correct VM code.
 */
public class VMWriter {

    private final BufferedWriter writer;

    /**
     * Creates a new VMWriter that writes to the given file.
     */
    public VMWriter(File outputFile) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(outputFile));
    }

    /* ---------------------------------------------------------
       Core low-level write method
       --------------------------------------------------------- */

    private void write(String command) throws IOException {
        writer.write(command);
        writer.newLine();
    }

    /* ---------------------------------------------------------
       Push & Pop commands
       --------------------------------------------------------- */

    /**
     * Writes a VM "push" command.
     */
    public void writePush(String segment, int index) throws IOException {
        write("push " + segment + " " + index);
    }

    /**
     * Writes a VM "pop" command.
     */
    public void writePop(String segment, int index) throws IOException {
        write("pop " + segment + " " + index);
    }

    /* ---------------------------------------------------------
       Arithmetic & Logical commands
       --------------------------------------------------------- */

    /**
     * Writes a VM arithmetic/logical command.
     * Examples: add, sub, neg, eq, lt, gt, and, or, not
     */
    public void writeArithmetic(String command) throws IOException {
        write(command);
    }

    /* ---------------------------------------------------------
       Branching commands
       --------------------------------------------------------- */

    public void writeLabel(String label) throws IOException {
        write("label " + label);
    }

    public void writeGoto(String label) throws IOException {
        write("goto " + label);
    }

    public void writeIf(String label) throws IOException {
        write("if-goto " + label);
    }

    /* ---------------------------------------------------------
       Function commands
       --------------------------------------------------------- */

    /**
     * Writes a VM function declaration.
     */
    public void writeFunction(String name, int nLocals) throws IOException {
        write("function " + name + " " + nLocals);
    }

    /**
     * Writes a VM call to another function/method.
     */
    public void writeCall(String name, int nArgs) throws IOException {
        write("call " + name + " " + nArgs);
    }

    /**
     * Writes a VM return command.
     */
    public void writeReturn() throws IOException {
        write("return");
    }

    /* ---------------------------------------------------------
       Cleanup
       --------------------------------------------------------- */

    /**
     * Closes the underlying output file.
     */
    public void close() throws IOException {
        writer.flush();
        writer.close();
    }
}
