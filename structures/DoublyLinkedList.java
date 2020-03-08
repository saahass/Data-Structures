package structures;

import java.util.NoSuchElementException;

/**
 * An example of a Doubly Linked List data structures
 * 
 * @author Saaqeb
 */

public class DoublyLinkedList <T> {

	/**
	 * An example of a generic Doubly Node
	 * 
	 * @class DoublyLinkedList
	 * @author Saaqeb
	 */

	protected static class DoublyNode<T>{
		
		public T data;
		public DoublyNode <T> next;
		public DoublyNode <T> prev;
		
		public DoublyNode(T data, DoublyNode <T> next, DoublyNode <T> prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		
		public DoublyNode(T data) {
			this(data,null,null);
		}
		
		public String toString() {
			return data.toString();
		}
	}
	
	
	public DoublyNode <T> front;
	private int size;
	
	/**
	 * Creates a doubly linked list
	 */
	public DoublyLinkedList() {
		this.front = null;
		this.size = 0;
	}
	
	/**
	 * Adds new item to the front of the linked list
	 * 
	 * @param newItem to add
	 */
	public void addFront (T newItem) {
		front = new DoublyNode <T> (newItem, front, null);
		size += 1;
	}
	
	/**
	 * Deletes the front of the linked list
	 * 
	 * @throws NoSuchElementException if linked list is empty
	 */
	public void deleteFront() {
		if (front == null) {
			throw new NoSuchElementException("Linked List is empty");
		}
		else {
			front = front.next;
			front.next.prev = null;
			size -= 1;
		}
	}
	
	/**
	 * Adds to the back of the linked list
	 * 
	 * @param newItem to add
	 */
	public void addBack (T newItem) {
		if (front == null) {
			front = new DoublyNode <T> (newItem);
		}
		else {
			DoublyNode <T> ptr = front;
			while (ptr.next != null) {
				ptr = ptr.next;
			}
			ptr.next = new DoublyNode <T> (newItem, front, ptr);
			size += 1;
		}
	}
	
	/**
	 * Deletes the targeted node
	 * 
	 * @param target node to delete
	 * @throws NoSuchElementException if linked list is empty
	 */
	public void deleteNode(T target) {
		if (front == null) {
			throw new NoSuchElementException("Linked List is empty");
		}
		DoublyNode <T> ptr = front, previous = null;
		while (ptr != null) {
			if (ptr.data.equals(target)) {
				break;
			}
			previous = ptr; ptr = ptr.next;
		}
		if (previous == null) {
			front = null;
		}
		previous.next = ptr.next;
		ptr.next.prev = previous;
		size--;
	}
	
	/**
	 * Replaces old data with new data
	 * 
	 * 
	 * @throws NoSuchElementException if linked list is empty
	 * @throws IllegalArgumentException if old data isn't in linked list
	 */
	public void replaceData(T oldData, T newData) {
		if (front == null) {
			throw new NoSuchElementException("list is empty");
		}
		for (DoublyNode <T> ptr = front; ptr != null; ptr = ptr.next) {
			if (ptr.data.equals(oldData)) {
				ptr.data = newData;
				return;
			}			
		}
		throw new 
		IllegalArgumentException("old data is not in the list");
		
	}
	
	/**
	 * Searches for whether or not a targeted node is present in 
	 * linked list. Does NOT return the the position of the targeted node
	 * 
	 * @param target to search for
	 * @throws NoSuchElementException if linked list is empty
	 */
	public boolean search(T target) {
		if (front == null) {
			throw new NoSuchElementException("Linked List is empty");
			// return false;
		}
		for (DoublyNode <T> ptr = front; ptr != null; ptr = ptr.next) {				
			if (ptr.data.equals(target)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Traverses the linked list
	 * 
	 * @throws NoSuchElementException if linked list is empty
	 */
	public void traverse() {
		if (front == null) {
			throw new NoSuchElementException("Linked List is empty");
		}
		for (DoublyNode <T> ptr = front; ptr != null; ptr = ptr.next) {
			System.out.print(" <--> " + ptr.data);
		}
		System.out.println("\");
	}
	
	/**
	 * Returns the size of the linked list
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Returns whether or not the linked list is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Clears the entire linked list
	 */
	public void clear() {
		front = null;
		size = 0;
	}
	
}
