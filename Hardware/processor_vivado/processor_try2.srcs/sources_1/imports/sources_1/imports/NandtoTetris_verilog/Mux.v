module Mux (
    input a,
    input b,
    input sel,
    output out
);
    wire notSel, aAndNotSel, bAndSel;

    Not inst1 (.in(sel), .out(notSel));
    And inst2 (.a(a), .b(notSel), .out(aAndNotSel));
    And inst3 (.a(b), .b(sel), .out(bAndSel));
    Or  inst4 (.a(aAndNotSel), .b(bAndSel), .out(out));
endmodule