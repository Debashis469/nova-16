module CPU (
    input clk,
    input [15:0] inM,
    input [15:0] instruction,
    input reset,
    output [15:0] outM,
    output writeM,
    output [14:0] addressM,
    output [15:0] pc
);
    wire isAInstr, isCInstr;
    wire [15:0] aRegIn, aluOut, aRegOut, dRegOut, aluY;
    wire loadAFromC, loadA, loadD;
    wire zrOut, ngOut;
    wire notPositive, positive, jumpNeg, jumpZero, jumpPos, jumpLE, jump, doJump;

    // Instruction decode
    Not inst1 (.in(instruction[15]), .out(isAInstr));
    Not inst2 (.in(isAInstr), .out(isCInstr));

    // A register
    Mux16 inst3 (.a(instruction), .b(aluOut), .sel(isCInstr), .out(aRegIn));
    And   inst4 (.a(isCInstr), .b(instruction[5]), .out(loadAFromC));
    Or    inst5 (.a(isAInstr), .b(loadAFromC), .out(loadA));
    
    ARegister inst6 (.clk(clk), .reset(reset), .in(aRegIn), .load(loadA), .out(aRegOut));
    assign addressM = aRegOut[14:0]; // out[0..14]=addressM

    // D register
    And inst7 (.a(isCInstr), .b(instruction[4]), .out(loadD));
    DRegister inst8 (.clk(clk), .reset(reset), .in(aluOut), .load(loadD), .out(dRegOut));

    // ALU Y input
    Mux16 inst9 (.a(aRegOut), .b(inM), .sel(instruction[12]), .out(aluY));

    // ALU computation
    ALU inst10 (
        .x(dRegOut),
        .y(aluY),
        .zx(instruction[11]),
        .nx(instruction[10]),
        .zy(instruction[9]),
        .ny(instruction[8]),
        .f(instruction[7]),
        .no(instruction[6]),
        .out(aluOut),
        .zr(zrOut),
        .ng(ngOut)
    );
    assign outM = aluOut; // out=outM

    // Memory write control
    And inst11 (.a(isCInstr), .b(instruction[3]), .out(writeM));

    // Jump logic
    Or  inst12 (.a(zrOut), .b(ngOut), .out(notPositive));
    Not inst13 (.in(notPositive), .out(positive));

    And inst14 (.a(instruction[2]), .b(ngOut), .out(jumpNeg));
    And inst15 (.a(instruction[1]), .b(zrOut), .out(jumpZero));
    And inst16 (.a(instruction[0]), .b(positive), .out(jumpPos));
    
    Or  inst17 (.a(jumpNeg), .b(jumpZero), .out(jumpLE));
    Or  inst18 (.a(jumpLE), .b(jumpPos), .out(jump));
    And inst19 (.a(isCInstr), .b(jump), .out(doJump));

    // Program counter
//    wire [15:0] pcFull;
    PC inst20 (.clk(clk), .in(aRegOut), .load(doJump), .inc(1'b1), .reset(reset), .out(pc)); // written pc instead of pc_full
//    assign pc = pcFull[14:0]; // out[0..14]=pc

endmodule