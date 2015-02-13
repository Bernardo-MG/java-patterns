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

import java.io.IOException;
import java.io.Reader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.StAXStreamBuilder;

import com.wandrell.pattern.parser.Parser;

/**
 * Implementation of {link InputParser} parsing an XML file using the StAX API,
 * with the help of the JDOM library.
 * <p>
 * For this a {@link org.jdom2.Document Document} is created from the input,
 * which is then sent to a {@link JDOMDocumentDecoder}, which will create the
 * returned object from it.
 * <p>
 * No validation can be applied.
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @param <V>
 *            the type to be parsed from the input
 */
public final class XMLParser implements Parser<Reader, Document> {

    /**
     * Builder to transform the input into a {@code Document}.
     * <p>
     * It is lazily instantiated.
     */
    private StAXStreamBuilder builder;

    /**
     * Constructs a parser.
     */
    public XMLParser() {
        super();
    }

    @Override
    public final Document parse(final Reader input) throws JDOMException,
            IOException {
        return getBuilder().build(getXMLReader(input));
    }

    /**
     * Returns the {@code SAXBuilder} to be used when creating a
     * {@code Document} from the parsed {@code InputStream} or {@code Reader}.
     * <p>
     * It will be created the first time it is required.
     * 
     * @return the {@code SAXBuilder} used
     */
    private final StAXStreamBuilder getBuilder() {
        if (builder == null) {
            builder = new StAXStreamBuilder();
        }

        return builder;
    }

    /**
     * Returns a {@code Reader} prepared for the StAX parsing process.
     * <p>
     * It will be a stream based reader, instead of an event based one, to make
     * the parsing process faster.
     * 
     * @param reader
     *            the base {@code Reader} to use as source
     * @return a {@code XMLStreamReader} for the same data
     */
    private final XMLStreamReader getXMLReader(final Reader reader) {
        final XMLInputFactory factory;     // Factory to create the reader
        final XMLStreamReader staxReader;  // Resulting reader

        if (!(reader instanceof XMLStreamReader)) {
            factory = XMLInputFactory.newInstance();

            try {
                staxReader = factory.createXMLStreamReader(reader);
            } catch (final XMLStreamException e) {
                throw new RuntimeException(e);
            }
        } else {
            staxReader = (XMLStreamReader) reader;
        }

        return staxReader;
    }

}
