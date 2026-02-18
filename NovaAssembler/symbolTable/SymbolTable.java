package symbolTable;

import java.util.HashMap;

 // Symbol table for Hack Assembler - maps symbols to addresses
public class SymbolTable {
    private final HashMap<String, Integer> symbolTable;

    // Creates a new empty symbol table
    public SymbolTable() {
        symbolTable = new HashMap<>();
    }


     // Adds the pair (symbol, address) to the table
    public void addEntry(String symbol, int address) {
        symbolTable.put(symbol, address);
    }


    // Does the symbol table contain the given symbol ?
    public boolean contains(String symbol) {
        return symbolTable.containsKey(symbol);
    }


    // Returns the address associated with the symbol
    public int getAddress(String symbol) {
        return symbolTable.get(symbol);
    }
}