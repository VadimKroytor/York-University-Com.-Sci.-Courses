//Vadim Kroytor Date: October 29, 2019
module grey;

reg a, b, c, expectx, expecty, expectz; //1-bit registers
wire tmpa, tmpb, tmpc, y1, y2, y, z1, z2, z;
integer i, j, k;
not nota(tmpa, a);
not notb(tmpb, b);
not notc(tmpc, c);

and y1(y1, tmpa, b);
and y2(y2, a, tmpb);

or y(y, y1, y2);

and z1(z1, tmpb, c);
and z2(z2, b, tmpc);

or z(z, z1, z2);

initial
begin
        for (i = 0; i < 2; i = i + 1)
        begin
            for (j = 0; j < 2; j = j + 1)
            begin
                for (k = 0; k < 2; k = k + 1)
                begin
                    a = i; b = j; c = k;
                    expectx = a;
                    expecty = ~a & b | a & ~b;
                    expectz = ~b & c | b & ~c;
                    #1
                    if (expectx === a && expecty === y && expectz === z)
                        $display("PASS: a=%b b=%b c=%b x=%b y=%b z=%b", a, b, c, a, y, z);
                    else
                        $display("FAIL: a=%b b=%b c=%b x=%b y=%b z=%b", a, b, c, a, y, z);
                end
            end
        end
        $finish;
end

endmodule
