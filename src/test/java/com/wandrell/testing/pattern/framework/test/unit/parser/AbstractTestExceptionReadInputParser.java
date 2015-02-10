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
package com.wandrell.testing.pattern.framework.test.unit.parser;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.Reader;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.InputParser;

/**
 * Abstract unit tests for {@link InputParser}, checks that exceptions are
 * thrown when errors occur during reading.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>An {@code Exception} is thrown when reading from a closed
 * {@code InputStream}.</li>
 * <li>An {@code Exception} is thrown when reading from a closed {@code Reader}.
 * </li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @see InputParser
 */
public abstract class AbstractTestExceptionReadInputParser<V> {

    /**
     * Parser being tested.
     */
    private final InputParser<V> parser;

    /**
     * Constructs the test.
     * 
     * @param parser
     *            parser to test
     */
    public AbstractTestExceptionReadInputParser(final InputParser<V> parser) {
        super();

        checkNotNull(parser, "Received a null pointer as parser");

        this.parser = parser;
    }

    /**
     * Tests that an {@code Exception} is thrown when reading from a closed
     * {@code InputStream}.
     * 
     * @throws Exception
     *             always, as part of the test
     */
    @Test(expectedExceptions = Exception.class)
    public final void testRead_InputStream_Closed_ThrowsException()
            throws Exception {
        InputStream stream = null; // Stubbed stream

        try {
            stream = new PipedInputStream();
            stream.close();
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }

        parser.read(stream);
    }

    /**
     * Tests an {@code Exception} is thrown when reading from a closed
     * {@code Reader}.
     * 
     * @throws Exception
     *             always, as part of the test
     */
    @Test(expectedExceptions = Exception.class)
    public final void testRead_Reader_Closed_ThrowsException() throws Exception {
        Reader reader = null; // Stubbed reader

        try {
            reader = new InputStreamReader(new PipedInputStream());
            reader.close();
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }

        parser.read(reader);
    }

    /**
     * Returns the parser being tested.
     * 
     * @return the parser being tested
     */
    protected final InputParser<V> getParser() {
        return parser;
    }

}
