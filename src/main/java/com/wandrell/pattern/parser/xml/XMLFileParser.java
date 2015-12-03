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

import org.jdom2.Document;

import com.wandrell.pattern.conf.XMLValidationType;
import com.wandrell.pattern.parser.Parser;

/**
 * Implementation of {@link Parser} for XML files, which adapts to the
 * validation requirements.
 * <p>
 * This parser can apply validation, but has a big difference which sets it
 * apart from {@link ValidatedXMLFileParser}, and it is that class is composed
 * of an instance of that parser and one of {@link NotValidatedXMLFileParser}.
 * <p>
 * The reason for this is that when no validation is applied, the instance of
 * {@code NotValidatedXMLFileParser} will be the one taking care of the parsing
 * process, and in any other case the {@code ValidatedXMLFileParser} will do the
 * work.
 * <p>
 * This way when no validation is used the parsing can become faster.
 * 
 * @author Bernardo Martínez Garrido
 */
public final class XMLFileParser implements Parser<Reader, Document> {

    /**
     * Parser with no validation.
     * <p>
     * This one is faster, and when no validation is required this fact is taken
     * advantage of.
     */
    private final NotValidatedXMLFileParser parserNotValidated = new NotValidatedXMLFileParser();
    /**
     * Parser with validation.
     * <p>
     * This is slower, but needed when validation is to be applied.
     */
    private final ValidatedXMLFileParser    parserValidated    = new ValidatedXMLFileParser();

    /**
     * Constructs an {@code XMLFileParser}.
     */
    public XMLFileParser() {
        super();
    }

    /**
     * Constructs a {@code XMLFileParser} with the specified validation.
     * 
     * @param validationType
     *            the validation type to use
     * @param validationFile
     *            reader for the validation file
     */
    public XMLFileParser(final XMLValidationType validationType,
            final Reader validationFile) {
        super();
        getValidatedParser().setValidation(validationType, validationFile);
    }

    /**
     * Returns the XML validation being used, or that no validation is being
     * applied.
     * 
     * @return the XML validation being used
     */
    public final XMLValidationType getValidationType() {
        return getValidatedParser().getValidationType();
    }

    /**
     * Parses the XML file from the input into a JDOM2 {@code Document}.
     * <p>
     * Validation can be applied during this process, which may cause failures
     * and exceptions to be thrown.
     * 
     * @param input
     *            {@code Reader} for the XML file
     * @return a {@code Document} with the XML contents
     */
    @Override
    public final Document parse(final Reader input) {
        return getParser().parse(input);
    }

    /**
     * Sets the validation type and file to be used.
     * 
     * @param type
     *            the validation type
     * @param file
     *            reader for the validation file
     */
    public final void setValidation(final XMLValidationType type,
            final Reader file) {
        getValidatedParser().setValidation(type, file);
    }

    /**
     * Returns the parser with no validation.
     * <p>
     * This is faster, a fact taken advantage of when no validation is required.
     * 
     * @return the parser with no validation
     */
    private final NotValidatedXMLFileParser getNotValidatedParser() {
        return parserNotValidated;
    }

    /**
     * Returns the parser to be used on the parsing process.
     * <p>
     * If validation is required the parser with no validation is returned,
     * otherwise the validated parser will be the one returned.
     * 
     * @return the parser to be used
     */
    private final Parser<Reader, Document> getParser() {
        final Parser<Reader, Document> parser; // Parser to use

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
     * <p>
     * This one is slower, so it will only be used if validation is needed.
     * 
     * @return the parser with validation
     */
    private final ValidatedXMLFileParser getValidatedParser() {
        return parserValidated;
    }

}
