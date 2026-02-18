module And (
    input a,
    input b,
    output out
);
    wire nandOut;

    Nand inst1 (.a(a), .b(b), .out(nandOut));
    Not  inst2 (.in(nandOut), .out(out));
endmodule