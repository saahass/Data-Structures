# Assignment 3 - Document Object Model (DOM) Tree

Please adhere to [Rutgers University's Academic Integrity Policy](http://academicintegrity.rutgers.edu/academic-integrity-policy/) and the [Department of CS Academic Integrity Policy](https://www.cs.rutgers.edu/academic-integrity/programming-assignments).

## Background

There are two packages:
1. **APP**
    - DOM: This is the application driver that uses an html file to create a DOM Tree with the code that is in it.     
      For example with the given html file:
      ```
      <html>
        <body>
          The cat in the hat.
        </body>
      </html>
      ```
      The corresponding DOM tree would look as such:
      ```
      html - \
        |
       body - \
         |
        The cat in the hat. - \
                        |
                        \
      ```

2. **STRUCTURES**
    - Stack: The only other data structure one is allowed to use (besides the ones preset in Java)
    - TagNode: An object illustrating a tagnode with a string as the data and a pointer to the first and second child.
    - Tree: This class creates the DOM tree with the help of methods that need to be completed
  
## Assignment

Inside Tree.java, the methods that need to be completed is

1. **build:** Builds the DOM tree with the given html file
2. **replaceTag:** replaces all occurrences of an old tag with the new tag
3. **boldRow:** bolds an entire row of a table that is targetted
4. **removeTag:** removes all occurrences of a specific tag
5. **addTag:** adds a tag to the tree

One must work inside these methods only, but declaring private methods inside Tree.java is allowed. One can assume that 
the first two tags will always be "html" and "body".

My implementation involves six private methods `addRec`, `buildRec`, `print`, `removeRec`, and `replaceRec`.

## Testing

Use any html file with the first two tags being "html" and "body". The file should only include one table. Build the 
tree and make any changes you would like to see be made to the tree (adding, deleting, and replacing tags).
