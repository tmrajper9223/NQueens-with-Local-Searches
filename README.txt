N-Queens
Generate A Board Where There No Queens Can Attack Each Other Using A Genetic Algorithm or Simulated Annealing.

Execute Program
-Give Execution Permissions to start_program.sh file -> chmod +x start_program.sh

Running From the Command Line:
	Switch To the Directory of the Project
	Run this command "javac Main.java Node.java GeneticAlgorithm.java SimulatedAnnealing.java"
	Run "java Main"

You Will Be Prompted to Enter a Choice 1, 2, or 3
1: Genetic Algorithm- Will Prompt You to Enter a Mutation Rate between 0 and 1. In this Implementation Higher Mutation Rate(0.5-0.9) leads to a much
	faster execution Time. A lower Mutation Rate (0.1-0.5) Will lead to a higher Execution Time. Allow 5-10 seconds to run.

2: Simulated Annealing: Will Immediately run once entered. The Decay Function is temp = temp * 0.99 and the starting temp is 10000.
	This algorithm may or may not solve all of the 400 instances. Allow 5-20 seconds to run.

3: Exits Program

Output:
Both Outputs display the nxn board configuration and the positions of the queen.

Board Implementation: [1, 2, 3, 4] ---> Q x x x x
					   x Q x x x
					   x x Q x x
					   x x x Q x
					   x x x x Q

Genetic: Displays At which generation the board solved, Time it took to solve all 400 instances

Simulated Annealing: Displays The time it took to solve 400 instances. By solve I mean complete, May be 1-2 Attacks left on Board.
	
