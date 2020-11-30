/***********************************
 * EECS 2031 - Lab 6
 * Filename: calc.c
 * Author: Kroytor, Vadim
 * Email: vadimkroytor@gmail.com
 * Login ID: 216 092 454
 ************************************/

#include <stdio.h>
#include <stdlib.h>

/* Implement a simple calculator.
 Input: two operands and one operator as command-line arguments.
 Output: the result displayed on the standard output.
 */
int calc(int op_one, char operator, int op_two);
int strequal(char *str_one, char *str_two);
int strsize(char *str_one);
int toInt(char *var);
int power(int base, int n);

void main(int argc, char *argv[]) {

	if (argc == 2 && strequal(argv[1], "calc")) {
		printf("Usage: calc [operand_1] [operator] [operand_2] \n");
		return;
	}
	int result = 0;
	int i = 0;

	int op_one = toInt(argv[1]);
	char operator = *argv[2];
	int op_two = toInt(argv[3]);

	printf("%d\n", result = calc(op_one, operator, op_two));
	return;
}

int calc(int op_one, char operator, int op_two) {
	int result = 0;
	if (operator == '+') {
		result = op_one + op_two;
	}
	else if (operator == '-') {
		result = op_one - op_two;
	}
	else if (operator == 'x') {
		result = op_one * op_two;
	}
	else if (operator == '/') {
		result = op_one / op_two;
	}
	else if (operator == '%') {
		result = op_one % op_two;
	}
	return result;
}
int toInt(char *var) {
	int result = 0;
	int n = 0;
	int index = strsize(var) - 1;
	while (index >= 0) {
		result += power(10, n++) * (*(var + index--) - '0');


	}
	return result;
}
/*
 * 115
 * 5 * 10^0 + 1 * 10^1 + 1 * 10^2
 */
int power(int base, int n) {
	int result = 1;
	if (n == 0) {
		return 1;
	}

	while (n >= 1) {
		result *= base;
		n--;
	}
	return result;
}
int strequal(char *str_one, char*str_two) {
	int isEqual = 0;
	int i = 0;
	while (*(str_one + i) != '\0') {
		if (*(str_one + i) != *(str_two + i) || strsize(str_one) < strsize(str_two)) {
			break;
		}
		i++;
	}

	if (*(str_one + i) == '\0' && strsize(str_one) == strsize(str_two)) {
		isEqual = 1;
	}

	return isEqual;
}

int strsize(char *str_one) {
	int i = 0;
	while (*(str_one + i) != '\0') {
		i++;
	}
	return i;
}
