
import java.util.Random;

/**
 * 
 */

/**
 * @author Tarik Rajper
 *
 */
public class SimulatedAnnealing {
	
	public SimulatedAnnealing() {
		
	}
	
	/**
	 * Takes in a Starting temperature and Board and stops only when Temp is below a certain value
	 * I Found this configurations to work the best for n=25; Still runs slow but solves a few of them
	 * Generates a random Succesor and if that value is not better than the current, I calc the prob of
	 * swapping current for that node
	 * Decay function = t * 0.99/0.80
	 * @param problem
	 * @param temp
	 * @return
	 */
	public Node SA(Node problem, double temp) {
		Node current = problem;
		Random rand = new Random();
		double t = temp;
		while (t < Integer.MAX_VALUE) {
			if (t < 0.000001 || current.getAttacks() == 0) {
				return current;
			}
			
			Node next = getRandomSuccessor(current);
			
			if (next.getAttacks() < current.getAttacks()) {
				current = next;
				t = t * 0.8;	//I found that a faster decay rate here leads to similar results with better execution time
				continue;
			}
			
			if (current.getAttacks() < next.getAttacks()) {
				double changeInEnergy = next.getAttacks() - current.getAttacks();
				double prob = Math.exp((changeInEnergy * -1)/t);
				float probOfChange = rand.nextFloat();
				if (probOfChange < prob) {
					current = next;
				}
			}
			t = t * 0.99;
		}
		return current;
	}
	
	/**
	 * Generates Random moves for all queens and randomly chooses one move from all of the generated moves
	 * @param current
	 * @return
	 */
	public Node getRandomSuccessor(Node current) {
		Node[] moves = new Node[current.getBoard().length];
		Random rand = new Random();
		for (int i = 0; i < moves.length; i++) {
			int[] board = current.getBoard().clone();
			int randomSwap = rand.nextInt(current.getBoard().length);
			board[randomSwap] = rand.nextInt(board.length);
			moves[i] = new Node(board);
		}
		int randomSuccessor = rand.nextInt(moves.length);
		return moves[randomSuccessor];
	}
}
