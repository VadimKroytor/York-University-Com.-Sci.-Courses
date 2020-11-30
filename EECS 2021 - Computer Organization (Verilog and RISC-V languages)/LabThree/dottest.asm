//Vadim Kroytor September 24, 2019
A1:	DF	1.5, 2.5, 3.5
A2:	DF	2.5, 3.5, 4.5
	ld	x10, A1(x0)  //Argument 1
	ld 	x11, A2(x0)  // Argument 2
	addi	x12, x0, 3	 // (Argument 3) 3 elements in both arrays
	addi	x28, x0, 24 //final element address PLUS eight
	addi 	x10, x0, 0 
	addi 	x7, x0, 0
loop:	
	fld	f1, A1(x7)
	fld	f2, A2(x7)
	fmul.d 	f3, f1, f2 //cross multiplies elements in corresponding arrays
	fadd.d	f4, f3, f4 //adds the multiplication of the past two elements
	addi 	x7, x7, 8
	beq	x7, x28, done
	beq	x0, x0, loop
done:	fmv.x.d x10, f4
	addi	x10, x10, 0
	jalr	x0, 0(x10) //return
	