/**
 * 
 */
package edu.wou.cs260.SpellChecker;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * @author Jessica Crow
 * 
 */
public class DLList<E> implements List<E>, CompareCount, Queue<E> {

	// Fields
	 protected int size;
	private DLLNode firstNode;
	private DLLNode lastNode;
	protected int count = 0;

	// Constructor
	DLList() {
		size = 0;
		firstNode = null;
		lastNode = null;
	}

	// Innner class for nodes
	class DLLNode {
		// fields
		DLLNode prev;
		E data;
		DLLNode next;

		// methods
		// 3 constructors
		DLLNode() {
			this(null, null, null);
		}

		DLLNode(E d) {
			this(null, d, null);
		}

		DLLNode(DLLNode p, E d, DLLNode n) {
			prev = p;
			data = d;
			next = n;
		}
	}

	// node methods

	private DLLNode getNode(int arg0) {
		if (isEmpty()) {
			return null;
		}

		if (arg0 > size) {
			return null;
		}

		int i = 0;
		DLLNode node = firstNode;

		while (i < arg0) {
			node = node.next;
			i++;
		}

		return node;
	}

	private DLLNode getNode(Object arg0) {
		if (isEmpty()) {
			return null;
		}

		int i = 0;
		DLLNode node = firstNode;

		while (i < size()) {
			if (node.data.equals(arg0)) {
				return node;
			}

			else {
				node = node.next;
				i++;
			}
		}
		return null;
	}

	/**
	 * inner class for Iterator
	 */
	class DLLIterator implements Iterator<E> {

		DLLNode current = firstNode;

		@Override
		public boolean hasNext() {

			if (current != null) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public E next() {

			if (current != null) {
				E result = current.data;
				current = current.next;
				return result;
			} else {
				return null;
			}
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

	}

	

	@Override
	public boolean add(E arg0) {
		DLLNode node = new DLLNode(arg0);

		if (arg0 == null) {
			throw new NullPointerException();
		}

		if (isEmpty()) {
			firstNode = lastNode = node;
			size++;
		} else {
			lastNode.next = node;
			node.prev = lastNode;
			lastNode = node;
			size++;
		}

		return true;
	}

	@Override
	public void add(int arg0, E arg1) {

		if (arg1 == null) {
			throw new NullPointerException();
		}

		if (size == arg0) {
			add(arg1);
		}

		else if (size < arg0 || arg0 < 0) {
			throw new IndexOutOfBoundsException("int size too big");
		}

		else if (size > arg0) {
			DLLNode newNode = new DLLNode(arg1);
			DLLNode existingNode = getNode(arg0);

			if (existingNode.prev != null) {
				existingNode.prev.next = newNode;
			}
			newNode.prev = existingNode.prev;

			if (existingNode.next != null) {
				existingNode.next.prev = newNode;
			}
			newNode.next = existingNode;

			if (newNode != null) {
				existingNode.prev = newNode;
			}

			if (arg0 == 0) {
				firstNode = newNode;
			}
			size++;

		}

	}

	@Override
	public void clear() {
		firstNode = null;
		lastNode = null;
		size = 0;

	}

	@Override
	public boolean contains(Object arg0) {

		count++;
		if (arg0 == null) {
			throw new NullPointerException();
			
		} else if (getNode(arg0) == null) {
			return false;
			
		} else {
			return true;
		}
	}

	@Override
	public E get(int arg0) {
		DLLNode node = getNode(arg0);

		if (node == null) {
			throw new IndexOutOfBoundsException();
		}
		if (size < arg0 || arg0 < 0) {
			throw new IndexOutOfBoundsException("int size not valid");
		}

		return node.data;
	}

	public E get(Object arg1) {
		DLLNode node = getNode(arg1);

		if (node == null) {

			return null;
		}

		return node.data;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public Iterator<E> iterator() {

		return new DLLIterator();
	}

	@Override
	public boolean remove(Object arg0) {

		DLLNode node = getNode(arg0);

		if (arg0 == null) {
			throw new NullPointerException();
		}

		if (contains(arg0) == false) {
			return false;
		}

		if (node.prev != null) {
			node.prev.next = node.next;
			System.out.println("node removed" + node.data);
			size--;
			return true;
		} else {
			node.prev = null;
			firstNode = node.next;
		}

		if (node.next != null) {
			node.next.prev = node.prev;
			node.next = node.prev;
			size--;
			return true;
		} else {
			node.next = null;
			lastNode = node.prev;
		}
		size--;
		return true;

	}

	@Override
	public E remove(int arg0) {
		DLLNode node = getNode(arg0);

		if (node == null) {
			throw new IndexOutOfBoundsException();
		}

		if ((arg0 > size) || (arg0 < 0)) {
			throw new IndexOutOfBoundsException();
		}

		if (node.prev != null) {
			node.prev.next = node.next;
			System.out.println("removed: " + node.data);
			size--;
			return node.data;
		} else {
			node.prev = null;
			firstNode = node.next;
		}

		if (node.next != null) {
			node.next.prev = node.prev;
			size--;
			return node.data;
		} else {
			node.next = null;
			lastNode = node.prev;
		}
		size--;
		return node.data;

	}

	@Override
	public int size() {

		return size;
	}

	// ********************queue
	// methods***********************************************
	@Override
	public E element() {

		DLLNode node = firstNode;

		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		if (node.prev == null) {
			return firstNode.data;
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public boolean offer(E arg0) {

		add(arg0);
		return true;
	}

	@Override
	public E peek() {
		DLLNode node = firstNode;

		if (isEmpty() == true) {
			return null;
		}

		if (node.prev == null) {
			return firstNode.data;
		} else
			return null;
	}

	@Override
	public E poll() {

		if (isEmpty()) {
			return null;
		}

		DLLNode node = firstNode;
		remove();
		return node.data;
	}

	@Override
	public E remove() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		DLLNode node = getNode(0);
		remove(0);
		return node.data;
	}

	// **************all other methods **********************************

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends E> arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int indexOf(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E set(int arg0, E arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> subList(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLastCompareCount() {
		return count;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
