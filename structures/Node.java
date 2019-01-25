package structures;

/**
 * An example of a generic Node class
 * 
 * @class LinkedList
 * @class CircularLinkedList
 * @class Stack 
 * @class Queue
 * 
 * @author Saaqeb
 */

public class Node <T>{
	
	public T data;
	public Node <T> next;
	
	public Node(T data, Node <T> next){
		this.data = data;
		this.next = next;
	}
	
	public String toString() {
		return data.toString();
	}
	
}
