## Custom Task

* Many a times, you would want to create your own custom tasks.

* Let's write a Custom Task that will execute a sql query on MySQL Database through `build.gradle`. The code is located [here](03-Custom-Task)

* Adding a custom task keeps our `build.gradle` clean and readable. We can extend `DefaultTask` or any other exiting `task` class to create a custom one. We need to define a method annotated with `@TaskAction` to tell gradle the method/action to be executed. The code goes under `buildSrc` directory which is compiled and added to the classpath.

* We can also put this code in a custom plugin, which we will learn very soon.
