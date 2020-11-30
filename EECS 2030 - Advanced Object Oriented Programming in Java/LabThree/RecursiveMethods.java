import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Requirement:
 * You are required to implement all methods recursively.
 * You receive a zero if there is any occurrence of a loop (e.g., for, while).  
 * See under doc/index.html for the API of methods.
 * This lab requires definitions of recursive helper methods. 
 * You may review the recording of parts of Lecture 14 (October 28): 
 * 		https://youtu.be/J2CB3QHinQU 
 */
public class RecursiveMethods {
	/**
	 * Return an array storing the first n numbers in an arithmetic sequence, with
	 * initial term 'start' and common difference 'diff'. You can assume that n is
	 * non-negative (larger than or equal to 0). e.g., arithmeticArray(2, 3, 5)
	 * returns an array {2, 5, 8, 11, 14}.
	 * 
	 * @param start the first term in an arithmetic sequence
	 * @param diff  the common difference between terms in an arithmetic sequence
	 * @param n     the first n numbers in an arithmetic sequence
	 * @return an array representing the first n numbers in the specified arithmetic
	 *         sequence
	 * 
	 *         <b>You are forbidden to use the arithmeticList method below to solve
	 *         this problem.</b>
	 * 
	 *         Requirement: You are required to implement all methods recursively.
	 *         You receive a zero if there is any occurrence of a loop (e.g., for,
	 *         while).
	 */

	public int[] arithmeticArray(int start, int diff, int n) {
		int[] array = new int[n];
		int[] returnArray;
		if (n == 0) {
			returnArray = array;
		} else {
			array[0] = start;
			arithmeticArrayHelper(1, diff, array);
			returnArray = array;
		}
		return returnArray;
	}

	/**
	 * This is a recursive helper method expected to be used by arithmeticArray.
	 * 
	 * @param i    position in `seq` to be assigned to
	 * @param diff common difference of the arithmetics sequence
	 * @param seq  a partially filled arithmetic sequence
	 * 
	 *             Each recursive call to this helper method stores at index `i` of
	 *             the resulting arithmetic sequence `seq`, which is assumed to have
	 *             been partially filled at indices 0, 1, ..., i - 1.
	 */

	void arithmeticArrayHelper(int i, int diff, int[] seq) {

		if (i == seq.length) {
			return;
		}

		seq[i] = seq[i - 1] + diff;

		i++;
		arithmeticArrayHelper(i, diff, seq);

	}

	/**
	 * Return a list storing the first n numbers in an arithmetic sequence, with
	 * initial term 'start' and common difference 'diff'. You can assume that n is
	 * non-negative (larger than or equal to 0). e.g., arithmeticList(2, 3, 5)
	 * returns a list {2, 5, 8, 11, 14}.
	 * 
	 * @param start the first term in an arithmetic sequence
	 * @param diff  the common difference between terms in an arithmetic sequence
	 * @param n     the first n numbers in an arithmetic sequence
	 * @return a list representing the first n numbers in the specified arithmetic
	 *         sequence
	 * 
	 *         <b>You are forbidden to use the arithmeticArray method above to solve
	 *         this problem.</b>
	 * 
	 *         Requirement: You are required to implement all methods recursively.
	 *         You receive a zero if there is any occurrence of a loop (e.g., for,
	 *         while).
	 */
	public List<Integer> arithmeticList(int start, int diff, int n) {
		/*
		 * Your Task: Make use of the recursive method arithmeticListHelper.
		 */
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> returnList = new ArrayList<Integer>();

		if (n == 0) {
			returnList = list;
		} else {
			list.add(start);
			arithmeticListHelper(1, diff, list, n);
			returnList = list;
		}

		return returnList;
	}

	/**
	 * This is a recursive helper method expected to be used by arithmeticList.
	 * 
	 * @param i    position in `seq` to be assigned to
	 * @param diff common difference of the arithmetics sequence
	 * @param seq  a partially filled arithmetic sequence
	 * @param n    size of the arithmetic sequence to be built eventually
	 * 
	 *             Each recursive call to this helper method stores at index `i` of
	 *             the resulting arithmetic sequence `seq`, which is assumed to have
	 *             been partially filled at indices 0, 1, ..., i - 1.
	 */
	void arithmeticListHelper(int i, int diff, List<Integer> seq, int n) {
		if (i == n) {
			return;
		}

		seq.add(seq.get(i - 1) + diff);
		i++;
		arithmeticListHelper(i, diff, seq, n);
	}

	/**
	 * Return whether or not an array represents the first n numbers of an
	 * arithmetic sequence. An arithmetic sequence has a common difference between
	 * every two adjacent terms. The array may or may not be empty. e.g.,
	 * isArithmeticArray({1, 3, 5, 8, 10}) returns false and isArithmeticArray({1,
	 * 3, 5, 7, 9}) returns true (because the common difference is 2).
	 * 
	 * @param a an array
	 * @return true if input array a represents an arithmetic sequence; false
	 *         otherwise.
	 * 
	 *         <b>You are forbidden to use the isArithmeticList method below to
	 *         solve this problem.</b>
	 * 
	 *         Requirement: You are required to implement all methods recursively.
	 *         You receive a zero if there is any occurrence of a loop (e.g., for,
	 *         while).
	 */
	public boolean isArithmeticArray(int[] a) {
		boolean returnStatement;
		if (a.length == 0 || a.length == 1) {
			returnStatement = true;
		} else {
			int diff = a[1] - a[0];
			returnStatement = isArithmeticArrayHelper(1, diff, a);
		}

		return returnStatement;
	}

	/**
	 * This is a recursive helper method expected to be used by isArithmeticArray.
	 * 
	 * @param i    position in `seq`, starting from which the remaining sub-sequence
	 *             is an arithmetic sequence
	 * @param diff common difference of the arithmetics sequence
	 * @param a    an array which may or may not be an arithmetic sequence
	 * @return whether or not the sub-sequence of `a` with indices i - 1, i, i + 1,
	 *         ..., a.length - 1 is an arithmetic sequence
	 * 
	 *         Each recursive call to this helper method considers if elements of
	 *         `a` at indices `i - 1` and `i` make two valid adjacent elements in
	 *         the arithmetic sequence, i.e., their difference is equal to the
	 *         common difference `diff`. Also, it considers if the remaining
	 *         sub-sequence (indices i + 1, i + 2, ..., a.length - 1) is an
	 *         arithmetic sequence with common difference `diff`.
	 */
	private static int elemSubArray;

	static boolean isArithmeticArrayHelper(int i, int diff, int[] a) {

		if (i < a.length) {
			elemSubArray = a[i] - a[i - 1];
		}

		if (i == a.length) {
			return elemSubArray == diff;
		}

		i++;
		return elemSubArray == diff && isArithmeticArrayHelper(i, diff, a);
	}

	/**
	 * Return whether or not a list represents the first n numbers of an arithmetic
	 * sequence. An arithmetic sequence has a common difference between every two
	 * adjacent terms. The list may or may not be empty. e.g., isArithmeticList({1,
	 * 3, 5, 8, 10}) returns false and isArithmeticList({1, 3, 5, 7, 9}) returns
	 * true (because the common difference is 2).
	 * 
	 * @param l a list
	 * @return true if input list l represents an arithmetic sequence; false
	 *         otherwise.
	 * 
	 *         <b>You are forbidden to use the isArithmeticArray method above to
	 *         solve this problem.</b>
	 * 
	 *         Requirement: You are required to implement all methods recursively.
	 *         You receive a zero if there is any occurrence of a loop (e.g., for,
	 *         while).
	 */
	public boolean isArithmeticList(List<Integer> l) {
		boolean returnStatement;
		if (l.isEmpty() || l.size() == 1) {
			returnStatement = true;
		} else {
			int diff = l.get(1) - l.get(0);
			returnStatement = isArithmeticListHelper(1, diff, l);
		}
		return returnStatement;
	}

	/**
	 * This is a recursive helper method expected to be used by isArithmeticList.
	 * 
	 * @param i    position in `seq`, starting from which the remaining sub-sequence
	 *             is an arithmetic sequence
	 * @param diff common difference of the arithmetics sequence
	 * @param l    a list which may or may not be an arithmetic sequence
	 * @return whether or not the sub-sequence of `l` with indices i - 1, i, i + 1,
	 *         ..., l.size() - 1 is an arithmetic sequence
	 * 
	 *         Each recursive call to this helper method considers if elements of
	 *         `l` at indices `i - 1` and `i` make two valid adjacent elements in
	 *         the arithmetic sequence, i.e., their difference is equal to the
	 *         common difference `diff`. Also, it considers if the remaining
	 *         sub-sequence (indices i + 1, i + 2, ..., l.size() - 1) is an
	 *         arithmetic sequence with common difference `diff`.
	 */
	private static int elemSubList;

	boolean isArithmeticListHelper(int i, int diff, List<Integer> l) {

		if (i < l.size()) {
			elemSubList = l.get(i) - l.get(i - 1);
		}

		if (i == l.size()) {
			return elemSubList == diff;
		}

		i++;
		return elemSubList == diff && isArithmeticListHelper(i, diff, l);
	}

	/**
	 * Given a sorted input array a, return a sorted array of size a.length + 1,
	 * consisting of all elements of array a and integer i.
	 * 
	 * @param a an array that is sorted in a non-descending order
	 * @param i an integer
	 * @return a sorted array of size a.length + 1, consisting of all elements of
	 *         array a and integer i. e.g., insertIntoSortedArray({1, 2, 4, 5}, 3)
	 *         returns a sorted array {1, 2, 3, 4, 5}. 2 < 3 < 4 <b>You are
	 *         forbidden to use the insertIntoSortedList method below to solve this
	 *         problem.</b>
	 * 
	 *         Requirement: You are required to implement all methods recursively.
	 *         You receive a zero if there is any occurrence of a loop (e.g., for,
	 *         while).
	 */

	public int[] insertIntoSortedArray(int[] a, int i) {
		int[] returnArray = new int[a.length + 1];

		return insertIntoSortedArrayHelper(a, returnArray, i);

	}

	private int index = 0;

	private int[] insertIntoSortedArrayHelper(int[] a, int[] returnArray, int i) {

		if (a.length == 0) {
			returnArray[index] = i;
			return returnArray;
		}

		else if ((i > a[a.length - 1] && index == a.length - 1) || (i == a[a.length - 1] && index == a.length - 1)) {

			returnArray[index] = a[index];
			returnArray[index + 1] = i;
			return returnArray;
		}

		else if ((i < a[index] && index == 0) || (i == a[index] && index == 0)) {
			returnArray[index] = i;
			returnArray[index + 1] = a[index];
			return restOfArrayHelper(a, returnArray);
		} else if (a.length == 1 && i <= a[0]) {
			returnArray[index] = i;
			returnArray[index + 1] = a[index];
			return returnArray;
		} else if (a.length == 1 && i > a[0]) {
			returnArray[index] = a[index];
			returnArray[index + 1] = i;
			return returnArray;
		} else if ((i > a[index] && a[index + 1] > i) || i == a[index]) {
			returnArray[index] = a[index];
			returnArray[index + 1] = i;

			index++;
			return restOfArrayHelper(a, returnArray);

			// [] -> [1] i = 1
			// 2,3,4,5 -> 2,3,4,4,5; i = 4
			// 2,3,4,5 -> 2,2,3,4,5 i = 2
			// 2,3,4,5 -> 2,3,4,5,5 i = 5
			// 1 -> 1, 2 i = 2
			// 2 -> 1, 2 i = 1
			// 1 -> 1, 1 i = 1
		} else {
			returnArray[index] = a[index];
			// System.out.println(Arrays.toString(returnArray));

		}
		index++;
		return insertIntoSortedArrayHelper(a, returnArray, i);
	}

	private int[] restOfArrayHelper(int[] a, int[] returnArray) {
		if (index == returnArray.length - 1) {
			return returnArray;
		} else {
			returnArray[index + 1] = a[index];
		}
		index++;
		return restOfArrayHelper(a, returnArray);
	}
	/* */

	/**
	 * Given a sorted input list, return a sorted list of size list.size() + 1,
	 * consisting of all elements of the input list and integer i.
	 * 
	 * @param list a list that is sorted in a non-descending order -> ascending or
	 *             flat
	 * @param i    an integer
	 * @return a sorted list of size list.size() + 1, consisting of all elements of
	 *         the input list and integer i. e.g., insertIntoSortedList({1, 2, 4,
	 *         5}, 3) returns a sorted list {1, 2, 3, 4, 5}.
	 * 
	 *         <b>You are forbidden to use the insertIntoSortedArray method above to
	 *         solve this problem.</b>
	 * 
	 *         Requirement: You are required to implement all methods recursively.
	 *         You receive a zero if there is any occurrence of a loop (e.g., for,
	 *         while).
	 */
	public List<Integer> insertIntoSortedList(List<Integer> list, int i) {
		list.add(i);
		selectionSort(list);
		return list;
	}

	private void minToFront(List<Integer> list) {
		if (list.size() < 2) {
			return;
		}
		minToFront(list.subList(1, list.size()));

		Integer first = list.get(0);
		Integer second = list.get(1);
		if (second.compareTo(first) < 0) {
			list.set(0, second);
			list.set(1, first);
		}
	}

	private void selectionSort(List<Integer> list) {
		if (list.size() > 1) {
			minToFront(list);
			selectionSort(list.subList(1, list.size()));
		}
	}

	/**
	 * Given two sorted arrays left and right, where left is sorted in a
	 * non-descending order -> ascending or flat and right is sorted in a
	 * ***non-ascending*** order -> descending or flat, return an array (of size
	 * left.length + right.length) sorted in a non-descending order, consisting of
	 * all elements of arrays left and right.
	 * 
	 * @param left  an array sorted in a non-descending order -> ascending or flat
	 * @param right an array sorted in a non-ascending order -> descending or flat
	 * @return a sorted array of size left.length + right.length, consisting of all
	 *         elements of arrays left and right. e.g., mergeSortedArraysV2({1, 3,
	 *         5, 7}, {8, 6, 4, 2}) returns a sorted array {1, 2, 3, 4, 5, 6, 7, 8}.
	 * 
	 *         <b>You are forbidden to use the mergeSortedListsV2 method below to
	 *         solve this problem.</b>
	 * 
	 *         Requirement: You are required to implement all methods recursively.
	 *         You receive a zero if there is any occurrence of a loop (e.g., for,
	 *         while).
	 */
	private int mergedIndex = 0;
	private int leftIndex = 0;
	private int rightSubIndex = 0;

	public int[] mergeSortedArrays(int[] left, int[] right) {
		int[] combArray = new int[left.length + right.length];
		if (left.length == 0 && right.length == 0) {
			return left;
		}
		return mergeSortedArrayHelper(left, right, combArray);

	}

	private int[] mergeSortedArrayHelper(int[] left, int[] right, int[] combArray) {
		if (mergedIndex == combArray.length) {
			return combArray;
		}

		else if (right.length == 0) {
			combArray[mergedIndex] = left[leftIndex];
			mergedIndex++;
			leftIndex++;

		} else if (left.length == 0) {
			combArray[mergedIndex] = right[(right.length - 1) - rightSubIndex];
			mergedIndex++;
			rightSubIndex++;

		}
		if (leftIndex < left.length && rightSubIndex < right.length) {
			if (left[leftIndex] < right[(right.length - 1) - rightSubIndex]) {
				combArray[mergedIndex] = left[leftIndex];
				mergedIndex++;
				leftIndex++;

			} else if (right[(right.length - 1) - rightSubIndex] < left[leftIndex]) {
				combArray[mergedIndex] = right[(right.length - 1) - rightSubIndex];
				mergedIndex++;
				rightSubIndex++;

			}
		} else if (leftIndex < left.length) {
			combArray[mergedIndex] = left[leftIndex];
			mergedIndex++;
			leftIndex++;

		} else if (rightSubIndex < right.length) {
			combArray[mergedIndex] = right[(right.length - 1) - rightSubIndex];
			mergedIndex++;
			rightSubIndex++;

		}

		return mergeSortedArrayHelper(left, right, combArray);
	}

	/**
	 * Given two sorted lists left and right, where left is sorted in a
	 * non-descending order and right is sorted in a ***non-ascending*** order,
	 * return a list (of size left.length + right.length) sorted in a non-descending
	 * order, consisting of all elements of lists left and right.
	 * 
	 * @param left  a list sorted in a non-descending order
	 * @param right a list sorted in a non-ascending order
	 * @return a sorted list of size left.size() + right.size(), consisting of all
	 *         elements of lists left and right. e.g., mergeSortedListsV2({1, 3, 5,
	 *         7}, {8, 6, 4, 2}) returns a sorted list {1, 2, 3, 4, 5, 6, 7, 8}.
	 * 
	 *         <b>You are forbidden to use the mergeSortedArraysV2 method above to
	 *         solve this problem.</b>
	 * 
	 *         Requirement: You are required to implement all methods recursively.
	 *         You receive a zero if there is any occurrence of a loop (e.g., for,
	 *         while).
	 */
	private int combI = 0;
	private int lI = 0;
	private int rSI = 0;

	public List<Integer> mergeSortedLists(List<Integer> left, List<Integer> right) {
		List<Integer> combList = new ArrayList<Integer>();
		if (left.size() == 0 && right.size() == 0) {
			return left;
		}
		return mergeSortedListsHelper(left, right, combList);
	}

	public List<Integer> mergeSortedListsHelper(List<Integer> left, List<Integer> right, List<Integer> comb) {
		if (combI == left.size() + right.size()) {
			return comb;
		} else if (right.size() == 0) {
			comb.add(combI, left.get(lI));
			combI++;
			lI++;

		} else if (left.size() == 0) {
			comb.add(combI, right.get((right.size() - 1) - rSI));
			combI++;
			rSI++;

		}
		if (lI < left.size() && rSI < right.size()) {
			if (left.get(lI) < right.get((right.size() - 1) - rSI)) {
				comb.add(combI, left.get(lI));
				combI++;
				lI++;

			} else if (right.get((right.size() - 1) - rSI) < left.get(lI)) {
				comb.add(combI, right.get((right.size() - 1) - rSI));
				combI++;
				rSI++;

			}
		} else if (lI < left.size()) {
			comb.add(combI, left.get(lI));
			combI++;
			lI++;

		} else if (rSI < right.size()) {
			comb.add(combI, right.get((right.size() - 1) - rSI));

			combI++;
			rSI++;

		}

		return mergeSortedListsHelper(left, right, comb);
	}
}
