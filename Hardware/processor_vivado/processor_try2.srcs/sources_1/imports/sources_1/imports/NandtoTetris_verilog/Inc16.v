module Inc16 (
    input [15:0] in,
    output [15:0] out
);
    // HDL: b[0]=true, b[1..15]=false translates to 16'b000...001
    Add16 inst1 (
        .a(in), 
        .b({15'b0, 1'b1}), 
        .out(out)
    );
endmodule