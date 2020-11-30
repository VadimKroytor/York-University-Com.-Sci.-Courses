//Vadim Kroytor October 1, 2019
//nVal:	EQU	5
	//ld	x10, nVal(x0)
val:	DF 	1.0

	addi	sp, x0, 0	   //initialization
	addi	x6, x0, 1	   //x6 = 1
	addi 	x10, x0, 5    //n = 5
	blt	x10, x6, break2
	jal 	x1, div	
break:	ebreak  x0, x0, 0
break2: fld		f10, val(x0)
	ebreak 	x0, x0, 0
	
div:
	bne	x0, x10, recu //If n>1 //jump to recu
	jal	x0, break
	

recu:	
	fcvt.d.l f0, x10    //stores a0 in float form
	fcvt.d.l f1, x6	    //stores 1 in float form
ecall x0, f0, 1
	sd 	x1, -8(sp)  //push ra
	sd 	x10, -16(sp) //push a0
	addi	sp, sp, -16	//adjust sp
	addi	x10, x10, -1
	addi	sp, sp, 16  //adjust sp
	ld	x1, -8(sp)  //pop ra
	ld      x5, -16(sp) //pop a0
	fdiv.d	f3, f1, f0  //1/n
	fadd.d	f10, f10, f3
	jal	x1, div	    //recursive call and setting new return
	
 	jalr	x0, 0(x1)

