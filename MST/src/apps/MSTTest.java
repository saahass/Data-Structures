package apps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import structures.Graph;

/**
 * Minimum Spanning Tree Driver
 * @author Saaqeb 
 *
 */
public class MSTTest {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			Graph graph;
			while (true) {
				System.out.print("\nName of graph file: ");
				try { 
					graph = new Graph(sc.next()); 
					break;
				} catch (IOException e) {
					System.out.println("\nFile does not exist!");
				}
			}
			PartialTreeList ptl = MST.initialize(graph);
			ArrayList<PartialTree.Arc> al = MST.execute(ptl);
			System.out.print("\nMST Arcs: ");
			for (int i = 0; i < al.size(); i++) {
				System.out.print(al.get(i)+" ");
			}
			System.out.print("\n\nTry another file? (y or n): ");
			String s = sc.next();
			while (!s.equals("y") && !s.equals("n")) {
				System.out.print("\nIncorrect input!\n\nTry another file? (y or n): ");
				s = sc.next();
			}
			if (s.equals("n")) break;
		}
		sc.close();
	}

}
