//Copyright 1986-2022 Xilinx, Inc. All Rights Reserved.
//Copyright 2022-2024 Advanced Micro Devices, Inc. All Rights Reserved.
//--------------------------------------------------------------------------------
//Tool Version: Vivado v.2024.1 (win64) Build 5076996 Wed May 22 18:37:14 MDT 2024
//Date        : Sun Feb  1 20:24:59 2026
//Host        : Saurav running 64-bit major release  (build 9200)
//Command     : generate_target design_1_wrapper.bd
//Design      : design_1_wrapper
//Purpose     : IP block netlist
//--------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

module design_1_wrapper
   (B,
    G,
    R,
    clk,
    hsync,
    ps2_clk,
    ps2_data,
    rst,
    vsync);
  output [3:0]B;
  output [3:0]G;
  output [3:0]R;
  input clk;
  output hsync;
  input ps2_clk;
  input ps2_data;
  input rst;
  output vsync;

  wire [3:0]B;
  wire [3:0]G;
  wire [3:0]R;
  wire clk;
  wire hsync;
  wire ps2_clk;
  wire ps2_data;
  wire rst;
  wire vsync;

  design_1 design_1_i
       (.B(B),
        .G(G),
        .R(R),
        .clk(clk),
        .hsync(hsync),
        .ps2_clk(ps2_clk),
        .ps2_data(ps2_data),
        .rst(rst),
        .vsync(vsync));
endmodule
