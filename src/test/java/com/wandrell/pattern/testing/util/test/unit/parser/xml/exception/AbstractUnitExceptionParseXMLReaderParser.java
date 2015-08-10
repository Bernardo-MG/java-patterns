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
package com.wandrell.pattern.testing.util.test.unit.parser.xml.exception;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.Reader;

import org.apache.commons.io.IOUtils;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;

/**
 * Abstract implementation of {@link AbstractTestExceptionReadInputParser} for
 * an XML {@link InputParser}.
 * <p>
 * Adds the following cases:
 * <ol>
 * <li>A {@code JDOMException} is thrown when trying to parse a non XML file.
 * </li>
 * <li>An {@code Exception} is thrown when reading from a closed {@code Reader}.
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see InputParser
 */
public abstract class AbstractUnitExceptionParseXMLReaderParser<V> {

    private final Parser<Reader, Document> parser;

    /**
     * Constructs the test.
     * 
     * @param parser
     *            parser to read
     */
    public AbstractUnitExceptionParseXMLReaderParser(
            final Parser<Reader, Document> parser) {
        super();

        this.parser = parser;
    }

    /**
     * Tries to read a directory.
     * 
     * @param file
     *            the file handler to test
     */
    @Test(expectedExceptions = JDOMException.class)
    public final void testRead_NotXML_ThrowsException() throws Exception {
        parser.parse(new BufferedReader(
                new InputStreamReader(IOUtils.toInputStream(""))));
    }

    /**
     * Tests an {@code Exception} is thrown when reading from a closed
     * {@code Reader}.
     * 
     * @throws Exception
     *             always, as part of the test
     */
    @Test(expectedExceptions = Exception.class)
    public final void testRead_Reader_Closed_ThrowsException()
            throws Exception {
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
