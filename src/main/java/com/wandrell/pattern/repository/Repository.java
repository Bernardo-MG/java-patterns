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

import com.google.common.base.Predicate;

/**
 * Interface for the repository pattern. Offers a way to apply CRUD operations
 * into a collection of instances taken from a hidden source.
 * <p>
 * This serves as a way to handle persistent data, without needing to know where
 * that data persists.
 * <p>
 * Most of the methods on this interface are very simple, but the one used for
 * reading, the {@link #getCollection(Object) getCollection} method, has a bit
 * of additional complexity due it's requirement of a filter.
 * <p>
 * This method receives a class containing the required information to build a
 * subset of entities, created from those in the repository. How this works, and
 * which structure will be used as a filter, will depend on the implementation.
 * 
 * @author Bernardo Martínez Garrido
 * @param <V>
 *            the type stored on the repository
 * @param <V>
 *            the type being used to filter the entities
 * @see Predicate
 */
public interface Repository<V, F> {

    /**
     * Adds an entity to the repository.
     * 
     * @param entity
     *            the entity to add
     */
    public void add(final V entity);

    /**
     * Queries the entities in the repository and returns a subset of them.
     * <p>
     * The collection is created filtering the stored data with the specified
     * filter.
     * <p>
     * How this filter exactly work will depend on the implementation, it may be
     * an structure containing information to build an SQL query, or may just be
     * a predicate which the entities should validate.
     * 
     * @param filter
     *            the filter which discriminates the entities to be returned
     * @return the filtered subset of entities
     */
    public Collection<V> getCollection(final F filter);

    /**
     * Queries the entities in the repository and returns only one.
     * <p>
     * The collection is created filtering the stored data with the specified
     * filter.
     * <p>
     * How this filter exactly work will depend on the implementation, it may be
     * an structure containing information to build an SQL query, or may just be
     * a predicate which the entities should validate.
     * <p>
     * Unlike {@link #getCollection(Object) getCollection}, which returns all
     * the entities chosen by the filter, this method will return only one of
     * them, usually the first.
     * <p>
     * If no entity is found, then {@code null} will be returned.
     * 
     * @param filter
     *            the filter which discriminates the entities to be returned
     * @return a single entity found by the filter, or {@code null} is no entity
     *         is found
     */
    public V getEntity(final F filter);

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
