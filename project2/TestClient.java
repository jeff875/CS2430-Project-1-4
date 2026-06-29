package sets;

/**
 * Testing class for instantiating different set objects and performing set operations.
 * 
 * @author Jeffrey Paluch
 */
public class TestClient {

	public static void main(String[] args) {
		
		// Instantiating universal sets
		UniversalSet us1 = new UniversalSet(10); // Universal Set 1
		UniversalSet us2 = new UniversalSet(10); // Universal Set 2
		
		// Instantiating subsets
		Subset sub1 = new Subset(us1); // Subset 1
			int[] array1 = {1,4,5,7};
			sub1.add(array1);
			
		Subset sub2 = new Subset(us1); // Subset 2
			int[] array2 = {1,3,5,6};
			sub2.add(array2);
			
		Subset sub3 = new Subset(us1); // Subset 3
			int[] array3 = {5,7,9};
			sub3.add(array3);
			
		Subset sub4 = new Subset(us2); // Subset 4 (different universal set)
			int[] array4 = {2,8,9};
			sub4.add(array4);
			
		System.out.printf("%-42s%s", "", printIndices(us1,false));
		
		// Printing subsets
		System.out.printf("\n%-42s%s%n", "Subset 1: (US-1)", sub1);
		System.out.printf("%-42s%s%n", "Subset 2: (US-1)", sub2);
		System.out.printf("%-42s%s%n", "Subset 3: (US-1)", sub3);
		System.out.printf("\n%-42s%s%n", "Subset 4: (US-2)", sub4);
		
		// Invalid arguments and exceptions
		try {
			sub1.union(sub4); // Invalid union of 2 subsets
		} catch (IllegalArgumentException e) {
			System.out.printf("\n%-42s%s%n", "Subsets 1 & 4 Union: ", e.getMessage());
		}
		try {
			UniversalSet us3 = new UniversalSet(8); // Invalid size for universal set
		} catch (IllegalArgumentException e){
			System.out.printf("%-42s%s%n", "Universal Set of size n = 8: ", e.getMessage());
		}
		
		// Complement tests
		Subset S1Complement = sub1.complement();
		Subset S2Complement = sub2.complement();
		System.out.printf("\n%-42s%s%n", "Subset 1 Complement: ", S1Complement);
		System.out.printf("%-42s%s%n", "Subset 2 Complement: ", S2Complement);
		
		// Union and intersection tests
		Subset S1S2Union = sub1.union(sub2); // Union of 2 subsets
		Subset S2S1Union = sub2.union(sub1); // Commutative property of unions
		Subset S1S2S3Union = sub3.union(S1S2Union); // Union of 3 subsets
		
		Subset S1S2Intersection = sub1.intersection(sub2); // Intersection of 2 subsets
		Subset S2S1Intersection = sub2.intersection(sub1); // Commutative property of intersections
		Subset S1S2S3Intersection = sub3.intersection(S1S2Intersection); // Intersection of 3 subsets
		
		System.out.printf("\n%-42s%s", "", printIndices(us1,false));
		System.out.printf("\n%-42s%s%n", "Subsets 1 & 2 Union: ", S1S2Union);
		System.out.printf("%-42s%s%n", "Subsets 2 & 1 Union: ", S2S1Union);
		System.out.printf("%-42s%s%n", "Subsets 1, 2 & 3 Union: ", S1S2S3Union);
		
		System.out.printf("\n%-42s%s%n", "Subsets 1 & 2 Intersection: ", S1S2Intersection);
		System.out.printf("%-42s%s%n", "Subsets 2 & 1 Intersection: ", S2S1Intersection);
		System.out.printf("%-42s%s%n", "Subsets 1, 2 & 3 Intersection: ", S1S2S3Intersection);
		
		// Difference tests
		Subset S1S2Difference = sub1.difference(sub2); // sub1 - sub2
		Subset S2S1Difference = sub2.difference(sub1); // sub2 - sub1
		Subset S1S3Difference = sub1.difference(sub3); // sub1 - sub3
		System.out.printf("\n%-42s%s", "", printIndices(us1,false));
		System.out.printf("\n%-42s%s%n", "Subsets 1 & 2 Difference: (1 - 2) ", S1S2Difference);
		System.out.printf("%-42s%s%n", "Subsets 2 & 1 Difference: (2 - 1) ", S2S1Difference);
		System.out.printf("%-42s%s%n", "Subsets 1 & 3 Difference: (1 - 3) ", S1S3Difference);
		
		// Symmetric difference tests
		Subset S1S2Symmetric = sub1.symmetricDifference(sub2); // sub1 XOR sub2
		Subset S2S1Symmetric = sub2.symmetricDifference(sub1); // sub2 XOR sub1
		Subset S1S3Symmetric = sub1.symmetricDifference(sub3); // sub1 XOR sub3
		System.out.printf("\n%-42s%s%n", "Subsets 1 & 2 Symmetric Difference: ", S1S2Symmetric);
		System.out.printf("%-42s%s%n", "Subsets 2 & 1 Symmetric Difference: ", S2S1Symmetric);
		System.out.printf("%-42s%s%n", "Subsets 1 & 3 Symmetric Difference: ", S1S3Symmetric);
		
		// Instantiating M-sets
		Mset Msub1 = new Mset(us1); // M-set 1
			int[] Msub1ArrayA = {1,2,4,5,8,9};
			int[] Msub1ArrayB = {1,1,2,3,-1,3}; // Adding a negative number into M-set --> Corrects negative value to 0
			Msub1.alter(Msub1ArrayA, Msub1ArrayB);
			
		Mset Msub2 = new Mset(us1); // M-set 2
			int[] Msub2ArrayA = {1,3,4,6,11,9}; // Specifying non-existant index for M-set --> Ignores the corresponding index from both arrays
			int[] Msub2ArrayB = {2,1,3,5,2,4};
			Msub2.alter(Msub2ArrayA, Msub2ArrayB);
			
		Mset Msub3 = new Mset(us1); // M-set 3
			int[] Msub3ArrayA = {0,1,4,7,8};
			int[] Msub3ArrayB = {2,1,3,5,2,4}; // Element array smaller than Numbers array --> Ignores extra elements at the end of the Numbers array
			Msub3.alter(Msub3ArrayA, Msub3ArrayB);
			
		// Printing M-sets
		System.out.println("-".repeat(120));
		System.out.printf("\n%-42s%s%n", "Index", printIndices(us1,true));
		System.out.printf("\n%-42s%s%n", "Multi-set 1: ", Msub1);
		System.out.printf("%-42s%s%n", "Multi-set 2: ", Msub2);
		System.out.printf("%-42s%s%n", "Multi-set 3: ", Msub3);
		
		// Union tests (M-sets)
		Mset Ms1Ms2Union = Msub1.union(Msub2); // Maximum of 2 elements
		Mset Ms1Ms3Union = Msub1.union(Msub3);
		Mset Ms1Ms2Ms3Union = Msub3.union(Ms1Ms2Union); // Maximum of 3 elements
		
		System.out.printf("\n%-42s%s%n", "Multi-sets 1 & 2 Union: ", Ms1Ms2Union);
		System.out.printf("%-42s%s%n", "Multi-sets 1 & 3 Union: ", Ms1Ms3Union);
		System.out.printf("%-42s%s%n", "Multi-sets 1, 2 & 3 Union: ", Ms1Ms2Ms3Union);
		
		// Intersection tests (M-sets)
		
		Mset Ms1Ms2Intersection = Msub1.intersection(Msub2); // Minimum of 2 elements
		Mset Ms1Ms2Ms3Intersection = Msub3.intersection(Ms1Ms2Intersection); // Minimum of 3 elements
		Mset Ms1Ms3Intersection = Msub1.intersection(Msub3);
		
		System.out.printf("\n%-42s%s%n", "Multi-sets 1 & 2 Intersection: ", Ms1Ms2Intersection);
		System.out.printf("%-42s%s%n", "Multi-sets 1 & 3 Intersection: ", Ms1Ms3Intersection);
		System.out.printf("%-42s%s%n", "Multi-sets 1, 2 & 3 Intersection: ", Ms1Ms2Ms3Intersection);
		
		// Sum tests (M-sets)
		Mset Ms1Ms2Sum = Msub1.sum(Msub2); // Sum of 2 elements (Msub1 + Msub2)
		Mset Ms1Ms3Sum = Msub1.sum(Msub3); // (Msub1 + Msub3)
		Mset Ms1Ms2Ms3Sum = Msub3.sum(Ms1Ms2Sum); // Sum of 3 elements (Msub1 + Msub2 + Msub3)
		
		System.out.printf("\n%-42s%s%n", "Multi-sets 1 & 2 Sum: ", Ms1Ms2Sum);
		System.out.printf("%-42s%s%n", "Multi-sets 1 & 3 Sum: ", Ms1Ms3Sum);
		System.out.printf("%-42s%s%n", "Multi-sets 1, 2 & 3 Sum: ", Ms1Ms2Ms3Sum);
		
		// Difference tests (M-sets)
		Mset Ms1Ms2Diff = Msub1.difference(Msub2); // Difference of 2 elements (Msub1 - Msub2)
		Mset Ms2Ms1Diff = Msub2.difference(Msub1); // Commutative property (Msub2 - Msub1)
		Mset Ms1Ms3Diff = Msub1.difference(Msub3); // (Msub1 - Msub3)
		
		System.out.printf("\n%-42s%s%n", "Multi-sets 1 & 2 Difference: (1 - 2)", Ms1Ms2Diff);
		System.out.printf("%-42s%s%n", "Multi-sets 2 & 1 Difference: (2 - 1)", Ms2Ms1Diff);
		System.out.printf("%-42s%s%n", "Multi-sets 1 & 3 Difference: (1 - 3)", Ms1Ms3Diff);
		
	}

	/**
	 * Prints the indices of some universal set depending on the type of subset and size of universal set.
	 * 
	 * @param us Universal set of interest.
	 * @param Mset Chooses between subset or M-set representation.
	 * @return Indices of the specified universal set as a string.
	 */
	private static String printIndices(UniversalSet us, boolean Mset) {
		StringBuilder sb = new StringBuilder();
		
		if (Mset == false) { // Regular subset
			sb.append("[  0  ,");	
			for (int i = 1; i < us.getSize(); i++) {
				sb.append("   ").append(i).append("  ");
				if (i < us.getSize() - 1) {
					sb.append(",");
				}
			}
			sb.append("]");
			return sb.toString();
		} else { // M-set
			sb.append("[0");
			for (int i = 1; i < us.getSize(); i++) {
				sb.append(", ").append(i);
			}
			sb.append("]");
			return sb.toString();
		}
	}

}
