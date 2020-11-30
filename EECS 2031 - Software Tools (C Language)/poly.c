/***********************************
 * EECS2031 - Assignment 1
 * Filename: poly.c
 * Author: Kroytor, Vadim
 * Email: vadimkroytor@gmail.com
 * Login ID: 216 092 454
 ************************************/

#include "poly.h"

void init_polynom(int coeff[], int exp[]);
void get_polynom(int coeff[], int exp[]);
void polynom_to_string(int coeff[], int exp[], char s[]);
void eval_polynom(int coeff[], int exp[], double x, double *result);
void add_polynom(int co1[], int ex1[], int co2[], int ex2[]);
int strsize(char *str_one);
int power(int base, int n);
double powerd(double base, int n);
int toInt(char *var);
int len_int(int integer);

int main()
{
	/*
  int coeff1[ASIZE], exp1[ASIZE], coeff2[ASIZE], exp2[ASIZE];
  char strg[ASIZE*5];
  double x, y;

  do
  {
  init_polynom( coeff1, exp1 );
  get_polynom( coeff1, exp1 );
  polynom_to_string( coeff1, exp1, strg );
  printf( "%s\n", strg);

  init_polynom( coeff2, exp2 );
  get_polynom( coeff2, exp2 );
  polynom_to_string( coeff2, exp2, strg );
  printf( "%s\n", strg);

  add_polynom( coeff1, exp1, coeff2, exp2 );
  polynom_to_string( coeff1, exp1, strg );
  printf( "%s\n\n", strg);
  }
  while ( coeff1[0] );

  polynom_to_string( coeff2, exp2, strg );
  printf( "%s\n", strg);
  do {
    scanf( "%lf", &x );  	// to input a double use "%lf", not "%f"
    eval_polynom( coeff2, exp2, x, &y );
    printf( "p(%.4lf) = %.4lf \n", x, y );
  }
  while ( x );
*/
  return 0;  // normal termination
}

/*
 Initialize all coefficients and exponents of the polynomial to zero.
 */

//int i = 0; //global variable index used for manipulating elements in arrays coeff[] and exp[]
void init_polynom(int coeff[], int exp[]) {

	coeff[ASIZE];
	exp[ASIZE];
	int i = 0;
	while (i <= ASIZE - 1) {
		coeff[i] = 0;
		exp[i] = 0;
		i++;
	}
}

/*
 Get inputs from user using scanf() and store them in the polynomial.
 */
void get_polynom(int coeff[], int exp[]) {
	int in;
	int numOfOccur = 0;
	int index = 1; //skipping term element of input array
	int indexofcoeff = 0; //index of coeff and exp
	int indexofexp = 0;
	while (scanf("%d", &in) && in != 0) { //condition: last input must be 0
		if (numOfOccur % 2 == 1) {
			coeff[indexofcoeff++] = in;
		} else if (numOfOccur % 2 == 0 && numOfOccur != 0) {
			exp[indexofexp++] = in;
		}
		numOfOccur++;
	}

}
//ex input 3 -4 5 -10 2 20 0
// 5 -3 7 824 5 -7 3 1 2 9 0
/*
 Convert the polynomial to a string s.
 */

//return length of integer
int len_int(int integer) {
	int len = 0;
	if (integer < 0) {
		len++;
	}
	while (integer != 0) {
		integer /= 10;
		len++;
	}
	return len;
}
void polynom_to_string(int coeff[], int exp[], char s[]) {
	if (coeff[0] == 0 && exp[0] == 0) {
		s[0] = 0;
		s[1] = '\0';
		return;
	}
	int index_of_s = 0;
	int i = 0;
	int numOfOccur = 0;
	while (coeff[i] != 0) {
		if (coeff[0] == 1 && numOfOccur == 0) {
			//printf("+x^%d", exp[i]);
			s[index_of_s++] = 'x';
			s[index_of_s++] = '^';
			sprintf(&s[index_of_s], "%d", exp[i]);
			index_of_s += len_int(exp[i]);
			numOfOccur++;
		}
		else if (exp[i] == 0) {
			if (coeff[i] > 0) {
				//printf("+%d", coeff[i]);
				s[index_of_s++] = '+';
				sprintf(&s[index_of_s], "%d", coeff[i]);
				index_of_s += len_int(coeff[i]);

			} else {
				//printf("%d", coeff[i]);
				sprintf(&s[index_of_s], "%d", coeff[i]);
				index_of_s += len_int(coeff[i]);
			}
		} else if (coeff[i] == 1) {
			//printf("+x^%d", exp[i]);
			s[index_of_s++] = '+';
			s[index_of_s++] = 'x';
			s[index_of_s++] = '^';
			sprintf(&s[index_of_s], "%d", exp[i]);
			index_of_s += len_int(exp[i]);
			numOfOccur++;
		} else if (coeff[i] == -1) {
			//printf("-x^%d", exp[i]);
			s[index_of_s++] = '-';
			s[index_of_s++] = 'x';
			s[index_of_s++] = '^';
			sprintf(&s[index_of_s], "%d", exp[i]);
			index_of_s += len_int(exp[i]);
			numOfOccur++;
		} else if (coeff[i] > 0 && (numOfOccur != 0)) {

			//printf("+%dx^%d", coeff[i], exp[i]);
			s[index_of_s++] = '+';
			sprintf(&s[index_of_s], "%d", coeff[i]);
			index_of_s += len_int(coeff[i]);
			s[index_of_s++] = 'x';
			s[index_of_s++] = '^';
			sprintf(&s[index_of_s], "%d", exp[i]);
			index_of_s += len_int(exp[i]);
			numOfOccur++;

		} else {
			//printf("%dx^%d", coeff[i], exp[i]);
			sprintf(&s[index_of_s], "%d", coeff[i]);
			index_of_s += len_int(coeff[i]);
			s[index_of_s++] = 'x';
			s[index_of_s++] = '^';
			sprintf(&s[index_of_s], "%d", exp[i]);
			index_of_s += len_int(exp[i]);
			numOfOccur++;
		}
		i++;
	}
	s[index_of_s] = '\0';

}

/*
 Evaluate the polynomial for the value of x and store the result p(x) in variable result.
 */
void eval_polynom(int coeff[], int exp[], double x, double *result) {
	*result = 0;
	int i = 0;
	while (coeff[i] != 0) {

		*result += coeff[i] * powerd(x, exp[i]);

		i++;
	}
}

/*
 Add two polynomials and the result is stored in the first polynomial (arrays co1[] and ex1[]).
 */
void add_polynom(int co1[], int ex1[], int co2[], int ex2[]) {
	int copyc[ASIZE];
	int copye[ASIZE];
	init_polynom(copyc, copye);
	int i1 = 0;
	int i2 = 0;
	int ic3 = 0;
	while (co1[i1] != 0 && co2[i2] != 0) {
		if (ex1[i1] > ex2[i2] || (co2[i2] == 0)) {
			copyc[ic3] = co1[i1];
			copye[ic3++] = ex1[i1++];
		} else if (ex1[i1] < ex2[i2] || (co1[i1] == 0)) {
			copyc[ic3] = co2[i2];
			copye[ic3++] = ex2[i2++];
		} else if (ex1[i1] == ex2[i2]) {
			copyc[ic3] = co1[i1] + co2[i2];
			copye[ic3++] = ex1[i1];
			i1++;
			i2++;
		}
	}

	int i = 0;
		while (i <= ASIZE - 1) {
			co1[i] = 0;
			ex1[i] = 0;
			i++;
		}
	char s[ASIZE];
	polynom_to_string(co1, ex1, s);
	ic3 = 0;
	while (copyc[ic3] != 0) {
		co1[ic3] = copyc[ic3];
		ex1[ic3] = copye[ic3];
		ic3++;
	}
}

int strsize(char *str_one) {
	int i = 0;
	while (*(str_one + i) != '\0') {
		i++;
	}
	return i;
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

double powerd(double base, int n) {
	double result = 1;
	if (n == 0) {
		return 1;
	}

	while (n >= 1) {
		result *= base;
		n--;
	}
	return result;
}

int toInt(char *var) {
	int result = 0;
	int n = 0;
	int index = strsize(var) - 1;
	while (var[index] != ' ') {
		result += power(10, n++) * (*(var + index--) - '0');

	}
	return result;
}

/************************** END OF FILE ***************************/

