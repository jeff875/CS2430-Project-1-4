package sortingAlgorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains implementation for the Merge Sort algorithm for sorting lists of integers.
 * 
 * The ordered list and number of comparisons are stored in a {@link Result} object.
 * 
 * @author Jeffrey Paluch
 */
public class MergeSort {

	/**
	 * Sorts a list of integers in ascending order using the Merge Sort algorithm.
	 * 
	 * The unsorted list is repeatedly halved by recursively calling on itself, then merged together with the mergeHelper() method.
	 * 
	 * <br><br>
	 * <b> Time Complexity:<b>
	 * <ul>
	 * 	<li> {@code O(n*logn)} for all cases.</li>
	 *  	Repeatedly dividing in half takes {@code O(logn)} time.
	 *  	Inspecting each element takes {@code O(n)} time.
	 * </ul>
	 * 
	 * @param list The unsorted {@link java.util.List} of {@link java.lang.Integer} elements.
	 * @return The {@link Result} object containing the sorted list and number of comparisons.
	 * @throws IllegalArgumentException if the input list is null.
	 */
	public static Result mergeSort(List<Integer> list) {
		
		if (list == null) { // Throws exception for null list
			throw new IllegalArgumentException("The list cannot be null.");
		}
		
		if (list.size() <= 1) { // Base case
			return new Result(list,0);
		}
		
		int comparisonsTotal = 0; // Initializes total comparisons
		
		int avg = list.size() / 2;
		List<Integer> x = new ArrayList<>(list.subList(0, avg)); // Half 1
		List<Integer> y = new ArrayList<>(list.subList(avg, list.size())); // Half 2
		
		Result left = mergeSort(x); // Recursive call for dividing half 1
		x = left.sortedList();
		comparisonsTotal += left.comparisonCount(); // Updates total comparisons
		
		Result right = mergeSort(y); // Recursive call for dividing half 2
		y = right.sortedList();
		comparisonsTotal += right.comparisonCount(); // Updates total comparisons
		
		Result newList = mergeHelper(x,y); // Helper method merges the halves
		comparisonsTotal += newList.comparisonCount(); // Updates total comparisons
		
		return new Result(newList.sortedList(), comparisonsTotal); // Merges both halves together
			
	}
	
	/**
	 * Merges two sorted integer lists into one sorted list.
	 * 
	 * Uses two pointers to compare each item from the lists according to magnitude
	 * and appends the larger item to the output list.
	 * 
	 * @param x The left half of the integer list.
	 * @param y The right half of the integer list.
	 * @return The sorted combination of the integer lists.
	 */
	public static Result mergeHelper(List<Integer> x, List<Integer> y) {
		List<Integer> z = new ArrayList<>(); // Merged output list
		int i = 0;
		int j = 0;
		int comparisonsHelper = 0;
		
		while (i < x.size() && j < y.size()) { // Compares one item from each half until one is empty
			comparisonsHelper++;
			if (x.get(i) <= y.get(j)) {
				z.add(x.get(i));
				i++;
			} else {
				z.add(y.get(j));
				j++;
			}
		}
		
		while (i < x.size()) { // Appends leftover items from half 1 to output list
			z.add(x.get(i));
			i++;
		}
		while (j < y.size()) { // Appends leftover items from half 2 to output list
			z.add(y.get(j));
			j++;
		}
		
		return new Result(z, comparisonsHelper); // Returns Result object containing the merged list and number of comparisons
	}
	
}
