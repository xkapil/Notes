## Above Basics about Python

* OOPS
  * Not private and protected

  * Constructor is optional

```
class TestClass:

    def __init__(self, dept = "Finance"):
        self.department = dept

    def set_name(self, new_name):
        self.name = new_name

    def get_dept(self):
        return self.department

t = TestClass()
t2 = TestClass("HR")
print t.get_dept()
```

* Functional language
  * Pure Functions have no side-effects
  * Python isn't a pure functional language but it has the aspects of a functional programming eg. functions are first class citizens and can be passed around.
  * FP causes one to think very heavily about chaning operations together. This is underlying theme in data science as well.

* Map - a built-in function in Python

  * `map(function, iterable,...)` - The map function takes a function as an argument which is applied to all the elements of the iterable.

  * Example - square every element in list

  ```
    l1 = [1,2,3,4]

    def squaer(x):
      return x*x

    l2 = map(squaer, l1)
    print l2 # would print square of all elements in the list

  ```

  * Example - find minimum among two lists

  ```
    l1 = [1,2,3,4]
    l2 = [4,3,2,1]

    l3 = map(min, l1, l2) # using in-built min function which takes 2 arguments

    print l3 # would print [1,2,2,1]

  ```

  * This passing around of functions is hallmark of functional programming.

* Python Lambdas
  * A way to define an anonymous single line functions.
  * It can accept any number of parameters, but can have single expression in the body.
  * One can't have default values for parameters
  * Example:
  ```
    square = lambda x: x*x # a square function
    add_three = lambda a,b,c: a+b+c  
  ```
  * Refactoring the map example of squaring numbers in list which we just saw a while back

  ```
    l1 = [1,2,3,4]

    l2 = map(lambda x: x*x, l1)
    print l2 # would print square of all elements in the list

  ```

* List Comprehensions
  * Example:
  ```
    even_list = []
    for number in range(0, 1000):
      if number % 2 == 0:
          even_list.append(number)

    print even_list

  ```

  * An abbreviated way using list comprehension will look like:
  ```
  even_list = [number for number in range(0, 1000) if number % 2 == 0]
  ```
