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

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderXSDFactory;
import org.jdom2.input.sax.XMLReaders;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import com.wandrell.pattern.conf.XMLValidationType;
import com.wandrell.pattern.parser.Parser;

/**
 * Implementation of {@link Parser} for XML files, which can apply XSD or DTD
 * validation files. Behind the scenes this is using the JDOM2 library SAX API
 * classes.
 * <p>
 * A {@code Reader} for the file is received by the parser, and then transformed
 * into a {@link org.jdom2.Document Document} before being returned.
 * <p>
 * It should be noted that while the most obvious use of validation is verifying
 * the file, it can also be used to apply default values to it.
 * <p>
 * This parser is meant for those cases where validation is needed. If you don't
 * need it, think about using {@code XMLFileParser}, which may be faster.
 * 
 * @author Bernardo Martínez Garrido
 */
public final class ValidatedXMLFileParser implements Parser<Reader, Document> {

    /**
     * Builder to transform the {@code Reader} into a {@code Document}.
     * <p>
     * It is lazily instantiated.
     */
    private SAXBuilder        builder;
    /**
     * Flag indicating if the validation should be set on the builder or not.
     * <p>
     * This changes to {@code true} when the validation data changes, and to
     * {@code false} when the builder is created.
     */
    private Boolean           valChanged = true;
    /**
     * {@code Reader} for the validation file.
     * <p>
     * It will be used just once, when initializing the validation factory. So
     * it doesn't matter if it can be read multiple times or not.
     */
    private Reader            valData;
    /**
     * The type of validation being applied.
     */
    private XMLValidationType valType;

    /**
     * Constructs a {@code ValidatedXMLFileParser} with no validation.
     */
    public ValidatedXMLFileParser() {
        super();

        valType = XMLValidationType.NONE;
    }

    /**
     * Constructs a {@code ValidatedXMLFileParser} with the specified
     * validation.
     * 
     * @param validationType
     *            the validation type to use
     * @param validationFile
     *            reader for the validation file
     */
    public ValidatedXMLFileParser(final XMLValidationType validationType,
            final Reader validationFile) {
        super();

        checkNotNull(validationType,
                "Received a null pointer as validation type");
        checkNotNull(validationFile,
                "Received a null pointer as validation file reader");

        valType = validationType;
        valData = validationFile;
    }

    /**
     * Returns the validation type being used, or that no validation is being
     * applied.
     * 
     * @return the XML validation type being used
     */
    public final XMLValidationType getValidationType() {
        return valType;
    }

    /**
     * Parses the XML file from the input into a JDOM2 {@code Document}.
     * <p>
     * Validation will be applied during this process, which can cause failures
     * and exceptions to be thrown.
     * 
     * @param input
     *            {@code Reader} for the XML file
     * @return a {@code Document} with the XML contents
     */
    @Override
    public final Document parse(final Reader input) {
        Document doc;

        try {
            doc = getBuilder().build(input);
        } catch (final JDOMException | IOException e) {
            doc = null;
            throw new RuntimeException(e);
        }

        return doc;
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
        checkNotNull(type, "Received a null pointer as validation type");
        if (type != XMLValidationType.NONE) {
            checkNotNull(file,
                    "Received a null pointer as validation file reader");
        }

        valType = type;
        valData = file;

        setValidationChanged(true);
    }

    /**
     * Returns the {@code SAXBuilder} to be used when creating a
     * {@code Document} from the parsed {@code Reader}.
     * <p>
     * The builder will be created the first time it is required, or if the
     * validation data has been changed after the last call.
     * 
     * @return the {@code SAXBuilder} used
     */
    private final SAXBuilder getBuilder() {
        if (builder == null) {
            builder = new SAXBuilder();
        }

        if (hasValidationChanged()) {
            builder = new SAXBuilder();

            // If validation is being applied a factory for it is set
            switch (getValidationType()) {
                case XSD:
                    builder.setXMLReaderFactory(getXSDValidationFactory());
                    break;
                case DTD:
                    builder.setXMLReaderFactory(XMLReaders.DTDVALIDATING);
                    builder.setEntityResolver(getEntityResolver());
                    break;
                default:
                    builder.setXMLReaderFactory(null);
                    break;
            }

            setValidationChanged(false);
        }

        return builder;
    }

    /**
     * Returns the {@code EntityResolver} for the DTD validation process.
     * 
     * @return the {@code EntityResolver} for the DTD validation process
     */
    private final EntityResolver getEntityResolver() {
        return new EntityResolver() {

            /**
             * Contents of the DTD validation file.
             * <p>
             * This way it is only needed to use the reader once, no matter how
             * many times the validation is applied.
             * <p>
             * This will be initialized the first, and only, time the validation
             * file is read.
             */
            private String dtd;

            @Override
            public final InputSource resolveEntity(final String publicId,
                    final String systemId) throws IOException {
                final InputSource source;

                source = new InputSource(
                        IOUtils.toInputStream(getDTDFileContents(), "UTF-8"));
                source.setPublicId(publicId);
                source.setSystemId(systemId);

                return source;
            }

            /**
             * Returns the DTD validation file contents.
             * <p>
             * The first time this file is read it's contents will be stored in
             * the {@code dtd} variable.
             * <p>
             * This way the file is read only once, avoiding problems caused by
             * the file reader being closed.
             * 
             * @return the contents of the validation file
             * @throws IOException
             *             when an error occurs while reading the file
             */
            private final String getDTDFileContents() throws IOException {
                final StringBuilder readDTD;
                final BufferedReader reader;
                String line;

                if (dtd == null) {
                    readDTD = new StringBuilder();

                    reader = IOUtils.toBufferedReader(getValidationData());

                    line = reader.readLine();

                    while (line != null) {
                        readDTD.append(line);
                        line = reader.readLine();
                    }

                    reader.close();

                    dtd = readDTD.toString();
                }

                return dtd;
            }
        };
    }

    /**
     * Returns the reader for the validation file.
     * <p>
     * It should be noted that this is a single use reader. So the validation
     * data can, and should, be parsed once only.
     * 
     * @return the stream for the validation file
     */
    private final Reader getValidationData() {
        return valData;
    }

    /**
     * Returns the factory for the XSD validation process.
     * 
     * @return the XSD validation factory
     */
    private final XMLReaderJDOMFactory getXSDValidationFactory() {
        final XMLReaderJDOMFactory factoryValidation;
        final Source[] sources; // Sources for the validation files

        try {
            sources = new Source[1];
            sources[0] = new StreamSource(getValidationData());

            factoryValidation = new XMLReaderXSDFactory(sources);
        } catch (final JDOMException e) {
            throw new RuntimeException(e);
        }

        return factoryValidation;
    }

    /**
     * Indicates if the validation information has changed.
     * 
     * @return {@code true} if the validation data has changed, {@code false}
     *         otherwise
     */
    private final Boolean hasValidationChanged() {
        return valChanged;
    }

    /**
     * Sets the validation changed status.
     * <p>
     * If validation should be set again on the builder, this can be indicated
     * giving a {@code true} value to this method.
     * 
     * @param changed
     *            the validation changed status
     */
    private final void setValidationChanged(final Boolean changed) {
        valChanged = changed;
    }

}
