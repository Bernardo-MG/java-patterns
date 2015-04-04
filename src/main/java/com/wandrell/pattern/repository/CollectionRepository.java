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

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.LinkedList;

import com.google.common.base.Predicate;

/**
 * Collection-based implementation of {@code Repository}.
 * <p>
 * This is meant to be the most basic form of {@code Repository}, used when
 * there is no need of anything fancy such as persistence.
 * <p>
 * The {@link #getCollection(Predicate) getCollection} method works through the
 * use of a Guava {@code Predicate}. All the entities validating this predicate
 * will be returned.
 * 
 * @author Bernardo Martínez Garrido
 * @param <V>
 *            the type stored on the repository
 */
public final class CollectionRepository<V> implements
        Repository<V, Predicate<V>> {

    /**
     * The entities stored in the repository.
     */
    private final Collection<V> data;

    /**
     * Constructs a {@code CollectionRepository} using a {@code LinkedList} as
     * the {@code Collection}.
     */
    public CollectionRepository() {
        this(new LinkedList<V>());
    }

    /**
     * Constructs a {@code CollectionRepository} with the specified
     * {@code Collection}.
     * 
     * @param collection
     *            the data to store
     */
    public CollectionRepository(final Collection<V> collection) {
        super();

        checkNotNull(collection, "Received a null pointer as collection");

        this.data = collection;
    }

    @Override
    public final void add(final V entity) {
        getData().add(entity);
    }

    @Override
    public final Collection<V> getCollection(final Predicate<V> filter) {
        final Collection<V> result;

        result = new LinkedList<V>();
        for (final V entity : getData()) {
            if (filter.apply(entity)) {
                result.add(entity);
            }
        }

        return result;
    }

    @Override
    public final V getEntity(final Predicate<V> filter) {
        final Collection<V> entities;
        final V entity;

        entities = getCollection(filter);

        if (entities.isEmpty()) {
            entity = null;
        } else {
            entity = entities.iterator().next();
        }

        return entity;
    }

    @Override
    public final void remove(final V entity) {
        getData().remove(entity);
    }

    @Override
    public final void update(final V entity) {
        if (getData().contains(entity)) {
            remove(entity);
            add(entity);
        }
    }

    /**
     * Returns the entities being stored.
     * 
     * @return the entities being stored.
     */
    private final Collection<V> getData() {
        return data;
    }

}
