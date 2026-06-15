package sortingAlgorithms;

import java.util.List;

public final class Result {

	private final List<Integer> sortedList;
	private final int comparisonCount;
	
	public Result(List<Integer> sortedList, int comparisonCount) {
		this.sortedList = sortedList;
		this.comparisonCount = comparisonCount;
	}
	
	public List<Integer> sortedList() {
		return this.sortedList;
	}
	
	public int comparisonCount() {
		return this.comparisonCount;
	}
	
}
