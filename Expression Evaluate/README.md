# Assignment 2 - Expression Evaluate

Please adhere to [Rutgers University's Academic Integrity Policy](http://academicintegrity.rutgers.edu/academic-integrity-policy/) and the [Department of CS Academic Integrity Policy](https://www.cs.rutgers.edu/academic-integrity/programming-assignments).

## Background

There are two packages:
1. **APP**
    - Class Array: an Array has a String "name" and an int array of "items". 
    - Class Variable: a Variable has a String "name" and an int "value". 
    - Class Expression: an expression is a mathematical expression with any combination of numbers, parenthesis, object Array, and object Variable. For example, '(1+3)/2-a*A[2]' is a valid expression.
    - Class Evaluator: the driver for this application

2. **STRUCTURES**
    - Stack: The only other data structure one is allowed to use (besides the ones preset in Java)
  
## Assignment

Inside Expression.java, the methods that need to be completed is

1. **makeVariableList:** creating a list of Variables and Arrays based on the given expression
2. **evaluate:** evaluate the given expression by accessing the Variables or Arrays if needed

One must work inside these methods only, but declaring private methods inside Expression.java is allowed.

My implementation involves six private methods `hasPriority`, `math`, `contains`, `doubleNegatives`, `hasNegativeDigit`, and `negativeDigit`.

## Testing

Inside the file, if you want to create a variable, you must formate it as such:  `<name> <value>`

If you want to create an array, you must format it as such: `<name> <array size>`
By default, all values at all indices in an array are set to 0. 
To assign values to a certain index type `(<items index>,<items index valua>)` after `<name> <array size>`. 
For additional assignment, repeat the previous step, assigning it after the previous assignment.

Here is one example:
```
A 3
bee -9
Sea 4 (0,10) (3,2)
d 2 (0,1) (1,2)
```

This could be interpretted as:
```
A = 3
bee = -9
Sea = [10, 0, 0, 2, 0]
d = [1, 2]
```
