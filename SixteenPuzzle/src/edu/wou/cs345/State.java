/**
 * 
 */
package edu.wou.cs345;

import java.util.Arrays;

//import java.util.LinkedList;

/**
 * This class creates a state for the sixteen-puzzle problem.  
 * This class contains methods to check the state of the puzzle to see if solved
 * Also, to see if a move can/should be made and how to make a move.
 * 
 * @author Jessica Crow
 * @version CS 345 Lab 1 2014.04.22
 */
public class State{

	
	int[] arr; 
	int space;
	String moves;
	
	/**
	 * Constructor for first item in the queue
	 * First object has only an array, the space is found, and the moves are empty
	 * @param list new array containing the first position of elements
	 */
	State(int[] list){
		arr = list;
		space = getSpace(arr);
		moves = "";
		
	}
	
	/**
	 * Constructor used while program is running
	 * After a move has been made, a new object is created.
	 * @param list new array of int positions
	 * @param sp where the space is located
	 * @param mvs the order of moves the object has made
	 */
	State(int[] list, int sp, String mvs){
		arr = new int[16];
				for(int i = 0; i<16; i++){
						arr[i] = list[i];
				}
		space = sp;
		moves = mvs;
	}
	


	/**
	 * Checks state, if solved, print move list.
	 * If unsolved add to queue
	 * @param o checks to see if solved.
	 */
	public boolean checkState(State o){
		int[] testList = new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		if (Arrays.equals(o.arr, testList)){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * Checks to see if there is an up move.
	 * @param o enter object to check
	 * @return true if move can be made.
	 */
	public boolean canUp(State o){
		if (o.moves == "" && o.space > 3)
			return true;
		char last = o.moves.charAt(o.moves.length() - 1);
		if (last == 'D')
			return false;
		
		if (o.space >3)
			return true;
		
		else return false;
		
		}
	
		
	 /**
	 * Checks to see if there is an down move.
	 * @param o enter object to check
	 * @return true if move can be made.
	 */	public boolean canDown(State o){
		if (o.moves == "" && o.space < 12)
			return true;
		char last = o.moves.charAt(o.moves.length() - 1);
		if (last == 'U')
			return false;
		
		if (o.space <12)
			return true;
		
		else return false;
	}
	
		/**
		 * Checks to see if there is an right move.
		 * @param o enter object to check
		 * @return true if move can be made.
		 */
	public boolean canRight(State o){
		if (o.moves == "" && o.space % 4 !=3)
			return true;
		char last = o.moves.charAt(o.moves.length() - 1);
		if (last == 'L')
			return false;
		
		if (o.space % 4 !=3)
			return true;
		
		else return false;
	}
	
	/**
	 * Checks to see if there is an left move.
	 * @param o enter object to check
	 * @return true if move can be made.
	 */
	public boolean canLeft(State o){
		if (o.moves == "" && o.space % 4 != 0)
			return true;
		char last = o.moves.charAt(o.moves.length() - 1);
		if (last == 'R')
			return false;
		
		if (o.space % 4 != 0)
			return true;
		
		else {return false;}
	}
	
	/**
	 * Moves the space up one position and returns a new object
	 * @param o enter state to change
	 * @return returns new state after position move.
	 */
	public State moveUp(State o){
		int orgSpInx = o.space;//getSpace(o.arr); //get original space index
		int temp = o.arr[orgSpInx - 4]; //get value of what is in index to swap with
		o.arr[orgSpInx - 4] = 0;		//change value space index - 4 to 0, space
		o.arr[orgSpInx] = temp;			//move value of index -4 to original space index
		
		int newSpInx = o.space - 4;//getSpace(o.arr);
		String newMoves = (getMoves(o) + "U");
		State newState = new State (o.arr, newSpInx, newMoves);
	
		return newState;
	}
	
	/**
	 * Moves the space down one position and returns a new object
	 * @param o enter state to change
	 * @return returns new state after position move.
	 */
	public State moveDown(State o){
		int orgSpInx = getSpace(o.arr); //get original space index
		int temp = o.arr[orgSpInx + 4]; //get value of what is in index to swap with
		o.arr[orgSpInx + 4] = 0;		//change value space index + 4 to 0, space
		o.arr[orgSpInx] = temp;			//move value of index +4 to original space index
		
		int newSpInx = getSpace(o.arr);
		String newMoves = (getMoves(o) + "D");
		State newState = new State (o.arr, newSpInx, newMoves);
	
		return newState;
	}
	
	/**
	 * Moves the space right one position and returns a new object
	 * @param o enter state to change
	 * @return returns new state after position move.
	 */
	public State moveRight(State o){
		int orgSpInx = getSpace(o.arr); //get original space index
		int temp = o.arr[orgSpInx + 1]; //get value of what is in index to swap with
		o.arr[orgSpInx + 1] = 0;		//change value space index + 1 to 0, space
		o.arr[orgSpInx] = temp;			//move value of index +1 to original space index
		
		int newSpInx = getSpace(o.arr);
		String newMoves = (getMoves(o) + "R");
		State newState = new State (o.arr, newSpInx, newMoves);
	
		return newState;
	}
	
	/**
	 * Moves the space left one position and returns a new object
	 * @param o enter state to change
	 * @return returns new state after position move.
	 */
	public State moveLeft(State o){
		int orgSpInx = getSpace(o.arr); //get original space index
		int temp = o.arr[orgSpInx - 1]; //get value of what is in index to swap with
		o.arr[orgSpInx - 1] = 0;		//change value space index -1 to 0, space
		o.arr[orgSpInx] = temp;			//move value of index -1 to original space index
		
		int newSpInx = getSpace(o.arr);
		String newMoves = (getMoves(o) + "L");
		State newState = new State (o.arr, newSpInx, newMoves);
	
		return newState;
	}
	
	/**
	 * Returns a string of moves that have been made
	 * @param State enter state 
	 * @return print list of moves that have been made
	 */
	public void printMoves(State st){
		String str = getMoves(st);
		System.out.println(str);
	}
	
	/**
	 * Get a copy of the array
	 * @param st enter State object
	 * @return array of the current state
	 */
	public int[] getArray(State st){
		int[] temp = st.arr;
		return temp;
	}
	
	
	/**
	 * Returns array index of the space
	 * 
	 * @param array input current state array
	 * @return index of space
	 */
	public int getSpace(int[] array){
		int value = 0;
		int place = 0;
		for(int i=0; i < array.length; i++ ){
			if (array[i] == value){
				place= i;				//can change to break out.
			}
		}
		return place;
	}
	
	/**
	 * Returns string list of moves
	 * @param args
	 */
	public String getMoves(State o){
		return o.moves;
	}
	
	
	public static void main(String[] args) {

		
	}

}
