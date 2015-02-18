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
package com.wandrell.pattern.parser.xml;

import java.io.InputStream;
import java.io.Reader;

import org.jdom2.Document;

import com.wandrell.pattern.conf.XMLValidationType;
import com.wandrell.pattern.parser.Parser;

/**
 * Implementation of {@link Parser} for XML files, which can apply XSD or DTD
 * validation files.
 * <p>
 * The difference with this and {@link ValidatedXMLFileParser} is that this
 * class is composed of an instance of that parser and of
 * {@link NotValidatedXMLFileParser}, and switches between them.
 * <p>
 * When no validation is applied, the instance of
 * {@code NotValidatedXMLFileParser} will be used, otherwise the
 * {@code ValidatedXMLFileParser} will take care of the parsing process.
 * <p>
 * This way when no validation is required the faster parser is used.
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 */
public final class XMLFileParser implements Parser<Reader, Document> {

    /**
     * Parser with no validation.
     * <p>
     * This is faster. When no validation is required this fact is taken
     * advantage of, using this parser.
     */
    private final NotValidatedXMLFileParser parserNotValidated = new NotValidatedXMLFileParser();
    /**
     * Parser with validation.
     * <p>
     * This is slower, but needed when validation is applied.
     */
    private final ValidatedXMLFileParser    parserValidated    = new ValidatedXMLFileParser();

    /**
     * Constructs a parser.
     */
    public XMLFileParser() {
        super();
    }

    /**
     * Returns the validation type being used.
     * 
     * @return the XML validation type being used
     */
    public final XMLValidationType getValidationType() {
        return getValidatedParser().getValidationType();
    }

    /**
     * Parses the XML file from the input into a JDOM2 {@code Document}.
     * 
     * @param input
     *            {@code Reader} for the XML file
     * @return a {@code Document} with the XML contents
     * @throws Exception
     *             when an error occurs during parsing
     */
    @Override
    public final Document parse(final Reader input) throws Exception {
        return getParser().parse(input);
    }

    /**
     * Sets the validation type and file to be used.
     * 
     * @param type
     *            the validation type
     * @param validationStream
     *            stream for the validation file
     */
    public final void setValidation(final XMLValidationType type,
            final InputStream validationStream) {
        getValidatedParser().setValidation(type, validationStream);
    }

    /**
     * Returns the parser with no validation.
     * 
     * @return the parser with no validation
     */
    private final NotValidatedXMLFileParser getNotValidatedParser() {
        return parserNotValidated;
    }

    /**
     * Returns the parser to be used on the parsing process.
     * <p>
     * When no validation is required, this will be the parser with no
     * validation. Otherwise the validated parser will be used.
     * 
     * @return the parser to be used
     */
    private final Parser<Reader, Document> getParser() {
        final Parser<Reader, Document> parser;

        if (getValidationType() == XMLValidationType.NONE) {
            // No validation being applied
            parser = getNotValidatedParser();
        } else {
            // Validation required
            parser = getValidatedParser();
        }

        return parser;
    }

    /**
     * Returns the parser with validation.
     * 
     * @return the parser with validation
     */
    private final ValidatedXMLFileParser getValidatedParser() {
        return parserValidated;
    }

}
