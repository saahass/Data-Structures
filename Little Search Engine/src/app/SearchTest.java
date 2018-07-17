package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import lse.LittleSearchEngine; 

/**
 * Little Search Engine driver
 * @author Saaqeb 
 *
 */
public class SearchTest {
	
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		LittleSearchEngine lse = new LittleSearchEngine();
		System.out.print("\nEnter document: ");
		lse.makeIndex(sc.nextLine(), "noisewords.txt");
		System.out.println("\nSuccess!");
		int choice = 0;
		while (choice != 2) {
			System.out.print("\n(1)Search or (2)Quit: ");
			try { choice = Integer.parseInt(sc.nextLine()); } 
			catch (NumberFormatException e) { 
				System.out.println("\nInvalid Input!"); 
				continue;
			}
			switch (choice) {
				case 1 : top5(lse);
				case 2 : break;
				default : System.out.print("\nInvalid Input!");
			}
		}
	}
	
	private static void top5(LittleSearchEngine lse) {
		System.out.print("\nInput first word: ");
		String word1 = sc.nextLine();
		System.out.print("\nInput second word: ");
		String word2 = sc.nextLine();
		System.out.println("\nTOP 5 SEARCH:\n");
		ArrayList<String> search = lse.top5search(word1, word2);
		for (String s : search) {
			System.out.print(s+" --> ");
		}
		System.out.print("\\\n");
	}

}
