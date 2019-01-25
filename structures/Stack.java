package structures;

/**
 * An example of a Stack using Nodes
 * 
 * @author Saaqeb
 */

public class Stack <T> {

	public Node <T> front;
	private int size;
	
	/**
	 * Creates an empty stack
	 */
	public Stack() {
		this.front = null;
		this.size = 0;
	}
	
	/**
	 * Pushes a new item into the stack
	 * 
	 * @param newItem to push
	 */
	public void push(T newItem) {
		front = new Node <T> (newItem, front);
		size += 1;
	}
	
	/**
	 * Pops the top most item from the stack
	 * 
	 * @return the data of the popped node
	 * @throws NullPointerException if the stack is empty
	 */
	public T pop() {
		if (front == null) {
			throw new NullPointerException("Stack is empty");
		}
		T item = front.data;
		front = front.next;
		size -= 1;
		return item;
	}
	
	/**
	 * Traverses the entire stack 
	 * 
	 * @throws NullPointerException if the stack is empty
	 */
	public void traverse() {
		if (front == null) {
			throw new NullPointerException("Stack is empty");
		}
		int layer = this.size;
		for (Node <T> ptr = front; ptr != null; ptr = ptr.next) {
			System.out.println(layer + ": " + ptr.data);
			layer -= 1;
		}
	}
	
	/**
	 * Returns whether or not the stack is empty
	 */
	public boolean isEmpty(){
		return size == 0;
	}
	
	/**
	 * Returns the size of the stack
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Clears the entire stack
	 */
	public void clear() {
		front = null;
		size = 0;
	}
	
}
