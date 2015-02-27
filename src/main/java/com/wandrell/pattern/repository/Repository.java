/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2014 the original author or authors.
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
package com.wandrell.pattern.repository;

import java.util.Collection;

/**
 * Interface for the repository pattern.
 * <p>
 * Implementations of this interface contain a set of entities. The source of
 * this data does not matter, as one of the uses of this interface is hiding it.
 * <p>
 * These entities can be easily modified or queried with the interface's
 * methods, which allow CRUD operations.
 * <p>
 * Most of the methods related to these operations are very simple, but the one
 * used for reading, the {@link #getCollection} method, has a bit of additional
 * complexity due to the use of {@code Predicate}.
 * <p>
 * Still, it is very straightforward. The method will return all the entities
 * which validate the received {@code Predicate}.
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @param <V>
 *            the type stored on the repository
 */
public interface Repository<V> {

    /**
     * Filter for querying repositories.
     * <p>
     * All the entities which validate this filter will be returned by
     * {@code getCollection}.
     * 
     * @author Bernardo Martínez Garrido
     *
     * @param <V>
     *            the type stored on the repository
     */
    public interface Filter<V> {

        /**
         * Checks if the entity is to be accepted or not.
         * <p>
         * If it is accepted, then it should be returned by the
         * {@code getCollection} method.
         * 
         * @param entity
         *            the entity to check
         * @return {@code true} if the entity is valid, {@code false} otherwise
         */
        public Boolean isValid(final V entity);

    }

    /**
     * Adds an entity to the repository.
     * 
     * @param entity
     *            the entity to add
     */
    public void add(final V entity);

    /**
     * Queries the entities in the repository and returns a collection of them.
     * <p>
     * The collection is created filtering the stored data with the specified
     * {@code Filter}.
     * <p>
     * This works the same as filtering a {@code Collection}. All the entities
     * validating the {@code Filter} will be returned.
     * 
     * @param filter
     *            the {@code Filter} used on the filter
     * @return the filtered subset of entities
     */
    public Collection<V> getCollection(final Filter<V> filter);

    /**
     * Removes an entity from the repository.
     * 
     * @param entity
     *            the entity to remove
     */
    public void remove(final V entity);

    /**
     * Updates an entity on the repository.
     * 
     * @param entity
     *            the entity to update.
     */
    public void update(final V entity);

}
