# Java Patterns
A collection of common design patterns for Java.

Not all the design patterns have been included in this library. This is a compilation of only those which I've needed, and only if there wasn't any available implementation for them. 

For each of them an interface and, when possible, also a basic implementation are offered.

Currently the patterns included are as follows:
- The **Command**, and the **CommandExecutor**, help to encapsulate code inside an object.
- The **Outputter** allows writing for files, or just sending structures, without worrying about the actual implementation.
- The **Parser** transforms one object into another, keeping as much of the data intact as possible.
- The **Prototype** permits creating an identical copy of a base template.
- The **Repository** allows handling data persistence as if you were working on a collection.

## Documentation
Documentation is always generated for the latest release:

- The [latest release documentation page][site-release].
- The [the latest release Javadoc site][javadoc-release].

Documentation is also generated from the latest snapshot, taken from the 'develop' branch:

- The [the latest snapshot documentation page][site-develop].
- The [the latest snapshot Javadoc site][javadoc-develop].

The site sources come along the source code, so it is always possible to generate them using the Maven site command.

## Status
The current version is 0.2.0

### Issues management
Issues are managed at the GitHub [project issues page][issues].

## Building the code
The application is coded in Java, using Maven to handle the project's configuration and tests.

### Prerequisites
The project has been tested on the following Java versions:
* JDK 7
* JDK 8
* OpenJDK 7

All other dependencies are handled through Maven, and noted in the included POM file.

### Getting the code
The code can be found at the [GitHub project page][scm].

## License
The project is released under the [MIT License][license].

[issues]: https://github.com/Bernardo-MG/Java-Patterns/issues
[javadoc-develop]: http://docs.wandrell.com/development/maven/java-patterns/apidocs
[javadoc-release]: http://docs.wandrell.com/maven/java-patterns/apidocs
[license]: http://www.opensource.org/licenses/mit-license.php
[scm]: http://github.com/Bernardo-MG/Java-Patterns
[site-develop]: http://docs.wandrell.com/development/maven/java-patterns
[site-release]: http://docs.wandrell.com/maven/java-patterns