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
