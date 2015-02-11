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
package com.wandrell.pattern.parser.xml.input;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.jdom2.Document;
import org.jdom2.JDOMException;

import com.wandrell.pattern.parser.InputParser;
import com.wandrell.pattern.parser.Parser;

/**
 * Abstract implementation of {@code InputParser} based on JDOM.
 * <p>
 * This is a composite parser, which will create a JDOM {@code Document} from
 * the input, and then hand it to a {@link Parser}, which will create from it
 * the resulting object.
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @param <V>
 *            the type to be parsed from the input
 */
public abstract class AbstractJDOMInputParser<V> implements InputParser<V> {

    /**
     * The parser to transform the generated {@code Document} into the returned
     * value.
     */
    private final Parser<Document, V> documentParser;

    /**
     * Constructs a parser with the specified processor and no validation.
     * 
     * @param docParser
     *            the parser for the created {@code Document}
     */
    public AbstractJDOMInputParser(final Parser<Document, V> docParser) {
        super();

        checkNotNull(docParser, "Received a null pointer as document parser");

        documentParser = docParser;
    }

    /**
     * Parses an object from an {@code InputStream}.
     * 
     * @param stream
     *            {@code InputStream} with the data to be parsed
     * @return an object parsed from the stream
     * @throws JDOMException
     *             when errors occur in parsing
     * @throws IOException
     *             when an I/O error prevents a document from being fully parsed
     */
    @Override
    public final V read(final InputStream stream) throws JDOMException,
            IOException {
        checkNotNull(stream, "Received a null pointer as input stream");

        return getDocumentParser().parse(getDocument(stream));
    }

    /**
     * Parses an object from a {@code Reader}.
     * 
     * @param reader
     *            {@code Reader} with the data to be parsed
     * @return an object parsed from the stream
     * @throws JDOMException
     *             when errors occur in parsing
     * @throws IOException
     *             when an I/O error prevents a document from being fully parsed
     */
    @Override
    public final V read(final Reader reader) throws JDOMException, IOException {
        checkNotNull(reader, "Received a null pointer as reader");

        return getDocumentParser().parse(getDocument(reader));
    }

    /**
     * Returns the parser which transforms the generated {@code Document} into
     * the returned value.
     * 
     * @return the parser for the {@code Document}
     */
    private final Parser<Document, V> getDocumentParser() {
        return documentParser;
    }

    /**
     * Creates a JDOM {@code Document} from the specified stream.
     * 
     * @param stream
     *            {@code InputStream} with the data to be parsed
     * @return a {@code Document} parsed from the stream
     * @throws JDOMException
     *             when errors occur in parsing
     * @throws IOException
     *             when an I/O error prevents a document from being fully parsed
     */
    protected abstract Document getDocument(final InputStream stream)
            throws JDOMException, IOException;

    /**
     * Creates a JDOM {@code Document} from the specified stream.
     * 
     * @param reader
     *            {@code Reader} with the data to be parsed
     * @return a {@code Document} parsed from the reader
     * @throws JDOMException
     *             when errors occur in parsing
     * @throws IOException
     *             when an I/O error prevents a document from being fully parsed
     */
    protected abstract Document getDocument(final Reader reader)
            throws JDOMException, IOException;

}
