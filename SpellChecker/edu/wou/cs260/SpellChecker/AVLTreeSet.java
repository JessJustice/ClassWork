
package edu.wou.cs260.SpellChecker;

import edu.wou.cs260.SpellChecker.BSTreeSet;
import java.math.*;
/**
 * @author Jessica Crow
 *
 */

public class AVLTreeSet<T extends Comparable<T>> extends BSTreeSet<T> implements CompareCount {

	/**
	 * @param args
	 */


	
	@Override
	protected Node addHelper(Node subTree, T element) {
		if (subTree == null) {
			size++;
			return new Node(element);
		}
		// go left
		else if (subTree.item.compareTo(element) > 0) {
			subTree.lChild = addHelper(subTree.lChild, element);
		}
		// go right
		else {
			subTree.rChild = addHelper(subTree.rChild, element);
		}
		subTree = balance(subTree);
		return subTree;
	} 
	
	/*
	 * Gets height of current node.
	 * @param curr current node being analyzed
	 * @return returns the height of the current node.
	 */
	private int getHeight( Node curr) {
		return (curr == null)?-1 : curr.height;
		}
	
	/*
	 * Fixes the height of the nodes.
	 * @param curr node to begin with to fix height value
	 */
	private void fixHeight( Node curr) {
		int lch = -1, rch = -1;
		if (curr.lChild != null){
			lch = curr.lChild.height;}
		if (curr.rChild != null){
			rch = curr.rChild.height;}
		curr.height = Math.max(lch,  rch)+1;
	
	}
	
	/*
	 * Retrieved balance value.
	 * @param curr node to be evaluated for balance value
	 * @return returns balance value of curr
	 */
	private int getBalanceVal( Node curr) {
		return getHeight(curr.rChild) - getHeight(curr.lChild);
	}
	
	/*
	 * Rotates portion of AVL tree to right one time.
	 * @param curr Node at which the tree rotates.
	 * @return returns new node in the previous position of curr
	 */
	private Node singleRotateRight( Node curr) {
		Node temp = curr.lChild;
		
		curr.lChild = temp.rChild;
		temp.rChild = curr; //was curr.rChild
		
	
		fixHeight(curr);
		fixHeight(temp);
		return temp;
	}
	
	/*
	 * Rotates portion of AVL tree to left one time.
	 * @param curr Node at which the tree rotates.
	 * @return returns new node in the previous position of curr
	 */
	private Node singleRotateLeft( Node curr) {
		Node temp = curr.rChild;
		
		curr.rChild = temp.lChild;
		temp.lChild = curr;
		
		fixHeight(curr);
		fixHeight(temp);
		return temp;
	}
	
	/*
	 * Rotates portion of AVL tree to right one time, then left.
	 * @param curr Node at which the tree rotates.
	 * @return returns new node in the previous position of curr
	 */
	private Node doubleRotateRight( Node curr) {
		curr.lChild = singleRotateLeft(curr.lChild);
		
		return singleRotateRight(curr);
	}
	
	/*
	 * Rotates portion of AVL tree to left one time, then right.
	 * @param curr Node at which the tree rotates.
	 * @return returns new node in the previous position of curr
	 */
	private Node doubleRotateLeft( Node curr) {
		curr.rChild = singleRotateRight(curr.rChild);
		return singleRotateLeft(curr);
	}
	
	/*
	 * Tests and balances tree if needed.
	 * @param curr Node where testing begins to see if balance is needed.
	 * @return returns curr unchanged, or changed if rotation required
	 */
	private Node balance( Node curr) {
		//int balValue = getBalanceVal(curr);
		if (getBalanceVal(curr)<2 && getBalanceVal(curr)> -2){
			fixHeight(curr);
			return curr;
		}
		else if(getBalanceVal(curr) < -1){ //was -2
			if (getBalanceVal(curr.lChild) <= 0){ //was >1
				return singleRotateRight(curr);
			}
			else{
				return doubleRotateRight(curr);
			}
		}
		else{
			if(getBalanceVal(curr.rChild) >= 0){
		
				return singleRotateLeft(curr);
			}
		else{
			return doubleRotateLeft(curr);
			}
		}
	}

	public static void main(String[] args) {
	}
}
