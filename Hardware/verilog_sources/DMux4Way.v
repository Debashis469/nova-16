module DMux4Way (
    input in,
    input [1:0] sel,
    output a, b, c, d
);
    wire dmuxAB, dmuxCD;

    DMux inst1 (.in(in),     .sel(sel[1]), .a(dmuxAB), .b(dmuxCD));
    DMux inst2 (.in(dmuxAB), .sel(sel[0]), .a(a), .b(b));
    DMux inst3 (.in(dmuxCD), .sel(sel[0]), .a(c), .b(d));
endmodule