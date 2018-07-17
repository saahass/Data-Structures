package structures;

import java.util.*;

/**
 * This class implements an HTML DOM Tree. Each node of the tree is a TagNode, with fields for
 * tag/text, first child and sibling.
 * 
 */
public class Tree {
	
	/**
	 * Root node
	 */
	TagNode root=null;
	
	/**
	 * Scanner used to read input HTML file when building the tree
	 */
	Scanner sc;
	
	/**
	 * Initializes this tree object with scanner for input HTML file
	 * 
	 * @param sc Scanner for input HTML file
	 */
	public Tree(Scanner sc) {
		this.sc = sc;
		root = null;
	}
	
	/**	
	 * **COMPLETE THIS METHOD**
	 * Builds the DOM tree from input HTML file, through scanner passed
	 * in to the constructor and stored in the sc field of this object. 
	 * 
	 * The root of the tree that is built is referenced by the root field of this object.
	 */
	public void build() {
		root = buildRec();
	}
	
	private TagNode buildRec() {
		if (!sc.hasNextLine()) {					// finished scanning file
			return null;
		}
		String s = sc.nextLine(); 
		if (s.startsWith("<")) {
			if (s.startsWith("</")) {				// closing tag
				return null;
			}
			return new TagNode 					// opening tag
					(s.substring(1, s.length()-1),buildRec(),buildRec());	
		} else {
			return new TagNode(s,null,buildRec());			// no tag
		}
	}
	
	/**	
	 * **COMPLETE THIS METHOD**
	 * Replaces all occurrences of an old tag in the DOM tree with a new tag
	 * 
	 * @param oldTag Old tag
	 * @param newTag Replacement tag
	 */
	public void replaceTag(String oldTag, String newTag) {
		replaceRec(oldTag,newTag,root);
	}
	
	private void replaceRec(String oldTag, String newTag, TagNode ptr) {
		if (ptr == null) {				// base case
			return;
		}
		if (ptr.tag.equals(oldTag)) {			// oldTag is found, replace with newTag
			ptr.tag = newTag;
		}
		replaceRec(oldTag,newTag,ptr.firstChild);	// search for oldTag in first child
		replaceRec(oldTag,newTag,ptr.sibling);		// search for oldTag in sibling
	}
	
	/**	
	 * **COMPLETE THIS METHOD**
	 * Boldfaces every column of the given row of the table in the DOM tree. The boldface (b)
	 * tag appears directly under the td tag of every column of this row.
	 * 
	 * @param row Row to bold, first row is numbered 1 (not 0).
	 */
	public void boldRow(int row) {
		if (row < 1) { 								// if row is >= 0
			throw new IllegalArgumentException("row can't be less than 1"); 
		}
		TagNode rowTag = null;
		try {
			rowTag = search(root,"table").firstChild;			// searching for table
		} catch (NoSuchElementException e) {				// if table not found in html file
			throw new NoSuchElementException("table not found in html file");
		}
		for (int i = 0; i < row; i++) {			// iterating to find the correct row
			rowTag = rowTag.sibling;
		}
		// boldfacing every column in a certain row
		for (TagNode ptr = rowTag.firstChild; ptr != null; ptr = ptr.sibling) {
			ptr.firstChild = new TagNode("b",ptr.firstChild,null);
		}
	}
	
	/**
	 * Search method to find the target TagNode
	 * 
	 */
	private TagNode search(TagNode ptr, String target) {
		if (ptr == null) {			// base case
			return null;
		}
		if (ptr.tag.equals(target)) {		// target found
			return ptr;
		}
		TagNode node = search(ptr.firstChild, target);		// searching through first child
		if (node == null) {
			node = search(ptr.sibling, target);		// if target not found, search sibling
		}
		return node;	
	}
	
	/**
	 * **COMPLETE THIS METHOD**
	 * Remove all occurrences of a tag from the DOM tree. If the tag is p, em, or b, all occurrences of the tag
	 * are removed. If the tag is ol or ul, then All occurrences of such a tag are removed from the tree, and, 
	 * in addition, all the li tags immediately under the removed tag are converted to p tags. 
	 * 
	 * @param tag Tag to be removed, can be p, em, b, ol, or ul
	 */
	public void removeTag(String tag) {
		removeRec(root,null,false,tag);
	}
	
	private void removeRec(TagNode ptr, TagNode prev, boolean check, String target) {
		if (ptr == null) {			// base case
			return;
		}
		if (target.equals(ptr.tag)) {
			if (prev == null) {						
				throw new NullPointerException("cannot remove html tag");
			}
			if (target.equals("ol") || target.equals("ul")) {
				TagNode ptr2 = ptr.firstChild;
				while (ptr2.sibling != null) {			// iterating to make "li" tags "p"
					ptr2.tag = "p";
					ptr2 = ptr2.sibling;
				}
				ptr2.tag = "p";
				if (check) {					// if target tag is prev's first child
					ptr2.sibling = ptr.sibling;
					prev.firstChild = ptr.firstChild;
				} else {					// if target tag is prev's sibling	
					ptr2.sibling = ptr.sibling;			
					prev.sibling = ptr.firstChild;
				}
			}
			else {
				if (check) {					// if target tag is prev's first child
					ptr.firstChild.sibling = ptr.sibling;
					prev.firstChild = ptr.firstChild;
				} else {					// if target tag is prev's sibling
					ptr.firstChild.sibling = ptr.sibling;
					prev.sibling = ptr.firstChild;
				}
			}
		}
		removeRec(ptr.firstChild, ptr, true, target);
		removeRec(ptr.sibling, ptr, false, target);
	}
	
	/**
	 * **COMPLETE THIS METHOD**
	 * Adds a tag around all occurrences of a word in the DOM tree.
	 * 
	 * @param word Word around which tag is to be added
	 * @param tag Tag to be added
	 */
	public void addTag(String word, String tag) {
		addRec(root,null,true,word,tag);
	}
	
	private void 
	addRec(TagNode ptr, TagNode prev, boolean check, String target, String tag) {
		if (ptr == null) {
			return;
		}
		if (ptr.tag.equalsIgnoreCase(target)) {
			if (prev == null) {
				throw new NullPointerException("cannot tag outside html tag");
			}
			if (check) {
				prev.firstChild = new TagNode(tag, ptr, null);
			} else {
				prev.sibling = new TagNode(tag, ptr, null);
			}
			addRec(ptr.firstChild, ptr, true, target, tag);
			addRec(ptr.sibling, ptr, false, target, tag);
		}
		else {
			// word is a substring of larger string
		}
	}
	
	/**
	 * Gets the HTML represented by this DOM tree. The returned string includes
	 * new lines, so that when it is printed, it will be identical to the
	 * input file from which the DOM tree was built.
	 * 
	 * @return HTML string, including new lines. 
	 */
	public String getHTML() {
		StringBuilder sb = new StringBuilder();
		getHTML(root, sb);
		return sb.toString();
	}
	
	private void getHTML(TagNode root, StringBuilder sb) {
		for (TagNode ptr=root; ptr != null;ptr=ptr.sibling) {
			if (ptr.firstChild == null) {
				sb.append(ptr.tag);
				sb.append("\n");
			} else {
				sb.append("<");
				sb.append(ptr.tag);
				sb.append(">\n");
				getHTML(ptr.firstChild, sb);
				sb.append("</");
				sb.append(ptr.tag);
				sb.append(">\n");	
			}
		}
	}
	
	/**
	 * Prints the DOM tree. 
	 *
	 */
	public void print() {
		print(root, 1);
	}
	
	private void print(TagNode root, int level) {
		for (TagNode ptr=root; ptr != null;ptr=ptr.sibling) {
			for (int i=0; i < level-1; i++) {
				System.out.print("      ");
			};
			if (root != this.root) {
				System.out.print("|---- ");
			} else {
				System.out.print("      ");
			}
			System.out.println(ptr.tag);
			if (ptr.firstChild != null) {
				print(ptr.firstChild, level+1);
			}
		}
	}
}
