package sortingAlgorithms;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {

	/**
	 * Sorts a list of integers in ascending order using the Quick Sort algorithm.
	 * 
	 * A pivot point is selected from the unsorted list and each item is distributed to
	 * one of three new lists depending on their value. The method is recursively called
	 * on itself using each of these lists as input, and a sorted list is returned.
	 * 
	 * @complexity
	 * <b> Time Complexity:<b>
	 * <ul>
	 * 	<li> {@code O(n logn)} on average.</li>
	 * 	<li> {@code O(n^2)} for the worst case.</li>
	 * </ul>
	 * 
	 * @param list The unsorted list of integers.
	 * @return The sorted list of integers.
	 */
	public static List<Integer> quickSort(List<Integer> list){
		
		if (list.size() <= 1) { // Base case
			return list;
		}
		
		int pivot = list.get(list.size()-1); // Pivot point from input list
		
		List<Integer> less = new ArrayList<>();
		List<Integer> equal = new ArrayList<>();
		List<Integer> more = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) { // Distribution into three lists
			if (list.get(i) < pivot) {
				less.add(list.get(i));
			} else if (list.get(i) == pivot) {
				equal.add(list.get(i));
			} else {
				more.add(list.get(i));
			}
		}
		
		List<Integer> out = new ArrayList<>(); // Sorted output list
		
		out.addAll(quickSort(less));
		out.addAll(quickSort(equal));
		out.addAll(quickSort(more));
		
		return out;
	}
	
}
