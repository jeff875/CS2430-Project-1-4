package sortingAlgorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains implementation for the Quick Sort algorithm for sorting lists of integers.
 * 
 * The ordered list and number of comparisons are stored in a {@link Result} object.
 * 
 * @author Jeffrey Paluch
 */
public class QuickSort {

	/**
	 * Sorts a list of integers in ascending order using the Quick Sort algorithm.
	 * 
	 * A pivot point is selected from the unsorted list and each item is distributed to one of three new lists depending on their value.
	 * The method is recursively called on itself using each of these lists as input, and a sorted list is returned.
	 * 
	 * <br><br>
	 * <b> Time Complexity:<b>
	 * <ul>
	 * 	<li> {@code O(n*logn)} for the best or average case.</li>
	 *  Repeatedly dividing in half takes {@code O(logn)} time.
	 *  Inspecting each element takes {@code O(n)} time.
	 * 	<li> {@code O(n^2)} for the worst case.</li>
	 * 		If the smallest or largest element is selected as the pivot, there are {@code n} steps instead of {@code logn} steps.
	 * </ul>
	 * 
	 * @param list The unsorted {@link java.util.List} of {@link java.lang.Integer} elements.
	 * @return The {@link Result} object containing the sorted list and number of comparisons.
	 * @throws IllegalArgumentException if the input list is null.
	 */
	public static Result quickSort(List<Integer> list){
		
		if (list == null) { // Throws exception for null list
			throw new IllegalArgumentException("The list cannot be null.");
		}
		
		if (list.size() <= 1) { // Base case
			return new Result(list,0);
		}
		
		int comparisons = 0; // Initializes total comparisons
		int pivot = list.get(list.size()/2); // Pivot point from input list
		
		List<Integer> less = new ArrayList<>();
		List<Integer> equal = new ArrayList<>();
		List<Integer> more = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) { // Distribution into three lists
			comparisons++;
			if (list.get(i) < pivot) {
				less.add(list.get(i));
			} else {
				if (list.get(i) > pivot) {
					more.add(list.get(i));
					comparisons++;
				} else {
				equal.add(list.get(i));
				}
			}
		}
		
		List<Integer> out = new ArrayList<>(); // Sorted output list
		
		Result lessR = quickSort(less);
		out.addAll(lessR.sortedList());
		comparisons += lessR.comparisonCount(); // Updates total comparisons
		
		out.addAll(equal);
		
		Result moreR = quickSort(more);
		out.addAll(moreR.sortedList());
		comparisons += moreR.comparisonCount(); // Updates total comparisons
		
		return new Result(out, comparisons); // Returns Result object with sorted list and number of total comparisons
	}
}