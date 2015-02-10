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
package com.wandrell.testing.pattern.framework.test.integration.parser;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.ResourceUtils;
import com.wandrell.pattern.parser.InputParser;

/**
 * Abstract integration tests for {@link InputParser}, checks that the parsing
 * process reads sources correctly.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Reading a source parses the correct data when using an
 * {@code InputStream} or a {@code Reader}.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @see InputParser
 */
public abstract class AbstractITReadInputParser<V> {

    /**
     * The parser being tested.
     */
    private final InputParser<V> parser;
    /**
     * Path to the test data file.
     */
    private final String         path;
    /**
     * Expected result from the parsing process.
     */
    private final V              value;

    /**
     * Constructs the test.
     * 
     * @param parser
     *            parser being tested
     * @param path
     *            path to the test values file
     * @param value
     *            expected result
     */
    public AbstractITReadInputParser(final InputParser<V> parser,
            final String path, final V value) {
        super();

        checkNotNull(parser, "Received a null pointer as parser");
        checkNotNull(path, "Received a null pointer as path");
        checkNotNull(value, "Received a null pointer as value");

        this.parser = parser;
        this.path = path;
        this.value = value;
    }

    /**
     * Tests that reading a source parses the correct data when using an
     * {@code InputStream}.
     * 
     * @throws Exception
     *             never, this is just a required declaration
     */
    @Test
    public final void testRead_InputStream_ResultExpected() throws Exception {
        final InputStream stream;

        stream = ResourceUtils.getClassPathInputStream(getPath());

        Assert.assertEquals(getParser().read(stream), getValue());
    }

    /**
     * Tests that reading a source parses the correct data when using a
     * {@code Reader}.
     * 
     * @throws Exception
     *             never, this is just a required declaration
     */
    @Test
    public final void testRead_Reader_ResultExpected() throws Exception {
        final Reader reader;

        reader = new BufferedReader(new InputStreamReader(
                ResourceUtils.getClassPathInputStream(getPath())));

        Assert.assertEquals(getParser().read(reader), getValue());
    }

    /**
     * Returns the parser being tested.
     * 
     * @return the parser being tested
     */
    protected final InputParser<V> getParser() {
        return parser;
    }

    /**
     * Returns the path to the test data file.
     * 
     * @return the path to the test data file
     */
    protected final String getPath() {
        return path;
    }

    /**
     * Returns the expected value.
     * 
     * @return the expected value
     */
    protected final V getValue() {
        return value;
    }

}
