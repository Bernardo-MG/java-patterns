/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
/**
 * Provides interfaces and basic implementations for the repository pattern.
 * <p>
 * This pattern is similar to a DAO, as it allows accessing data through CRUD
 * operations, hiding the source of this data.
 * <p>
 * It can be hard to find differences between a DAO and a repository, but the
 * first is used as an interface between the application and a table on a
 * database, while the second is a domain driven pattern, which may or not be
 * linked to a database.
 * <h2>Interfaces</h2>
 * <p>
 * The {@link com.wandrell.pattern.repository.Repository Repository} interface
 * achieves this through the
 * {@link com.wandrell.pattern.repository.Repository#getCollection(Object)
 * getCollection} method, which through the use of a filter class will return a
 * specific subset of the repository's entities.
 * <p>
 * How this works, and which class will be used depends on each implementation.
 * <h2>Implementations</h2>
 * <p>
 * A basic implementation,
 * {@link com.wandrell.pattern.repository.CollectionRepository
 * CollectionRepository}, offers a simple and fast way of creating the simplest
 * possible repository.
 * <p>
 * It stores entities inside a {@code Collection}, and filters them through
 * Guava classes, specifically it requires a {@code Predicate} which the
 * entities to be returned should validate.
 */
package com.wandrell.pattern.repository;

