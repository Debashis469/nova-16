package tokenizer;

import util.TokenType;

import java.io.*;
import java.util.*;

/**
 * LumaTokenizer
 * ----------------
 * Reads a Luma source file, removes comments and whitespace,
 * and provides a stream of tokens one at a time.
 */
public class LumaTokenizer {

    private BufferedReader reader;
    private String currentLine;
    private int index;

    private String currentToken;
    private TokenType currentTokenType;

    private boolean insideBlockComment = false;

    /* ------------------ LANGUAGE DEFINITIONS ------------------ */

    private static final Set<String> KEYWORDS = Set.of(
            "class", "constructor", "function", "method",
            "field", "static", "var",
            "int", "char", "boolean", "void",
            "true", "false", "null", "this",
            "let", "do", "if", "else", "while", "return"
    );

    private static final Set<Character> SYMBOLS = Set.of(
            '{','}','(',')','[',']','.',',',';',
            '+','-','*','/','&','|','<','>','=','~'
    );

    /* ------------------ CONSTRUCTOR ------------------ */

    public LumaTokenizer(String inputFile) throws IOException {
        reader = new BufferedReader(new FileReader(inputFile));
        currentLine = null;
        index = 0;
    }

    /* ------------------ CORE CONTROL ------------------ */

    public boolean hasMoreTokens() throws IOException {
        if (currentLine != null && index < currentLine.length()) {
            return true;
        }
        return readNextValidLine();
    }

    /**
     * Reads next line skipping comments and empty lines
     */
    private boolean readNextValidLine() throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {

            line = stripComments(line).trim();
            index = 0;

            if (!line.isEmpty()) {
                currentLine = line;
                return true;
            }
        }
        return false;
    }

    /**
     * Removes "//", "/* ... * /", and "/** ... * /" style comments
     */
    private String stripComments(String line) {
        StringBuilder cleaned = new StringBuilder();
        int i = 0;

        while (i < line.length()) {
            if (insideBlockComment) {
                if (i + 1 < line.length() &&
                        line.charAt(i) == '*' &&
                        line.charAt(i + 1) == '/') {
                    insideBlockComment = false;
                    i += 2;
                } else {
                    i++;
                }
            } else {
                if (i + 1 < line.length()) {
                    String two = line.substring(i, i + 2);

                    if (two.equals("//")) break;

                    if (two.equals("/*")) {
                        insideBlockComment = true;
                        i += 2;
                        continue;
                    }
                }
                cleaned.append(line.charAt(i));
                i++;
            }
        }
        return cleaned.toString();
    }

    /* ------------------ TOKEN ADVANCE ------------------ */

    public void advance() throws IOException {
        if (!hasMoreTokens()) return;

        while (index < currentLine.length() &&
                Character.isWhitespace(currentLine.charAt(index))) {
            index++;
        }

        char c = currentLine.charAt(index);

        /* SYMBOL */
        if (SYMBOLS.contains(c)) {
            currentToken = String.valueOf(c);
            currentTokenType = TokenType.SYMBOL;
            index++;
            return;
        }

        /* STRING CONSTANT */
        if (c == '"') {
            index++;
            int start = index;
            while (currentLine.charAt(index) != '"') index++;
            currentToken = currentLine.substring(start, index);
            currentTokenType = TokenType.STRING_CONST;
            index++;
            return;
        }

        /* INTEGER CONSTANT */
        if (Character.isDigit(c)) {
            int start = index;
            while (index < currentLine.length() &&
                    Character.isDigit(currentLine.charAt(index))) index++;
            currentToken = currentLine.substring(start, index);
            currentTokenType = TokenType.INT_CONST;
            return;
        }

        /* IDENTIFIER / KEYWORD */
        int start = index;
        while (index < currentLine.length() &&
                (Character.isLetterOrDigit(currentLine.charAt(index))
                        || currentLine.charAt(index) == '_')) {
            index++;
        }

        currentToken = currentLine.substring(start, index);
        currentTokenType = KEYWORDS.contains(currentToken)
                ? TokenType.KEYWORD
                : TokenType.IDENTIFIER;
    }

    /* ------------------ ACCESSORS ------------------ */

    public TokenType tokenType() { return currentTokenType; }

    public String keyword() {
        if (currentTokenType == TokenType.KEYWORD)
            return currentToken;
        throw new IllegalStateException("keyword() called but token is not a keyword.");
    }

    public char symbol() { return currentToken.charAt(0); }

    public String identifier() { return currentToken; }

    public int intVal() { return Integer.parseInt(currentToken); }

    public String stringVal() { return currentToken; }

    /* ============================================================
                 SAVE / RESTORE FOR LOOKAHEAD
       ============================================================ */

    private String savedLine;
    private int savedIndex;
    private String savedToken;
    private TokenType savedTokenType;

    /**
     * Save tokenizer state (used before lookahead).
     */
    public void save() {
        savedLine = currentLine;
        savedIndex = index;
        savedToken = currentToken;
        savedTokenType = currentTokenType;
    }

    /**
     * Restore tokenizer state after lookahead.
     */
    public void restore() {
        currentLine = savedLine;
        index = savedIndex;
        currentToken = savedToken;
        currentTokenType = savedTokenType;
    }

    /**
     * Peek next token WITHOUT consuming it.
     */
    public String peek() throws IOException {
        save();
        advance();
        String tok = currentToken;
        restore();
        return tok;
    }
}
