package sortingAlgorithms;

import java.util.List;

/**
 * Contains implementation for the Shaker Sort algorithm for sorting lists of integers.
 * 
 * The ordered list and number of comparisons are stored in a {@link Result} object.
 * 
 * @author Jeffrey Paluch
 */
public class ShakerSort {

	/**
	 * Sorts a list of integers in ascending order using the Shaker Sort algorithm.
	 * 
	 * The list is searched linearly in alternating directions as it compares adjacent integer elements.
	 * If no alterations are made during a search, the algorithm concludes. Otherwise, the list shrinks and the loop continues.
	 * 
	 * <br><br>
	 * <b> Time Complexity:<b>
	 * <ul>
	 * 	<li> {@code O(n)} for best case.</li>
	 * 		Moving in each direction takes {@code O(n)} time, for which there is only one loop in the ideal case.
	 *  <li> {@code O(n^2)} for average and worst cases.</li>
	 *  	For a reversed, worst-case list, the time complexity results in a summation of i = 1 through n.
	 * </ul>
	 * 
	 * @param list The unsorted {@link java.util.List} of {@link java.lang.Integer} elements.
	 * @return The {@link Result} object containing the sorted list and number of comparisons.
	 * @throws IllegalArgumentException if the input list is null.
	 */
	public static Result shakerSort(List<Integer> list) {
		
		if (list == null) { // Throws exception for null list
			throw new IllegalArgumentException("The list cannot be null.");
		}
		
		int start = 0;
		int end = list.size()-1;
		boolean unsorted = true; // Checks for an element swap
		
		int comparisons = 0; // Initializes total comparisons
		
		while (unsorted) {
			
			unsorted = false;
			
			for (int i = start; i < end; i++) {
				comparisons++;
				if (list.get(i) > list.get(i+1)) { // Swaps elements
					int c = list.get(i);
					list.set(i, list.get(i+1));
					list.set(i+1, c);
					unsorted = true; // Confirms element swap
				}
			}
			
			if (!unsorted) { // If no elements were swapped
				return new Result(list,comparisons);
			}
			
			end--; // Decrements unsorted list size
			unsorted = false;
		
			for (int i = end; i > start; i--) {
				comparisons++;
				if (list.get(i) < list.get(i-1)) { // Swaps elements
					int c = list.get(i);
					list.set(i, list.get(i-1));
					list.set(i-1, c);
					unsorted = true; // Confirms element swap
				}
			}
			start++;
		}
		
		return new Result(list,comparisons); // Returns Result object containing sorted list and total number of comparisons
		
	}
}
