module Or8Way (
    input [7:0] in,
    output out
);
    wire or01, or23, or45, or67;
    wire or0123, or4567;

    Or inst1 (.a(in[0]), .b(in[1]), .out(or01));
    Or inst2 (.a(in[2]), .b(in[3]), .out(or23));
    Or inst3 (.a(in[4]), .b(in[5]), .out(or45));
    Or inst4 (.a(in[6]), .b(in[7]), .out(or67));

    Or inst5 (.a(or01), .b(or23), .out(or0123));
    Or inst6 (.a(or45), .b(or67), .out(or4567));

    Or inst7 (.a(or0123), .b(or4567), .out(out));
endmodule