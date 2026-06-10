package sortingAlgorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MakingData {
	
	public static List<List<Integer>> generateData(int sets, int n) {
		
		// Initializes List that holds multiple Integer Lists
		List<List<Integer>> dataset = new ArrayList<>();
				
		// Initializes Random object
		Random rand = new Random();
		
		// Ensures n = 4,6, or 8
		if (n != 4 && n != 6 && n!= 8) {
			throw new IllegalArgumentException("n must be 4, 6, or 8");
		}
		
		for (int i = 0; i < sets; i++) {
			List<Integer> data = new ArrayList<>(n);
			for (int j = 0; j < n; j++) {
				data.add(rand.nextInt(100));
			}
			dataset.add(data);
		}
		
		return dataset;
	}
	
	public static void main(String[] args) {
		
		List<Integer> a = MakingData.generateData(3,6).get(0);
		System.out.println(a);
		System.out.println(MergeSort.mergeSort(a));
		System.out.println(QuickSort.quickSort(a));
	}
	
}
