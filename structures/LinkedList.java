package structures;

import java.util.NoSuchElementException;

/**
 * An example of a generic linked list class
 * 
 * @author Saaqeb
 */

public class LinkedList <T extends Comparable<T>>{

	public Node <T> front;
	private int size;
	
	/**
	 * Creates an empty Linked List
	 */
	public LinkedList() {
		this.front = null;
		this.size = 0;
	}
	
	/**
	 * Adds item to front of linked list
	 * 
	 * @param item to add at the front of the linked list
	 */
	public void addFront(T item) {
		front = new Node <T> (item, front);
		size += 1;
	}
	
	/**
	 * Adds data before the target to the linked list
	 * 
	 * 
	 * @param target to add before
	 * @param data to add to the linked list
	 */
	public void addBefore(T target, T data) {
		if (front == null) {
			front = new Node<T>(data,null);
			size++; return;
		}
		Node<T> ptr = front, prev = null;
		while (ptr != null) {
			if (ptr.data.equals(target)) {
				break;
			}
			prev = ptr;
			ptr = ptr.next;
		}
		if (prev == null) {
			front = new Node<T>(data,front);
			size++; return;
		}
		prev.next = new Node<T>(data,ptr);
		size++;
	}
	
	/**
	 * Adds an item to the second before last position on the linked list 
	 * 
	 * @param item to add to the linked list
	 */
	public void addBeforeLast(T item) {
		if (front == null) {
			front = new Node<T>(item,null);
			size++; return;
		}
		Node<T> ptr = front, prev = null;
		while (ptr.next != null) {
			prev = ptr; ptr = ptr.next;
		}
		if (prev == null) {
			front = new Node<T>(item,front);
			size++; return;
		}
		prev.next = new Node<T>(item,ptr);
		size++;
	}
	
	/**
	 * Returns the number of occurrences of a target in a linked list
	 * 
	 * @param target to be found
	 * @return Number of occurrences
	 * @throws NoSuchElementException if linked list is empty
	 */
	public int numberOfOccurences(T target) {
		if (front == null) {
			throw new NoSuchElementException("Linked List is empty");
		}
		Node<T> ptr = front;
		int count = 0;
		while (ptr != null) {
			if (ptr.data.equals(target)) 
				count++;
		}
		return count;
	}
	
	/**
	 * Deletes every other node in the linked list starting with 
	 * the second (index 1) node
	 * 
	 * @throws NoSuchElementException if linked list is empty
	 */
	public void deleteEveryOther() {
		if (front == null) {
			throw new NoSuchElementException("Linked List is empty");
		}
		int count = 1;
		Node<T> ptr = front, prev = null;
		while (ptr != null) {
			if (count % 2 == 0) {
				prev.next = ptr.next;
				ptr= ptr.next; 
				size--; continue;
			}
			prev = ptr; ptr = ptr.next;
			count += 1;
		}
	}
	
	/**
	 * Deletes all occurrences of a target in a linked list
	 * 
	 * @param target to be found
	 * @throws NoSuchElementException if linked list is empty
	 */
	public void deleteAllOccurences(T target) {
		if (front == null) {
			throw new NoSuchElementException("Linked List is empty");
		}
		while (front.data.equals(target)) {
			front = front.next;
		}
		Node<T> ptr = front, prev = null;
		while (ptr != null) {
			if (ptr.data.equals(target)) {
				prev.next = ptr.next;
				ptr = ptr.next; 
				size--; continue;
			}
			prev = ptr; ptr = ptr.next;
		}
	}
	
	/**
	 * Deletes the first element (index 0) of linked list
	 * 
	 * @throws NoSuchElementException if linked list is empty
	 * @return the deleted data
	 */
	public T deleteFront() {
		if (front == null) {
			throw new NoSuchElementException("Linked List is empty");
		}
		T item = front.data;
		front = front.next;
		size -= 1;
		return item;
	}
	
	/**
	 * Adds a new item to the back of the linked list
	 * 
	 * @param newItem to add
	 */
	public void addBack (T newItem) {
		if (front == null) {
			front = new Node <T> (newItem, front);
		}
		Node <T> ptr = front;
		while (ptr.next != null) {
			ptr = ptr.next;
		}
		ptr.next = new Node <T> (newItem, front);
		size += 1;
	}
	
	/**
	 * Deletes the first occurrence of a target in a linked list
	 * 
	 * @param target to be found
	 * @throws NoSuchElementException if linked list is empty
	 */
	public void deleteNode(T target) {
		if (front == null) {
			throw new NoSuchElementException("Linked List is empty");
		}
		Node <T> ptr = front, prev = null;
		while (ptr != null) {
			if (ptr.data.equals(target)) {
				break;
			}
			prev = ptr; ptr = ptr.next;
		}
		if (prev == null) {
			front = null;
		}
		prev.next = ptr.next;
		size -= 1;
	}
	
	
	/**
	 * Replaces old data with new data in linked list
	 * 
	 * @param oldData to be replaced
	 * @param newData to be added
	 * @throws NoSuchElementException if linked list is empty
	 * @throws IllegalArgumentException if the old is not in linked list
	 */
	public void replaceData(T oldData, T newData) {
		if (front == null) {
			throw new NoSuchElementException("Linked List is empty");
		}
		for (Node <T> ptr = front; 
				ptr != null; ptr = ptr.next) {
			if (ptr.data.equals(oldData)) {
				ptr.data = newData;
				return;
			}
		}
		throw new IllegalArgumentException("Original data is not "
					+ "in the Linked List");
	}
	
	/**
	 * Searches for target in the linked list
	 * 
	 * @throws NoSuchElementException if linked list is empty
	 * @return boolean based on if the target is in the linked list
	 */
	public boolean search(T target) {
		if (front == null) {
			throw new NoSuchElementException("Linked List is empty");
		}
		for (Node <T> ptr = front; 
				ptr != null; ptr = ptr.next) {
			if (ptr.data.equals(target)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Traverse through the linked list, printing every single element
	 * 
	 * @throws NoSuchElementException if linked list is empty
	 */
	public void traverse() {
		if (front == null) {
			throw new NoSuchElementException("Linked List is empty");
		}
		for (Node<T> ptr = front;ptr != null; ptr = ptr.next) {
			System.out.print(ptr.data + " --> ");
		}
		System.out.println("\");
	}
	
	/**
	 * Getter method for the size of the linked list
	 * 
	 * @return Size of the linked list
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Checks if the linked list is empty
	 * 
	 * @return boolean based on the size of the linked list
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Clears the linked list
	 */
	public void clear() {
		front = null;
		size = 0;
	}
	
}
