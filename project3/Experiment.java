package optimalSelection;

/**
CS 2430 - 501
Programming Project 3 – Summer 2026

This class features methods that aim to compute the optimal subset of a set of twelve experiments, each with a weight and rating, given that
the weight must not exceed 700 kg and the rating must be as large as possible. The methods include greedy algorithms, a brute force algorithm,
and a dynamic programming algorithm that each generate a subset with a total weight and rating. These subsets are returned and displayed
together for comparison.
*/

public record Experiment(int id, String name, int weight, int rating) {

}
