package sortingAlgorithms;

import java.util.List;

/**
 * Holds the sorted integer lists and number of comparisons inside of a Result object.
 * 
 * @author Jeffrey Paluch
 */
public final class Result {

	private final List<Integer> sortedList;
	private final int comparisonCount;
	
	/**
	 * Constructor for a Result object with an integer list of number of comparisons.
	 * 
	 * @param sortedList Sorted integer list.
	 * @param comparisonCount Number of comparisons.
	 */
	public Result(List<Integer> sortedList, int comparisonCount) {
		this.sortedList = sortedList;
		this.comparisonCount = comparisonCount;
	}
	
	/**
	 * Getter method for the integer list.
	 * 
	 * @return The sorted integer list.
	 */
	public List<Integer> sortedList() {
		return this.sortedList;
	}
	
	/**
	 * Getter method for the number of comparisons.
	 * 
	 * @return The number of comparisons.
	 */
	public int comparisonCount() {
		return this.comparisonCount;
	}
	
}
