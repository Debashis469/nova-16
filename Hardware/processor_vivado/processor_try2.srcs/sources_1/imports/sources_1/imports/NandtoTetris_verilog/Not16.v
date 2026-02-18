module Not16 (
    input [15:0] in,
    output [15:0] out
);
    Not inst1  (.in(in[0]),  .out(out[0]));
    Not inst2  (.in(in[1]),  .out(out[1]));
    Not inst3  (.in(in[2]),  .out(out[2]));
    Not inst4  (.in(in[3]),  .out(out[3]));
    Not inst5  (.in(in[4]),  .out(out[4]));
    Not inst6  (.in(in[5]),  .out(out[5]));
    Not inst7  (.in(in[6]),  .out(out[6]));
    Not inst8  (.in(in[7]),  .out(out[7]));
    Not inst9  (.in(in[8]),  .out(out[8]));
    Not inst10 (.in(in[9]),  .out(out[9]));
    Not inst11 (.in(in[10]), .out(out[10]));
    Not inst12 (.in(in[11]), .out(out[11]));
    Not inst13 (.in(in[12]), .out(out[12]));
    Not inst14 (.in(in[13]), .out(out[13]));
    Not inst15 (.in(in[14]), .out(out[14]));
    Not inst16 (.in(in[15]), .out(out[15]));
endmodule