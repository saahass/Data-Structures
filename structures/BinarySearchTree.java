package structures;

import java.util.NoSuchElementException;

public class BinarySearchTree <T extends Comparable <T> >{

	protected static class BSTNode <T extends Comparable<T>>{
		public T key;
		public BSTNode <T> left, right;
		private int height;
		
		public BSTNode(T key, BSTNode <T> left, BSTNode <T> right) {
			this.key = key;
			this.left = null;
			this.right = null;
		}

		protected static <T extends Comparable<T>> 
		void fillHeights(BSTNode<T> root) {
			if (root == null) {
				return;
			}
			fillHeights(root.left);
			fillHeights(root.right);
			if (root.left == null) {
				if (root.right == null) {
					root.height = 0;
					return;
				} else {
					root.height = root.right.height + 1;
					return;
				}
			} else if (root.right == null) {
				if (root.left == null) {
					root.height = 0;
					return;
				} else {
					root.height = root.left.height + 1;
					return;
				}
			} else {
				Integer leftHeight = root.left.height, 
						rightHeight = root.right.height;
				int check = leftHeight.compareTo(rightHeight);
				root.height = check >= 0 ? 
						leftHeight + 1 : rightHeight + 1;
			}
		}
		
		public String toString() {
			return key.toString();
		}
	}
	
	
	public BSTNode <T> root;
	private int size;
	
	/**
	 * Creates an empty Binary Search Tree
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
	}
	
	/**
	 * Searches for the targeted node
	 * 
	 * @param target to search for
	 * @return whether or not target is present
	 */
	public boolean search (T target){
		BSTNode <T> ptr = root;
		while (ptr != null) {
			int c = target.compareTo(ptr.key);	
			if (c == 0) {
				return true;		// target found
			} else if (c > 0) {
				ptr = ptr.right;
			} else {
				ptr = ptr.left;
			}
		}
		return false;
	}
	
	
	/**
	 * Inserts the new key to the binary search tree. 
	 * No duplicates allowed!!
	 * 
	 * @param key to insert
	 */
	public void insert(T key) {
		BSTNode <T> ptr = root, prev = null;
		int check = 0;
		while (ptr != null) {
			check = key.compareTo(ptr.key);
			if (check == 0) {		// no duplicates allowed
				throw new 
				IllegalArgumentException(key + " is already in BST");
			}
			prev = ptr;
			ptr = (check > 0) ? ptr.right : ptr.left;
		}
		BSTNode <T> newNode = new BSTNode<T>(key, null, null);
		if (prev == null) {
			root = newNode;
		} else if (check > 0) {
			prev.right = newNode;
		} else {
			prev.left = newNode;
		} size += 1;
	}
	
	/**
	 * Recursive insert method
	 * 
	 * @param key
	 */
	public void insertRec(T key) {
		root = insertRec(root,key);
		size+=1;
	}
	
	private BSTNode<T> insertRec(BSTNode<T> ptr, T key) {
		if (ptr == null) {
			return new BSTNode<T>(key,null,null);
		}
		int check = key.compareTo(ptr.key);
		if (check == 0) {		// ptr.key == key, no duplicates allowed
			throw new IllegalArgumentException(key + " is already in BST");
		} else if (check > 0) {
			ptr.right = insertRec(ptr.right,key);
		} else {
			ptr.left = insertRec(ptr.left,key);
		}
		return ptr;
	}
	
	/**
	 * In order traversal of the binary search tree
	 */
	public void inorderTraversal() {
		inorder(this.root);
	}
	
	private static <T extends Comparable<T>> void 
	inorder(BSTNode<T> root) {
		if (root == null) {			// recursive base case
			return;
		}
		inorder(root.left);			//L
		System.out.print(root.key + " ");	//V
		inorder(root.right);		//R
	}
	
	/**
	 * Pre order traversal of the binary search tree
	 */
	public void preorderTraversal() {
		preorder(root);
	}
	
	private static <T extends Comparable<T>> void 
	preorder(BSTNode<T> root) {
		if (root == null) {			// recursive base case
			return;
		}
		System.out.println(root.key + " ");		// V
		preorder(root.left);			// L
		preorder(root.right);		// R
	}
	
	/**
	 * Post order traversal of the binary search tree
	 */
	public void postorderTraversal() {
		postorder(root);
	}
	
	private static <T extends Comparable<T>> void 
	postorder(BSTNode<T> root) {
		if (root == null) {			// recursive base case
			return;
		}
		preorder(root.left);			// L
		preorder(root.right);		// R
		System.out.println(root.key + " ");		// V
	}
	
	/**
	 * Deletes the targeted node from BST
	 * 
	 * @param target to delete
	 */
	public void delete(T target) {
		/**
		 * Step 1: find node to delete: 
		 */
		BSTNode<T> x = root;
		BSTNode<T> p = null;
		int check = 0;
		while (x != null) {
			check = target.compareTo(x.key);
			if (check == 0) { break; }		// found target in BST
			p = x;
			x = check > 0 ? x.right : x.left; 
		}
		
		/**
		 *  Step 2: check if target is not found
		 */
		if (x == null) {
			throw new NoSuchElementException("Target not found in BST");
		}
		
		/**
		 * Step 3: check if x has two children
		 */
		BSTNode <T> y = null;
		if (x.left != null && x.right != null) {
			y = x.left;									// take a left (or right) turn
			p = x;
			while (y.right != null) {					// go all the way to the right (or left)
				p = y;
				y = y.right;
			}
			
			x.key = y.key;
		}
		
		if (p == null) {
			root = (x.left != null) ? x.left : x.right;
			size -= 1; return;
		}
		
		// case 1 and 2 && removing y from case 3
		BSTNode <T> tmp = (x.right != null) ? x.right : x.left;
		if (x == p.left) {
			p.left = tmp;
		}
		else {
			p.right = tmp;
		}
		size -= 1;
		
	}
	
    /**
     * Reverses the binary search tree to have the left child 
     * be greater than parent and the right child to be smaller 
     * than the parent.
     * 
     */
    public void reverseKeys() {
    		reverseKeys(root);
    }
    
    private static <T extends Comparable<T>> 
    void reverseKeys(BSTNode<T> root) {
    		if (root == null) {
    			return;
    		}
    		BSTNode<T> ptr = root.left;
    		root.left = root.right;
    		root.right = ptr;
    		reverseKeys(root.left);
    		reverseKeys(root.right);
    }
    
    /**
     * Fills the heights of the binary search tree
     */
    public void fillHeights() {
    	BSTNode.fillHeights(root);
    }
    
}
