module vga_engine(
    input clk_40,          // System Clock (100MHz)
    input rst, 
    
    // RAM Interface Signals
//    output wire pixel_clk_out,    
    output wire [12:0] ram_addr,  
    input wire [15:0] ram_data_in,
    
    // VGA Monitor Signals
    output hsync, 
    output vsync, 
    output [3:0] R, 
    output [3:0] G, 
    output [3:0] B
);

    // --- 1. Clock Generation ---
//    reg clk_50_reg = 0;
//    always @(posedge clk) begin
//        clk_50_reg <= ~clk_50_reg;
//    end
//    wire clk_40 = clk_50_reg; 
//    assign pixel_clk_out = clk_40;

//wire clk_40;
    
//    clk_wiz_0 clk_inst (
//        .clk_in1(clk),    
//        .clk_out1(clk_40), 
//        .reset(rst)     
//    );
    


    
    // --- 2. VGA Counters ---
    reg [15:0] hctr;
    reg [15:0] vctr;
    reg vtrig;

    always @(posedge clk_40) begin
        if(rst) begin
            hctr <= 0;
            vtrig <= 0;
        end else begin
            if(hctr < 1055) begin
                hctr <= hctr + 1;
                vtrig <= 1'b0;
            end else begin
                hctr <= 0;
                vtrig <= 1'b1;
            end
        end
    end

    always @(posedge clk_40) begin
        if(rst) begin
            vctr <= 0;
        end else if(vtrig == 1) begin
            if(vctr < 627) 
                vctr <= vctr + 1;
            else 
                vctr <= 0;
        end
    end
    
    assign hsync = (hctr >= 840 && hctr < 968) ? 1'b0 : 1'b1;
    assign vsync = (vctr >= 601 && vctr < 605) ? 1'b0 : 1'b1;

    // --- 3. Centering & Address Calculation ---
    
    // Constants for Centering
    localparam X_START = 144; // (800 - 512) / 2
    localparam Y_START = 172;  // (600 - 256) / 2
    localparam X_END   = 144 + 512; // 656
    localparam Y_END   = 172 + 256;  // 428

    wire valid_region;
    assign valid_region = (hctr >= X_START && hctr < X_END) && 
                          (vctr >= Y_START && vctr < Y_END);

    // Calculate Relative Coordinates (0 to 255, 0 to 511)
    // We use wires to handle the subtraction before address logic
    wire [15:0] rel_h = hctr - X_START;
    wire [15:0] rel_v = vctr - Y_START;

    // Address Formula: (Row * 16) + (Col / 16)
    // Since rel_v and rel_h start at 0 inside the region, this maps correctly to RAM address 0.
    assign ram_addr = valid_region ? ( (rel_v<<5) + rel_h[8:4] ) : 13'd0; // exchanged rel_h and rel_v

    // --- 4. Data Pipeline & Color Output ---
    reg [3:0] bit_selector; 
    reg active_pixel;       
    
    always @(posedge clk_40) begin
        // Pipeline alignment
        // Note: Since X_START (272) is a multiple of 16, hctr[3:0] 
        // effectively equals rel_h[3:0], so alignment is preserved.
        bit_selector <= hctr[3:0];  
        active_pixel <= valid_region; 
    end

    reg [3:0] R_reg, G_reg, B_reg;

    always @(*) begin
        if(active_pixel) begin
            // Bit 1 = Blue, Bit 0 = White
            if(ram_data_in[bit_selector] == 1'b1) begin
                R_reg = 4'h0; G_reg = 4'h0; B_reg = 4'hF; // Blue
            end else begin
                R_reg = 4'hF; G_reg = 4'hF; B_reg = 4'hF; // White
            end
        end 
        else begin
            // Black Background outside the image
            R_reg = 4'h0; G_reg = 4'h0; B_reg = 4'h0; 
        end
    end

    assign R = R_reg;
    assign G = G_reg;
    assign B = B_reg;

endmodule