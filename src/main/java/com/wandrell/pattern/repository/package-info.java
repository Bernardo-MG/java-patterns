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
 * This pattern is similar to a DAO, it allows accessing data through CRUD
 * operations, hiding the source if this data.
 * <p>
 * The main difference is when accessing this data. While the DAO operates in a
 * case by case way, the repository works like a collection which can be
 * searched.
 * <p>
 * The {@link com.wandrell.pattern.repository.Repository Repository} interface
 * achieves this through the
 * {@link com.wandrell.pattern.repository.Repository#getCollection getCollection}
 * method, which makes use of the Java 8 {@code Predicate} to return a subset of
 * the repository's data.
 * <p>
 * A basic implementation,
 * {@link com.wandrell.pattern.repository.CollectionRepository
 * CollectionRepository}, offers a simple and fast way of creating the simplest
 * possible repository.
 */
package com.wandrell.pattern.repository;

