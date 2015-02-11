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
package com.wandrell.testing.pattern.test.unit.parser.xml.write.exception;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Writer;

import org.jdom2.Document;
import org.jdom2.Element;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.OutputParser;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.output.XMLOutputParser;
import com.wandrell.testing.pattern.framework.conf.XMLConf;

/**
 * Unit tests for {@link XMLOutputParser}, checking that exceptions are thrown
 * when errors occur during writing.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>An {@code Exception} is thrown when writing a XML file using a closed
 * {@code OutputStream} or {@code Writer}.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @see XMLOutputParser
 */
public final class TestExceptionWriteNoValidationXMLOutputParser {

    /**
     * Parser being tested.
     */
    private OutputParser<Integer> parser;
    /**
     * Value to parse.
     */
    private Integer               value = 1;

    /**
     * Default constructor.
     */
    public TestExceptionWriteNoValidationXMLOutputParser() {
        super();
    }

    /**
     * Tests that an {@code Exception} is thrown when writing a XML file using a
     * closed {@code OutputStream}.
     * 
     * @throws Exception
     *             always, as part of the test
     */
    @Test(expectedExceptions = Exception.class)
    public final void testWrite_OutputStream_Closed_ThrowsException()
            throws Exception {
        OutputStream stream = null; // Closed stream

        try {
            stream = new BufferedOutputStream(new PipedOutputStream(
                    new PipedInputStream()));
            stream.close();
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }

        parser.write(stream, value);
    }

    /**
     * Tests that an {@code Exception} is thrown when writing a XML file using a
     * closed {@code Writer}.
     * 
     * @throws Exception
     *             always, as part of the test
     */
    @Test(expectedExceptions = Exception.class)
    public final void testWrite_Writer_Closed_ThrowsException()
            throws Exception {
        Writer writer = null; // Closed writer

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new BufferedOutputStream(new PipedOutputStream(
                            new PipedInputStream()))));
            writer.close();
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }

        parser.write(writer, value);
    }

    /**
     * Creates the parser being tested before any test is run.
     */
    @BeforeClass
    private final void initialize() {
        final Parser<Integer, Document> encoder;

        encoder = new Parser<Integer, Document>() {

            @Override
            public final Document parse(final Integer value) {
                final Document writeDoc;

                writeDoc = new Document();
                writeDoc.addContent(new Element(XMLConf.NODE_ROOT));
                writeDoc.getRootElement().addContent(
                        new Element(XMLConf.NODE_VALUE).addContent(value
                                .toString()));

                return writeDoc;
            }

        };

        parser = new XMLOutputParser<Integer>(encoder);
    }

}
