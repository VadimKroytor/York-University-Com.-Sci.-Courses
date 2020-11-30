//Vadim Kroytor; Section B; September 17, 2019; sums elements in array
ORG 0x100
A: DD 1, -2, 2, -4, 5, -6, 7, 8 //0-56
	ld x31, A+56(x0)
	addi x6, x0, 0 //sets current address of array to 0
loop:	ld x5, A(x6) //start of loop
	bge x5, x0, skip //checks if number > 0
	sub x5, x0, x5 //makes value absolute if negative
skip:	add x7, x7, x5 //sums up values
	sd x7, d(x0)
	addi x6, x6, 8 //add current address by its offset
	beq x5, x31, e //if equals, then ebreak
beq	x0, x0, loop //if 0=0 (which it always does) then loop

e:      ecall x0, x7, 0 //outputs sum

ebreak x0, x0, 0 //end code

d: 	DM 1

