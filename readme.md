# Java Patterns
Common design patterns for Java.

Not all the design patterns have been included in this library. This is a compilation of only those which I've needed, and only if there wasn't any available implementation for them. 

For each of them an interface and, when possible, also a basic implementation are offered.

Currently the patterns included are as follows:
* Command and Executor patterns. For encapsulating code executions.
* Repository pattern. To access persistent entities as if they were on a list.
* Parser pattern. To transform a data structure into another.
* Outputter pattern. To send data structures through output operations.
* Prototype pattern. To easily create copies of existing entities.

## Webpage
Currently the project has no webpage.

### Documentation webpage
The project has a [Maven site][] and [Javadoc page][] with the information from the
latest release.

## Status
The project is still under development, so expect a certain degree of volatility.

Still, it is mostly stable, and classpath or name changes should be infrequent, but classes may be added or removed without warning.

### Issues management
Issues are managed at the GitHub [project issues page][].

## Building the code
The application is coded in Java, using Maven to handle the project's configuration and tests.

### Prerequisites
Has been tested on the following Java versions:
* JDK 7
* JDK 8
* OpenJDK 7

All other dependencies are handled through Maven, and noted in the included POM file.

### Getting the code
The code can be found at the GitHub [project page][].

To acquire it through Git use the following clone URI:

`git clone https://github.com/Bernardo-MG/Java-Patterns.git`

#### Repository
Releases can be found in the [releases repository][] on Bintray.

It can be added to Maven as a repository using the following URI:
`http://dl.bintray.com/bernardo-mg/maven`

## Continuous integration
The continuous integration information can be found at the [project CI page][] based on Travis CI.

## License
The project is released under the [MIT License][].

[Javadoc page]: http://docs.wandrell.com/maven/java-patterns/apidocs
[Maven site]: http://docs.wandrell.com/maven/java-patterns
[MIT License]: http://www.opensource.org/licenses/mit-license.php
[project CI page]: https://travis-ci.org/Bernardo-MG/Java-Patterns
[project issues page]: https://github.com/Bernardo-MG/Java-Patterns/issues
[project page]: http://github.com/Bernardo-MG/Java-Patterns
[releases repository]: http://dl.bintray.com/bernardo-mg/java-patterns