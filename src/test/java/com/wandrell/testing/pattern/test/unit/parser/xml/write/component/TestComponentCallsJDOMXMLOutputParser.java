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
package com.wandrell.testing.pattern.test.unit.parser.xml.write.component;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Writer;

import org.jdom2.Document;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.OutputParser;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.output.XMLOutputParser;

/**
 * Unit tests for {@link XMLOutputParser}, checking that the
 * {@code JDOMDocumentOutputProcessor} module is called when reading.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>When writing to an {@code OutputStream} the
 * {@code JDOMDocumentOutputProcessor} is called</li>
 * <li>When writing to a {@code Writer} the {@code JDOMDocumentOutputProcessor}
 * is called</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @see XMLOutputParser
 */
public final class TestComponentCallsJDOMXMLOutputParser {

    /**
     * Default constructor.
     */
    public TestComponentCallsJDOMXMLOutputParser() {
        super();
    }

    /**
     * Tests that when writing to an {@code OutputStream} the
     * {@code JDOMDocumentOutputProcessor} is called.
     * <p>
     * As a side effect of this test a file is created. But this has no impact
     * on the test.
     * 
     * @throws Exception
     *             never, this is just a required declaration
     */
    @SuppressWarnings("unchecked")
    @Test
    public final void testWrite_OutputStream_CorrectCallNumbers()
            throws Exception {
        final OutputStream stream;         // Stream for the output file
        final Parser<Object, Document> processor;// Output processor
        final OutputParser<Document> parser; // Tested parser

        processor = Mockito.mock(Parser.class);

        Mockito.when(processor.parse(Matchers.any()))
                .thenReturn(new Document());

        parser = new XMLOutputParser();

        stream = new BufferedOutputStream(new PipedOutputStream(
                new PipedInputStream()));

        parser.write(stream, processor.parse(new Integer(0)));

        Mockito.verify(processor, Mockito.times(1)).parse(Matchers.any());
        Mockito.verify(processor, Mockito.only()).parse(Matchers.any());
    }

    /**
     * Tests that when writing to an {@code OutputStream} the
     * {@code JDOMDocumentOutputProcessor} is called.
     * <p>
     * As a side effect of this test a file is created. But this has no impact
     * on the test.
     * 
     * @throws Exception
     *             never, this is just a required declaration
     */
    @SuppressWarnings("unchecked")
    @Test
    public final void testWrite_OutputWriter_CorrectCallNumbers()
            throws Exception {
        final Writer writer;               // Writer for the output file
        final Parser<Object, Document> processor;// Output processor
        final OutputParser<Document> parser; // Tested parser

        processor = Mockito.mock(Parser.class);

        Mockito.when(processor.parse(Matchers.any()))
                .thenReturn(new Document());

        parser = new XMLOutputParser();

        writer = new BufferedWriter(new OutputStreamWriter(
                new BufferedOutputStream(new PipedOutputStream(
                        new PipedInputStream()))));

        parser.write(writer, processor.parse(new Integer(0)));

        Mockito.verify(processor, Mockito.times(1)).parse(Matchers.any());
        Mockito.verify(processor, Mockito.only()).parse(Matchers.any());
    }

}
