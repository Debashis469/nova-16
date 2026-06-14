module Bit (
    input in,
    input load,
    output out
);
    wire muxOut, dffOut;

    Mux inst1 (.a(dffOut), .b(in), .sel(load), .out(muxOut));
    // Note: DFF instantiation assumes you have a DFF module defined
    DFF inst2 (.in(muxOut), .out(dffOut)); 
    
    assign out = dffOut; // Connecting the second output pin from HDL line 17
endmodule