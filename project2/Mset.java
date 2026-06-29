package sets;

import java.util.Arrays;

/**
 * Represents a multi-set belonging to some universal set.
 * Uses an integer array to denote the presence and number of elements at their respective indices.
 * 
 * @author Jeffrey Paluch
 */
public class Mset {
	private final UniversalSet U; // The UniversalSet that this Mset belongs to
	private final int[] frequency; // The integer array which represents the elements from the UniversalSet and their amounts
	
	/**
	 * Constructs a multi-set, named Mset, belonging to some UniversalSet.
	 * Initializes a default-valued integer array.
	 * 
	 * @param U The UniversalSet of the multi-set.
	 */
	public Mset(UniversalSet U) {
		this.U = U;
		this.frequency = new int[U.getSize()];
	}

	/**
	 * Creates a union between this Mset and another Mset.
	 * Takes the larger value from these Msets and inserts it into the union Mset.
	 * 
	 * @return The union of these Msets.
	 */
	public Mset union(Mset other) {
		if (this.U != other.U) {
			throw new IllegalArgumentException("Subsets don't belong to the same universal set");
		}
		Mset temp = new Mset(this.U);
		for (int i = 0; i < U.getSize(); i++) {
			temp.frequency[i] += Math.max(this.frequency[i], other.frequency[i]);
		}
		return temp;
	}
	
	/**
	 * Creates an intersection between this Mset and another Mset.
	 * Takes the smaller value from these Msets and inserts it into the intersection Mset.
	 * 
	 * @return The intersection of these Msets.
	 */
	public Mset intersection(Mset other) {
		if (this.U != other.U) {
			throw new IllegalArgumentException("Subsets don't belong to the same universal set");
		}
		Mset temp = new Mset(this.U);
		for (int i = 0; i < U.getSize(); i++) {
			temp.frequency[i] += Math.min(this.frequency[i], other.frequency[i]);
		}
		return temp;
	}
		
	/**
	 * Computes the difference between this Mset and another Mset.
	 * Subtracts the overlapping elements of another Mset from this Mset.
	 * 
	 * @return The difference of these Msets.
	 */
	public Mset difference(Mset other) {
		if (this.U != other.U) {
			throw new IllegalArgumentException("Subsets don't belong to the same universal set");
		}
		Mset temp = new Mset(this.U);
		for (int i = 0; i < U.getSize(); i++) {
			temp.frequency[i] = Math.max(0, this.frequency[i] - other.frequency[i]);
		}
		return temp;
	}
	
	/**
	 * Computes the sum between this Mset and another Mset.
	 * Adds each element of another Mset to this Mset.
	 * 
	 * @return The sum of these Msets.
	 */
	public Mset sum(Mset other) {
		if (this.U != other.U) {
			throw new IllegalArgumentException("Subsets don't belong to the same universal set");
		}
		Mset temp = new Mset(this.U);
		for (int i = 0; i < U.getSize(); i++) {
			temp.frequency[i] = this.frequency[i] + other.frequency[i];
		}
		return temp;
	}
	
	/**
	 * Adds some number of elements at the specified index of the frequency array.
	 * 
	 * @param element Index of frequency array.
	 * @param number Number of elements.
	 */
	public void add(int element, int number) {
		if (element >= 0 && element < U.getSize() && number >= 0) {
			frequency[element] += number;
		}
	}
	
	/**
	 * Removes some number of elements at the specified index of the frequency array.
	 * 
	 * @param element Index of frequency array.
	 * @param number Number of elements.
	 */
	public void remove(int element, int number) {
		if ((element >= 0 && element < U.getSize()) && (frequency[element] > 0 && number > 0)) {
			frequency[element] -= number;
			
			if (frequency[element] < 0) {
				frequency[element] = 0;
			}
		}
	}
	
	/**
	 * Adds or subtracts some number of multiple elements at the specified indices of the frequency array.
	 * 
	 * @param elements Indices of frequency array.
	 * @param numbers Number of each element.
	 */
	public void alter(int[] elements, int[] numbers) {
		int counter = 0;
		if (elements.length <= numbers.length) {
			for (int n : elements) {
				if (n >= 0 && n < U.getSize()) {
					frequency[n] += numbers[counter];
					if (frequency[n] < 0) {
						frequency[n] = 0;
					}
				}
				counter++;
			}
		}
	}
	
	/**
	 * Returns a string representation of the Mset's frequency array.
	 * 
	 * @return String representation of the frequency array.
	 */
	@Override
	public String toString() {
		return Arrays.toString(frequency);
	}
}
