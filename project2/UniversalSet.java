package sets;

/**
 * Represents a universal set with an immutable size.
 * 
 * @author Jeffrey Paluch
 */
public class UniversalSet {

	private final int size; // Universal set size
		
	/**
	 * Construct a UniversalSet of a specified size greater than or equal to 10.
	 * 
	 * @param size The universal set's size.
	 */
	public UniversalSet(int size) {
		if (size < 10) {
			throw new IllegalArgumentException("The size of the universal set is too small.");
		}
		this.size = size;
	}
		
	/**
	 * Gets the size of the UniversalSet.
	 * 
	 * @return The universal set's size.
	 */
	public int getSize() {
		return this.size;
	}
}
