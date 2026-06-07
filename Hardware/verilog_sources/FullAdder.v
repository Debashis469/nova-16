module FullAdder (
    input a,
    input b,
    input c,
    output sum,
    output carry
);
    wire s1, c1, c2;

    HalfAdder inst1 (.a(a),  .b(b), .sum(s1), .carry(c1));
    HalfAdder inst2 (.a(s1), .b(c), .sum(sum), .carry(c2));
    Or        inst3 (.a(c1), .b(c2), .out(carry));
endmodule