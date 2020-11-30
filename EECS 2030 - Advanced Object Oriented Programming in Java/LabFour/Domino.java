package eecs2030.lab2;


/*
 * NOTE TO STUDENTS: The four methods you need to complete can 
 * be found at the end of this file.
 */



public class Domino implements Comparable<Domino> {

	/**
	 * The smallest possible value for a side of a domino.
	 */
	public static final int MIN_VALUE = 0;
	
	/**
	 * The largest possible value for a side of a domino. 
	 */
	public static final int MAX_VALUE = 6;

	/**
	 * The two values on the domino.
	 */
	private int val1;
	private int val2;

	
	public Domino() {
		this(0, 0);
	}

	
	public Domino(int value1, int value2) {
		if (!isValueOK(value1) || !isValueOK(value2)) {
			throw new IllegalArgumentException();
		}
		this.val1 = value1;
		this.val2 = value2;
	}

	
	public Domino(Domino other) {
		this(other.val1, other.val2);
	}

	
	private static boolean isValueOK(int value) {
		return value >= MIN_VALUE && value <= MAX_VALUE;
	}
	
	
	@Override
	public int hashCode() {
		return this.getSmallerValue() + 11 * this.getLargerValue();
	}
	

	@Override
	public String toString() {
		return "[" + this.getSmallerValue() + " : " + this.getLargerValue() + "]";
	}
	
	
	/*
	 * You need to implement the four methods below. Both compareTo and equals 
	 * should make use of getSmallerValue and getLargerValue.
	 */
	
	
	public int getSmallerValue() {
		int returnValue = this.val1;
		if (this.val1 < this.val2) {
			returnValue = this.val1;
		}
		else if (this.val2 < this.val1) {
			returnValue = this.val2;
		}
		return returnValue;
	}

	
	public int getLargerValue() {
		int returnValue = this.val1;
		if (this.val1 > this.val2) {
			returnValue = this.val1;
		}
		else if (this.val2 > this.val1) {
			returnValue = this.val2;
		}
		return returnValue;
	}

	
	@Override
	public int compareTo(Domino other) {
		int returnValue = 0;
		if (this.equals(other)) {
			returnValue = 0;
		}
		else if (this.getSmallerValue() != other.getSmallerValue()) {
				returnValue = this.getSmallerValue() - other.getSmallerValue();
		}
		else {
			returnValue = this.getLargerValue() - other.getLargerValue();
		}
		System.out.println(this.getSmallerValue() + " & " + this.getLargerValue() +
				" plus " + other.getSmallerValue() + " & " + other.getLargerValue() +
				": " + returnValue);
		return returnValue;
	}
	

	@Override
	public boolean equals(Object obj) {
		boolean returnStatement;
		if (this == obj) {
			returnStatement = true;
		}
		else if (obj == null) {
			returnStatement = false; 
		}
		else if (obj.getClass() != this.getClass()) {
			returnStatement = false;
		}
		
		else {
			Domino other = (Domino) obj;
			returnStatement = this.val1 == other.val1 &&
					this.val2 == other.val2 || this.val1 == other.val2
					&& this.val2 == other.val1;
		}
		return returnStatement;
	}
	

	
}