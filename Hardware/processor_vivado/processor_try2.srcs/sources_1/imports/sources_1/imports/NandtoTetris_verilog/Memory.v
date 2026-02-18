module Memory (
    input clk,
//    input clk_40,
    input reset,
    input [15:0] in,
    input load,
    input [14:0] address, // HDL defines address[15], Verilog uses [14:0] usually, but matching input size
    output [15:0] out,
//    output hsync, vsync,
//    output [3:0] R, G, B,
    input ps2_clk, ps2_data,
    input [15:0]out_a,
    output [12:0]address_a,
    output load_a,
    output [15:0]in_a
);
    wire isRAM, notA13, isScreen, isKeyboard, loadRAM, loadScreen;
    wire [15:0] ramOut, screenOut, kbdOut, ramOrScreen;

    Not inst1 (.in(address[14]), .out(isRAM));

    Not inst2 (.in(address[13]), .out(notA13));
    And inst3 (.a(address[14]), .b(notA13), .out(isScreen));

    And inst4 (.a(address[14]), .b(address[13]), .out(isKeyboard));

    And inst5 (.a(isRAM), .b(load), .out(loadRAM));
    And inst6 (.a(isScreen), .b(load), .out(loadScreen));

    RAM16K inst7 (
        .clk(clk),
        .reset(reset),
        .in(in),
        .load(loadRAM),
        .address(address[13:0]), // HDL [0..13] -> Verilog [13:0]
        .out(ramOut)
    );

//    Screen inst8 (
//        .clk(clk),
//        .in(in),
//        .load(loadScreen),
//        .address(address[12:0]), // HDL [0..12] -> Verilog [12:0]
//        .out(screenOut)
//    );
//--------------------------------------------------


//wire [12:0]ram_addr;
//wire [15:0]ram_data_in;

assign screenOut = out_a;
assign address_a = address[12:0];
assign load_a = loadScreen;
assign in_a = in;



    
//    Screen sc(
//    // Port A (Used by CPU/Writer)
//    .reset(reset),
//   .clk_a(clk),
//   .in_a(in),
//   .load_a(loadScreen),   //loadScreen  (changed 11:52 29jan, reverted 12:06)      // Write Enable for Port A
//   .address_a(address[12:0]),
//   .out_a(screenOut),
   
   
//   .clk_b(clk_40),
//   .in_b(),
//   .load_b(1'b0),           // Write Enable for Port B (usually 0 for VGA)
//   .address_b(ram_addr),
//   .out_b(ram_data_in)
//);

//--------------------------------------------------
wire [7:0] w_data_to_register;

    // 1. The PS/2 Interface
    ps2_interface my_ps2 (
        .clk(clk),
        .rst(reset),
        .ps2_clk(ps2_clk),
        .ps2_data(ps2_data),
        .d_in(w_data_to_register) // Outputs 131, 132, 133, 134, or 0
    );

    // 2. Your Register Instance
    Keyboard inst9 (
        .out(kbdOut), 
        .in({8'b0,w_data_to_register}) // Connected here
    );
   

    Mux16 inst10 (.a(ramOut), .b(screenOut), .sel(address[14]), .out(ramOrScreen));
    Mux16 inst11 (.a(ramOrScreen), .b(kbdOut), .sel(isKeyboard), .out(out));
    
    // ---------------- vga controller ----------------------

    
//    vga_engine vga1(
//     .clk_40(clk_40), 
//     .rst(reset), 
////     .pixel_clk_out(port_b_clk),    // Output the 40MHz clock so RAM Port B can use it
//     .ram_addr(ram_addr),  // Address sent TO the RAM
//     .ram_data_in(ram_data_in),// Data received FROM the RAM
//     .hsync(hsync), 
//     .vsync(vsync), 
//     .R(R), 
//     .G(G), 
//     .B(B)
//    );
    
    //-------------------------------------------------------
//     ila_0 ila_inst(
//.clk(clk),


//.probe0(ram_data_in),
//.probe1(address),
//.probe2(screenOut),
//.probe3(ram_addr)
//);

    
    //--------------------------------------------------------
endmodule