package parser;

import tokenizer.LumaTokenizer;
import util.Kind;
import util.TokenType;
import symboltable.SymbolTable;
import vm.VMWriter;

import java.io.IOException;

/**
 * CompilationEngine - Recursive descent parser that compiles Luma to VM code
 */
public class CompilationEngine {

    private final LumaTokenizer tokenizer;
    private final VMWriter vmWriter;
    private final SymbolTable symbolTable;

    private String className;
    private int labelCounter = 0;

    public CompilationEngine(LumaTokenizer tokenizer, VMWriter writer, SymbolTable symbolTable) throws IOException {
        this.tokenizer = tokenizer;
        this.vmWriter = writer;
        this.symbolTable = symbolTable;
        tokenizer.advance();
    }

    /* ============================================================
                           UTILITY HELPERS
       ============================================================ */

    private String nextLabel(String base) {
        return base + (labelCounter++);
    }

    private void eat(String expected) throws IOException {
        boolean matches = false;

        if (tokenizer.tokenType() == TokenType.KEYWORD) {
            matches = tokenizer.keyword().equals(expected);
        } else if (tokenizer.tokenType() == TokenType.SYMBOL) {
            matches = (tokenizer.symbol() == expected.charAt(0));
        }

        if (!matches) {
            throw new RuntimeException("Syntax error: expected '" + expected + "'");
        }
        tokenizer.advance();
    }

    private void eatSymbol(char c) throws IOException {
        if (tokenizer.tokenType() != TokenType.SYMBOL || tokenizer.symbol() != c) {
            throw new RuntimeException("Expected symbol: " + c);
        }
        tokenizer.advance();
    }

    // Reads type from current token (handles both keywords and identifiers)
    private String readType() throws IOException {
        String type;
        if (tokenizer.tokenType() == TokenType.KEYWORD) {
            type = tokenizer.keyword();  // int, char, boolean, void
        } else {
            type = tokenizer.identifier();  // ClassName
        }
        tokenizer.advance();
        return type;
    }

    /* ============================================================
                           CLASS COMPILATION
       ============================================================ */

    public void compileClass() throws IOException {
        eat("class");

        className = tokenizer.identifier();
        tokenizer.advance();

        eatSymbol('{');

        while (tokenizer.tokenType() == TokenType.KEYWORD &&
                (tokenizer.keyword().equals("static") || tokenizer.keyword().equals("field"))) {
            compileClassVarDec();
        }

        while (tokenizer.tokenType() == TokenType.KEYWORD &&
                (tokenizer.keyword().equals("constructor") ||
                        tokenizer.keyword().equals("function") ||
                        tokenizer.keyword().equals("method"))) {
            compileSubroutineDec();
        }

        eatSymbol('}');
    }

    /* ============================================================
                           CLASS VARS
       ============================================================ */

    private void compileClassVarDec() throws IOException {
        Kind kind = tokenizer.keyword().equals("static") ? Kind.STATIC : Kind.FIELD;
        tokenizer.advance();

        String type = readType();

        symbolTable.define(tokenizer.identifier(), type, kind);
        tokenizer.advance();

        while (tokenizer.tokenType() == TokenType.SYMBOL && tokenizer.symbol() == ',') {
            tokenizer.advance();
            symbolTable.define(tokenizer.identifier(), type, kind);
            tokenizer.advance();
        }

        eatSymbol(';');
    }

    /* ============================================================
                         SUBROUTINE DECLARATIONS
       ============================================================ */

    private void compileSubroutineDec() throws IOException {
        String subType = tokenizer.keyword(); // constructor/function/method
        tokenizer.advance();

        readType(); // return type

        String subName = tokenizer.identifier();
        tokenizer.advance();

        symbolTable.startSubroutine();

        if (subType.equals("method")) {
            symbolTable.define("this", className, Kind.ARG);
        }

        eatSymbol('(');
        compileParameterList();
        eatSymbol(')');

        compileSubroutineBody(subName, subType);
    }

    private void compileParameterList() throws IOException {
        // Check for empty parameter list
        if (tokenizer.tokenType() == TokenType.SYMBOL && tokenizer.symbol() == ')') {
            return;
        }

        // First parameter
        String type = readType();
        String name = tokenizer.identifier();
        tokenizer.advance();
        symbolTable.define(name, type, Kind.ARG);

        // Additional parameters
        while (tokenizer.tokenType() == TokenType.SYMBOL && tokenizer.symbol() == ',') {
            tokenizer.advance();
            type = readType();
            name = tokenizer.identifier();
            tokenizer.advance();
            symbolTable.define(name, type, Kind.ARG);
        }
    }

    /* ============================================================
                         SUBROUTINE BODY
       ============================================================ */

    private void compileSubroutineBody(String subName, String subType) throws IOException {
        eatSymbol('{');

        while (tokenizer.tokenType() == TokenType.KEYWORD &&
                tokenizer.keyword().equals("var")) {
            compileVarDec();
        }

        int nLocals = symbolTable.varCount(Kind.VAR);
        vmWriter.writeFunction(className + "." + subName, nLocals);

        if (subType.equals("constructor")) {
            int nFields = symbolTable.varCount(Kind.FIELD);
            vmWriter.writePush("constant", nFields);
            vmWriter.writeCall("Memory.alloc", 1);
            vmWriter.writePop("pointer", 0); // THIS = allocated memory
        }

        if (subType.equals("method")) {
            vmWriter.writePush("argument", 0);
            vmWriter.writePop("pointer", 0); // THIS = argument 0
        }

        compileStatements();
        eatSymbol('}');
    }

    /* ============================================================
                           LOCAL VAR DECLARATIONS
       ============================================================ */

    private void compileVarDec() throws IOException {
        eat("var");
        String type = readType();

        symbolTable.define(tokenizer.identifier(), type, Kind.VAR);
        tokenizer.advance();

        while (tokenizer.tokenType() == TokenType.SYMBOL && tokenizer.symbol() == ',') {
            tokenizer.advance();
            symbolTable.define(tokenizer.identifier(), type, Kind.VAR);
            tokenizer.advance();
        }

        eatSymbol(';');
    }

    /* ============================================================
                             STATEMENTS
       ============================================================ */

    private void compileStatements() throws IOException {
        while (tokenizer.tokenType() == TokenType.KEYWORD) {
            String keyword = tokenizer.keyword();
            switch (keyword) {
                case "let": compileLet(); break;
                case "if": compileIf(); break;
                case "while": compileWhile(); break;
                case "do": compileDo(); break;
                case "return": compileReturn(); break;
                default: return;
            }
        }
    }

    /* ============================================================
                                 LET
       ============================================================ */

    private void compileLet() throws IOException {
        eat("let");

        String name = tokenizer.identifier();
        tokenizer.advance();

        boolean arrayAssignment = false;

        if (tokenizer.tokenType() == TokenType.SYMBOL && tokenizer.symbol() == '[') {
            arrayAssignment = true;
            tokenizer.advance();
            compileExpression();
            eatSymbol(']');
            pushVariable(name);
            vmWriter.writeArithmetic("add");
        }

        eatSymbol('=');
        compileExpression();
        eatSymbol(';');

        if (arrayAssignment) {
            vmWriter.writePop("temp", 0);
            vmWriter.writePop("pointer", 1);
            vmWriter.writePush("temp", 0);
            vmWriter.writePop("that", 0);
        } else {
            popVariable(name);
        }
    }

    /* ============================================================
                                 IF
       ============================================================ */

    private void compileIf() throws IOException {
        eat("if");
        eatSymbol('(');
        compileExpression();
        eatSymbol(')');

        String IF_TRUE = nextLabel("IF_TRUE");
        String IF_FALSE = nextLabel("IF_FALSE");
        String IF_END = nextLabel("IF_END");

        vmWriter.writeIf(IF_TRUE);
        vmWriter.writeGoto(IF_FALSE);
        vmWriter.writeLabel(IF_TRUE);

        eatSymbol('{');
        compileStatements();
        eatSymbol('}');

        vmWriter.writeGoto(IF_END);
        vmWriter.writeLabel(IF_FALSE);

        if (tokenizer.tokenType() == TokenType.KEYWORD &&
                tokenizer.keyword().equals("else")) {
            eat("else");
            eatSymbol('{');
            compileStatements();
            eatSymbol('}');
        }

        vmWriter.writeLabel(IF_END);
    }

    /* ============================================================
                               WHILE
       ============================================================ */

    private void compileWhile() throws IOException {
        eat("while");

        String WHILE_EXP = nextLabel("WHILE_EXP");
        String WHILE_END = nextLabel("WHILE_END");

        vmWriter.writeLabel(WHILE_EXP);

        eatSymbol('(');
        compileExpression();
        eatSymbol(')');

        vmWriter.writeArithmetic("not");
        vmWriter.writeIf(WHILE_END);

        eatSymbol('{');
        compileStatements();
        eatSymbol('}');

        vmWriter.writeGoto(WHILE_EXP);
        vmWriter.writeLabel(WHILE_END);
    }

    /* ============================================================
                               DO
       ============================================================ */

    private void compileDo() throws IOException {
        eat("do");
        compileSubroutineCall();
        eatSymbol(';');
        vmWriter.writePop("temp", 0); // ignore return value
    }

    /* ============================================================
                              RETURN
       ============================================================ */

    private void compileReturn() throws IOException {
        eat("return");

        if (!(tokenizer.tokenType() == TokenType.SYMBOL && tokenizer.symbol() == ';')) {
            compileExpression();
        } else {
            vmWriter.writePush("constant", 0); // void return
        }

        eatSymbol(';');
        vmWriter.writeReturn();
    }

    /* ============================================================
                           SUBROUTINE CALL
       ============================================================ */

    private void compileSubroutineCall() throws IOException {
        String name = tokenizer.identifier();
        tokenizer.advance();

        int nArgs = 0;

        if (tokenizer.tokenType() == TokenType.SYMBOL && tokenizer.symbol() == '.') {
            tokenizer.advance();

            Kind kind = symbolTable.kindOf(name);

            if (kind != Kind.NONE) {
                // Method call on object
                pushVariable(name);
                nArgs++;
                name = symbolTable.typeOf(name) + "." + tokenizer.identifier();
            } else {
                // Function call on class
                name = name + "." + tokenizer.identifier();
            }
            tokenizer.advance();
        } else {
            // Method call on current object
            vmWriter.writePush("pointer", 0);
            nArgs++;
            name = className + "." + name;
        }

        eatSymbol('(');
        nArgs += compileExpressionList();
        eatSymbol(')');

        vmWriter.writeCall(name, nArgs);
    }

    /* ============================================================
                           EXPRESSION LIST
       ============================================================ */

    private int compileExpressionList() throws IOException {
        int count = 0;

        if (tokenizer.tokenType() == TokenType.SYMBOL && tokenizer.symbol() == ')') {
            return 0;
        }

        compileExpression();
        count++;

        while (tokenizer.tokenType() == TokenType.SYMBOL && tokenizer.symbol() == ',') {
            tokenizer.advance();
            compileExpression();
            count++;
        }

        return count;
    }

    /* ============================================================
                                EXPRESSION
       ============================================================ */

    private void compileExpression() throws IOException {
        compileTerm();

        while (tokenizer.tokenType() == TokenType.SYMBOL &&
                "+-*/&|<>=".indexOf(tokenizer.symbol()) >= 0) {

            char op = tokenizer.symbol();
            tokenizer.advance();
            compileTerm();
            writeOp(op);
        }
    }

    private void writeOp(char op) throws IOException {
        switch (op) {
            case '+': vmWriter.writeArithmetic("add"); break;
            case '-': vmWriter.writeArithmetic("sub"); break;
            case '*': vmWriter.writeCall("Math.multiply", 2); break;
            case '/': vmWriter.writeCall("Math.divide", 2); break;
            case '&': vmWriter.writeArithmetic("and"); break;
            case '|': vmWriter.writeArithmetic("or"); break;
            case '<': vmWriter.writeArithmetic("lt"); break;
            case '>': vmWriter.writeArithmetic("gt"); break;
            case '=': vmWriter.writeArithmetic("eq"); break;
        }
    }

    /* ============================================================
                                TERM
       ============================================================ */

    private void compileTerm() throws IOException {
        TokenType type = tokenizer.tokenType();

        // Integer constant
        if (type == TokenType.INT_CONST) {
            vmWriter.writePush("constant", tokenizer.intVal());
            tokenizer.advance();
            return;
        }

        // String constant
        if (type == TokenType.STRING_CONST) {
            writeStringConstant(tokenizer.stringVal());
            tokenizer.advance();
            return;
        }

        // Keyword constant
        if (type == TokenType.KEYWORD) {
            writeKeywordConstant(tokenizer.keyword());
            tokenizer.advance();
            return;
        }

        // Identifier (var, array, or subroutine call)
        if (type == TokenType.IDENTIFIER) {
            String name = tokenizer.identifier();
            String next = tokenizer.peek();

            // Array access: var[expr]
            if ("[".equals(next)) {
                tokenizer.advance();
                pushVariable(name);
                eatSymbol('[');
                compileExpression();
                eatSymbol(']');
                vmWriter.writeArithmetic("add");
                vmWriter.writePop("pointer", 1);
                vmWriter.writePush("that", 0);
                return;
            }

            // Subroutine call
            if ("(".equals(next) || ".".equals(next)) {
                compileSubroutineCall();
                return;
            }

            // Simple variable
            tokenizer.advance();
            pushVariable(name);
            return;
        }

        // Parenthesized expression
        if (tokenizer.tokenType() == TokenType.SYMBOL && tokenizer.symbol() == '(') {
            tokenizer.advance();
            compileExpression();
            eatSymbol(')');
            return;
        }

        // Unary operators: - , ~
        if (tokenizer.tokenType() == TokenType.SYMBOL &&
                (tokenizer.symbol() == '-' || tokenizer.symbol() == '~')) {

            char unary = tokenizer.symbol();
            tokenizer.advance();
            compileTerm();

            if (unary == '-') {
                vmWriter.writeArithmetic("neg");
            } else {
                vmWriter.writeArithmetic("not");
            }
            return;
        }

        throw new RuntimeException("Invalid term");
    }

    /* ============================================================
                       HELPERS FOR VARIABLES
       ============================================================ */

    private void pushVariable(String name) throws IOException {
        Kind kind = symbolTable.kindOf(name);
        int index = symbolTable.indexOf(name);

        switch (kind) {
            case STATIC: vmWriter.writePush("static", index); break;
            case FIELD: vmWriter.writePush("this", index); break;
            case ARG: vmWriter.writePush("argument", index); break;
            case VAR: vmWriter.writePush("local", index); break;
            case NONE: throw new RuntimeException("Undefined variable: " + name);
        }
    }

    private void popVariable(String name) throws IOException {
        Kind kind = symbolTable.kindOf(name);
        int index = symbolTable.indexOf(name);

        switch (kind) {
            case STATIC: vmWriter.writePop("static", index); break;
            case FIELD: vmWriter.writePop("this", index); break;
            case ARG: vmWriter.writePop("argument", index); break;
            case VAR: vmWriter.writePop("local", index); break;
            case NONE: throw new RuntimeException("Undefined variable: " + name);
        }
    }

    /* ============================================================
                       KEYWORD CONSTANT HANDLERS
       ============================================================ */

    private void writeKeywordConstant(String k) throws IOException {
        switch (k) {
            case "true":
                vmWriter.writePush("constant", 0);
                vmWriter.writeArithmetic("not");
                break;
            case "false":
            case "null":
                vmWriter.writePush("constant", 0);
                break;
            case "this":
                vmWriter.writePush("pointer", 0);
                break;
        }
    }

    /* ============================================================
                       STRING CONSTANT HANDLING
       ============================================================ */

    private void writeStringConstant(String s) throws IOException {
        vmWriter.writePush("constant", s.length());
        vmWriter.writeCall("String.new", 1);

        for (char c : s.toCharArray()) {
            vmWriter.writePush("constant", (int) c);
            vmWriter.writeCall("String.appendChar", 2);
        }
    }
}