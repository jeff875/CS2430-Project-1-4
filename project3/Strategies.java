package optimalSelection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Strategies {
	private static String boldOn = "\u001B[1m";
	private static String boldOff = "\u001B[0m";
	private final static double MILLION = 1_000_000;
	
	private static final List<Experiment> experiments = new ArrayList<>(List.of(
			new Experiment(1,"Cloud Patterns", 36, 5),
			new Experiment(2,"Solar Flares", 264, 9),
			new Experiment(3,"Solar Power", 188, 6),
			new Experiment(4,"Binary Stars", 203, 8),
			new Experiment(5,"Relativity", 104, 8),
			new Experiment(6,"Seed Viability", 7, 4),
			new Experiment(7,"Sun Spots", 90, 2),
			new Experiment(8,"Mice Tumors", 65, 8),
			new Experiment(9,"Microgravity Plant Growth", 75, 5),
			new Experiment(10,"Micrometeorites", 170, 9),
			new Experiment(11,"Cosmic Rays", 80, 7),
			new Experiment(12,"Yeast Fermentation", 27, 4)
			));
	
	/**
	 * Implements the rating-first greedy method. The input list is sorted by rating in descending order and each experiment
	 * is appended to the return list until the weight limit is reached.
	 * 
	 * @param list The experiment list.
	 * @return The subset of the experiment list.
	 */
	public static List<Experiment> highestRating(List<Experiment> list) {
		List<Experiment> sortedList = new ArrayList<>(list);
		sortedList.sort(Comparator.comparingInt(Experiment::rating).reversed());
		
		List<Experiment> result = new ArrayList<>();
		int currentWeight = 0;
		final int weightLimit = 700;
		
		for (Experiment exp : sortedList) {
			if (currentWeight + exp.weight() <= weightLimit) {
				result.add(exp);
				currentWeight += exp.weight();
			}
		}
		return result;
	}
	
	/**
	 * Implements the lowest-weight greedy method.The input list is sorted by weight in ascending order and each experiment is appended
	 * to the return list until the weight limit is reached.
	 * 
	 * @param list The experiment list.
	 * @return The subset of the experiment list.
	 */
	public static List<Experiment> lowestWeight(List<Experiment> list) {
		List<Experiment> sortedList = new ArrayList<>(list);
		sortedList.sort(Comparator.comparingInt(Experiment::weight));
		
		List<Experiment> result = new ArrayList<>();
		int currentWeight = 0;
		final int weightLimit = 700;
		
		for (Experiment exp : sortedList) {
			if (currentWeight + exp.weight() <= weightLimit) {
				result.add(exp);
				currentWeight += exp.weight();
			}
		}
		return result;
	}
	
	/**
	 * Implements the best rating-to-weight greedy method. The input list is sorted by rating-to-weight ratio in descending order and
	 * each experiment is appended to the return list until the weight limit is reached.
	 * 
	 * @param list The experiment list.
	 * @return The subset of the experiment list.
	 */
	public static List<Experiment> bestRatio(List<Experiment> list) {
		List<Experiment> sortedList = new ArrayList<>(list);
		sortedList.sort(Comparator.comparingDouble((Experiment exp) -> (double) exp.rating() / exp.weight()).reversed());
			
		List<Experiment> result = new ArrayList<>();
		int currentWeight = 0;
		final int weightLimit = 700;
		
		for (Experiment exp : sortedList) {
			if (currentWeight + exp.weight() <= weightLimit) {
				result.add(exp);
				currentWeight += exp.weight();
			}
		}
		return result;
	}
	
	/**
	 * Represents a subset of the experiment list that contains some combination of experiments with a total weight and rating.
	 * Used in conjunction with the brute force method to store the weights and ratings of each possible subset.
	 */
	public static class Subset {
		List<Experiment> experimentSubset;
		int subsetWeight;
		int subsetRating;
		
		public Subset(List<Experiment> exp, int weight, int rating) {
			this.experimentSubset = exp;
			this.subsetWeight = weight;
			this.subsetRating = rating;
		}
	}
	
	/**
	 * Generates every possible combination for a bit string of some length using bit-shift operators.
	 * Configured to represent the list of experiments so as to construct every possible subset, printing the most
	 * optimal subsets and their respective weights and ratings.
	 */
	public static void bruteForce() {
		int size = experiments.size();
		int totalSubsets = 1 << size; // 2^12 = 4096
		int limit = 3; // number of desired results
		List<Subset> subsetList = new ArrayList<>(); // empty Experiment power set and corresponding weights and ratings
		
		for (int i = 0; i < totalSubsets; i++) { // 4096 iterations for each unique bit string
			List<Experiment> expSubset = new ArrayList<>();
			int currentWeight = 0;
			int currentRating = 0;
			
			for (int j = 0; j < size; j++) { // 12 iterations for each bit
				if ((i & (1 << j)) != 0) { // bitwise AND for each bit
					Experiment exp = experiments.get(j);
					expSubset.add(exp);
					
					currentWeight += exp.weight();
					currentRating += exp.rating();				
				}
			}
			
			if (currentWeight <= 700) {
				subsetList.add(new Subset(expSubset, currentWeight, currentRating)); // updates power set with attached weight and rating
			}			
		}
		
		subsetList.sort(Comparator.comparingInt((Subset sub) -> sub.subsetRating).reversed());
		
		for (int i = 0; i < limit; i++) {
			Subset s = subsetList.get(i);
			System.out.printf(boldOn + "Subset %d Rating: %d%n" + boldOff, (i+1), s.subsetRating);
			System.out.printf("Subset %d Weight: %d%n", (i+1), s.subsetWeight);
			System.out.printf("Subset %d Experiments:%n%n", (i+1));
			for (Experiment e : s.experimentSubset) {
				System.out.println(e);
			}
			System.out.printf("%n%s%n%n","- ".repeat(36));
		}
		
	}
	
	/**
	 * Computes the optimal subset of experiments using dynamic programming. A 13x701 2D-array is constructed where the rows correspond to each
	 * experiment and columns each possible weight. Cells hold the total rating of the current subset, where each next column and row must
	 * stay the same or increase in value. Therefore, the method prints the cell in the bottom right which holds the optimal rating. The optimal
	 * subset's contents are appended to an empty list by working up through the bottom right to the top left of the 2D-array.
	 * 
	 * @param list The experiment list.
	 */
	public static void DP(List<Experiment> list) {
		int size = experiments.size();
		int limit = 700;
		int[][] table = new int[size+1][limit+1]; // 13 x 701 2D-Array
		
		for (int i = 0; i < size; i++) {
			Experiment current = list.get(i);
			
			for (int j = 0; j <= limit; j++) {
				if (current.weight() <= j) {
					int exclude = table[i][j];
					int include = table[i][j - current.weight()] + current.rating();
					
					table[i+1][j] = Math.max(exclude, include);
				} else {
					table[i+1][j] = table[i][j]; // Gives the current cell's value to the cell underneath it
				}
			}
		}
		
		List<Experiment> selected = new ArrayList<>(); // Optimal subset
		
		int s = limit;
		
		for (int i = size; i > 0; i--) { // Traverses bottom-right to top-left
			if (table[i][s] != table [i-1][s]) {
				Experiment previous = list.get(i-1);
				selected.add(previous);
				s -= previous.weight();
			}
		}
		
		printExperiments(selected, "Dynamic Programming");
	}
	
	/**
	 * Iterates through each experiment in a subset and prints the experiment, then the total weight and rating of the subset.
	 * 
	 * @param list The experiment list.
	 * @param strategy The method name.
	 */
	public static void printExperiments(List<Experiment> list, String strategy) {
		int weight = 0;
		int rating = 0;
		
		System.out.println(boldOn + strategy + boldOff);
		System.out.println("-".repeat(71));
		
		for (Experiment exp : list) {
			System.out.println(exp);
			weight += exp.weight();
			rating += exp.rating();
		}
		System.out.println("\nTotal Weight: " + weight);
		System.out.println("Total Rating: " + rating);
		System.out.printf("_".repeat(71) + "\n".repeat(3));
	}
	
	/**
	 * Prints the subsets generated by each of the greedy methods, the brute force method, and the dynamic programming method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		List<Experiment> list1 = highestRating(experiments);
		printExperiments(list1, "Highest Rating First");
		
		List<Experiment> list2 = lowestWeight(experiments);
		printExperiments(list2, "Lightest Weight First");
		
		List<Experiment> list3 = bestRatio(experiments);
		printExperiments(list3, "Best Rating-to-Weight Ratio First");
		
		long start1 = System.nanoTime();
		bruteForce();
		double time1 = (double) (System.nanoTime()-start1)/MILLION;
		
		long start2 = System.nanoTime();
		DP(experiments);			
		double time2 = (double) (System.nanoTime()-start2)/MILLION;
		
		System.out.printf("Brute Force time: %.4f ms%nDynamic Programming time: %.4f ms",time1,time2);
		
	}
	
}
