module Or (
    input a,
    input b,
    output out
);
    wire notA, notB;

    Not  inst1 (.in(a), .out(notA));
    Not  inst2 (.in(b), .out(notB));
    Nand inst3 (.a(notA), .b(notB), .out(out));
endmodule