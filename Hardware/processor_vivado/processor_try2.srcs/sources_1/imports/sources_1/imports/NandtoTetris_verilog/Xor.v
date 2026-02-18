module Xor (
    input a,
    input b,
    output out
);
    wire notA, notB, aAndNotB, notAAndB;

    Not inst1 (.in(a), .out(notA));
    Not inst2 (.in(b), .out(notB));
    
    And inst3 (.a(a), .b(notB), .out(aAndNotB));
    And inst4 (.a(notA), .b(b), .out(notAAndB));
    
    Or  inst5 (.a(aAndNotB), .b(notAAndB), .out(out));
endmodule