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
 * Interface for the repository pattern. Offers a way to apply CRUD operations
 * into a collection of instances taken from a hidden source.
 * <p>
 * This serves as a way to handle persistent data, without needing to know where
 * that data persists.
 * <p>
 * Most of the methods on this interface are very simple, but the one used for
 * reading, the {@link #getCollection getCollection(Filter)} method, has a bit
 * of additional complexity due it's use of {@link Filter Filter}.
 * <p>
 * And still that one is too very straightforward, as that method will just
 * return all the entities which validate the received {@code Filter}.
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
     * It is used to indicate which properties are wanted on the entities
     * acquired from a {@code Repository}.
     * 
     * @author Bernardo Martínez Garrido
     *
     * @param <V>
     *            the type stored on the repository
     */
    public interface Filter<V> {

        /**
         * Checks if the entity is acceptable or not.
         * <p>
         * This is used to check that the entity has a precise series of
         * properties.
         * <p>
         * For example, it can check that the entity has a concrete name.
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
     * {@code Filter}. All the entities validating this filter will be returned.
     * 
     * @param filter
     *            the {@code Filter} to be applied on the entities
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
