`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 28.01.2026 14:06:26
// Design Name: 
// Module Name: top_comp
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


//module top_comp(
//input clk, rst, output hsync, vsync, output [3:0]R, output [3:0]G, output [3:0]B
//    );
    
    
    
//    Screen sc(
//    // Port A (Used by CPU/Writer)
//   .clk_a(clk),
//   .in_a(in),
//   .load_a(loadScreen),           // Write Enable for Port A
//   .address_a(address[12:0]),
//   .out_a(screenOut),
   
//    Keyboard inst9 (
//        .out(kbdOut), 
//        .in({8'b0,w_data_to_register}) // Connected here
//    );
   

//    Mux16 inst10 (.a(ramOut), .b(screenOut), .sel(address[14]), .out(ramOrScreen));
//    Mux16 inst11 (.a(ramOrScreen), .b(kbdOut), .sel(isKeyboard), .out(out));
    
   
//   .clk_b(port_b_clk),
//   .in_b(),
//   .load_b(1'b0),           // Write Enable for Port B (usually 0 for VGA)
//   .address_b(ram_addr),
//   .out_b(ram_data_in)
//);
    
    
//   ps2_interface my_ps2 (
//   .clk(clk),
//   .rst(1'b0),
//   .ps2_clk(ps2_clk),
//   .ps2_data(ps2_data),
//   .d_in(w_data_to_register) // Outputs 131, 132, 133, 134, or 0
//    );
    
//     vga_engine vga1(
//     .clk(clk), 
//     .rst(1'b0), 
//     .pixel_clk_out(port_b_clk),    // Output the 40MHz clock so RAM Port B can use it
//     .ram_addr(ram_addr),  // Address sent TO the RAM
//     .ram_data_in(ram_data_in),// Data received FROM the RAM
//     .hsync(hsync), 
//     .vsync(vsync), 
//     .R(R), 
//     .G(G), 
//     .B(B)
//    );
//endmodule
