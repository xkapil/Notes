## **Introduction**

* Gradle gently nudges you to use Groovy DSL tailored to the task of building code.

* General-purpose coding is always available as a fallback

* Build.gradle allows you to write groovy code in itself.

* Although, the code will be a maintenance hazard, but it does help you achieve things simplistically.

* When the standard Gradle DSL doesn’t have the language to describe what you want your build to do, you can extend the DSL through plug-ins

## **Getting Started**

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

* You can use `gradle -b custom-build.gradle` if the build file name is different than *build.gradle*
