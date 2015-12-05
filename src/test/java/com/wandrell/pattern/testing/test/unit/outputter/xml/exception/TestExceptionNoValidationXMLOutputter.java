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

package com.wandrell.pattern.testing.test.unit.outputter.xml.exception;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Writer;

import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.outputter.Outputter;
import com.wandrell.pattern.outputter.xml.XMLOutputter;

/**
 * Unit tests for {@link XMLOutputter}, checking that exceptions are thrown when
 * errors occur during writing.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>An {@code Exception} is thrown when writing a XML file using a closed
 * {@code OutputStream}.</li>
 * <li>An {@code Exception} is thrown when writing a XML file using a closed
 * {@code Writer}.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see XMLOutputter
 */
public final class TestExceptionNoValidationXMLOutputter {

    /**
     * Outputter being tested.
     */
    private Outputter<Document> outputter;

    /**
     * Default constructor.
     */
    public TestExceptionNoValidationXMLOutputter() {
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
            stream = new BufferedOutputStream(
                    new PipedOutputStream(new PipedInputStream()));
            stream.close();
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }

        outputter.output(new Document(), stream);
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
            writer = new BufferedWriter(
                    new OutputStreamWriter(new BufferedOutputStream(
                            new PipedOutputStream(new PipedInputStream()))));
            writer.close();
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }

        outputter.output(new Document(), writer);
    }

    /**
     * Creates the parser being tested before any test is run.
     */
    @BeforeClass
    private final void initialize() {
        outputter = new XMLOutputter();
    }

}
