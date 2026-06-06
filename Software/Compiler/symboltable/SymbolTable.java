package symboltable;

import util.Kind;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private static class Symbol {
        String type;
        Kind kind;
        int index;

        Symbol(String type, Kind kind, int index) {
            this.type = type;
            this.kind = kind;
            this.index = index;
        }
    }

    private Map<String, Symbol> classScope = new HashMap<>();
    private Map<String, Symbol> subroutineScope = new HashMap<>();

    private int staticCount = 0;
    private int fieldCount = 0;
    private int argCount = 0;
    private int varCount = 0;

    public SymbolTable() {}

    public void startSubroutine() {
        subroutineScope.clear();
        argCount = 0;
        varCount = 0;
    }

    public void define(String name, String type, Kind kind) {
        int index;
        switch (kind) {
            case STATIC -> index = staticCount++;
            case FIELD -> index = fieldCount++;
            case ARG -> index = argCount++;
            case VAR -> index = varCount++;
            default -> throw new IllegalArgumentException("Invalid kind");
        }

        Symbol symbol = new Symbol(type, kind, index);

        if (kind == Kind.STATIC || kind == Kind.FIELD) {
            classScope.put(name, symbol);
        } else {
            subroutineScope.put(name, symbol);
        }
    }

    public Kind kindOf(String name) {
        if (subroutineScope.containsKey(name))
            return subroutineScope.get(name).kind;

        if (classScope.containsKey(name))
            return classScope.get(name).kind;

        return Kind.NONE;
    }

    public String typeOf(String name) {
        if (subroutineScope.containsKey(name))
            return subroutineScope.get(name).type;

        if (classScope.containsKey(name))
            return classScope.get(name).type;

        return null;
    }

    public int indexOf(String name) {
        if (subroutineScope.containsKey(name))
            return subroutineScope.get(name).index;

        if (classScope.containsKey(name))
            return classScope.get(name).index;

        return -1;
    }

    public int varCount(Kind kind) {
        return switch (kind) {
            case STATIC -> staticCount;
            case FIELD -> fieldCount;
            case ARG -> argCount;
            case VAR -> varCount;
            default -> 0;
        };
    }
}
