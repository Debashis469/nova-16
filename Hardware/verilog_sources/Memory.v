module Memory (
    input [15:0] in,
    input load,
    input [15:0] address, // HDL defines address[15], Verilog uses [14:0] usually, but matching input size
    output [15:0] out
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
        .in(in),
        .load(loadRAM),
        .address(address[13:0]), // HDL [0..13] -> Verilog [13:0]
        .out(ramOut)
    );

    Screen inst8 (
        .in(in),
        .load(loadScreen),
        .address(address[12:0]), // HDL [0..12] -> Verilog [12:0]
        .out(screenOut)
    );

    Keyboard inst9 (.out(kbdOut));

    Mux16 inst10 (.a(ramOut), .b(screenOut), .sel(address[14]), .out(ramOrScreen));
    Mux16 inst11 (.a(ramOrScreen), .b(kbdOut), .sel(isKeyboard), .out(out));
endmodule