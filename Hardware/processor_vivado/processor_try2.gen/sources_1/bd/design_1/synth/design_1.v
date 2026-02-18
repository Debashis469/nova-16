//Copyright 1986-2022 Xilinx, Inc. All Rights Reserved.
//Copyright 2022-2024 Advanced Micro Devices, Inc. All Rights Reserved.
//--------------------------------------------------------------------------------
//Tool Version: Vivado v.2024.1 (win64) Build 5076996 Wed May 22 18:37:14 MDT 2024
//Date        : Sun Feb  1 20:24:59 2026
//Host        : Saurav running 64-bit major release  (build 9200)
//Command     : generate_target design_1.bd
//Design      : design_1
//Purpose     : IP block netlist
//--------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

(* CORE_GENERATION_INFO = "design_1,IP_Integrator,{x_ipVendor=xilinx.com,x_ipLibrary=BlockDiagram,x_ipName=design_1,x_ipVersion=1.00.a,x_ipLanguage=VERILOG,numBlks=4,numReposBlks=4,numNonXlnxBlks=0,numHierBlks=0,maxHierDepth=0,numSysgenBlks=0,numHlsBlks=0,numHdlrefBlks=3,numPkgbdBlks=0,bdsource=USER,synth_mode=None}" *) (* HW_HANDOFF = "design_1.hwdef" *) 
module design_1
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
  (* X_INTERFACE_INFO = "xilinx.com:signal:clock:1.0 CLK.CLK CLK" *) (* X_INTERFACE_PARAMETER = "XIL_INTERFACENAME CLK.CLK, CLK_DOMAIN design_1_clk_in1_0, FREQ_HZ 100000000, FREQ_TOLERANCE_HZ 0, INSERT_VIP 0, PHASE 0.0" *) input clk;
  output hsync;
  (* X_INTERFACE_INFO = "xilinx.com:signal:clock:1.0 CLK.PS2_CLK CLK" *) (* X_INTERFACE_PARAMETER = "XIL_INTERFACENAME CLK.PS2_CLK, CLK_DOMAIN design_1_ps2_clk_0, FREQ_HZ 100000000, FREQ_TOLERANCE_HZ 0, INSERT_VIP 0, PHASE 0.0" *) input ps2_clk;
  input ps2_data;
  (* X_INTERFACE_INFO = "xilinx.com:signal:reset:1.0 RST.RST RST" *) (* X_INTERFACE_PARAMETER = "XIL_INTERFACENAME RST.RST, INSERT_VIP 0, POLARITY ACTIVE_HIGH" *) input rst;
  output vsync;

  wire [3:0]Computer_0_B;
  wire [3:0]Computer_0_G;
  wire [3:0]Computer_0_R;
  wire [12:0]Computer_0_address_a;
  wire Computer_0_hsync;
  wire [15:0]Computer_0_in_a;
  wire Computer_0_load_a;
  wire Computer_0_vsync;
  wire [15:0]Screen_0_out_a;
  wire [15:0]Screen_0_out_b;
  wire clk_in1_0_1;
  wire clk_wiz_0_clk_out1;
  wire clk_wiz_0_clk_out2;
  wire ps2_clk_0_1;
  wire ps2_data_0_1;
  wire reset_0_1;
  wire [12:0]vga_engine_0_ram_addr;

  assign B[3:0] = Computer_0_B;
  assign G[3:0] = Computer_0_G;
  assign R[3:0] = Computer_0_R;
  assign clk_in1_0_1 = clk;
  assign hsync = Computer_0_hsync;
  assign ps2_clk_0_1 = ps2_clk;
  assign ps2_data_0_1 = ps2_data;
  assign reset_0_1 = rst;
  assign vsync = Computer_0_vsync;
  design_1_Computer_0_0 Computer_0
       (.address_a(Computer_0_address_a),
        .clk(clk_wiz_0_clk_out2),
        .in_a(Computer_0_in_a),
        .load_a(Computer_0_load_a),
        .out_a(Screen_0_out_a),
        .ps2_clk(ps2_clk_0_1),
        .ps2_data(ps2_data_0_1),
        .reset(reset_0_1));
  design_1_Screen_0_0 Screen_0
       (.address_a(Computer_0_address_a),
        .address_b(vga_engine_0_ram_addr),
        .clk_a(clk_wiz_0_clk_out2),
        .clk_b(clk_wiz_0_clk_out1),
        .in_a(Computer_0_in_a),
        .in_b({1'b0,1'b0,1'b0,1'b0,1'b0,1'b0,1'b0,1'b0,1'b0,1'b0,1'b0,1'b0,1'b0,1'b0,1'b0,1'b0}),
        .load_a(Computer_0_load_a),
        .load_b(1'b0),
        .out_a(Screen_0_out_a),
        .out_b(Screen_0_out_b));
  design_1_clk_wiz_0_0 clk_wiz_0
       (.clk_in1(clk_in1_0_1),
        .clk_out1(clk_wiz_0_clk_out1),
        .clk_out2(clk_wiz_0_clk_out2),
        .reset(reset_0_1));
  design_1_vga_engine_0_0 vga_engine_0
       (.B(Computer_0_B),
        .G(Computer_0_G),
        .R(Computer_0_R),
        .clk_40(clk_wiz_0_clk_out1),
        .hsync(Computer_0_hsync),
        .ram_addr(vga_engine_0_ram_addr),
        .ram_data_in(Screen_0_out_b),
        .rst(reset_0_1),
        .vsync(Computer_0_vsync));
endmodule
