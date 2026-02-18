module DMux8Way (
    input in,
    input [2:0] sel,
    output a, b, c, d, e, f, g, h
);
    wire dmuxABCD, dmuxEFGH;

    DMux     inst1 (.in(in),       .sel(sel[2]), .a(dmuxABCD), .b(dmuxEFGH));
    DMux4Way inst2 (.in(dmuxABCD), .sel(sel[1:0]), .a(a), .b(b), .c(c), .d(d));
    DMux4Way inst3 (.in(dmuxEFGH), .sel(sel[1:0]), .a(e), .b(f), .c(g), .d(h));
endmodule