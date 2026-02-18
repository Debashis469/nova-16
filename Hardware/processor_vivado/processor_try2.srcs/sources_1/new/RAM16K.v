//module RAM16K (
//    input clk,
//    input reset,
//    input [15:0] in,
//    input load,
//    input [13:0] address,
//    output reg [15:0] out
//);

//    // Infer Block RAM
//    reg [15:0] mem [0:16383];

//    always @(posedge clk) begin
//        if(reset) begin
//            out <= 0;
//        end else begin
//            if (load) begin
//                mem[address] <= in;
//            end
//            out <= mem[address];
//        end
//    end

//endmodule

module RAM16K (
    input         clk,
    input         reset,
    input  [15:0] in,
    input         load,
    input  [13:0] address,
    output [15:0] out
);

    reg [15:0] mem [0:16383];

    // WRITE: synchronous
    always @(posedge clk) begin
        if (load) begin
            mem[address] <= in;
        end
    end

    // READ: combinational
    assign out = mem[address];

endmodule