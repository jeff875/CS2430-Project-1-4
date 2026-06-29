package sets;

import java.util.stream.Collectors;

/**
 * Represents a subset belonging to some universal set.
 * Uses a boolean array to denote the presence of elements at their respective indices.
 * 
 * @author Jeffrey Paluch
 */
public class Subset {
	private final UniversalSet U; // The UniversalSet that this Subset belongs to
	private final boolean[] subset; // The boolean array which represents the elements from the UniversalSet
	
	/**
	 * Constructs a Subset belonging to some UniversalSet.
	 * Initializes a default-valued boolean array.
	 * 
	 * @param U The UniversalSet of the Subset.
	 */
	public Subset(UniversalSet U) {
		this.U = U;
		this.subset = new boolean[U.getSize()];
	}
	
	/**
	 * Creates a complement of this Subset.
	 * Inverts each value of the boolean array.
	 * 
	 * @return The complement of this Subset.
	 */
	public Subset complement() {
		Subset temp = new Subset(this.U);
		for (int i = 0; i < U.getSize(); i++) {
			temp.subset[i] = !this.subset[i];
		}
		return temp;
	}
	
	/**
	 * Creates a union between this Subset and another Subset.
	 * Combines the elements from two Subsets into one Subset.
	 * 
	 * @return The union of these Subsets.
	 */
	public Subset union(Subset other) {
		if (this.U != other.U) {
			throw new IllegalArgumentException("Subsets don't belong to the same universal set");
		}
		
		Subset temp = new Subset(this.U);
		for (int i = 0; i < U.getSize(); i++) {
				temp.subset[i] = this.subset[i] || other.subset[i];
		}
		return temp;
	}
	
	/**
	 * Creates an intersection between this Subset and another Subset.
	 * Joins the overlapping elements from two Subsets into one Subset.
	 * 
	 * @return The intersection of these Subsets.
	 */
	public Subset intersection(Subset other) {
		if (this.U != other.U) {
			throw new IllegalArgumentException("Subsets don't belong to the same universal set");
		}
		
		Subset temp = new Subset(this.U);
		for (int i = 0; i < U.getSize(); i++) {
				temp.subset[i] = this.subset[i] && other.subset[i];
		}
		return temp;
	}
	
	/**
	 * Computes the difference between this Subset and another Subset.
	 * Subtracts the overlapping elements of another Subset from this Subset.
	 * 
	 * @return The difference of these Subsets.
	 */
	public Subset difference(Subset other) {
		if (this.U != other.U) {
			throw new IllegalArgumentException("Subsets don't belong to the same universal set");
		}
		
		Subset temp = new Subset(this.U);
		for (int i = 0; i < U.getSize(); i++) {
				temp.subset[i] = this.subset[i] && !other.subset[i];
		}
		
		return temp;
	}
	
	/**
	 * Computes the symmetric difference between this Subset and another Subset.
	 * Performs the exclusive OR operation (XOR) on two Subsets.
	 * 
	 * @return The symmetric difference of these Subsets.
	 */
	public Subset symmetricDifference(Subset other) {
		if (this.U != other.U) {
			throw new IllegalArgumentException("Subsets don't belong to the same universal set");
		}
		
		Subset temp = new Subset(this.U);
		for (int i = 0; i < U.getSize(); i++) {
				temp.subset[i] = this.subset[i] ^ other.subset[i];
		}
		
		return temp;
	}
	
	/**
	 * Adds an element at the specified index of the subset array.
	 * 
	 * @param element Index of subset array.
	 */
	public void add(int element) {
		if (element >= 0 && element < U.getSize()) {
			this.subset[element] = true;
		}
	}
	
	/**
	 * Adds multiple elements at the specified indices of the subset array.
	 * 
	 * @param elements Indices of subset array.
	 */
	public void add(int[] elements) {
		for (int n : elements) {
			subset[n] = true;
		}
	}
	
	/**
	 * Removes an element at the specified index of the subset array.
	 * 
	 * @param element Index of subset array.
	 */
	public void remove(int element) {
		if (element >= 0 && element < U.getSize()) {
			subset[element] = false;
		}
	}
	
	/**
	 * Removes multiple elements at the specified indices of the subset array.
	 * 
	 * @param elements Indices of subset array.
	 */
	public void remove(int[] elements) {
		for (int n : elements) {
			subset[n] = false;
		}
	}
	
	/**
	 * Returns a string representation of the Subset's subset array.
	 * Bolds the occurrence of each true value.
	 * 
	 * @return String representation of the subset array.
	 */
	@Override
	public String toString() {
		 return "[" + java.util.stream.IntStream.range(0, subset.length)
         .mapToObj(i -> subset[i] ? "\u001B[1mtrue \u001B[0m" : "false")
         .collect(Collectors.joining(", ")) + "]";
	}
}
