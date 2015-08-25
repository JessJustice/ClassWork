/**
 * 
 */
package edu.wou.cs260.SpellChecker;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;


/**
 * @author Jessica Crow
 * 
 */
public class BSTreeSet<T extends Comparable<T>> implements Set<T>, CompareCount {

	// fields
	protected Node root = null;
	protected int size = 0;
	protected int count;

	// <<<<<<<<<<<<<<<<<<<inner node class>>>>>>>>>>>>>>>>>>>>>>
	class Node {
		// fields
		T item;
		int height;
		Node lChild;
		Node rChild;

		// methods

		// 3 constructors
		Node() {
			this(null, null, null);
		}

		Node(T item) {
			this(null, item, null);
		}

		Node(Node lChild, T item, Node rChild) {
			this.lChild = lChild;
			this.item = item;
			this.rChild = rChild;
			height = 0;
		}
	}

	// <<<<<<<<<<<Inner class iterator>>>>>>>>>>>>>>
	class BSTreeIterator implements Iterator<T> {

		Queue< Node> bstreeQueue;
		
		public BSTreeIterator(){
			bstreeQueue = new DLList<Node>();
			bstreeQueue.add(root);
			
		}
		@Override
		public boolean hasNext() {
			
			return !bstreeQueue.isEmpty();
		}

		@Override
		public T next() {
			
			if (bstreeQueue.isEmpty()) {
				return null;
			}			
			
			Node temp = bstreeQueue.remove();
			
			if (temp.lChild != null) {
				bstreeQueue.add(temp.lChild);
			}
			if (temp.rChild != null) {
				bstreeQueue.add(temp.rChild);
			}
			System.out.println( temp.item);
			return temp.item;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}
	}

	@Override
	public boolean add(T e) {
		count++;
		if (e == null) {
			throw new NullPointerException("Null item not allowed");
		}

		root = addHelper(root, e);
		
		count++;
		if (root == null) {
			return false;
		} else {
			return true;
		}
	}

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
		return subTree;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return false;
	}

	@Override
	public void clear() {
		size = 0;
		root = null;

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object o) {
		count++;
		if (o == null) {
			throw new NullPointerException();
		}
		return containsHelper(root, (T) o);
	}

	protected boolean containsHelper(Node subTree, T element) {
		if (subTree == null) {
			return false;
		} else if (subTree.item.equals(element)) {
			return true;
		}

		// go left
		else if (subTree.item.compareTo(element) > 0) {
			{
				return containsHelper(subTree.lChild, element);
			}
		}
		// go right
		else {
			return containsHelper(subTree.rChild, element);
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		if (root == null) {
			return true;
		} else
			return false;
	}

	@Override
	public Iterator<T> iterator() {
		return new BSTreeIterator();
	}
	
	@Override
	public int getLastCompareCount() {
		return count;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
	}	

}
