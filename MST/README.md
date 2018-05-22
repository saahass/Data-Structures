# Assignment 5 - Minimum Spanning Tree (MST)

Please adhere to [Rutgers University's Academic Integrity Policy](http://academicintegrity.rutgers.edu/academic-integrity-policy/) and the [Department of CS Academic Integrity Policy](https://www.cs.rutgers.edu/academic-integrity/programming-assignments).

## Background

There are two packages:
1. **APP**
    - MSTTest: This is the application driver (I am the author).
    - MST: This executes the MST algorithm.
    - PartialTree: This class is a part of a tree. Contains a nested class "Arcs" that contains the weights of each link.
    - PartialTreeList: A list of partial trees. The list is stored in a circular linked list with a nested class "Node". Also contains an iterator to iterate through the list.

2. **STRUCTURES**
    - Graph: A weighted and undirected graph that is stored in an adjacency matrix using a hashtable
    - MinHeap: A minimum heap data structure
    - Vertex: A vertex of a graph. Had a nested class "Neighbors" to store all of the neighbors of that Vertex.
  
## Assignment

Inside PartialTreeList.java, the methods that need to be completed is:

1. **remove:** Loads all key words from a specific document into a hashtable for searching
2. **removeTreeContaining:** Given a word, return after removing trailing punctuation if it is a non-numeric word 

Inside MST.java, the methods that need to be completed is:

3. **initialize:** initializes the MST algorithm
4. **execute:** executes the MST algorithm

One must work inside these methods only, but declaring private methods inside MST.java and/or PartialTreeList.java is allowed.

My implementation involves two private methods `isVertexInTree` and `deleteNode`.

I am the author of `MSTTest.java`. Let me know if there are any bugs.

## Testing

Using the driver provided, test your MST algorithms with any undirected and weighted graph you want.
