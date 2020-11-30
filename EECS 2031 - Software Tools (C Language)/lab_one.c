/***********************************
* EECS 2031 - Lab 7
* Filename: mmavg.c
 * Author: Kroytor, Vadim
 * Email: vadimkroytor@gmail.com
 * Login ID: 216 092 454
************************************/


#include <stdio.h>
#include <stdlib.h>

int strequal(char *str_one, char *str_two);
int strsize(char *str_one);
int toInt(char *var);
int power(int base, int n);


int toInt(char *var) {
	int result = 0;
	int n = 0;
	int index = strsize(var) - 1;

	while (index >=  0) {

		if (*(var + index) == '+') {
			index--;
		}
		else if (*(var + index) == '-') {
			index--;
			result *= -1;

		}
		else {
			result += power(10, n) * (*(var + index) - '0');
			n++;
			index--;
		}


	}
	return result;
}

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


/* Function main()
   Input: a set of integers as command-line arguments.
   Output: the maximum, minimum and average of the above set displayed on the standard output.
 */

int main( int argc, char *argv[] )
{
	if (argc == 2 && strequal(argv[1], "mmavg")) {
			printf("Usage: mmavg int1 int2 int3 ... \n");
			return -1;
		}
    int max = toInt(argv[1]); //make sure to define the variables to a particular value or else garbage values may be defined to the variable by the compiler
    int min = toInt(argv[1]);
    float avg = 0;

    int i = 1; //skipping ./mmavg input
    int currentValue;
    while (i <= argc - 1) {
    	currentValue = toInt(argv[i]);

    	if (currentValue > max) {
    		max = currentValue;

    	}
    	else if (currentValue < min) {
    		min = currentValue;

    	}
    	avg += currentValue;
    	i++;
    }

    avg /= argc - 1;



    printf( " max: %d \n min: %d \n avg: %.2f \n", max, min, avg );
    return 0;
}

