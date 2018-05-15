package app;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import lse.LittleSearchEngine;
import lse.Occurrence;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
		LittleSearchEngine lse = new LittleSearchEngine();
		
		System.out.println(lse.getKeyword("Word"));
		System.out.println(lse.getKeyword("night,"));
		System.out.println(lse.getKeyword("question??"));
		System.out.println(lse.getKeyword("Could"));
		System.out.println(lse.getKeyword("test-case"));
		
		HashMap <String,Occurrence> hm = lse.loadKeywordsFromDocument("pohlx.txt");
		for (String i: hm.keySet()) {
			System.out.print(i+" --> ");
			System.out.println(hm.get(i));
		}
		
		lse.makeIndex("docs.txt", "noisewords.txt");
		for (String i: lse.getKeywordsIndex().keySet()) {
			System.out.print(i+" --> ");
			System.out.println(lse.getKeywordsIndex().get(i));
		}
		
		ArrayList<Occurrence> occs = new ArrayList<>();
		occs.add(new Occurrence("doc1.txt",20));
		occs.add(new Occurrence("doc2.txt",15));
		occs.add(new Occurrence("doc3.txt",14));
		occs.add(new Occurrence("doc4.txt",12));
		occs.add(new Occurrence("doc5.txt",12));
		occs.add(new Occurrence("doc6.txt",10));
		occs.add(new Occurrence("doc7.txt",8));
		occs.add(new Occurrence("doc8.txt",21));
		ArrayList<Integer> al = lse.insertLastOccurrence(occs);
		for (int i=0;i<al.size();i++) {
			System.out.print(al.get(i)+" --> ");
		}
		al.clear();occs.clear();
		occs = new ArrayList<>();
		occs.add(new Occurrence("doc1.txt",20));
		occs.add(new Occurrence("doc2.txt",15));
		occs.add(new Occurrence("doc3.txt",14));
		occs.add(new Occurrence("doc4.txt",12));
		occs.add(new Occurrence("doc5.txt",12));
		occs.add(new Occurrence("doc6.txt",10));
		occs.add(new Occurrence("doc7.txt",8));
		occs.add(new Occurrence("doc8.txt",1));
		al = lse.insertLastOccurrence(occs);
		for (int i=0;i<al.size();i++) {
			System.out.print(al.get(i)+" --> ");
		}
		al.clear();occs.clear();
		occs = new ArrayList<>();
		occs.add(new Occurrence("doc1.txt",20));
		occs.add(new Occurrence("doc2.txt",15));
		occs.add(new Occurrence("doc3.txt",14));
		occs.add(new Occurrence("doc4.txt",12));
		occs.add(new Occurrence("doc5.txt",12));
		occs.add(new Occurrence("doc6.txt",10));
		occs.add(new Occurrence("doc7.txt",8));
		occs.add(new Occurrence("doc8.txt",13));
		al = lse.insertLastOccurrence(occs);
		for (int i=0;i<al.size();i++) {
			System.out.print(al.get(i)+" --> ");
		}
		al.clear();occs.clear();
		occs = new ArrayList<>();
		occs.add(new Occurrence("doc1.txt",20));
		occs.add(new Occurrence("doc2.txt",15));
		occs.add(new Occurrence("doc3.txt",14));
		occs.add(new Occurrence("doc4.txt",12));
		occs.add(new Occurrence("doc5.txt",12));
		occs.add(new Occurrence("doc6.txt",10));
		occs.add(new Occurrence("doc7.txt",8));
		occs.add(new Occurrence("doc8.txt",10));
		al = lse.insertLastOccurrence(occs);
		for (int i=0;i<al.size();i++) {
			System.out.print(al.get(i)+" --> ");
		}
		al.clear();occs.clear();
		ArrayList<String> res = lse.top5search("lol", "shoulder");
		for (int i=0;i<al.size();i++) {
			System.out.print(res.get(i)+" --> ");
		}
		
	}
	
}
