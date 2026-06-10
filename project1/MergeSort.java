package sortingAlgorithms;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

	/**
	 * Sorts a list of integers in ascending order using the Merge Sort algorithm.
	 * 
	 * The unsorted list is repeatedly halved by recursively calling on itself, then
	 * merged together with the merge() method.
	 * 
	 * @complexity
	 * <b> Time Complexity:<b>
	 * <ul>
	 * 	<li> {@code O(n logn)} for all cases.</li>
	 * </ul>
	 * 
	 * @param list The unsorted list of integers.
	 * @return The sorted list of integers.
	 */
	public static List<Integer> mergeSort(List<Integer> list) {
		
		if (list.size() <= 1) { // Base case
			return list;
		}
		
		int avg = list.size() / 2;
		List<Integer> x = new ArrayList<>(list.subList(0, avg)); // Half 1
		List<Integer> y = new ArrayList<>(list.subList(avg, list.size())); // Half 2
		
		x = mergeSort(x); // Recursive call for dividing half 1
		y = mergeSort(y); // Recursive call for dividing half 2
		
		return merge(x,y); // Merges both halves together
			
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
	public static List<Integer> merge(List<Integer> x, List<Integer> y) {
		List<Integer> z = new ArrayList<>(); // Merged output list
		int i = 0;
		int j = 0;
		
		while (i < x.size() && j < y.size()) { // Compares one item from each half until one is empty
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
		
		return z; // Returns merged output list
	}
	
}
