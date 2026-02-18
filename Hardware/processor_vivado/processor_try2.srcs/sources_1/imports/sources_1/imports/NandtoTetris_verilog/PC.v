module PC (
    input clk,          // Added explicit clock
    input [15:0] in,
    input load,
    input inc,
    input reset,
    output [15:0] out
);
    wire [15:0] incOut, incMux, loadMux, nextOut;
    // Feedback wire: Register 'out' is used internally
    wire [15:0] feedback;

    Inc16    inst1 (.in(feedback), .out(incOut));
    Mux16    inst2 (.a(feedback), .b(incOut), .sel(inc), .out(incMux));
    Mux16    inst3 (.a(incMux), .b(in), .sel(load), .out(loadMux));
    Mux16    inst4 (.a(loadMux), .b(16'b0), .sel(reset), .out(nextOut));
    
    Register inst5 (.clk(clk), .reset(reset), .in(nextOut), .load(1'b1), .out(feedback));

    assign out = feedback;
endmodule