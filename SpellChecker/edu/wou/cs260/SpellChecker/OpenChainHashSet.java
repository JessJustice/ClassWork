/**
 * 
 */
package edu.wou.cs260.SpellChecker;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import edu.wou.cs260.SpellChecker.DLList;

/**
 * @author Jessica Crow
 * @version Lab 5 December 8, 2013
 *
 */
public class OpenChainHashSet<E> implements Set<E>, CompareCount{

	private int tableSize = 150001;
	private int size = 0;
	private DLList<E>[] hashTable;
	private int count = 0;
	
	
	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	OpenChainHashSet(){
		hashTable = (DLList<E>[]) new DLList [tableSize];
	}
	
	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	OpenChainHashSet(int tableSize){
		this.tableSize = tableSize;
		hashTable = (DLList<E>[]) new DLList [tableSize];
	}
	
	/**
	 * inner class for Iterator
	 */
	class HashIterator implements Iterator<E> {
		
		int index = 0;
		Iterator<E> DLLit = hashTable[index].iterator();

		@Override
		public boolean hasNext() {
			return (DLLit.hasNext() || index +1 < tableSize );
		}

		@Override
		public E next() {
			if ( DLLit.hasNext() == true){
				
				return DLLit.next();
			}
			else{
				index++;
				DLLit = hashTable[index].iterator();
				return next();
			}
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
	}
	
	public int findHash(E item){
		int index = Math.abs( item.hashCode()) % tableSize;
		return index;
	}

	@Override
	public int getLastCompareCount() {
		return count;
	}

	@Override
	public boolean add(E arg0) {

		int index = findHash(arg0);
		if (hashTable[ index] == null){
			hashTable[index] = new DLList<E>();
			size++;
			return hashTable[index].add(arg0);
			
		}
		else {
			if(hashTable[index].contains(arg0)== true){
				return false;
			}
			else{
			size++;
			return hashTable[index].add(arg0);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		size = 0;
		hashTable = (DLList<E>[]) new DLList [tableSize];
		
		
	}

	@Override
	public boolean contains(Object arg0) {
		@SuppressWarnings("unchecked")
		
		int index = findHash((E) arg0);
		count ++;
		if( hashTable[index] == null){
			return false;
		}
		
		else if(hashTable[index].contains(arg0)== true){
			//check to see if in chain use dllist contains
			return true;
		}
		else{ return false;}
	}

	@Override
	public boolean isEmpty() {
		if (size == 0){
			return true;
		}
		else{
		return false;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new HashIterator();
	}
	

	@Override
	public int size() {
		return size;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object arg0) {
		
		int index = findHash((E) arg0);
		if (hashTable[index] == null){
			return false;
		}
		
		else{
			//if only one set to null, if more than one just remove
			if(hashTable[index].contains(arg0) == true){
				hashTable[index].remove(arg0);
				size--;
				
				if(hashTable[index].isEmpty() == true){
					hashTable[index] = null;
					return true;
				}
				else return true;
			}
			else return false;
		}
	}
	
	//Other methods<<<<<<<<<<<<<<<<<<<<

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	


	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
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
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OpenChainHashSet<Integer> testSet = new OpenChainHashSet<Integer>( 7);
		testSet.add(5);
		//System.out.println(size);

	}

}
