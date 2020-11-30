package eecs2030.lab2;

import java.util.Objects;

public class Nickel implements Comparable<Nickel> {

	private int year;

	/**
	 * The monetary value of a nickel in cents.
	 */
	public final int CENTS = 5;
	/**
	 * Initializes this nickel to have the specified issue year
	 * @param year the year this coin was issued in
	 * @throws IllegalArgumentException if year is less than 1858
	 * @pre. year must be greater than or equal to 1858 
	 */
	public Nickel(int year) {
		if (year < 1858) {
			throw new IllegalArgumentException("Year must be greater than 1858");
		}
		this.year = year;
	}
	
	/**
	 * Returns the issue year of this coin
	 * @return the issue year of this coin
	 */
	public int issueYear() {
		return this.year;
	}
	
	/**
	 * Computes the difference of the issue year of this nickel and the issue year
	 * of the other nickel
	 * @return the difference of the issue year of this nickel and the issue year
	 * of the other nickel
	 */
	@Override
	public int compareTo(Nickel other) {
		return this.year - other.year;
	}
	
	/**
	 * Compares this nickel to the specified object for equality. The result is true
	 * if obj is a nickel. The issue year is not considered when comparing two nickels
	 * for equality.
	 * @return true if obj is a nickel
	 */
	@Override
	public boolean equals(Object obj) {
		boolean returnStatement;
		if (obj == null) {
			returnStatement = false;
		}
		else if (!(this.getClass() == obj.getClass())) {
			returnStatement = false;
		}
		else {
			returnStatement = true;
		}
		return returnStatement;
	}
	@Override 
	/**
	 * Returns a hash code for this nickel. Specifically, this method returns
	 * the issue year of this nickel.
	 * @return the issue year of this nickel
	 */
	public int hashCode() {
		return Objects.hash(this.year);
	}


}
