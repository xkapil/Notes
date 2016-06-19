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
* `DefaultTask` doesn't really performs any task, but it do have methods and properties to interact with the Gradle project model.
* Methods of `DefaultTask`:
  * **dependsOn(task)**

    Different ways to use this method:
    ```
    // Declare that world depends on hello
    // Preserves any previously defined dependencies as well
    task world {
      dependsOn hello
    }
    // An alternate way to express the same dependency
    task world {
      dependsOn << hello
    }
    // Do the same using single quotes (which are usually optional)
    task world {
      dependsOn 'hello'
    }
    // Explicitly call the method on the task object
    task world
    world.dependsOn hello
    // A shortcut for declaring dependencies
    task world(dependsOn: hello)
    ```

    Having multiple dependencies:
    ```
    // Pass dependencies as a variable-length list
    task world {
    dependsOn hello, hello2
    }
    // Declare dependencies one at a time
    task world {
    dependsOn << hello
    dependsOn << hello2
    }
    // Explicitly call the method on the task object, and pass variable-length list
    task world
    world.dependsOn hello, hello2
    // A shortcut for dependencies only
    // Note the Groovy list syntax
    task world(dependsOn: [hello, hello2])
    ```
  * **doFirst(closure)**

    Adds a block of executable code to the beginning of a task’s action. One can invoke it multiple times, and each time a closure will be appended to the beginning of the *execution lifecycle phase*.

    Different ways to invoke the said method:

    ```
    // Actual Task named world
    task world {
      print ', World!'
    }
    // Approach 1 - Calling the method on the task object
    world.doFirst {
      print 'Hello'
    }
    // Approach 2 - Calling the doFirst method inside the task’s configuration block
    world {
      doFirst {
        println 'create schema'
      }
    }
    ```
    Repeated calls to the `doFirst` method are additive. Each previous call’s action code is retained, and the new closure is appended to the start of the list to be executed in order.
  * **doLast(closure)**
    Exactly similar to `doFirst()`
  * **onlyIf(closure)**
  The `onlyIf` method allows you to express a predicate which determines whether a task should be executed. The value of the predicate is the value returned by the closure. Using this method, you can disable the execution of a task which might otherwise run as a normal part of the build’s dependency chain.
  ```
  task createSchema << {
    println 'create database schema'
  }
  task loadTestData(dependsOn: createSchema) << {
    println 'load test data'
  }
  loadTestData.onlyIf {
    System.properties['load.data'] == 'true'
  }
  ```
  Below are two varied invocations of the above build file.
  ```
  $ gradle loadTestData
  create database schema
  :loadTestData SKIPPED
  // second invocation with the System property
  $ gradle -Dload.data=true loadTestData
  :createSchema
  create database schema
  :loadTestData
  load test data
  ```
* Properties of `DefaultTask`:
  * Prop1

  * Prop 2
