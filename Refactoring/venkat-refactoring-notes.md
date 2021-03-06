Notes from Venkat's Refactoring tech talk
---

The other day, I was listening to Venkat Subramaniam Tech Talk on Refactoring, and thought of jotting down the points that I can recollect today.

* Refactoring improves *code quality*.

* Why bother improving quality, when customer doesn't sees the code? Because, one cannot be agile if the code sucks.

* Nobody can write wonderful code in one go. Code improvement (or Refactoring) is iterative in nature, and should be done collectively.

* Easier to maintain. A good software goes through more maintenance cycles (new releases with new features).

* Programs must be written for programmers to read.

* Frederick Brook said "Go ahead and build a s/w, and when you are done with it, throw it away. Try again, you might get it right."

* Throw/Refactor everything you do every few minutes. It can be hard, but approach it well.

* Knock dependency before you try to Mock dependency.

* Fears while refactoring:

  * What if I break a working piece? Automated tests should handle this fear.
  * Is my change worse than the original code? Do it collectively, and take colleagues opinions. Pair programming.
  * It works. Why should I mess with it?

* Principles:
  * Code should be simple and clear. Remember, simple is hard.
  * Reduce code. A great programmer avoids writing code.
  * Dont try to write clever code
  * Cohesion. SRP. Avoid long methods. Methods should fit in single screen
  * No Comments
  * Methods should be at different levels of abstraction. *What did you do over weekend* was a classy example, that explains the point precisely.
  * Take extremely small steps
  * Frequent check-in
  * Have courage to throw the code and re-start.
