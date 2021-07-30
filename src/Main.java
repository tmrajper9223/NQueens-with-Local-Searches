import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Tarik Rajper
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		initialize();
	}
	
	/**
	 * User input for testing either SA or Genetic
	 */
	@SuppressWarnings("resource")
	public static void initialize() {
		Scanner scan = new Scanner(System.in);
		while (true) {
			choices();
			int choice = scan.nextInt();

			switch(choice) {
			case 1:
				System.out.println("Enter a Mutation Rate between 0 and 1 (0.9 most optimal): ");	//0.9 most optimal; lower mutation rate=longer execution time
				float mRate = scan.nextFloat();
				
				System.out.println("25x25 Board with Generations set to a MAX of 10000 and Mutation Rate set to " + 
						(mRate * 100) + "%\n");
				
				ArrayList<Node> solvedGenetic = new ArrayList<Node>();
				int[] generations = new int[3];
				
				System.out.println("Solving 400 N Queen Instances with the Genetic Algorithm. This should take less than 1 minute...");
				long gStart = System.nanoTime();
				for (int i = 0; i < 400; i++) {
					Node solution = geneticAlogrithm(mRate);
					if (solution.getAttacks() == 0) {
						solvedGenetic.add(solution);
					}
				}
				long gEnd = System.nanoTime();
				
				for (int i = 0; i < 3; i++) {
					generations[i] = solvedGenetic.get(i).getGenerations();
				}
				
				geneticStats(solvedGenetic, generations);
				
				System.out.println("Solved: " + solvedGenetic.size());
				System.out.println("Time to Solve: " + ((gEnd-gStart)/1000000000) + " Seconds");
				continue;

			case 2:
				PriorityQueue<Node> solvedAnnealing = new PriorityQueue<Node>();
				System.out.println("Configurations: 25x25 Board, Initial Temperature: 10000, Stopping Threshold: 0.000001, Decay Function: Temperature * 0.99");
				System.out.println("Solving 400 N Queen Instances with Simulated Annealing. This should take less than 1 minute...");
				long aStart = System.nanoTime();
				for (int i = 0; i < 400; i++) {
					Node solution = simulatedAnnealing();
					solvedAnnealing.add(solution);
				}
				long aEnd = System.nanoTime();
				
				annnealingStats(solvedAnnealing);
				
				//Pops the first 3 from printing out stats so I add 3 to this size
				System.out.println("Solved: " + (solvedAnnealing.size() + 3) + "\n");
				System.out.println("Time to Solve: " + ((aEnd-aStart)/1000000000) + " Seconds");
				continue;

			case 3:
				System.out.println("Exiting...");
				System.exit(1);
				break;

			default:
				System.out.println("Invalid Choice!\nExiting...");
				System.exit(1);
				break;
			}
		}
	}
	
	/**
	 * Calls genetic algorithm with user input Mutation Rate
	 * @param mRate Mutation Rate
	 * @return
	 */
	public static Node geneticAlogrithm(float mRate) {
		PriorityQueue<Node> population = new PriorityQueue<Node>();
		for (int i = 0; i < 50; i++) {
			Node board = new Node(25);
			Node node = new Node(board.getBoard());
			population.offer(node);
		}
		GeneticAlgorithm genetic = new GeneticAlgorithm(population, 10000, mRate);
		Node solutionState = genetic.genetic(population);
		solutionState.setGeneration(genetic.getGenerations());
		
		return solutionState;
	}
	
	/**
	 * Calls SA algorithm on one board
	 * @return
	 */
	public static Node simulatedAnnealing() {
		Node b = new Node(25);
		Node n = new Node(b.getBoard());
		SimulatedAnnealing sa = new SimulatedAnnealing();
		Node solution = sa.SA(n, 100000.0);
		return solution;
	}
	
	/**
	 * Prints the results of one genetic algorithm
	 * @param solved Stores all of the solved boards
	 * @param generations Stores what generation the board was solved at
	 */
	public static void geneticStats(ArrayList<Node> solved, int[] generations) {
		for (int i = 0; i < 3; i++) {
			Node fullBoard = solved.get(i);
			int[] board = fullBoard.getBoard();
			printFullBoard(board);
			printQueenPositions(board);
			System.out.println("\n\nNumber of Attacking Queens: " + fullBoard.getAttacks());
			System.out.println("\nSolved At Generation " + generations[i] + "\n");
		}
	}
	
	/**
	 * Prints the results of the SA algorithm
	 * @param solved Stores all of the solved boards
	 */
	public static void annnealingStats(PriorityQueue<Node> solved) {
		for (int i = 0; i < 3; i++) {
			Node fullBoard = solved.poll();
			int[] board = fullBoard.getBoard();
			printFullBoard(board);
			printQueenPositions(board);
			System.out.println("\n\nNumber of Attacking Queens: " + fullBoard.getAttacks());
		}
	}
	
	/**
	 * Prints the positions of the Queen: Index of Array = column; Value of Index = Row
	 * @param board
	 */
	public static void printQueenPositions(int[] board) {
		System.out.println("Final Configuration\n");
		System.out.println("Queen Positions: ");
		for (int i = 0; i < board.length; i++) {
			System.out.print(board[i] + " ");
		}
	}
	
	public static void choices() {
		System.out.println("\nSelect a Choice: ");
		System.out.println("1: Genetic Algorithm");
		System.out.println("2: Simulated Annealing");
		System.out.println("3: Exit Program");
	}
	
	/**
	 * Places Queens on to board and displays nxn board
	 * @param board
	 */
	public static void printFullBoard(int[] board) {
		int size = board.length * board.length;
		String[] fullBoard = new String[size];
		
		for (int i = 0; i < size; i++) {
			fullBoard[i] = "x";
		}
		
		for (int i = 0; i < board.length; i++) {
			int position = (board[i] * board.length) + i;
			fullBoard[position] = "Q";
		}
		
		for (int i = 0; i < size; i++) {
			if (i % board.length == 0) {
				System.out.print("\n" + fullBoard[i] + " ");
				continue;
			}
			System.out.print(fullBoard[i] + " ");
		}
		
		System.out.println("\n");
	}

}
