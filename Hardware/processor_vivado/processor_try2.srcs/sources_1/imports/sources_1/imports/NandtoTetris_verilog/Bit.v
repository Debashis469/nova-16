module Bit (
    input clk,
    input reset,
    input in,
    input load,
    output out
);
    wire muxOut, dffOut;

    Mux inst1 (.a(dffOut), .b(in), .sel(load), .out(muxOut));
    // Note: DFF instantiation assumes you have a DFF module defined
    DFF inst2 (.clk(clk), .reset(reset), .in(muxOut), .out(dffOut)); 
    
    assign out = dffOut; // Connecting the second output pin from HDL line 17
endmodule