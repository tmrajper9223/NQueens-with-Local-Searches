import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 
 */

/**
 * @author Tarik Rajper
 *
 */
public class GeneticAlgorithm {
	
	private int maxGenerations;
	private float mutationRate;
	private int generations;
	
	/**
	 * Sets the initial Population, Max Generations, and the Mutation Rate
	 * @param population
	 * @param maxGenerations
	 * @param mutationRate
	 */
	public GeneticAlgorithm(PriorityQueue<Node> population, int maxGenerations, float mutationRate) {
		this.maxGenerations = maxGenerations;
		this.mutationRate = mutationRate;
		generations = 1;
	}
	
	/**
	 * Iterates through a certain number of generations
	 * Takes the 2 most fit individuals from the populations as parents and reproduces a child
	 * Chance of mutation occurring is based on user input
	 * Algorithm performs better with higher mutation rate and slower with small mutation rate
	 * @param population
	 * @return
	 */
	public Node genetic(PriorityQueue<Node> population) {
		Random rand = new Random();
		while (generations < maxGenerations + 1) {
			PriorityQueue<Node> newPopulation = new PriorityQueue<Node>();
			for (int i = 0; i < population.size(); i++) {
				if (population.peek().getAttacks() == 0) {
					return population.poll();
				}
				
				Node x = fitness(population)[0];
				Node y = fitness(population)[1];
				
				Node child = reproduce(x, y);
				
				float willMutate = rand.nextFloat();
				if (willMutate < mutationRate) {
					child = mutate(child);
				}
				
				newPopulation.offer(child);
			}
			generations++;
			population = newPopulation;
		}
		return population.poll();
	}
	
	/**
	 * Delects Top 2 most fit from the populations and returns the 2 Nodes as an array
	 * @param population
	 * @return
	 */
	public Node[] fitness(PriorityQueue<Node> population) {
		int counter = 0;
		Node[] parents = new Node[2]; 
		Iterator<Node> iter = population.iterator();
		while (iter.hasNext()) {
			if (counter == 2)
				break;
			parents[counter] = iter.next();
			counter++;
		}
		return parents;
	}
	
	/**
	 * Takes a random size from Node x and a random size from Node y to create a new child
	 * @param x
	 * @param y
	 * @return
	 */
	public Node reproduce(Node x, Node y) {
		Random rand = new Random();
		int c = rand.nextInt(x.getBoard().length);
		int[] board = new int[x.getBoard().length];
		System.arraycopy(x.getBoard(), 0, board, 0, c);
		System.arraycopy(y.getBoard(), c, board, c, x.getBoard().length - c);
		Node child = new Node(board);
		return child;
	}
	
	/**
	 * Randomly moves one queen on the board
	 * @param child
	 * @return
	 */
	public Node mutate(Node child) {
		int[] board = child.getBoard();
		Random rand = new Random();
		int c = rand.nextInt(board.length);
		board[c] = rand.nextInt(board.length);
		Node mutated = new Node(board);
		return mutated;
	}
	
	public int getGenerations() {
		return this.generations;
	}
}
