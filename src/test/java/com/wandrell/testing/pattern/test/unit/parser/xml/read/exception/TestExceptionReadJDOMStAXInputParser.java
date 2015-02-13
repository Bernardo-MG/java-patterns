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
package com.wandrell.testing.pattern.test.unit.parser.xml.read.exception;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.io.IOUtils;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.input.SAXParser;

/**
 * Unit tests for {@link StAXInputParser}, checking that a
 * {@code JDOMParseException} is thrown when parsing files which are not XML
 * valid.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>When reading a {@code Reader} for a file which is not an XML file a
 * {@code JDOMParseException} is thrown</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @see StAXInputParser
 */
public final class TestExceptionReadJDOMStAXInputParser {

    /**
     * Parser being tested.
     */
    private Parser<Reader, Document> parser;

    public TestExceptionReadJDOMStAXInputParser() {
        super();
    }

    /**
     * Creates the parser being tested before any test is run.
     */
    @BeforeTest
    public final void initialize() {
        parser = new SAXParser();
    }

    /**
     * Tests that when reading an {@code InputStream} for a file which is not an
     * XML file a {@code JDOMParseException} is thrown.
     * 
     * @throws Exception
     *             never, this is just a required declaration
     */
    @Test(expectedExceptions = JDOMException.class)
    public final void testRead_Reader_Empty_Exception() throws Exception {
        final InputStream stream;

        stream = IOUtils.toInputStream("");

        parser.parse(new BufferedReader(new InputStreamReader(stream)));
    }

}
