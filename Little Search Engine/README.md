# Assignment 4 - Little Search Engine

Please adhere to [Rutgers University's Academic Integrity Policy](http://academicintegrity.rutgers.edu/academic-integrity-policy/) and the [Department of CS Academic Integrity Policy](https://www.cs.rutgers.edu/academic-integrity/programming-assignments).

## Background

There are two packages:
1. **APP**
    - SearchTest: This is the application driver

2. **STRUCTURES**
    - Occurrence: Stores the document name and the frequency that a word appears in that document
    - LittleSearchEngine: An object that stores words and their occurrences into a hashtable for efficient searching
  
## Assignment

Inside LittleSearchEngine.java, the methods that need to be completed is

1. **loadKeywordsFromDocument:** Loads all key words from a specific document into a hashtable for searching
2. **getKeyword:** Given a word, return after removing trailing punctuation if it is a non-numeric word 
3. **mergeKeywords:** Merges the master Little Search Engine hashtable with the hashtable returned from loadKeywordsFromDocument
4. **insertLastOccurrence:** Inserts the word and its occurrence in the correct position in an ArrayList using binary search
5. **top5search:** Searches for the top 5 documents containing either of the two inputted words

One must work inside these methods only, but declaring private methods inside LittleSearchEngine.java is allowed.

My implementation involves six private methods `hasPunctuation`, `removeDuplicates`, and `selectionSort`.

I am the author of `SearchTest.java`. Let me know if there are any bugs.

## Testing

Using the driver provided, test your search engine with any documents you want.
