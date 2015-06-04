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

import java.util.Map;

/**
 * Interface for filtering a
 * {@link com.wandrell.pattern.repository.FilteredRepository FilteredRepository}
 * when queries are required.
 * <p>
 * Usually this will be a {@code FilteredRepository} taking data from an SQL
 * database.
 * <p>
 * The query and it's parameters are separated. That way the actual query to be
 * used will be built inside the {@code FilteredRepository}, using whatever API
 * or implementation it requires.
 * 
 * @author Bernardo Martínez Garrido
 * @see com.wandrell.pattern.repository.Repository Repository
 */
public interface QueryData {

    /**
     * The parameters to be applied to the query.
     * 
     * @return the query's parameters
     */
    public Map<String, Object> getParameters();

    /**
     * The query for creating a subset of the {@code Repository} entities.
     * 
     * @return the query for acquiring the entities subset
     */
    public String getQuery();

}
