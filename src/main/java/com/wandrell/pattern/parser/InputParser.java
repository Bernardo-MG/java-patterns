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
package com.wandrell.pattern.parser;

import java.io.InputStream;
import java.io.Reader;

/**
 * Interface for parsing Java objects from input operations in a generic way,
 * hiding the actual API being used. For handling output operations check
 * {@link OutputParser}.
 * <p>
 * The parsing is expected to return an object parsed from the input, and to
 * close that same input once the operation is finished.
 * <p>
 * Nothing more is required from the implementations of this interface, as it is
 * meant to serve as an adapter for any API.
 * <p>
 * That is the same reason for using generic exceptions on the reading methods.
 * As the API is not known, the exceptions being thrown are also unknown.
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @param <V>
 *            the type to be parsed from the input
 */
public interface InputParser<V> {

    /**
     * Parses an object from an {@code InputStream}.
     * <p>
     * The stream is expected to be closed once the operation is finished.
     * 
     * @param stream
     *            stream with the data to be parsed
     * @return an object parsed from the stream
     * @throws Exception
     *             when reading causes an error
     */
    public V read(final InputStream stream) throws Exception;

    /**
     * Parses an object from a {@code Reader}.
     * <p>
     * The reader is expected to be closed once the operation is finished.
     * 
     * @param reader
     *            reader with the data to be parsed
     * @return an object parsed from the reader
     * @throws Exception
     *             when reading causes an error
     */
    public V read(final Reader reader) throws Exception;

}
