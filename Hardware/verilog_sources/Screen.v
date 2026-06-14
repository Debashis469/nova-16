module Screen (
    // Port A (CPU / Writer)
    input  wire        clk_a,
    input  wire [15:0] in_a,
    input  wire        load_a,
    input  wire [12:0] address_a,
    output wire [15:0] out_a,

    // Port B (VGA Reader)
    input  wire        clk_b,     // only used for writes
    input  wire [15:0] in_b,
    input  wire        load_b,
    input  wire [12:0] address_b,
    output wire [15:0] out_b
);

    // 8192 × 16-bit screen RAM
    reg [15:0] ram [0:8191];

    // -----------------------------
    // Write logic (synchronous)
    // -----------------------------

    always @(posedge clk_a) begin
        if (load_a) begin
            ram[address_a] <= in_a;
        end
//        out_a = ram[address_a];
    end

    always @(posedge clk_b) begin
        if (load_b) begin
            ram[address_b] <= in_b;
        end
//        out_b = ram[address_b];
    end

    // -----------------------------
    // Read logic (combinational)
    // -----------------------------

    assign out_a = ram[address_a];
    assign out_b = ram[address_b];

endmodule


