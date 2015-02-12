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
package com.wandrell.testing.pattern.framework.test.integration.parser.dsv.read;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.ResourceUtils;
import com.wandrell.pattern.parser.InputParser;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.dsv.input.DSVInputParser;

/**
 * Abstract integration tests for {@link DSVInputParser}.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>The returned entries count is correct.</li>
 * <li>The returned entries values are correct.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @see InputParser
 */
public abstract class AbstractITReadDSVInputParser {

    /**
     * Data parsed from the DSV file.
     */
    private Collection<Collection<Integer>> data;
    /**
     * Path to the DSV file.
     */
    private final String                    path;
    /**
     * Delimiter used in the DSV file.
     */
    private final Character                 separator;

    /**
     * Constructs the test with the specified data.
     * 
     * @param separator
     *            the delimiter of the DSV
     * @param path
     *            path to the DSV file
     */
    public AbstractITReadDSVInputParser(final Character separator,
            final String path) {
        super();

        this.separator = separator;
        this.path = path;
    }

    /**
     * Creates the data for the test.
     * 
     * @throws Exception
     *             never, this is just a required declaration
     */
    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<Collection<Collection<Integer>>> parser;
        final Parser<String[], Collection<Integer>> parserLine;

        parserLine = new Parser<String[], Collection<Integer>>() {

            @Override
            public final Collection<Integer> parse(final String[] input) {
                final Collection<Integer> results;

                results = new LinkedList<>();
                for (final String value : input) {
                    if (value.matches("\\d+")) {
                        results.add(Integer.parseInt(value));
                    }
                }

                return results;
            }

        };

        parser = new DSVInputParser<Collection<Integer>>(separator, parserLine);

        data = parser.read(ResourceUtils.getClassPathInputStream(path));
    }

    /**
     * Tests that the returned entries values are correct.
     */
    @Test
    public final void testRead_LinesContent() {
        final Iterator<Collection<Integer>> itr;
        Collection<Integer> col;

        itr = data.iterator();

        col = new LinkedList<>();
        col.add(1);
        col.add(2);
        col.add(3);
        col.add(4);
        col.add(5);
        col.add(6);
        Assert.assertEquals(itr.next(), col);

        col = new LinkedList<>();
        col.add(7);
        col.add(8);
        col.add(9);
        Assert.assertEquals(itr.next(), col);

        col = new LinkedList<>();
        col.add(1);
        Assert.assertEquals(itr.next(), col);

        col = new LinkedList<>();
        Assert.assertEquals(itr.next(), col);

        col = new LinkedList<>();
        col.add(2);
        Assert.assertEquals(itr.next(), col);
    }

    /**
     * Tests that the returned entries count is correct.
     */
    @Test
    public final void testRead_LinesCount() {
        Assert.assertEquals(data.size(), 5);
    }

}
