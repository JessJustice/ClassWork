package edu.wou.cs345;

import java.util.LinkedList;

/**
 * This class is part of the sixteen puzzle program.
 * This class creates and adds functionality for the queue that is
 * needed to keep track of the state objects.
 * 
 * @author Jessica Crow
 * @version CS 345 Lab 1 2014.04.22
 *
 * @param <T> input state object
 */
public class Queue<T> {
	
	@SuppressWarnings("rawtypes")
	public LinkedList<T> queue;// = new LinkedList<Object>();
	
	/**
	 * Queue constructor
	 */
	Queue(){
		 queue = new LinkedList<T>();
	}

	/**
	 * Adds objects to the end of the queue
	 * @param e Object to add
	 */
	public void add(T e){
		queue.add(e);
	}	
	
	/**
	 * Removes objects from queue
	 * @return returns the object in first position of queue
	 */
	public Object remove(){
		Object o = queue.removeFirst();
		return o;
	}
	
	/**
	 * returns the total number of objects in the queue
	 * @return size of queue
	 */
	public int size(){
		int i = queue.size();
		return i;
	}
	
	/**
	 * Checks to see if the queue is empty
	 * @return true if queue is empty, false if not.
	 */
	public boolean isEmpty(){
		if (queue.size() == 0)
			return true;
		else return false;
	}

}
