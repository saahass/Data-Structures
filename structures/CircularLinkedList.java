package structures;

import java.util.NoSuchElementException;

/**
 * An example of a generic Circular Linked List
 * 
 * @author Saaqeb
 */

public class CircularLinkedList <T>{

	public Node <T> tail;
	private int size;
	
	/**
	 * Creates a new circular linked list
	 */
	public CircularLinkedList() {
		tail = null;
		size = 0;
	}
	
	/**
	 * Adds a new item to front of linked list
	 * 
	 * @param newItem to add at the front of the linked list
	 */
	public void addFront (T newItem) {
		Node <T> newNode = new Node <T> (newItem, null);
		if (tail == null) {
			newNode.next = newNode;
			size += 1;
		} else {
			newNode.next = tail.next;
			tail.next = newNode;
			size += 1;
		}
	}
	
	/**
	 * Deletes the first element (index 0) of linked list
	 * 
	 * @throws NoSuchElementException if linked list is empty
	 * @return the deleted data
	 */
	public T deleteFront() {
		if (tail == null) {
			throw new NoSuchElementException("Linked List is empty");
		}
		else if (tail == tail.next) {
			T item = tail.data;
			tail = null;
			return item;
		}
		else {
			T item = tail.next.data;
			tail.next = tail.next.next;
			return item;
		}
	}
	
	/**
	 * Deletes the first occurrence of a target in a linked list
	 * 
	 * @param target to be found
	 * @throws NoSuchElementException if linked list is empty
	 */
	public void deleteNode(T target) {
		if (tail == null) {
			throw new NoSuchElementException("Linked List is empty");
		}
		Node <T> ptr = tail, prev = null;
		while (ptr != tail.next) {
			if (ptr.data.equals(target)) { break; }
			prev = ptr; ptr = ptr.next;
		}
		if (prev == null) {
			tail = null;
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
		if (tail == null) {
			throw new NoSuchElementException("Linked List is empty");
		}
		for (Node <T> ptr = tail; ptr != tail.next; ptr = ptr.next) {
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
		if (tail == null) {
			throw new NoSuchElementException("Linked List is empty");
		}
		for (Node <T> ptr = tail; ptr != tail.next; ptr = ptr.next) {
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
		if (tail == null) {
			throw new NoSuchElementException("Linked List is empty");
		}
		Node <T> ptr = tail.next.next;
		System.out.print(tail.next.data);
		do {
			System.out.print("-->" + ptr.data);
			ptr = ptr.next;
		}while (ptr != tail.next);
		System.out.println();
	}
	
	/**
	 * Getter method for the size of the linked list
	 * 
	 * @return Size of the linked list
	 */
	public int getSize() {
		return this.size;
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
		tail = null;
		size = 0;
	}
	
}
