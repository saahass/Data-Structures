package lse;

import java.io.*;
import java.util.*;

/**
 * This class builds an index of keywords. Each keyword maps to a set of pages in
 * which it occurs, with frequency of occurrence in each page.
 *
 */
public class LittleSearchEngine {
	
	/**
	 * This is a hash table of all keywords. The key is the actual keyword, and the associated value is
	 * an array list of all occurrences of the keyword in documents. The array list is maintained in 
	 * DESCENDING order of frequencies.
	 */
	HashMap<String,ArrayList<Occurrence>> keywordsIndex;
	
	/**
	 * The hash set of all noise words.
	 */
	HashSet<String> noiseWords;
	
	/**
	 * Creates the keyWordsIndex and noiseWords hash tables.
	 */
	public LittleSearchEngine() {
		keywordsIndex = new HashMap<String,ArrayList<Occurrence>>(1000,2.0f);
		noiseWords = new HashSet<String>(100,2.0f);
	}
	
    /**
     * Getter method for driver or testing
     * @return HashMap KeywordsIndex
     */
	public HashMap<String,ArrayList<Occurrence>> getKeywordsIndex() {
		return keywordsIndex;
	}
	
	/**
	 * ** COMPLETE THIS METHOD **
	 * Scans a document, and loads all keywords found into a hash table of keyword occurrences
	 * in the document. Uses the getKeyWord method to separate keywords from other words.
	 * 
	 * @param docFile Name of the document file to be scanned and loaded
	 * @return Hash table of keywords in the given document, each associated with an Occurrence object
	 * @throws FileNotFoundException If the document file is not found on disk
	 */
	public HashMap<String,Occurrence> loadKeywordsFromDocument(String docFile) 
	throws FileNotFoundException {
		if (docFile == null) {
			throw new FileNotFoundException("file not found");
		}
		Scanner sc = new Scanner(new File(docFile));
		HashMap<String,Occurrence> kws = new HashMap<String,Occurrence>();
		while (sc.hasNext()) {
			String word = getKeyword(sc.next());
			if (word == null) {
				continue;
			}
			if (kws.containsKey(word)) {
				kws.get(word).frequency++;
			} else {
				kws.put(word, new Occurrence(docFile,1));
			}
		}
		sc.close();
		return kws;
	}
	
	/**
	 * ** COMPLETE THIS METHOD **
	 * Merges the keywords for a single document into the master keywordsIndex
	 * hash table. For each keyword, its Occurrence in the current document
	 * must be inserted in the correct place (according to descending order of
	 * frequency) in the same keyword's Occurrence list in the master hash table. 
	 * This is done by calling the insertLastOccurrence method.
	 * 
	 * @param kws Keywords hash table for a document
	 */
	public void mergeKeywords(HashMap<String,Occurrence> kws) {
		for (String key : kws.keySet()) {
			Occurrence oc = kws.get(key);
			if (keywordsIndex.containsKey(key)) {
				ArrayList<Occurrence> occs = keywordsIndex.get(key);
				occs.add(oc); insertLastOccurrence(occs);
				keywordsIndex.put(key, occs);
			} else {
				ArrayList<Occurrence> occs = new ArrayList<Occurrence>();
				occs.add(oc); insertLastOccurrence(occs);
				keywordsIndex.put(key, occs);
			}
		}
	}
	
	/**
	 * ** COMPLETE THIS METHOD **
	 * Given a word, returns it as a keyword if it passes the keyword test,
	 * otherwise returns null. A keyword is any word that, after being stripped of any
	 * trailing punctuation, consists only of alphabetic letters, and is not
	 * a noise word. All words are treated in a case-INsensitive manner.
	 * 
	 * Punctuation characters are the following: '.', ',', '?', ':', ';' and '!'
	 * 
	 * @param word Candidate word
	 * @return Keyword (word without trailing punctuation, LOWER CASE)
	 */
	public String getKeyword(String word) {
		if (word == null) {
			return null;
		}
		word = word.toLowerCase();
		if (noiseWords.contains(word)) {
			return null;
		}
		while (!Character.isAlphabetic(word.charAt(word.length()-1))) {
			word = word.substring(0,word.length()-1);
		}
		if (hasPunctuation(word) || word == null) {
			return null;
		}
		return word;
	}
	
	/**
	 * Checks if string has punctuation in the middle
	 * 
	 * @return true if it has punctuation
	 */
	private static boolean hasPunctuation(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isLetter(s.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ** COMPLETE THIS METHOD **
	 * Inserts the last occurrence in the parameter list in the correct position in the
	 * list, based on ordering occurrences on descending frequencies. The elements
	 * 0..n-2 in the list are already in the correct order. Insertion is done by
	 * first finding the correct spot using binary search, then inserting at that spot.
	 * 
	 * @param occs List of Occurrences
	 * @return Sequence of mid point indexes in the input list checked by the binary search process,
	 *         null if the size of the input list is 1. This returned array list is only used to test
	 *         your code - it is not used elsewhere in the program.
	 */
	public ArrayList<Integer> insertLastOccurrence(ArrayList<Occurrence> occs) {
		if (occs.size() < 2) {
			return null;
		}
		Occurrence oc = occs.get(occs.size()-1);
		occs.remove(occs.size()-1);
		int left = 0, middle = 0; 
		int right = occs.size()-1;
		ArrayList<Integer> midpoints = new ArrayList<Integer>();
		int target = oc.frequency;
		
		while (left <= right) {
			middle = (left + right)/2;
			midpoints.add(middle);
			int midKey = occs.get(middle).frequency;
			if (target == midKey) {
				break;
			} 
			if (target < midKey) {
				left = middle + 1;
				middle += 1;
			}
			if (target > midKey){
				right = middle - 1;
			}
		}
		occs.add(middle, oc);
		return midpoints;
	}
	
	/**
	 * This method indexes all keywords found in all the input documents. When this
	 * method is done, the keywordsIndex hash table will be filled with all keywords,
	 * each of which is associated with an array list of Occurrence objects, arranged
	 * in decreasing frequencies of occurrence.
	 * 
	 * @param docsFile Name of file that has a list of all the document file names, one name per line
	 * @param noiseWordsFile Name of file that has a list of noise words, one noise word per line
	 * @throws FileNotFoundException If there is a problem locating any of the input files on disk
	 */
	public void makeIndex(String docsFile, String noiseWordsFile) 
	throws FileNotFoundException {
		// load noise words to hash table
		Scanner sc = new Scanner(new File(noiseWordsFile));
		while (sc.hasNext()) {
			String word = sc.next();
			noiseWords.add(word);
		}
		
		// index all keywords
		sc = new Scanner(new File(docsFile));
		while (sc.hasNext()) {
			String docFile = sc.next();
			HashMap<String,Occurrence> kws = loadKeywordsFromDocument(docFile);
			mergeKeywords(kws);
		}
		sc.close();
	}
	
	/**
	 * ** COMPLETE THIS METHOD **
	 * Search result for "kw1 or kw2". A document is in the result set if kw1 or kw2 occurs in that
	 * document. Result set is arranged in descending order of document frequencies. (Note that a
	 * matching document will only appear once in the result.) Ties in frequency values are broken
	 * in favor of the first keyword. (That is, if kw1 is in doc1 with frequency f1, and kw2 is in doc2
	 * also with the same frequency f1, then doc1 will take precedence over doc2 in the result. 
	 * The result set is limited to 5 entries. If there are no matches at all, result is null.
	 * 
	 * @param kw1 First keyword
	 * @param kw1 Second keyword
	 * @return List of documents in which either kw1 or kw2 occurs, arranged in descending order of
	 *         frequencies. The result size is limited to 5 documents. If there are no matches, returns null.
	 */
	public ArrayList<String> top5search(String kw1, String kw2) {
		kw1 = kw1.toLowerCase();
		kw2 = kw2.toLowerCase();
		ArrayList<Occurrence> occ1 = new ArrayList<Occurrence>();
		ArrayList<Occurrence> occ2 = new ArrayList<Occurrence>();
		ArrayList<Occurrence> comb = new ArrayList<Occurrence>();
		if (keywordsIndex.containsKey(kw1)) {
			occ1 = keywordsIndex.get(kw1);
			comb.addAll(occ1);
		}
		if (keywordsIndex.containsKey(kw2)) {
			occ2 = keywordsIndex.get(kw2);
			comb.addAll(occ2);
		}
		comb = selectionSort(comb);
		comb = removeDuplicates(comb);
		comb = selectionSort(comb);
		ArrayList<String> results = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			try{ results.add(comb.get(i).document); } 
			catch (IndexOutOfBoundsException e) { break; }
		}
		return results;
	}
	
	/**
	 * Selection sort method on ArrayList of Occurrences
	 * 
	 * @return
	 */
    private static ArrayList<Occurrence> selectionSort(ArrayList<Occurrence> list) {
        for (int i = 0; i < list.size()-1; i++) {
            int big = i;
            for (int j = i+1; j < list.size(); j++) {
                if (list.get(big).frequency < list.get(j).frequency) {
                		big = j;
            		}
            }
            if (big != i) {
	            Occurrence swap = list.get(i);
	            list.set(i, list.get(big));
	            list.set(big,swap);
            }
            
        }
        return list;
    }
    
    /**
     * Removes duplicates in ArrayList of Occurrences
     * 
     * @return
     */
    private static ArrayList<Occurrence> removeDuplicates(ArrayList<Occurrence> list) {
    		for (int i = 0; i < list.size()-1; i++) {
			for (int j = i+1; j < list.size(); j++) {
				if (list.get(i).document.equals(list.get(j).document)) {
					if (list.get(i).frequency > list.get(j).frequency) { 
    						list.remove(j); 
    					} else { list.remove(i); }
				}
			}
		}
		return list;
    }
    
}
