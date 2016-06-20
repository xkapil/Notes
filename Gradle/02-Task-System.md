## Task System

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

One would assume that it prints `hello, world` first and then `Config block`, but that's not true.

> It will first execute the `Config block` during the *configuration lifecycle phase* of the task and then the `Action block` during the *execution lifecycle phase*.

The configuration block is the place to set up variables and data structures that will be
needed by the task action when (and if) it runs later on in the build.

* **Tasks are Objects** in Gradle. A task object has properties and methods just like any other object. We can even control the type of each task object, and access unique, type-specific functionality accordingly.

* By default, each new task derives from `DefaultTask` very similar to Java wherein each class descends from `Object`.
* `DefaultTask` doesn't really performs any task, but it do have methods and properties to interact with the Gradle project model.
---
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
---
* Properties of `DefaultTask`:
  * `didWork` - A boolean property indicating whether the task completed successfully.
  * `enabled` - A boolean property indicating whether the task will execute. `world.enabled = false`
  * `path` - A string property containing the fully qualified path of a task. By default, a task’s path is simply the name of the task with a leading colon.
  ```
  task hello << {
    println "THIS TASK'S PATH IS ${path}"
  }
  $ gradle hello
  THIS TASK'S PATH IS :hello
  ```
  The leading colon indicates that the task is located in the top-level build file. However, for a given build, not all tasks must be present in the top-level build file, since Gradle supports dependent subprojects, or nested builds. If the task existed in a nested build called subProject , then the path would be *:subProject:hello*.
  * `logger` - A reference to the internal Gradle logger object. The Gradle logger implements the `org.slf4j.Logger`  interface, but with a few extra logging levels added. Supported logging levels are:
  * `logging`
  * `description`
  * `temporaryDir` - The `temporaryDir` property returns a `File` object pointing to a temporary directory belonging to this build file.

* Dynamic Properties - A task object functions like a hash map, able to contain whatever other arbitrary prop-
erty names and values we care to assign to it (as long as the names don’t collide with
the built-in property names).
```
task copyFiles {
  // Dynamic property
  fileManifest = [ 'data.csv', 'config.json' ]
}
task createArtifact(dependsOn: copyFiles) << {
  println "FILES IN MANIFEST: ${copyFiles.fileManifest}"
}
$ gradle createArtifact
FILES IN MANIFEST: [data.csv, config.json]
```
---
* Task Types - Declaring a task type is a lot like extending a base class in an object-oriented programming language. Here are few examples:
  * Copy - It copies files from one directory into another, with optional restrictions on which file patterns are included or excluded.
  ```
  task copyFiles(type: Copy) {
    from 'resources'
    into 'target'
    include '**/*.xml', '**/*.txt', '**/*.properties'
  }  
  ```
  Note that the `from` , `into` , and `include` methods are inherited from the `Copy`

  * Jar - It creates a jar file from source set. Java plugin contains this task.
  ```
  apply plugin: 'java'
  task customJar(type: Jar) {
    manifest {
        attributes firstKey: 'firstValue', secondKey: 'secondValue'
      }
      archiveName = 'hello.jar'
      destinationDir = file("${buildDir}/jars")
      from sourceSets.main.classes
  }
  ```
  The contents of the JAR are identified by the `from sourceSets.main.classes` line, which specifies that the compiled .class files of the main Java sources are to be included. The `from` method is identical to the one used in the `CopyTask` example, which reveals an interesting insight: the `Jar` task extends the `Copy` task.
  The expression being assigned to `destinationDir` is worth noting. It would be natural just to assign a string to `destinationDir`, but the property expects an argument compatible with `java.io.File`. The `file()` method, which is always available inside a Gradle build file, converts the string to a `File` object.

  * JavaExec - It runs a java class with a `main()`.
  ```
  apply plugin: 'java'
  repositories {
      mavenCentral()
  }
  dependencies {
    runtime 'commons-codec:commons-codec:1.5'
  }
  task encode(type: JavaExec, dependsOn: classes) {
    main = 'org.gradle.example.commandline.MetaphoneEncoder'
    args = "The rain in Spain falls mainly in the plain".split().toList()
    classpath sourceSets.main.classesDir
    classpath configurations.runtime
  }
  ```
