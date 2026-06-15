package sortingAlgorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class TestingData {
	
	static long seed1 = 50L;
	static long seed2 = 40L;
	static long seed3 = 30L;
	
	/**
	 * Generates a list of integer lists with equal sizes.
	 * 
	 * Accepts two parameters that correspond to the number of integer lists and their size.
	 * Uses {@link java.util.Random} to generate random integers and requires a list size
	 * of 4, 6, or 8.
	 * 
	 * @param sets The number of integer lists in the list.
	 * @param n The size of each integer list.
	 * @return {@link java.util.List} with integer lists as elements or null.
	 */
	public static List<List<Integer>> generateData(int sets, int n) {
		
		if (n != 4 && n != 6 && n != 8) { // Ensures n = 4,6, or 8
			return null;
		}
		
		// Initializes List that holds multiple Integer Lists
		List<List<Integer>> dataset = new ArrayList<>();
				
		// Initiaizes Random object
		Random rand = new Random(seed3);
		
		for (int i = 0; i < sets; i++) {
			List<Integer> data = new ArrayList<>(n);
			for (int j = 0; j < n; j++) {
				data.add(rand.nextInt(100));
			}
			dataset.add(data);
		}
		
		return dataset;
		
	}
	
	public static void printData(List<List<Integer>> dataset, Function<List<Integer>, Result> sorter, String algorithm) {
		
		for (int i = 0; i < dataset.size(); i++) {
			List<Integer> clone = new ArrayList<>(dataset.get(i));
			Result result = sorter.apply(clone);
			System.out.println("\nSorted " + algorithm + " list " + (i+1) + ": " + result.sortedList());
			System.out.println(algorithm + " Comparisons list " + (i+1) + ": \u001B[1m" + result.comparisonCount() + " \u001B[0m");
			}
		System.out.println("-".repeat(60));
		
	}
	
	public static void printLists(List<List<Integer>> dataset) {
		for (int i = 0; i < dataset.size(); i++) {
			System.out.println("List " + (i+1) + ": " + dataset.get(i));
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		List<List<Integer>> A = TestingData.generateData(10,8);
		List<List<Integer>> B = TestingData.generateData(10,6);
		List<List<Integer>> C = TestingData.generateData(10,4);
		
		List<List<Integer>> invalid = TestingData.generateData(2, 5);
		List<List<Integer>> empty = TestingData.generateData(0, 4);
		List<List<Integer>> negative = TestingData.generateData(-1, 4);
		
		//System.out.println(invalid);
		//System.out.println(empty);
		//System.out.println(negative);
		
		System.out.println("Unsorted Lists with 8 elements\n");
		printLists(A);
		System.out.println("Unsorted Lists with 6 elements\n");
		printLists(B);
		System.out.println("Unsorted Lists with 4 elements\n");
		printLists(C);
		
		System.out.println("\n\u001B[1mSet with 8 elements\u001B[0m");
		printData(A, MergeSort::mergeSort, "MergeSort");
		printData(A, QuickSort::quickSort, "QuickSort");
		printData(A, HeapSort::heapSort, "HeapSort");
		printData(A, ShakerSort::shakerSort, "ShakerSort");
		
		System.out.println("\n\u001B[1mSet with 6 elements\u001B[0m");
		printData(B, MergeSort::mergeSort, "MergeSort");
		printData(B, QuickSort::quickSort, "QuickSort");
		printData(B, HeapSort::heapSort, "HeapSort");
		printData(B, ShakerSort::shakerSort, "ShakerSort");
		
		System.out.println("\n\u001B[1mSet with 4 elements\u001B[0m");
		printData(C, MergeSort::mergeSort, "MergeSort");
		printData(C, QuickSort::quickSort, "QuickSort");
		printData(C, HeapSort::heapSort, "HeapSort");
		printData(C, ShakerSort::shakerSort, "ShakerSort");
	}
	
}
