package utils;

public enum CommandType {
    C_ARITHMETIC,
    C_PUSH,
    C_POP,
    // program flow
    C_LABEL,
    C_GOTO,
    C_IF,
    // function calling
    C_FUNCTION,
    C_CALL,
    C_RETURN
}
