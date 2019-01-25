package structures;

/**
 * An example of a generic Queue class
 * 
 * @author Saaqeb
 */

public class Queue <T> {

	public Node <T> rear;
	private int size;
	
	/**
	 * Creates an empty Queue
	 */
	public Queue() {
		this.rear = null;
		this.size = 0;
	}
	
	/**
	 * Enqueues a new item into the queue
	 * 
	 * @param newItem to enqueue
	 */
	public void enqueue(T newItem) {
		if (rear == null) {
			rear = new Node <T> (newItem, null);
			rear.next = rear;
		} else {
			Node <T> tmpr = new Node <T> (newItem, rear.next);
			rear.next = tmpr;
			rear = tmpr;
		}
		size++;
	}
	
	/**
	 * Dequeues the first item in the queue
	 * 
	 * @return the data of the dequeued node
	 * @throws NullPointedException if the queue is empty
	 */
	public T dequeue() {
		if (rear == null) {
			throw new NullPointerException("Queue is empty");
		}
		T item = rear.data;
		if (rear == rear.next) {
			rear = null;
		} else {
			rear.next = rear.next.next;
		}
		size--;
		return item;
	}
	
	/**
	 * Traverses the queue, printing out everything in order
	 * 
	 * @throws NullPointedException if the queue is empty
	 */
	public void traverse() {
		if (rear == null) {
			throw new NullPointerException("Queue is empty");
		}
		Node <T> ptr = rear.next.next;
		System.out.print(rear.next.data);
		do {
			System.out.print(" --> " + ptr.data);
			ptr = ptr.next;
		} while (ptr != rear.next);
		System.out.println();
	}
	
	/**
	 * Clears the queue
	 */
	public void clear() {
		rear = null;
		size = 0;
	}
	
	/**
	 * Returns the size of the queue
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns whether the queue is empty or not
	 */
	public boolean isEmpty() {
		return size == 0;
	}

}

