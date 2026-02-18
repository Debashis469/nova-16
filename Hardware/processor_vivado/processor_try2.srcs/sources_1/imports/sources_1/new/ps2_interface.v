module ps2_interface(
    input clk,           // System Clock
    input rst,
    input ps2_clk,       // PS/2 Clock Line
    input ps2_data,      // PS/2 Data Line
    output reg [7:0] d_in // The value 131-134, 32, or 0
    );

    // --- 1. Synchronization & Edge Detection ---
    reg [1:0] ps2_clk_sync;
    always @(posedge clk) begin
        ps2_clk_sync <= {ps2_clk_sync[0], ps2_clk};
    end

    wire ps2_clk_falling = (ps2_clk_sync == 2'b10);

    // --- 2. Serial Data Capture ---
    reg [10:0] shift_reg; 
    reg [3:0] bit_count;  

    always @(posedge clk) begin
        if (rst) begin
            bit_count <= 0;
            shift_reg <= 0;
        end else if (ps2_clk_falling) begin
            shift_reg <= {ps2_data, shift_reg[10:1]}; 
            if (bit_count == 10)
                bit_count <= 0;
            else
                bit_count <= bit_count + 1;
        end
    end

    wire rx_done = (bit_count == 10) && ps2_clk_falling;
    wire [7:0] rx_byte = shift_reg[9:2]; 

    // --- 3. Scancode Processing FSM ---
    reg extended;   
    reg break_code; 

    always @(posedge clk) begin
        if (rst) begin
            d_in <= 0;
            extended <= 0;
            break_code <= 0;
        end else if (rx_done) begin
            if (rx_byte == 8'hE0) begin
                extended <= 1'b1;
            end
            else if (rx_byte == 8'hF0) begin
                break_code <= 1'b1;
            end
            else begin
                // --- Case A: Extended Keys (Arrows) ---
                if (extended) begin
                    if (break_code) begin
                        if (rx_byte == 8'h75 || rx_byte == 8'h72 || 
                            rx_byte == 8'h6B || rx_byte == 8'h74) begin
                            d_in <= 8'd0;
                        end
                    end 
                    else begin
                        case (rx_byte)
                            8'h75: d_in <= 8'd131; // UP
                            8'h72: d_in <= 8'd133; // DOWN
                            8'h6B: d_in <= 8'd130; // LEFT
                            8'h74: d_in <= 8'd132; // RIGHT
                            default: ; 
                        endcase
                    end
                end
                // --- Case B: Normal Keys (Space Bar) ---
                else begin
                    if (break_code) begin
                        if (rx_byte == 8'h29) begin
                            d_in <= 8'd0; // Space Released
                        end
                    end
                    else begin
                        if (rx_byte == 8'h29) begin
                            d_in <= 8'd32; // Space Pressed
                        end
                    end
                end
                
                // Reset flags after processing the byte
                extended <= 1'b0;
                break_code <= 1'b0;
            end
        end
    end
endmodule