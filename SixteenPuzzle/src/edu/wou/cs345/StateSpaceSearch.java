/**
 * 
 */
package edu.wou.cs345;


import java.util.Scanner;


/**
 * @author Jessica
 *
 */
public class StateSpaceSearch {
	
	//public State state;
	@SuppressWarnings("rawtypes")
	public Queue queue;

	/**
	 * Main program
	 * A queue is entered with a beginning state and all moves are checked, 
	 * if applicable all moves are made and a new state is created and placed
	 * in the queue. The loop runs until the problem is solved or there is 
	 * nothing left in the queue
	 * 
	 * @author Jessica Crow
	 * @version CS 345 Lab 1 2014.04.22
	 * @param queue with a beginning state.
	 */
	public static  void solvePuzzle(Queue<State> queue){
		while( queue.isEmpty() == false){
			
		State state = (State) queue.remove();
		int[] nar = state.getArray(state);
		int nsp = state.getSpace(state.arr);
		String nst = state.getMoves(state);
		
		if (state.checkState(state)== true){
		System.out.println(state.getMoves(state));
	}
		
			if (state.canUp(state) == true){
				//State tempState = (State) state.clone();
				State tempState = new State(nar, nsp, nst);
				State newUState = tempState.moveUp(tempState);
				if (newUState.checkState(newUState) == true){
					System.out.println(newUState.moves);
					break;
				}
				else queue.add(newUState);
				
			}
			if (state.canDown(state) == true){
				State tempState2 = new State(nar,nsp, nst);
				State newDState = tempState2.moveDown(tempState2);
				if (newDState.checkState(newDState) == true){
					System.out.println((newDState.moves));
					break;
				}
				else queue.add(newDState);
			}
			
			if (state.canRight(state) == true){
				State tempState3 = new State(nar, nsp, nst);
				State newRState = tempState3.moveRight(tempState3);
				if (newRState.checkState(newRState) == true){
					System.out.println(newRState.moves);
					break;
				}
				else queue.add(newRState);
				
			}
			if (state.canLeft(state) == true){
				State tempState4 = new State(nar, nsp, nst);
				State newLState = tempState4.moveLeft(state);
				if (newLState.checkState(newLState) == true){
					System.out.println(newLState.moves);
					break;
				}
				else queue.add(newLState);

			}
		}
		}

		

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] arr = new int[16];
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter number sequence for puzzle only leaving a space between numbers. \n"  +
				"List cannot start with zero:");

		for(int i = 0; i<16; i++){
			int in = scan.nextInt();
			arr[i] = in;
		}
		Queue<State> queue = new Queue<State>();
		
		State something = new State(arr);
		
		queue.add(something);
		solvePuzzle(queue);
	}


	

}
