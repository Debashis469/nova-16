package codewriter;

import utils.CommandType;

import java.io.*;

public class CodeWriter {

    private final BufferedWriter out;
    private String fileName = "Unknown";
    private int labelCounter = 0;
    private int returnCounter = 0;
    private String currentFunction = ""; // used to scope labels

    public CodeWriter(File outFile) throws IOException {
        out = new BufferedWriter(new FileWriter(outFile));
        write("// VM Translator Output");
        // bootstrap: set SP=256 and call Sys.init (book recommends)
        write("// ---------- bootstrap/startup ----------");
        write("@256");
        write("D=A");
        write("@SP");
        write("M=D");
        // call Sys.init
        writeCall("Sys.init", 0);
        write("// ---------- end bootstrap ----------");
    }

    public void setFileName(String name) {
        this.fileName = name.replace(".vm", "");
    }

    // ---------- arithmetic ----------
    public void writeArithmetic(String cmd) throws IOException {
        write("// " + cmd);

        switch (cmd) {
            case "add": writeBinary("M=D+M"); break;
            case "sub": writeBinary("M=M-D"); break;
            case "and": writeBinary("M=D&M"); break;
            case "or":  writeBinary("M=D|M"); break;
            case "neg": writeUnary("M=-M");  break;
            case "not": writeUnary("M=!M");  break;
            case "eq": writeCompare("JEQ"); break;
            case "gt": writeCompare("JGT"); break;
            case "lt": writeCompare("JLT"); break;
            default:
                throw new UnsupportedOperationException("Unknown arithmetic command: " + cmd);
        }
    }


    // ---------- push/pop ----------
    public void writePushPop(CommandType type, String segment, int index) throws IOException {
        write("// " + (type == CommandType.C_PUSH ? "push " : "pop ") + segment + " " + index);

        try {
            if (type == CommandType.C_PUSH) writePush(segment, index);
            else writePop(segment, index);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void writePush(String segment, int index) throws IOException {
        switch (segment) {
            case "constant":
                write("@" + index);
                write("D=A");
                pushD();
                break;

            case "local":
            case "argument":
            case "this":
            case "that":
                write("@" + base(segment));
                write("D=M");
                write("@" + index);
                write("A=D+A");
                write("D=M");
                pushD();
                break;

            case "temp":
                write("@R" + (5 + index));
                write("D=M");
                pushD();
                break;

            case "pointer":
                write("@" + (3 + index));
                write("D=M");
                pushD();
                break;

            case "static":
                write("@" + fileName + "." + index);
                write("D=M");
                pushD();
                break;

            default:
                throw new UnsupportedOperationException("Unknown push segment: " + segment);
        }
    }

    private void writePop(String segment, int index) throws IOException {
        switch (segment) {
            case "local":
            case "argument":
            case "this":
            case "that":
                write("@" + base(segment));
                write("D=M");
                write("@" + index);
                write("D=D+A");
                write("@R13");
                write("M=D");
                popToD();
                write("@R13");
                write("A=M");
                write("M=D");
                break;

            case "temp":
                popToD();
                write("@R" + (5 + index));
                write("M=D");
                break;

            case "pointer":
                popToD();
                write("@" + (3 + index));
                write("M=D");
                break;

            case "static":
                popToD();
                write("@" + fileName + "." + index);
                write("M=D");
                break;

            default:
                throw new UnsupportedOperationException("Unknown pop segment: " + segment);
        }
    }

    // ---------- program flow ----------
    public void writeLabel(String label) throws IOException {
        write("// label " + label);
        write("(" + makeLabel(label) + ")");
    }

    public void writeGoto(String label) throws IOException {
        write("// goto " + label);
        write("@" + makeLabel(label));
        write("0;JMP");
    }

    public void writeIf(String label) throws IOException {
        write("// if-goto " + label);
        // pop top of stack into D, jump if D != 0
        popToD();
        write("@" + makeLabel(label));
        write("D;JNE"); // jump when D != 0
    }

    // ---------- function calling ----------
    public void writeFunction(String functionName, int nLocals) throws IOException {
        write("// function " + functionName + " " + nLocals);
        currentFunction = functionName;
        write("(" + functionName + ")");
        // initialize local variables to 0
        for (int i = 0; i < nLocals; i++) {
            write("D=0");
            pushD();
        }
    }

    public void writeCall(String functionName, int nArgs) throws IOException {
        write("// call " + functionName + " " + nArgs);
        String returnLabel = "RET_" + functionName + "$" + (++returnCounter);

        // push return-address
        write("@" + returnLabel);
        write("D=A");
        pushD();

        // push LCL, ARG, THIS, THAT
        write("@LCL"); write("D=M"); pushD();
        write("@ARG"); write("D=M"); pushD();
        write("@THIS"); write("D=M"); pushD();
        write("@THAT"); write("D=M"); pushD();

        // ARG = SP - nArgs - 5
        write("@SP");
        write("D=M");
        write("@" + (nArgs + 5));
        write("D=D-A");
        write("@ARG");
        write("M=D");

        // LCL = SP
        write("@SP");
        write("D=M");
        write("@LCL");
        write("M=D");

        // goto functionName
        write("@" + functionName);
        write("0;JMP");

        // return label
        write("(" + returnLabel + ")");
    }

    public void writeReturn() throws IOException {
        write("// return");
        // FRAME = LCL (store in R13)
        write("@LCL");
        write("D=M");
        write("@R13");
        write("M=D");

        // RET = *(FRAME - 5) (store in R14)
        write("@5");
        write("A=D-A");
        write("D=M");
        write("@R14");
        write("M=D");

        // *ARG = pop() (pop top into D, store into ARG)
        popToD();
        write("@ARG");
        write("A=M");
        write("M=D");

        // SP = ARG + 1
        write("@ARG");
        write("D=M+1");
        write("@SP");
        write("M=D");

        // THAT = *(FRAME - 1)
        write("@R13");
        write("AM=M-1");
        write("D=M");
        write("@THAT");
        write("M=D");

        // THIS = *(FRAME - 2)
        write("@R13");
        write("AM=M-1");
        write("D=M");
        write("@THIS");
        write("M=D");

        // ARG = *(FRAME - 3)
        write("@R13");
        write("AM=M-1");
        write("D=M");
        write("@ARG");
        write("M=D");

        // LCL = *(FRAME - 4)
        write("@R13");
        write("AM=M-1");
        write("D=M");
        write("@LCL");
        write("M=D");

        // goto RET
        write("@R14");
        write("A=M");
        write("0;JMP");
    }

    // ---------- helpers ----------

    private String makeLabel(String label) {
        // scope the label by current function if available
        if (currentFunction == null || currentFunction.isEmpty()) {
            return label;
        } else {
            return currentFunction + "$" + label;
        }
    }

    private void pushD() throws IOException {
        write("@SP");
        write("A=M");
        write("M=D");
        write("@SP");
        write("M=M+1");
    }

    private void popToD() throws IOException {
        write("@SP");
        write("AM=M-1");
        write("D=M");
    }

    private void writeUnary(String op) throws IOException {
        write("@SP");
        write("A=M-1");
        write(op);
    }

    private void writeBinary(String op) throws IOException {
        write("@SP");
        write("AM=M-1");
        write("D=M");
        write("A=A-1");
        write(op);
    }

    private void writeCompare(String jump) throws IOException {
        String t = "TRUE" + (++labelCounter);
        String e = "END" + labelCounter;

        write("@SP");
        write("AM=M-1");
        write("D=M");
        write("A=A-1");
        write("D=M-D");
        write("@" + t);
        write("D;" + jump);

        write("@SP");
        write("A=M-1");
        write("M=0");
        write("@" + e);
        write("0;JMP");

        write("(" + t + ")");
        write("@SP");
        write("A=M-1");
        write("M=-1");

        write("(" + e + ")");
    }

    private String base(String segment) {
        switch (segment) {
            case "local": return "LCL";
            case "argument": return "ARG";
            case "this": return "THIS";
            case "that": return "THAT";
        }
        return "";
    }

    private void write(String s) throws IOException {
        out.write(s);
        out.newLine();
    }

    public void close() throws IOException {
        out.flush();
        out.close();
    }
}
