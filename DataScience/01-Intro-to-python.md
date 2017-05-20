## Introduction to Python

#### Functions

* Function can be assigned to a variable

```
  def add(x,y):
    return x+y

  a = add #assigning the function to a variable
  a(2,3) #5
```
* Function can have default values

```
  def add(x,y=3):
    return x+y

  a = add #assigning the function to a variable
  a(2) #5
```

* A built-in type function which returns the type of the variable

```
  x = "This is string"
  type(x) #string

  def my_function():
    return 5 + 4

  type(my_function) #Function

```

#### Built-in Types

* Tuples
* Lists
* Dictionary


* Tuples
  * Immutable
  * Items in order
  * Types can be mixed
  * Iterable

```
  x = (1, "a", 10, 'b')
  type(x) #Tuple

```

  * Unpacking in Tuples

  ```
    x = ("firstname", "lastname", age)
    fname, lname, age = x #this will unpack values from tuple into separate vars
  ```

* Lists
  * Iterable

```
  x = [1, "a", 10, 'b']
  type(x) #List

  for item in x:
    print item

  x.append("ha")

  print len(x) #5

  [1, 2] + [3, 4] will return [1, 2, 3, 4]

```
  * A string is nothing but list of charcters. Hence, all list operations will work on string as well
  * Slicing is very useful with string and is core to the python laguage. It's very useful in data science.

```
  x = "This is string"
  x[0] #T
  x[0:2] #Th
  x[-1] #g
  x[:3] #Thi
  x[3:] #s is string

```

* Dictionary
  * No order
  * key-value

```
my_dict = {"key1": "value1", "key2":"value2"}
my_dict["key3"] = "value3" #will append another key

for k in my_dict:
  print k #will print all keys

for k in my_dict.keys():
  print k #will print all keys

for v in my_dict.values():
  print v #will print all values

for k, v in my_dict.items():
  print k
  print v
```

* Supports Unicode (UTF) which can be used to represent million characters including foreign languages, mathematical symbols, emojis etc

* Formatting

```
print "{} is my name, and my age is {}".format(name, age)
```
