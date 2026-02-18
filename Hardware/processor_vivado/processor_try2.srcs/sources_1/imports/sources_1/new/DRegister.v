`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 25.01.2026 17:02:56
// Design Name: 
// Module Name: DRegister
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


module DRegister(
    input clk,          // Added explicit clock
    input reset,
    input [15:0] in,
    input load,
    output [15:0] out
    );
    //----------------
    Bit inst1  (.clk(clk), .reset(reset), .in(in[0]),  .load(load), .out(out[0]));
    Bit inst2  (.clk(clk), .reset(reset), .in(in[1]),  .load(load), .out(out[1]));
    Bit inst3  (.clk(clk), .reset(reset), .in(in[2]),  .load(load), .out(out[2]));
    Bit inst4  (.clk(clk), .reset(reset), .in(in[3]),  .load(load), .out(out[3]));
    Bit inst5  (.clk(clk), .reset(reset), .in(in[4]),  .load(load), .out(out[4]));
    Bit inst6  (.clk(clk), .reset(reset), .in(in[5]),  .load(load), .out(out[5]));
    Bit inst7  (.clk(clk), .reset(reset), .in(in[6]),  .load(load), .out(out[6]));
    Bit inst8  (.clk(clk), .reset(reset), .in(in[7]),  .load(load), .out(out[7]));
    Bit inst9  (.clk(clk), .reset(reset), .in(in[8]),  .load(load), .out(out[8]));
    Bit inst10 (.clk(clk), .reset(reset), .in(in[9]),  .load(load), .out(out[9]));
    Bit inst11 (.clk(clk), .reset(reset), .in(in[10]), .load(load), .out(out[10]));
    Bit inst12 (.clk(clk), .reset(reset), .in(in[11]), .load(load), .out(out[11]));
    Bit inst13 (.clk(clk), .reset(reset), .in(in[12]), .load(load), .out(out[12]));
    Bit inst14 (.clk(clk), .reset(reset), .in(in[13]), .load(load), .out(out[13]));
    Bit inst15 (.clk(clk), .reset(reset), .in(in[14]), .load(load), .out(out[14]));
    Bit inst16 (.clk(clk), .reset(reset), .in(in[15]), .load(load), .out(out[15]));
    
    //--------------------
endmodule
