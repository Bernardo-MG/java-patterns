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
package com.wandrell.pattern.repository;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Default implementation of {@link QueryData}.
 * <p>
 * This is an immutable class, which will just store the data to be used in a
 * query, not allowing it to be edited.
 * 
 * @author Bernardo Martínez Garrido
 */
public final class DefaultQueryData implements QueryData {

    /**
     * Parameters for the query.
     * <p>
     * These will be set into the query string inside the
     * {@code com.wandrell.pattern.repository.Repository Repository}, adapting
     * it to the API being used.
     */
    private final Map<String, Object> params;
    /**
     * The string for the query.
     */
    private final String              queryStr;

    /**
     * Constructs a {@code DefaultQuery} with no parameters.
     * 
     * @param query
     *            the query string
     */
    public DefaultQueryData(final String query) {
        this(query, new LinkedHashMap<String, Object>());
    }

    /**
     * Constructs a {@code DefaultQuery} with the specified query's data.
     * <p>
     * Parameters are pairs of keys on the query and the object which will be
     * used to substitute them.
     * 
     * @param query
     *            the query string
     * @param parameters
     *            the query's parameters
     */
    public DefaultQueryData(final String query,
            final Map<String, Object> parameters) {
        super();

        checkNotNull(query, "Received a null pointer as query");
        checkNotNull(parameters, "Received a null pointer as parameters");

        queryStr = query;
        params = parameters;
    }

    @Override
    public final void addParameter(final String key, final Object value) {
        checkNotNull(key, "Received a null pointer as key");
        checkNotNull(value, "Received a null pointer as value");

        params.put(key, value);
    }

    @Override
    public final void addParameters(final Map<String, Object> parameters) {
        checkNotNull(parameters, "Received a null pointer as parameters");

        params.putAll(parameters);
    }

    @Override
    public final Map<String, Object> getParameters() {
        return Collections.unmodifiableMap(params);
    }

    @Override
    public final String getQuery() {
        return queryStr;
    }

    @Override
    public final void removeParameter(final String key) {
        params.remove(key);
    }

}
