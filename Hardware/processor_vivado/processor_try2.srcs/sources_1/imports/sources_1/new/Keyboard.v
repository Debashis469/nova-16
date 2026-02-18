module Keyboard(
    output wire [15:0] out, // Assuming 16-bit bus based on input concatenation
    input wire [15:0] in
    );

    // Since no clock is passed in the instantiation, this acts as a 
    // direct connection (Combinational Logic).
    // The "Holding" of the value is done by the ps2_interface module.
    
    assign out = in;

endmodule