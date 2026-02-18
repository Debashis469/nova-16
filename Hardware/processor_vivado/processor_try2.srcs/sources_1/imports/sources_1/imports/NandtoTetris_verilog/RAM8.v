module RAM8 (
    input clk,          // Added explicit clock
    input [15:0] in,
    input load,
    input [2:0] address,
    output [15:0] out
);
    wire load0, load1, load2, load3, load4, load5, load6, load7;
    wire [15:0] r0, r1, r2, r3, r4, r5, r6, r7;

    DMux8Way inst1 (
        .in(load),
        .sel(address),
        .a(load0), .b(load1), .c(load2), .d(load3),
        .e(load4), .f(load5), .g(load6), .h(load7)
    );

    Register inst2 (.clk(clk), .in(in), .load(load0), .out(r0));
    Register inst3 (.clk(clk), .in(in), .load(load1), .out(r1));
    Register inst4 (.clk(clk), .in(in), .load(load2), .out(r2));
    Register inst5 (.clk(clk), .in(in), .load(load3), .out(r3));
    Register inst6 (.clk(clk), .in(in), .load(load4), .out(r4));
    Register inst7 (.clk(clk), .in(in), .load(load5), .out(r5));
    Register inst8 (.clk(clk), .in(in), .load(load6), .out(r6));
    Register inst9 (.clk(clk), .in(in), .load(load7), .out(r7));

    Mux8Way16 inst10 (
        .a(r0), .b(r1), .c(r2), .d(r3),
        .e(r4), .f(r5), .g(r6), .h(r7),
        .sel(address),
        .out(out)
    );
endmodule