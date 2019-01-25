package structures;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Implementation of a stack class using an ArrayList
 * 
 * @author Saaqeb
 */

public class StackAL <T>{

	public ArrayList <T> items;
	
	/**
	 * Creates a stack with an initial capacity of 10
	 */
	public StackAL() {
		items = new ArrayList<T>(10);
	}
	
	/**
	 * Creates a stack with the provided user input capacity
	 * 
	 * @param capacity of the stack
	 */
	public StackAL(int capacity) {
		items = new ArrayList<T>(capacity);
	}
	
	/**
	 * Pushes a new item into the stack
	 * 
	 * @param newItem to push
	 */
	public void push(T newItem) {
		items.add(newItem);
	}
	
	/**
	 * Pops the topmost element of the stack
	 * 
	 * @return the deleted stack data
	 * @throws NoSuchElementException if stack is empty
	 */
	public T pop() throws NoSuchElementException {
		try {
			T data = items.get(size() - 1);
			items.remove(size() - 1);
			return data;
		}
		catch (NoSuchElementException e) {
			throw new NoSuchElementException("Stack is empty");
		}
	}
	
	/**
	 * Traverses the entire stack
	 * 
	 * @throws NullPointerException if the stack is empty
	 */
	public void traverse() {
		if (items.isEmpty()) {
			throw new NullPointerException("Stack is empty");
		}
		for (int layer = size(); layer > 0; layer--) {
			System.out.println(layer + ": " + items.get(layer-1));
		}
	}
	
	/**
	 * Returns whether or not the stack is empty
	 */
	public boolean isEmpty() {
		return items.isEmpty();
	}
	
	/**
	 * Returns the size of the stack
	 */
	public int size() {
		return items.size();
	}
	
	/**
	 * Clears the entire stack
	 */
	public void clear() {
		items.clear();
	}
	
}
