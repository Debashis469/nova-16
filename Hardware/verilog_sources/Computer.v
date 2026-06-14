module Computer (
    input reset
);
    wire [15:0] pc;
    wire [15:0] instruction;
    wire [15:0] memoryOut;
    wire [15:0] cpuOut;
    wire writeM;
    wire [14:0] addressM;

    ROM32K inst1 (
        .address(pc[14:0]), 
        .out(instruction)
    );

    CPU inst2 (
        .inM(memoryOut),
        .instruction(instruction),
        .reset(reset),
        .outM(cpuOut),
        .writeM(writeM),
        .addressM(addressM),
        .pc(pc[14:0]) // HDL pc=pc (15 bits)
    );

    Memory inst3 (
        .in(cpuOut),
        .load(writeM),
        .address(addressM),
        .out(memoryOut)
    );
endmodule