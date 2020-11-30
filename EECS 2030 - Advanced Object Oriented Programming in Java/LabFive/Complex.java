package eecs2030.lab3;

import java.util.Arrays;
import java.util.List;

/**
 * A class that represents immutable complex numbers.
 * 
 * @author EECS2030 Fall 2019
 * 
 */
public final class Complex {

	private final double re;
	
	private final double im;
	
	//private final double i = Math.sqrt(-1);
	
	public static final Complex I = new Complex(0.0, 1.0);
	
	public static final Complex ONE = new Complex(1.0, 0.0);
	
	/**
	 * Initializes this complex number to <code>0 + 0i</code>.
	 * 
	 */
	public Complex() {
		this(0, 0);
	}

	/**
	 * Initializes this complex number so that it has the same real and
	 * imaginary parts as another complex number.
	 * 
	 * @param other
	 *            the complex number to copy.
	 */
	public Complex(Complex other) {
		this(other.re, other.im);
		
	}

	/**
	 * Initializes this complex number so that it has the given real
	 * and imaginary components.
	 * 
	 * @param re
	 *            the real part of the complex number.
	 * @param im
	 *            the imaginary part of the complex number.
	 */
	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}

	/**
	 * A static factory method that returns a new complex number whose real part
	 * is equal to re and whose imaginary part is equal to 0.0
	 * 
	 * @param re
	 *            the desired real part of the complex number
	 * @return a new complex number whose real part is equal to re and whose
	 *         imaginary part is equal to 0.0
	 */
	public static Complex real(double re) {
		return new Complex(re, 0.0);
	}

	/**
	 * A static factory method that returns a new complex number whose real part
	 * is equal to 0.0 and whose imaginary part is equal to im
	 * 
	 * @param im
	 *            the desired imaginary part of the complex number
	 * @return a new complex number whose real part is equal to 0.0 and whose
	 *         imaginary part is equal to im
	 */
	public static Complex imag(double im) {
		
		return new Complex(0.0, im);
	}

	/**
	 * Get the real part of the complex number.
	 * 
	 * @return the real part of the complex number.
	 */
	public double re() {
		
		return this.re;
	}

	/**
	 * Get the imaginary part of the complex number.
	 * 
	 * @return the imaginary part of the complex number.
	 */
	public double im() {
		
		return this.im;
	}

	/**
	 * Add this complex number and another complex number to obtain a new
	 * complex number. Neither this complex number nor c is changed by
	 * this method.
	 * 
	 * @param c
	 *            The complex number to add to this complex number.
	 * @return a new Complex number equal to the sum of this complex number and
	 *         c.
	 */
	public Complex add(Complex c) {
		
		return new Complex(this.re + c.re, this.im + c.im);
	}

	/**
	 * Multiply this complex number with another complex number to obtain a new
	 * complex number. Neither this complex number nor c is changed by
	 * this method.
	 * 
	 * <p>
	 * This is not an industrial strength implementation of complex number
	 * multiplication. In particular, issues related to the differences between
	 * <code>-0.0</code> and <code>0.0</code> and infinite real or imaginary
	 * parts are not taken into account.
	 * 
	 * @param c
	 *            The complex number to multiply by.
	 * @return a new Complex number equal to this complex number multiplied by
	 *         c.
	 */
	public Complex multiply(Complex c) {
		
		return new Complex((this.re * c.re) - (this.im * c.im),
				(this.im * c.re) + (this.re * c.im));
	}

	/**
	 * Compute the magnitude of this complex number.
	 * 
	 * @return the magnitude of this complex number.
	 */
	public double mag() {
		
		return Math.hypot(this.re, this.im);
	}

	/**
	 * Return a hash code for this complex number.
	 * 
	 * <p>
	 * This implementation uses a very crude algorithm to compute
	 * the hash code; the hash code is computed as follows:
	 * 
	 * <ol>
	 * <li>compute the value equal to <code>9999</code> times the real part
	 *     of this complex number
	 * <li>compute the value equal to <code>99</code> times the imaginary part
	 *     of this complex number
	 * <li>compute the sum of the values computed in Steps 1 and 2
	 * <li>casts the value computed in Step 3 to an <code>int</code>
	 * <li>returns the value computed in Step 4
	 * </ol>
	 * 
	 * <p>
	 * In production code, consider implementing hashCode() using
	 * {@link java.util.Objects#hash}
	 * 
	 * @return a hash code value for this complex number.
	 */
	@Override
	public int hashCode() {
		
		return (int) ((9999d * this.re) + (99d * this.im));
	}

	/**
	 * Compares this complex number with the specified object. The result is
	 * <code>true</code> if and only if the argument is a <code>Complex</code>
	 * number with the same real and imaginary parts as this complex number.
	 * 
	 * @param obj
	 *            the object to compare this Complex number against.
	 * @return true if the given object is a Complex number equal to this
	 *         complex number, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Complex other = (Complex) obj;
		return (this.re == other.re && this.im == other.im);
	}

	/**
	 * Returns a string representation of this complex number.
	 * 
	 * <p>
	 * The returned string is the real part of the complex number, followed by a
	 * space, followed by a <code>+</code> or <code>-</code> sign, followed by a
	 * space, followed by the absolute value of the imaginary part of the
	 * complex number, followed by an <code>i</code>. The sign is <code>+</code>
	 * if the imaginary part of the complex number is positive, and
	 * <code>-</code> if the imaginary part of the complex number is negative.
	 * 
	 * For example the complex number with real and imaginary parts equal to
	 * zero has the string representation <code>0.0 + 0.0i</code>. The complex
	 * number with real part equal to zero and imaginary part equal to
	 * <code>-1</code> has the string representation <code>0.0 - 1.0i</code>.
	 * 
	 * @return a string representation of this complex number.
	 * 
	 */
	@Override
	public String toString() {
		String s = "";
		
		if (im() >= 0) {
			s += re() + " + " + Math.abs(im()) + "i";
		}
		else {
			s += re() + " - " + Math.abs(im()) + "i";
		}
		return s;
	}

	/**
	 * Returns a complex number holding the value represented by the given
	 * string.
	 * 
	 * <p>
	 * <code>valueOf</code> tries to create a complex number from a string
	 * representation of the complex number. Strings that can interpreted as
	 * complex numbers are those strings returned by
	 * <code>Complex.toString</code>.
	 * 
	 * @param s
	 *            a string representation of a complex number.
	 * @return a Complex number equal to the complex number represented by the
	 *         given string.
	 * @throws IllegalArgumentException
	 *             if the string cannot be interpreted as a complex number.
	 * @pre. s has a space before and after the + or - sign separating the
	 *       real and imaginary parts of the complex number
	 */
	public static Complex valueOf(String s) {
		Complex result = null;
		String t = s.trim();
		List<String> parts = Arrays.asList(t.split("\\s+"));
		
		if (parts.size() == 3 && (parts.indexOf("+") == 1 || parts.indexOf("-") == 1)
				&& parts.get(2).endsWith("i") == true) {
			result = new Complex(Double.valueOf(parts.get(0)), 
					Double.valueOf((parts.get(1).concat(parts.get(2))).replaceAll("i", "")));
		}
		else {
			throw new IllegalArgumentException("Not a Complex Number");
		}
		
		return result;
	}


}
