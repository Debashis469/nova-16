module RAM16K (
    input clk,          // Added explicit clock
    input [15:0] in,
    input load,
    input [13:0] address, // HDL [14], but usually 14 bits [0..13]
    output [15:0] out
);
    wire load0, load1, load2, load3;
    wire [15:0] r0, r1, r2, r3;

    DMux4Way inst1 (
        .in(load),
        .sel(address[13:12]), // HDL [12..13] -> Verilog [13:12]
        .a(load0), .b(load1), .c(load2), .d(load3)
    );

    RAM4K inst2 (.clk(clk), .in(in), .load(load0), .address(address[11:0]), .out(r0));
    RAM4K inst3 (.clk(clk), .in(in), .load(load1), .address(address[11:0]), .out(r1));
    RAM4K inst4 (.clk(clk), .in(in), .load(load2), .address(address[11:0]), .out(r2));
    RAM4K inst5 (.clk(clk), .in(in), .load(load3), .address(address[11:0]), .out(r3));

    Mux4Way16 inst6 (
        .a(r0), .b(r1), .c(r2), .d(r3),
        .sel(address[13:12]),
        .out(out)
    );
endmodule