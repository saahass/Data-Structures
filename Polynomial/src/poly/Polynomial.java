package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		
		Node ptr1 = poly1;
		Node ptr2 = poly2;
		
		Node ptr3 = new Node(0,0,null);	
		Node front = ptr3;				//return variable
		
		while(true) {
			
			// if iteration through poly1 and poly2 are complete
			if (ptr1 == null && ptr2 == null) { break; }
			
			// if iterated through poly1 but not done with poly2
			else if (ptr1 == null) {
				ptr3.next = new Node(0,0,null);
				ptr3 = ptr3.next;
				
				ptr3.term.degree = ptr2.term.degree;
				ptr3.term.coeff = ptr2.term.coeff;
				ptr2 = ptr2.next; continue;
			}
			
			// if iterated though poly2 but not done with poly1
			else if (ptr2 == null) {
				ptr3.next = new Node(0,0,null);
				ptr3 = ptr3.next;
				
				ptr3.term.degree = ptr1.term.degree;
				ptr3.term.coeff = ptr1.term.coeff;
				ptr1 = ptr1.next; continue;
			}
			
			// if degree in poly1 < poly2, bring down poly1
			if(ptr1.term.degree < ptr2.term.degree) {
				ptr3.next = new Node(0,0,null);
				ptr3 = ptr3.next;
				
				ptr3.term.degree = ptr1.term.degree;
				ptr3.term.coeff = ptr1.term.coeff;
				ptr1 = ptr1.next; 
				
			}
			
			// if degree in poly1 > poly2, bring down poly2
			else if(ptr1.term.degree > ptr2.term.degree) {
				ptr3.next = new Node(0,0,null);
				ptr3 = ptr3.next;
				
				ptr3.term.degree = ptr2.term.degree;
				ptr3.term.coeff = ptr2.term.coeff;
				ptr2 = ptr2.next; 
				
			}
			
			// if degree is same, add coeff and bring it down
			else if (ptr1.term.degree == ptr2.term.degree){
				
				// if coeff cancels each other out
				float x = ptr1.term.coeff + ptr2.term.coeff;
				if(x == 0) { 
					ptr1 = ptr1.next; ptr2 = ptr2.next; 
					continue; 
				}
				
				ptr3.next = new Node(0,0,null);
				ptr3 = ptr3.next;
				
				ptr3.term.degree = ptr1.term.degree;
				ptr3.term.coeff = x;
				ptr1 = ptr1.next; ptr2 = ptr2.next; 
				
			}
			else;
		}
		// getting rid of first node "0x^0"
		front = front.next;
		return front;
	} 
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		
		// if both polynomials are empty
		if(poly1 == null || poly2 == null) {
			return null;
		}
		
		// initializing pointers
		Node front = new Node(-1,-1,null);
		Node ptr3 = front;
		boolean test = false;
		
		// iterating through poly1
		for(Node ptr1 = poly1; ptr1 != null; ptr1 = ptr1.next) {
			
			// iterating through poly2
			for(Node ptr2 = poly2; ptr2 != null; ptr2 = ptr2.next) {
				
				// retrieving the product's degree & coefficient
				int newDegree = ptr1.term.degree + ptr2.term.degree;
				float tempCoeff = ptr1.term.coeff * ptr2.term.coeff;
				
				// getting rid of the first node "-1,-1,null"
				if (test == false) {
					ptr3.next = new Node(tempCoeff, newDegree, null);
					ptr3 = ptr3.next; test = true; front = front.next;
				}
				else {
					// combining like terms and initializing final result
					tempCoeff = likeTerms(front, newDegree, tempCoeff);

					// if coefficient is 0
					if (tempCoeff == 0) { continue; }
					
					Node result = new Node(tempCoeff, newDegree, null);
					
					// finding the correct place to place Node result
					order(front, result, newDegree);
						
					// if result is added at the end, ptr3 is pointing 
					//		to the end
					if (ptr3.next == result) {
						ptr3 = ptr3.next;
					}
				}
			}
		}
		
		return front;
	}
	
	/**
	 * places node fit in front at proper location
	 * 
	 * @param front 
	 * @param fit into linked list
	 * @param degree of node fit
	 */
	private static void order(Node front, Node fit, int degree) {

		Node ptr4 = front;
		Node prev = null;
		
		// if first term is greater than Node fit, put it at front
		if (ptr4.term.degree > degree) {
			fit.next = ptr4;
			front = fit; 
			return;
		}
		
		prev = ptr4; ptr4 = ptr4.next;
		
		// iterating to find where to place Node fit
		while (ptr4 != null) {
			if (ptr4.term.degree > degree) {
				fit.next = ptr4;
				prev.next = fit;
				return;
			}
			prev = ptr4; ptr4 = ptr4.next;
		}
		// all else fails, place Node fit at tail
		prev.next = fit;
	}
	
	/**
	 * adds like terms within node @param poly 
	 * 
	 * @return final coefficient
	 */
	private static float likeTerms (Node poly, int degree, float coeff) {
		// should never happen
		if (poly.term.degree == -1) {
			System.out.println("failure");
			return -1;
		}
		
		// if front's degree is the same as the new degree
		while (poly.term.degree == degree) {
			coeff += poly.term.coeff;
			poly = poly.next;
		}
		
		Node ptr = poly;
		Node prev = null;
		
		// iterating to find like terms
		while (ptr != null) {
			if (ptr.term.degree == degree) {
				coeff += ptr.term.coeff;
				prev.next = ptr.next;
				ptr = ptr.next; 
			}
			else {
				prev = ptr;
				ptr = ptr.next;
			}
		}
		return coeff;
	}
	
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		float answer = 0;
		
		for (Node ptr = poly; ptr != null; ptr = ptr.next) {
			answer += ptr.term.coeff * 
					(float) Math.pow(x, ptr.term.degree);
		}
		return answer;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
