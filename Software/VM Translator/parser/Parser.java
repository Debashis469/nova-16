package parser;

import utils.CommandType;

import java.io.*;
import java.util.*;

public class Parser {

    private final List<String> commands = new ArrayList<>();
    private int current = -1;

    public Parser(File vmFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(vmFile));
        String line;
        while ((line = br.readLine()) != null) {
            String cleaned = line.split("//")[0].trim();
            if (!cleaned.isEmpty()) commands.add(cleaned);
        }
        br.close();
    }

    public boolean hasMoreCommands() {
        return current + 1 < commands.size();
    }

    public void advance() {
        current++;
    }

    public CommandType commandType() {
        String[] parts = commands.get(current).split("\\s+");
        String cmd = parts[0];
        switch (cmd) {
            case "push": return CommandType.C_PUSH;
            case "pop": return CommandType.C_POP;
            case "label": return CommandType.C_LABEL;
            case "goto": return CommandType.C_GOTO;
            case "if-goto": return CommandType.C_IF;
            case "function": return CommandType.C_FUNCTION;
            case "call": return CommandType.C_CALL;
            case "return": return CommandType.C_RETURN;
            default:
                // any other single-word command is an arithmetic/logical command
                return CommandType.C_ARITHMETIC;
        }
    }

    /**
     * arg1: For C_ARITHMETIC returns the command (e.g., "add" / "eq").
     * For C_RETURN there is no arg1 (returns null).
     * For others returns the first argument (label/function name/segment).
     */
    public String arg1() {
        String[] p = commands.get(current).split("\\s+");
        CommandType t = commandType();
        if (t == CommandType.C_RETURN) return null;
        if (t == CommandType.C_ARITHMETIC) return p[0];
        // for other commands, return the first argument if present
        return p.length > 1 ? p[1] : null;
    }

    /**
     * arg2: returns integer second argument for push/pop/function/call,
     * or throws if not present.
     */
    public int arg2() {
        String[] p = commands.get(current).split("\\s+");
        if (p.length < 3) throw new IllegalStateException("arg2 missing for command: " + commands.get(current));
        return Integer.parseInt(p[2]);
    }
}
