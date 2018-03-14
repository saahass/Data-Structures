# Assignment 1 - Polynomial

Please adhere to [Rutgers University's Academic Integrity Policy](http://academicintegrity.rutgers.edu/academic-integrity-policy/) and the [Department of CS Academic Integrity Policy](https://www.cs.rutgers.edu/academic-integrity/programming-assignments).

## Background

**Class Term:** Provides an object "term" that has a float "coeff" and an int "degree". For example, a term could be `"4x^3"`.

**Class Node:** The baseline for a linked list containing the object "term" and Node "next". For example, a node contains the term `"4x^3"` but links to another node that contains another term `"3x^9"`.

**Class PolyTest:** The driver for this application.

**Class Polynomial:** A polynomial is the linked list containing the object Node. For example, a Polynomial is `"3x^9 + 4x^3 - 5x^2"`.

## Assignment

Inside Polynomial.java, the methods that need to be completed is

1. **ADD:** adding two polynomials 
2. **MULTIPLY:** multiplying two polynomials
3. **EVALUATE:** evaluating a single polynomial with a given assignment to the variable

One must work inside these methods only, but declaring private methods inside Polynomial.java is allowed.

My implementation involves two private methods `order` and `likeTerms`.

## Testing

In order to test, you must create txt files with the format as such:  `<coeff> <degree>`

In order to implement multiple terms in a polynomial, simply go to the next line to create a new term in your polynomial.
Here is one example:
```
4 3
3 9
-5 2
```
This polynomial, if printed, will look like `"3x^9 + 4x^3 - 5x^2"`.
