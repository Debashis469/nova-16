`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 28.01.2026 18:55:30
// Design Name: 
// Module Name: tb_computer
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: 
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
//////////////////////////////////////////////////////////////////////////////////


module tb_computer();

reg clk = 0;
always #5 clk = ~clk;
reg reset;
Computer dut(
    .clk(clk),
    .reset(reset),
    .ps2_clk(), .ps2_data(),
    .vsync(), .hsync(), 
    .R(), .G(), .B()
);

wire loadScreen;
wire [15:0]address;
wire [15:0]screenOut;
wire [15:0] ram_addr;
wire [15:0] ram_data_in;
wire [15:0] pc;

assign loadScreen = dut.inst3.loadScreen;
assign address = dut.addressM;
assign screenOut = dut.inst3.screenOut;
assign ram_addr = dut.inst3.ram_addr;
assign ram_data_in = dut.inst3.ram_data_in;
assign pc = dut.pc;

initial begin
    reset = 1'b1;
    @(posedge clk);
    @(posedge clk);
    reset = 1'b0;
end

integer i,j;
initial begin
    for(i = 0; i<16384; i = i+1) begin
        dut.inst3.inst7.mem[i] = 0;
    end
    
    for(j = 0; j<8192; j = j+1) begin
        dut.inst3.sc.ram[j] = 0;
    end
    
//    dut.inst3.inst9 = 0;
end
endmodule
