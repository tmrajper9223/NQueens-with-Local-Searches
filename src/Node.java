import java.util.Random;

/**
 * 
 */

/**
 * @author Tarik Rajper
 *
 */
public class Node implements Comparable<Node> {
	
	private int[] board;
	private int attacks;
	private int generations;
	
	/**
	 * Takes a board and calculates the number of attacking queens
	 * @param board
	 */
	public Node(int[] board) {
		this.board = board;
		this.attacks = calcAttacks(board);
	}
	
	/**
	 * Generates New Board
	 * @param n Size of the board
	 */
	public Node(int n) {
		this.board = generateBoard(n);
	}
	
	/**
	 * Generates a board with index represnted as the column and the value at the index as the row
	 * [0,1,2,3,4]
	 * Q x x x x
	 * x Q x x x
	 * x x Q x x
	 * x x x Q x
	 * x x x x Q
	 * @param n
	 * @return
	 */
	public int[] generateBoard(int n) {
		Random rand = new Random();
		int[] board = new int[n];
		for (int i = 0; i < n; i++) {
			board[i] = rand.nextInt(n);
		}
		return board;
	}
	
	/**
	 * Counts the number of attacking queens in each Row and Diagonal
	 * @param board
	 * @return
	 */
	public int calcAttacks(int[] board) {
		int counter = 0;
		
		for (int i = 0; i < board.length-1; i++) {
			for (int j = i + 1; j < board.length; j++) {
				if (board[i] == board[j] || j - i == Math.abs(board[i] - board[j])) {
					counter++;
				}
			}
		}
		
		return counter;
	}
	
	/**
	 * Gets the Number of attacks for this board
	 * @return
	 */
	public int getAttacks() {
		return this.attacks;
	}
	
	/**
	 * Gets the board for this Instance
	 * @return
	 */
	public int[] getBoard() {
		return this.board;
	}
	
	/**
	 * Stores the Generations solved at, Only for Genetic Algorithm
	 * @param gen
	 */
	public void setGeneration(int gen) {
		this.generations = gen;
	}
	
	public int getGenerations() {
		return this.generations;
	}

	@Override
	public int compareTo(Node other) {
		return this.attacks - other.attacks;
	}

}
