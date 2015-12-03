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

package com.wandrell.pattern.parser.xml;

import java.io.Reader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.StAXStreamBuilder;

import com.wandrell.pattern.parser.Parser;

/**
 * Implementation of {@link Parser} for XML files, applying no validation.
 * Behind the scenes this is using the JDOM2 library StAX API classes.
 * <p>
 * A {@code Reader} for the file is received by the parser, and then transformed
 * into a {@link org.jdom2.Document Document} before being returned.
 * <p>
 * No validation can be applied to the parsing. If you need validation, for
 * example for applying default values, use {@link ValidatedXMLFileParser}.
 * 
 * @author Bernardo Martínez Garrido
 */
public final class NotValidatedXMLFileParser
        implements Parser<Reader, Document> {

    /**
     * Builder to transform the {@code Reader} into a {@code Document}.
     * <p>
     * It is lazily instantiated.
     */
    private StAXStreamBuilder builder;

    /**
     * Constructs a parser.
     */
    public NotValidatedXMLFileParser() {
        super();
    }

    /**
     * Parses the XML file from the input into a JDOM2 {@code Document}.
     * 
     * @param input
     *            {@code Reader} for the XML file
     * @return a {@code Document} with the XML contents
     */
    @Override
    public final Document parse(final Reader input) {
        Document doc;

        try {
            doc = getBuilder().build(getXMLReader(input));
        } catch (final JDOMException e) {
            doc = null;
            throw new RuntimeException(e);
        }

        return doc;
    }

    /**
     * Returns the {@code StAXStreamBuilder} to be used when creating a
     * {@code Document} from the parsed {@code Reader}.
     * <p>
     * It will be created the first time it is required.
     * 
     * @return the {@code StAXStreamBuilder} used
     */
    private final StAXStreamBuilder getBuilder() {
        if (builder == null) {
            builder = new StAXStreamBuilder();
        }

        return builder;
    }

    /**
     * Prepares a {@code Reader} for the StAX parsing process.
     * <p>
     * The returned {@code Reader} will be a stream based reader, instead of an
     * event based one, to make the parsing process faster.
     * 
     * @param reader
     *            the base {@code Reader} for the XML file
     * @return a {@code XMLStreamReader} for the same file
     */
    private final XMLStreamReader getXMLReader(final Reader reader) {
        final XMLInputFactory factory;     // Factory to create the reader
        final XMLStreamReader staxReader;  // Resulting reader

        factory = XMLInputFactory.newInstance();

        try {
            staxReader = factory.createXMLStreamReader(reader);
        } catch (final XMLStreamException e) {
            throw new RuntimeException(e);
        }

        return staxReader;
    }

}
