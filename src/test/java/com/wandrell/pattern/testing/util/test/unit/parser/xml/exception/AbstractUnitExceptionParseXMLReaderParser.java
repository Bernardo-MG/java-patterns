/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2014-2015 the original author or authors.
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

package com.wandrell.pattern.testing.util.test.unit.parser.xml.exception;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.Reader;

import org.apache.commons.io.IOUtils;
import org.jdom2.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;

/**
 * Abstract unit tests for an XML {@link Parser}, checking that exceptions are
 * thrown with invalid inputs.
 * <p>
 * The tested {@code Parser} should receive a {@code Reader} as input, and
 * return a {@code Document} as output. Additionally it has to be injected with
 * the use of Spring.
 * <p>
 * Adds the following cases:
 * <ol>
 * <li>A {@code Exception} is thrown when trying to parse an empty XML file.
 * </li>
 * <li>An {@code Exception} is thrown when reading from a closed {@code Reader}.
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see InputParser
 */
public abstract class AbstractUnitExceptionParseXMLReaderParser<V>
        extends AbstractTestNGSpringContextTests {

    /**
     * Parser to be tested.
     * <p>
     * It has to be injected with the use of Spring.
     */
    @Autowired
    private Parser<Reader, Document> parser;

    /**
     * Constructs the test.
     */
    public AbstractUnitExceptionParseXMLReaderParser() {
        super();
    }

    /**
     * Tests that a {@code Exception} is thrown when trying to parse a an empty
     * file.
     */
    @Test(expectedExceptions = Exception.class)
    public final void testRead_NotXML_ThrowsException() {
        parser.parse(new BufferedReader(
                new InputStreamReader(IOUtils.toInputStream(""))));
    }

    /**
     * Tests an {@code Exception} is thrown when reading from a closed
     * {@code Reader}.
     */
    @Test(expectedExceptions = Exception.class)
    public final void testRead_Reader_Closed_ThrowsException() {
        Reader reader = null; // Stubbed reader

        try {
            reader = new InputStreamReader(new PipedInputStream());
            reader.close();
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }

        parser.parse(reader);
    }

}
