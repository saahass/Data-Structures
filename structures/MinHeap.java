package structures;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Generic MinHeap data structure implementation
 * 
 * @author Saaqeb
 */
public class MinHeap<T extends Comparable<T>> {

	private ArrayList<T> items;
	
	/**
	 * Standard capacity of Heap set to 10
	 */
	public MinHeap() {
		items = new ArrayList<T>(10);
	}
	
	/**
	 * User specific capacity of Heap
	 * 
	 * @param capacity of heap
	 */
	public MinHeap(int capacity) {
		items = new ArrayList<T>(capacity);
	}
	
	/**
	 * Sift up on Min Heap
	 * 
	 * @param k sift up starting at index k
	 */
	public void siftup(int k) {
		while (k > 0) {
			int p = (k-1)/2;
			T parent = items.get(p), child = items.get(k);
			int check = parent.compareTo(child);
			if (check <= 0) { break; }
			items.set(p, child);
			items.set(k, parent);
			k = p;
		}
	}
	
	/**
	 * Sift down on Min Heap
	 * 
	 * @param k sift down starting at index k
	 */
	public void siftdown(int k) {				
		while (2*k+1 < items.size()) {		// parent has a left child
			int leftchild = 2*k+1, rightchild = 2*k+2;
			T parent = items.get(k);
			int check = leftchild;
			if (rightchild < items.size()) {		// right child exist ?
				// left child > right child ?
				check = items.get(leftchild).compareTo(items.get(rightchild));
				check = check > 0 ? rightchild : leftchild;
			}
			T smallerChild = items.get(check);	
			// parent smaller than child ?
			int c = parent.compareTo(smallerChild);
			if (c <= 0) { break; }		// parent is smaller than children
			// swap parent and smaller child
			items.set(check, parent);
			items.set(k, smallerChild);
			k = check; 			// update k to the correct index after swap
		}
	}
	
	/**
	 * Insert new data into heap
	 * 
	 * @param data to insert in heap
	 */
	public void insert(T data) {
		items.add(data);
		siftup(items.size()-1);
	}
	
	/**
	 * Deletes the smallest value of the Heap
	 * 
	 * @return smallest data in heap
	 * @throws NoSuchElementException if heap is empty
	 */
	public T deleteMin() {
		if (items.isEmpty()) { 
			throw new NoSuchElementException("heap is empty"); 
		}
		T data = items.get(0);
		if (items.size() == 1) {
			items.clear();
		} 
		else {
			items.set(0, items.get(items.size()-1));
			siftdown(0);
		}
		return data;
	}
	
	/** 
	 * Returns the max element without deleting
	 * 
	 * @return smallest data in heap without deleting 
	 * @throws NoSuchElementException if heap is empty
	 */
	public T getMin() {
		if (items.isEmpty()) {
			throw new NoSuchElementException("heap is empty");
		}
		return items.get(0);
	}
	
	/**
	 * Prints all values smaller than k
	 * 
	 * @param k to print values greater than 
	 */
	public void printSmaller(T k) {
		printSmaller(k,items.size()-1,0);
	}
	
	private void printSmaller(T k, int size, int index) {
		if (index > size) { return; }
		int c = items.get(index).compareTo(k);
		if (c >= 0) { return; }
		System.out.println(items.get(index));
		printSmaller(k,size,2*index+1);
		printSmaller(k,size,2*index+2);
	}
	
	/**
	 * Returns the size of the heap
	 */
	public int size() {
		return items.size();
	}
	
	/**
	 * Prints the entire heap 
	 */
	public String toString() {
		String ret = "";
		for (int i = 0; i < items.size(); i++) {
			ret += items.get(i) + " -> ";
		}
		ret+=" \\";
		return ret;
	}
	
	/**
	 * Returns whether or not the heap is empty
	 */
	public boolean isEmpty() {
		return items.isEmpty();
	}
	
	/**
	 * Clears the entire heap
	 */
	public void clear() {
		items.clear();
	}
	
	/**
	 * Returns the ArrayList containing the heap
	 * 
	 * @return Heap ArrayList
	 */
	public ArrayList<T> getHeap(){
		return items;
	}
	
}
