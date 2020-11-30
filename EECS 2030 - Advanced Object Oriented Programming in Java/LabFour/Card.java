package eecs2030.lab2;

import java.util.Objects;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

/*
 * NOTE TO STUDENTS: The two methods you need to complete can 
 * be found at the end of this file.
 */



public class Card implements Comparable<Card> {

	/**
	 * The rank of this card. 
	 */
	private Rank rank;
	
	/**
	 * The suit of this card. 
	 */
	private Suit suit;
	
	
	public Card(Rank rank, Suit suit) {
		if (rank == null || suit == null) {
			throw new NullPointerException();
		}
		this.rank = rank;
		this.suit = suit;
	}
	
	
	public Rank rank() {
		return this.rank;
	}
	
	
	public Suit suit() {
		return this.suit;
	}
	
	
	public Colour color() {
		return this.suit.color();
	}


	@Override
	public int hashCode() {
		return Objects.hash(this.rank, this.suit);
	}
	
	
	@Override
	public String toString() {
		return this.rank + " of " + this.suit;
	}
	
	/*
	 * You need to implement the compareTo and equals methods below.
	 * 
	 * For compareTo, you may compare ranks using the compareTo method 
	 * found in the Rank class. You may compare suits using the compareTo method 
	 * found in the Suit class.
	 */

	public static void main(String[] args) {
		//Card biscuits = new Card(Rank.ACE, Suit.CLUBS);
		//Card otherBiscuits = new Card(Rank.TWO, Suit.SPADES);
		
	}
	@Override
	public int compareTo(Card other) {
		int returnStatement = 0;
		int compareRank = this.rank.compareTo(other.rank);
		int compareSuit = this.suit.compareTo(other.suit);
		if (compareRank != 0) {
			if (compareRank > 0) {
				returnStatement = 1;
				}
				if (compareRank < 0) {
					returnStatement = -1;
				}
		}
		else { 
			if (compareSuit > 0) {
			returnStatement = 1;
			}
			if (compareSuit < 0) {
				returnStatement = -1;
			}
		}
		return returnStatement;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		else if (obj == null) {
			return false;
		}

		else if (this.getClass() != obj.getClass()) {
			return false;
		}
		Card other = (Card) obj;
		return (this.rank == other.rank && this.suit == other.suit);
		
	
	}
	
}
