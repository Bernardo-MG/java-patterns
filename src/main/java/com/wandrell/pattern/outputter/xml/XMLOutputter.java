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
package com.wandrell.pattern.outputter.xml;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.OutputStream;
import java.io.Writer;

import org.jdom2.Attribute;
import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;

import com.wandrell.pattern.conf.XMLValidationType;
import com.wandrell.pattern.outputter.Outputter;

/**
 * Implementation of {@code Outputter} for creating XML files.
 * <p>
 * For this a {@link org.jdom2.Document Document} is received and then sent
 * through an IO operation.
 * <p>
 * Validation information can be set into the outputter. This will be saved on
 * the resulting XML file along the other data.
 * <p>
 * The JDOM2 API is being used for this job.
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @see Document
 */
public final class XMLOutputter implements Outputter<Document> {

    /**
     * The name of the element registering the validation file.
     * <p>
     * This is used with DTD validation.
     */
    private static final String     ELEMENT_VALID    = "validator";
    /**
     * Prefix for the validation namespace.
     * <p>
     * This is used to build the namespace for XSD validation.
     */
    private static final String     NAMESPACE_PREFIX = "xsi";
    /**
     * URI for the validation namespace.
     * <p>
     * This is used to build the namespace for XSD validation.
     */
    private static final String     NAMESPACE_URI    = "http://www.w3.org/2001/XMLSchema-instance";
    /**
     * Attribute which indicates the validation file location.
     * <p>
     * This is used with XSD validation.
     */
    private static final String     XSD_ATTRIBUTE    = "noNamespaceSchemaLocation";
    /**
     * Path to the validation file.
     */
    private final String            validationPath;
    /**
     * Type of validation being used.
     */
    private final XMLValidationType validationType;

    /**
     * Constructs an outputter with no validation.
     */
    public XMLOutputter() {
        this(XMLValidationType.NONE, "");
    }

    /**
     * Constructs an outputter with the specified validation.
     * 
     * @param validation
     *            the validation type
     * @param path
     *            path to the validation file
     */
    public XMLOutputter(final XMLValidationType validation, final String path) {
        super();

        checkNotNull(validation, "Received a null pointer as validation type");
        checkNotNull(path, "Received a null pointer as validation path");

        validationType = validation;

        validationPath = path;
    }

    /**
     * Sends a {@code Document} through an {@code OutputStream}.
     * <p>
     * The {@code Document} will be transformed into an XML text file.
     * 
     * @param value
     *            {@code Document} to send
     * @param stream
     *            {@code OutputStream} to receive the resulting XML text
     * @throws Exception
     *             when there's any problem on the outputting process
     */
    @Override
    public final void send(final Document value, final OutputStream stream)
            throws Exception {

        checkNotNull(value, "Received a null pointer as value");
        checkNotNull(stream, "Received a null pointer as output stream");

        setValidation(value);

        new org.jdom2.output.XMLOutputter(Format.getPrettyFormat()).output(
                value, stream);
    }

    /**
     * Sends a {@code Document} through an {@code Writer}.
     * <p>
     * The {@code Document} will be transformed into an XML text file.
     * 
     * @param value
     *            {@code Document} to send
     * @param writer
     *            {@code Writer} to receive the resulting XML text
     * @throws Exception
     *             when there's any problem on the writing process
     */
    @Override
    public final void send(final Document value, final Writer writer)
            throws Exception {

        checkNotNull(value, "Received a null pointer as value");
        checkNotNull(writer, "Received a null pointer as writer");

        setValidation(value);

        new org.jdom2.output.XMLOutputter(Format.getPrettyFormat()).output(
                value, writer);
    }

    /**
     * Returns the name of the element registering the validation file.
     * <p>
     * This is used with DTD validation.
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
