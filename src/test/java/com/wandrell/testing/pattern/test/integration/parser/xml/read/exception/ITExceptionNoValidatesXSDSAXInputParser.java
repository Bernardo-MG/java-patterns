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
package com.wandrell.testing.pattern.test.integration.parser.xml.read.exception;

import org.jdom2.Document;
import org.jdom2.input.JDOMParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.ResourceUtils;
import com.wandrell.pattern.parser.InputParser;
import com.wandrell.pattern.parser.xml.XMLValidationType;
import com.wandrell.pattern.parser.xml.input.JDOMDocumentDecoder;
import com.wandrell.pattern.parser.xml.input.SAXInputParser;
import com.wandrell.testing.pattern.framework.conf.XMLConf;

/**
 * Integration tests for {@link SAXInputParser} using XSD validation.
 * <p>
 * Adds the following cases:
 * <ol>
 * <li>Reading a file which doesn't validate throws a {@code JDOMParseException}
 * .</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @see SAXInputParser
 */
public final class ITExceptionNoValidatesXSDSAXInputParser {

    /**
     * Parser to test
     */
    private InputParser<Integer> parser;

    /**
     * Default constructor.
     */
    public ITExceptionNoValidatesXSDSAXInputParser() {
        super();
    }

    /**
     * Tests that reading a file which doesn't validate throws a
     * {@code JDOMParseException}.
     * 
     * @throws Exception
     *             always, as part of the test
     */
    @Test(expectedExceptions = JDOMParseException.class)
    public final void testRead_NotValidates_ThrowsException() throws Exception {
        parser.read(ResourceUtils
                .getClassPathInputStream(XMLConf.INTEGER_NO_VALIDATES));
    }

    /**
     * Generates the document processor for the test parser.
     * 
     * @return the test document processor
     */
    private final JDOMDocumentDecoder<Integer>
            getJDOMDocumentProcessorInteger() {
        return new JDOMDocumentDecoder<Integer>() {

            @Override
            public final Integer decode(final Document doc) {
                final Integer value;

                value = Integer.parseInt(doc.getRootElement().getChildText(
                        XMLConf.NODE_VALUE));

                return value;
            }

        };
    }

    /**
     * Generates the parser to be tested before any test is run.
     */
    @BeforeClass
    private final void initialize() {
        parser = new SAXInputParser<Integer>(XMLValidationType.XSD,
                ResourceUtils.getClassPathInputStream(XMLConf.XSD_VALIDATION),
                getJDOMDocumentProcessorInteger());
    }

}
