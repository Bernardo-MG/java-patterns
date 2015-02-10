# Wandrell's Java Patterns
Library with various design patterns for Java.

The patterns currently included are:
* An API and basic implementation for the command and executor pattern.
* An API and basic implementation for the repository pattern.
* An API and basic implementations for the parser pattern.

### Webpage
Currently the project has no webpage.

#### Documentation webpage
The project has a [Maven site][] and [Javadoc page][] with the information from the
latest release.

### Status
The project is still under development, so expect a certain degree of volatility.

Still, it is mostly stable, and classpath or name changes should be infrequent, but classes may be added or removed without warning.

#### Issues management
Issues are managed at the GitHub [project issues page][].

## Building the code
The application is coded in Java, using Maven to handle the project's configuration and tests.

### Prerequisites
Requires JDK 8.

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