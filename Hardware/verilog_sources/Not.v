module Not (
    input in,
    output out
);
    Nand inst1 (.a(in), .b(in), .out(out));
endmodule