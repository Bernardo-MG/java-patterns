# Repository

A repository allows accessing data through CRUD operations in a similar way to a DAO. The difference is that while a DAO is meant to handle a single entity the repository works as if it were a collection of persistent entities.

## Interface

![Repository interface][repository-class_tree]

The [Repository][repository] is meant to hide the data access, so the code works only with domain model classes. It offers basic CRUD operations, and implementations of it may extend the interface and add methods as required.

An extension of this basic interface, [FilteredRepository][filtered_repository] allows executing queries on the entities.

The _getCollection_ and _getEntity_ methods take care of this. The first will return a subset of the entities contained in the repository, while the second will return a single one. 

Any object can be used as a filter for the queries, but it is expected to be able to work as such. A predicate which the entities to return must validate, or an SQL query are examples of it, but the actual object, and how it is used, will depend on the implementation.

### The Query filter

![Query class tree][query_data-class_tree]

Accompanying the repositories there is the [QueryData][query_data] interface.

It is meant to be used on repositories which depend on a database, usually an SQL database. Using this interface as a filter it is possible to give the repository all the data to build a query, and then get the result from it.

To ease it's use a basic implementation, [DefaultQueryData][default_query_data], is included.

## Collection Repository

![Collection repository class tree][collection_repository-class_tree]

A basic implementation of the repository, [CollectionRepository][collection_repository], is offered. This serves as a stub to avoid using a persistence system, or for those cases where a complexrepository is not needed, as it will just use a _Collection_ to store all the entities.

This repository queries the entities through the use of a Guava [Predicate][predicate], used instead of Java 8 own _Predicate_ to keep backwards compatibility. All the entities which make this predicate true will be returned.

[repository]: ./apidocs/com/wandrell/pattern/repository/Repository.html
[repository-class_tree]: ./images/repository_class_tree.png
[filtered_repository]: ./apidocs/com/wandrell/pattern/repository/FilteredRepository.html
[query_data]: ./apidocs/com/wandrell/pattern/repository/QueryData.html
[query_data-class_tree]: ./images/query_class_tree.png
[default_query_data]: ./apidocs/com/wandrell/pattern/repository/DefaultQueryData.html
[collection_repository]: ./apidocs/com/wandrell/pattern/repository/CollectionRepository.html
[collection_repository-class_tree]: ./images/collection_repository_class_tree.png
[predicate]: http://docs.guava-libraries.googlecode.com/git/javadoc/com/google/common/base/Predicate.html