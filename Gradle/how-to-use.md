**Introduction**
---
* Gradle gently nudges you to use Groovy DSL tailored to the task of building code.

* General-purpose coding is always available as a fallback

* Build.gradle allows you to write groovy code in itself.

* Although, the code will be a maintenance hazard, but it does help you achieve things simplistically.

* When the standard Gradle DSL doesn’t have the language to describe what you want your build to do, you can extend the DSL through plug-ins

**Getting Started**
---
* Basic build.gradle
```
task helloWorld << {
  println 'Hello World'
}
```
```
$ gradle helloWorld
```
This build.gradle defines a task that just prints a statement. Then the `gradle` commands invokes the task by calling it's name.

* Example of dependsOn
```
task hello << {
print 'hello, '
}
task world(dependsOn: hello) << {
println 'world'
}
```
```
$ gradle world
```
will produce the same result as previous one.

* `gradle tasks` gives you all the possible tasks that the user can execute.

* One can apply plugins to get more features out of it. Eg. `apply plugin: 'java'` will give you tasks like `clean`, `build`, `jar` etc.

* Few important command line options that you will use more often: `--help`, `-Dproperty=value` which sets a system property, `--info`, `--debug`, `--dry-run` or `-m`, `properties` and `tasks`

Task System
---

* Tasks are first-class citizens in the Gradle system, and have a `name` and `action` property. `Action` defines what the task will do. In our earlier build.gradle example, we had assigned `action` task using the left shift operator `<<`

* Example of appending tasks one at a time.
```
task hello
hello << {
print 'hello, '
}
hello << {
println 'world'
}
```
> This should again print `hello world`.

* Task can have a configuration block as well, which is defined by ommitting out the left shift operator.

Eg.
```
task hello
hello << {
print 'hello, '
}
hello << {
println 'world'
}
hello {
  println 'Config block'
}
```

One would assume that it prints `hello, world` first and then 'Config block', but that's not true.

> It will first execute the `Config block` during the *configuration lifecycle phase* of the task and then the `Action block` during the *execution lifecycle phase*.

The configuration block is the place to set up variables and data structures that will be
needed by the task action when (and if) it runs later on in the build.

* **Tasks are Objects** in Gradle. A task object has properties and methods just like any other object. We can even control the type of each task object, and access unique, type-specific functionality accordingly.

* By default, each new task derives from `DefaultTask` very similar to Java wherein each class descends from `Object`.
* 
