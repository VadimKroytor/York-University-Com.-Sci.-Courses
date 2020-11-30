//Vadim Kroytor, Section B, September 10th, 2019
//Program makes an array and sums up the elements in the array
A: DD 0, 1, 2, 3
ld x5, A(x0)
ld x6, A+8(x0)
ld x7, A+16(x0)
ld x28, A+24(x0)

add x29, x5, x6
add x29, x29, x7
add x29, x29, x28
add x6, x0, x29

