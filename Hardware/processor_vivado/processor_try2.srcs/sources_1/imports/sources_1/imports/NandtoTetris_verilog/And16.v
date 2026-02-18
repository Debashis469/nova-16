module And16 (
    input [15:0] a,
    input [15:0] b,
    output [15:0] out
);
    And inst1  (.a(a[0]),  .b(b[0]),  .out(out[0]));
    And inst2  (.a(a[1]),  .b(b[1]),  .out(out[1]));
    And inst3  (.a(a[2]),  .b(b[2]),  .out(out[2]));
    And inst4  (.a(a[3]),  .b(b[3]),  .out(out[3]));
    And inst5  (.a(a[4]),  .b(b[4]),  .out(out[4]));
    And inst6  (.a(a[5]),  .b(b[5]),  .out(out[5]));
    And inst7  (.a(a[6]),  .b(b[6]),  .out(out[6]));
    And inst8  (.a(a[7]),  .b(b[7]),  .out(out[7]));
    And inst9  (.a(a[8]),  .b(b[8]),  .out(out[8]));
    And inst10 (.a(a[9]),  .b(b[9]),  .out(out[9]));
    And inst11 (.a(a[10]), .b(b[10]), .out(out[10]));
    And inst12 (.a(a[11]), .b(b[11]), .out(out[11]));
    And inst13 (.a(a[12]), .b(b[12]), .out(out[12]));
    And inst14 (.a(a[13]), .b(b[13]), .out(out[13]));
    And inst15 (.a(a[14]), .b(b[14]), .out(out[14]));
    And inst16 (.a(a[15]), .b(b[15]), .out(out[15]));
endmodule