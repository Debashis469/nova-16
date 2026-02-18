module ALU (
    input [15:0] x,
    input [15:0] y,
    input zx, // zero the x input?
    input nx, // negate the x input?
    input zy, // zero the y input?
    input ny, // negate the y input?
    input f,  // compute out = x + y (if 1) or x & y (if 0)
    input no, // negate the out output?
    output [15:0] out,
    output zr,
    output ng
);
    wire [15:0] x0, x1, x2;
    wire [15:0] y0, y1, y2;
    wire [15:0] andOut, addOut, out0, out1;
    wire [7:0] outLow, outHigh;
    wire o1, o2, nzr;

    // x handling
    Mux16 inst1 (.a(x), .b(16'b0), .sel(zx), .out(x0));
    Not16 inst2 (.in(x0), .out(x1));
    Mux16 inst3 (.a(x0), .b(x1), .sel(nx), .out(x2));

    // y handling
    Mux16 inst4 (.a(y), .b(16'b0), .sel(zy), .out(y0));
    Not16 inst5 (.in(y0), .out(y1));
    Mux16 inst6 (.a(y0), .b(y1), .sel(ny), .out(y2));

    // function
    And16 inst7 (.a(x2), .b(y2), .out(andOut));
    Add16 inst8 (.a(x2), .b(y2), .out(addOut));
    Mux16 inst9 (.a(andOut), .b(addOut), .sel(f), .out(out0));

    // output handling
    Not16 inst10 (.in(out0), .out(out1));
    
    // In HDL: out[0..7]=outLow, out[8..15]=outHigh, out[15]=ng
    // In Verilog we wire these taps separately after the instance
    Mux16 inst11 (.a(out0), .b(out1), .sel(no), .out(out));
    
    assign outLow = out[7:0];
    assign outHigh = out[15:8];
    assign ng = out[15];

    // flags
    Or8Way inst12 (.in(outLow), .out(o1));
    Or8Way inst13 (.in(outHigh), .out(o2));
    Or     inst14 (.a(o1), .b(o2), .out(nzr));
    Not    inst15 (.in(nzr), .out(zr));

endmodule