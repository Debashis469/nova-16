module DMux (
    input in,
    input sel,
    output a,
    output b
);
    wire notSel;

    Not inst1 (.in(sel), .out(notSel));
    And inst2 (.a(in), .b(notSel), .out(a));
    And inst3 (.a(in), .b(sel), .out(b));
endmodule