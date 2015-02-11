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
package com.wandrell.pattern.parser.xml.output;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.jdom2.Attribute;
import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.wandrell.pattern.parser.OutputParser;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.XMLValidationType;

/**
 * Implementation of {@code OutputParser} parsing into an XML file.
 * <p>
 * For this a {@link org.jdom2.Document Document} is built from a received value
 * with the help of a {@link JDOMDocumentEncoder}, and then this is sent through
 * an IO operation.
 * <p>
 * Validation can be applied to the parsed file, in the form of XSD or DTD
 * files. This information will be kept on the resulting file.
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @param <V>
 *            the type to be parsed
 */
public final class XMLOutputParser<V> implements OutputParser<V> {

    /**
     * The name of the element registering the validation file.
     * <p>
     * This is used with DTD validation.
     */
    private static final String       ELEMENT_VALID    = "validator";
    /**
     * Prefix for the validation namespace.
     * <p>
     * This is used to build the namespace for XSD validation.
     */
    private static final String       NAMESPACE_PREFIX = "xsi";
    /**
     * URI for the validation namespace.
     * <p>
     * This is used to build the namespace for XSD validation.
     */
    private static final String       NAMESPACE_URI    = "http://www.w3.org/2001/XMLSchema-instance";
    /**
     * Attribute which indicates the validation file location.
     * <p>
     * This is used with XSD validation.
     */
    private static final String       XSD_ATTRIBUTE    = "noNamespaceSchemaLocation";
    /**
     * The parser which will generate a {@code Document} from the received
     * value.
     */
    private final Parser<V, Document> documentParser;
    /**
     * Path to the validation file.
     */
    private final String              validationPath;
    /**
     * Type of validation being used.
     */
    private final XMLValidationType   validationType;

    /**
     * Constructs a parser with the specified processor and no validation.
     * 
     * @param docParser
     *            the parser for creating a {@code Document} from the received
     *            value
     */
    public XMLOutputParser(final Parser<V, Document> docParser) {
        this(XMLValidationType.NONE, "", docParser);
    }

    /**
     * Constructs a parser with the specified processor and validation.
     * 
     * @param docParser
     *            the parser for creating a {@code Document} from the received
     *            value
     * @param validation
     *            the validation type
     * @param path
     *            path to the validation file
     */
    public XMLOutputParser(final XMLValidationType validation,
            final String path, final Parser<V, Document> docParser) {
        super();

        checkNotNull(docParser, "Received a null pointer as document parser");
        checkNotNull(validation, "Received a null pointer as validation type");
        checkNotNull(path, "Received a null pointer as validation path");

        documentParser = docParser;

        validationType = validation;

        validationPath = path;
    }

    /**
     * Parses an object and sends it through an {@code OutputStream}.
     * 
     * @param stream
     *            {@code OutputStream} to receive the parsed object
     * @param value
     *            object to parse
     * @throws IOException
     *             when there's any problem writing
     */
    @Override
    public final void write(final OutputStream stream, final V value)
            throws IOException {
        final Document doc;

        checkNotNull(stream, "Received a null pointer as output stream");
        checkNotNull(value, "Received a null pointer as value");

        doc = getDocumentParser().parse(value);

        setValidation(doc);

        new XMLOutputter(Format.getPrettyFormat()).output(doc, stream);
    }

    /**
     * Parses an object and sends it through a {@code Writer}.
     * 
     * @param writer
     *            {@code Writer} to receive the parsed object
     * @param value
     *            object to parse
     * @throws IOException
     *             when there's any problem writing
     */
    @Override
    public final void write(final Writer writer, final V value)
            throws IOException {
        final Document doc;

        checkNotNull(writer, "Received a null pointer as writer");
        checkNotNull(value, "Received a null pointer as value");

        doc = getDocumentParser().parse(value);

        setValidation(doc);

        new XMLOutputter(Format.getPrettyFormat()).output(doc, writer);
    }

    /**
     * Returns the {@code Parser} in charge of generating the {@code Document}.
     * 
     * @return the {@code Parser} in charge of generating the {@code Document}
     */
    private final Parser<V, Document> getDocumentParser() {
        return documentParser;
    }

    /**
     * Returns the name of the element registering the validation file.
     * <p>
     * This is used with DTD validation..
     * 
     * @return the DTD validation file element name
     */
    private final String getDTDValidatorName() {
        return ELEMENT_VALID;
    }

    /**
     * Returns the path to the validation file.
     * 
     * @return the path to the validation file.
     */
    private final String getValidationPath() {
        return validationPath;
    }

    /**
     * Returns the type of validation being used.
     * 
     * @return the type of validation being used
     */
    private final XMLValidationType getValidationType() {
        return validationType;
    }

    /**
     * Returns the attribute which indicates the validation file location.
     * <p>
     * This is used with XSD validation.
     * 
     * @return the XSD validation file attribute
     */
    private final String getXSDAttribute() {
        return XSD_ATTRIBUTE;
    }

    /**
     * Returns the validation namespace.
     * <p>
     * This is used with XSD validation.
     * 
     * @return the validation file namespace
     */
    private final Namespace getXSDNamespace() {
        return Namespace.getNamespace(NAMESPACE_PREFIX, NAMESPACE_URI);
    }

    /**
     * Sets the validation data into the specified {@code Document}.
     * 
     * @param doc
     *            the {@code Document} into which to set the validation data
     */
    private final void setValidation(final Document doc) {
        final Namespace namespc; // Namespace for the XSD file
        final Element root;      // Document's root

        switch (getValidationType()) {
            case XSD:
                root = doc.getRootElement();

                namespc = getXSDNamespace();
                root.addNamespaceDeclaration(namespc);
                root.setAttribute(new Attribute(getXSDAttribute(),
                        getValidationPath(), namespc));
                break;
            case DTD:
                doc.setDocType(new DocType(getDTDValidatorName(),
                        getValidationPath()));
                break;
            default:
        }
    }

}
