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
There is documentation both for the latest release and the latest snapshot.

Information for the latest release can be consulted at the [documentation page][site-release] and the [Javadoc site][javadoc-release].

The latest information for the 'develop' branch, from which the snapshots are created, can be found at the [development documentation page][site-develop] and the [development Javadoc site][javadoc-develop].

As these pages are generated through the Maven site command, it is always possible to generate them from the source code taken from the repository.

## Status
The project is still under development, so expect a certain degree of volatility.

Still, it is mostly stable, and classpath or name changes should be infrequent, but classes may be added or removed without warning.

### Issues management
Issues are managed at the GitHub [project issues page][issues].

## Building the code
The application is coded in Java, using Maven to handle the project's configuration and tests.

### Prerequisites
Has been tested on the following Java versions:
* JDK 7
* JDK 8
* OpenJDK 7

All other dependencies are handled through Maven, and noted in the included POM file.

### Getting the code
The code can be found at the [GitHub project page][scm].

#### Repository
Releases can be found in the [releases repository][releases-repo] on Bintray.

## Continuous integration
The continuous integration information can be found at the [project CI page][ci] based on Travis CI.

## License
The project is released under the [MIT License][license].

[ci]: https://travis-ci.org/Bernardo-MG/Java-Patterns
[issues]: https://github.com/Bernardo-MG/Java-Patterns/issues
[javadoc-develop]: http://docs.wandrell.com/development/maven/java-patterns/apidocs
[javadoc-release]: http://docs.wandrell.com/maven/java-patterns/apidocs
[license]: http://www.opensource.org/licenses/mit-license.php
[releases-repo]: http://dl.bintray.com/bernardo-mg/java-patterns
[scm]: http://github.com/Bernardo-MG/Java-Patterns
[site-develop]: http://docs.wandrell.com/development/maven/java-patterns
[site-release]: http://docs.wandrell.com/maven/java-patterns