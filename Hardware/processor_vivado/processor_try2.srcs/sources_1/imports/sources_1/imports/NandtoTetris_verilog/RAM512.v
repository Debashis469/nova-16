module RAM512 (
    input clk,          // Added explicit clock
    input [15:0] in,
    input load,
    input [8:0] address,
    output [15:0] out
);
    wire load0, load1, load2, load3, load4, load5, load6, load7;
    wire [15:0] r0, r1, r2, r3, r4, r5, r6, r7;

    DMux8Way inst1 (
        .in(load),
        .sel(address[8:6]), // HDL [6..8] -> Verilog [8:6]
        .a(load0), .b(load1), .c(load2), .d(load3),
        .e(load4), .f(load5), .g(load6), .h(load7)
    );

    RAM64 inst2 (.clk(clk), .in(in), .load(load0), .address(address[5:0]), .out(r0));
    RAM64 inst3 (.clk(clk), .in(in), .load(load1), .address(address[5:0]), .out(r1));
    RAM64 inst4 (.clk(clk), .in(in), .load(load2), .address(address[5:0]), .out(r2));
    RAM64 inst5 (.clk(clk), .in(in), .load(load3), .address(address[5:0]), .out(r3));
    RAM64 inst6 (.clk(clk), .in(in), .load(load4), .address(address[5:0]), .out(r4));
    RAM64 inst7 (.clk(clk), .in(in), .load(load5), .address(address[5:0]), .out(r5));
    RAM64 inst8 (.clk(clk), .in(in), .load(load6), .address(address[5:0]), .out(r6));
    RAM64 inst9 (.clk(clk), .in(in), .load(load7), .address(address[5:0]), .out(r7));

    Mux8Way16 inst10 (
        .a(r0), .b(r1), .c(r2), .d(r3),
        .e(r4), .f(r5), .g(r6), .h(r7),
        .sel(address[8:6]),
        .out(out)
    );
endmodule