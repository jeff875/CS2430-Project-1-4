package sortingAlgorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains implementation for the Heap Sort algorithm for sorting lists of integers.
 * 
 * The ordered list and number of comparisons are stored in a {@link Result} object.
 * 
 * @author Jeffrey Paluch
 */
public class HeapSort {
	
	/**
	 * Sorts a list of integers in ascending order using the Heap Sort algorithm.
	 * 
	 * The list is treated as a binary heap with size {@code n}, from which a max-heap is constructed using the heapHelper() method.
	 * Through a loop with {@code n} steps, the integer at the parent node is shuffled to the end of the list and the heapHelper() method is called
	 * until the list is sorted.
	 *
	 * <br><br>
	 * <b> Time Complexity:<b>
	 * <ul>
	 * 	<li> {@code O(n*logn)} for all cases.</li>
	 * 		Constructing the max-heap takes {@code O(n)} time.
	 *  	Iterating through the heap after removal of the largest element takes {@code O(logn)} time.
	 * </ul>
	 * 
	 * @param list The unsorted {@link java.util.List} of {@link java.lang.Integer} elements.
	 * @return The {@link Result} object containing the sorted list and number of comparisons.
	 * @throws IllegalArgumentException if the input list is null.
	 */
	public static Result heapSort(List<Integer> list) {
		
		if (list == null) { // Throws exception for null list
			throw new IllegalArgumentException("The list cannot be null.");
		}
		
		List<Integer> out = new ArrayList<>(list); // Copies input list
		int comparisonsTotal = 0; // Initializes total comparisons
		
		int n = out.size(); // List size
		
		for (int i = n/2 - 1; i >= 0; i--) { // Initial max-heap sort
			comparisonsTotal += heapHelper(out,n,i);
		}
		
		for (int i = n - 1; i > 0; i--) { // Largest elements shuffled to back
			int c = out.get(0);
			out.set(0, out.get(i));
			out.set(i, c);
			
			comparisonsTotal += heapHelper(out,i,0); // Subsequent max-heap sorts
		}
		
		return new Result(out,comparisonsTotal); // Sorted output list
	}
	
	/**
	 * Constructs a max-heap structure from an unsorted list of integers.
	 * 
	 * Finds the largest integer between a parent node and its children, then swaps their values. If a swap has occurred, the method
	 * recursively calls on itself with the altered child node as a parameter.
	 * 
	 * @param list The unsorted {@link java.util.List} of {@link java.lang.Integer} elements.
	 * @param n The size of the list.
	 * @param i The index of the node being altered.
	 */
	public static int heapHelper(List<Integer> list, int n, int i) {
		int max = i; // Parent node
		int left = 2*i + 1; // Left child
		int right = 2*i + 2; // Right child
		int comparisonsHelper = 0; // Initializes helper comparisons
		
		if (left < n) { 
			comparisonsHelper++;
			if (list.get(left) > list.get(max)) {
			max = left; // Compares parent to left
			}
		}
		
		if (right < n) { 
			comparisonsHelper++;
			if (list.get(right) > list.get(max)) {
			max = right; // Compares parent to right
			}
		}
		
		if (max != i) { // Checks for a swap
			int c = list.get(i);
			list.set(i, list.get(max));
			list.set(max, c);
			comparisonsHelper += heapHelper(list,n,max); // Continues down the heap
		}
		
		return comparisonsHelper; // Returns number of helper comparisons
	}

	
}
