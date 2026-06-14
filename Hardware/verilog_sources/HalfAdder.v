module HalfAdder (
    input a,
    input b,
    output sum,
    output carry
);
    Xor inst1 (.a(a), .b(b), .out(sum));
    And inst2 (.a(a), .b(b), .out(carry));
endmodule