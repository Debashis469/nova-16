module Computer (
//    input clk_40,
    input clk, 
    input reset, 
    input ps2_clk, ps2_data,
//    output vsync, output hsync,  
//    output [3:0] R, G, B,
    input [15:0]out_a,
    output [12:0]address_a,
    output load_a,
    output [15:0]in_a
);  
    wire [15:0] pc; 
    wire [15:0] instruction;
    wire [15:0] memoryOut;
    wire [15:0] cpuOut;
    wire writeM; 
    wire [14:0] addressM;

    ROM32K inst1 (
//        .clk(clk),
        .address(pc), 
        .out(instruction)
    ); 

    CPU inst2 ( 
        .clk(clk), 
        .inM(memoryOut), 
        .instruction(instruction),
        .reset(reset),
        .outM(cpuOut), 
        .writeM(writeM), 
        .addressM(addressM),
        .pc(pc[14:0]) // HDL pc=pc (15 bits)
    );
    
//    wire clk_40;
    
//    clk_wiz_0 clk_inst (
//        .clk_in1(clk),    
//        .clk_out1(clk_40), 
//        .reset(rst)       
//    );


    Memory inst3 (
        .clk(clk),
//        .clk_40(clk_40),
        .reset(reset),
        .in(cpuOut),
        .load(writeM),
        .address(addressM),
        .out(memoryOut),
//        .hsync(hsync), .vsync(vsync),
//        .R(R), .G(G), .B(B),
        .ps2_clk(ps2_clk), .ps2_data(ps2_data),
        .out_a(out_a),
        .address_a(address_a),
        .load_a(load_a),
        .in_a(in_a)
    );
    

endmodule