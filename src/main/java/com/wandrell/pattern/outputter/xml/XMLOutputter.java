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
 * Implementation of {@link Outputter} for XML files. Behind the scenes this is
 * based on the JDOM 2 API.
 * <p>
 * The data is received as a JDOM 2 {@link org.jdom2.Document Document}, along
 * the output object to be used.
 * <p>
 * It is also possible to include XML validation data, which will be added to
 * the {@code Document} before sending it. This should be set using the
 * {@link #setValidation(Document) setValidation} method before outputting the
 * data.
 * <p>
 * Note that the validation file path won't be checked in any way, just stored
 * on the resulting file as received.
 * 
 * @author Bernardo Martínez Garrido
 * @see Document
 */
public final class XMLOutputter implements Outputter<Document> {

    /**
     * The name of the element which will register the validation file being
     * used.
     * <p>
     * This will be used only if DTD validation is set.
     */
    private static final String ELEMENT_VALID    = "validator";
    /**
     * Prefix for the validation namespace.
     * <p>
     * This will be used only if XSD validation is set.
     */
    private static final String NAMESPACE_PREFIX = "xsi";
    /**
     * URI for the validation namespace.
     * <p>
     * This will be used only if XSD validation is set.
     */
    private static final String NAMESPACE_URI    = "http://www.w3.org/2001/XMLSchema-instance";
    /**
     * Attribute indicating the validation file location.
     * <p>
     * This will be used only if XSD validation is set.
     */
    private static final String XSD_ATTRIBUTE    = "noNamespaceSchemaLocation";
    /**
     * Path to the validation file.
     * <p>
     * It should be noted that this file won't be read or validated in any way.
     * <p>
     * The path will be stored along the rest of the validation data on the
     * resulting file, but only if any kind of validation has been set.
     */
    private String              valFilePath;
    /**
     * Type of validation being used.
     * <p>
     * If the validation is set to 'NONE' no validation will be applied.
     */
    private XMLValidationType   validationType;

    /**
     * Constructs an {@code XMLOutputter} with no validation.
     */
    public XMLOutputter() {
        this(XMLValidationType.NONE, "");
    }

    /**
     * Constructs an {@code XMLOutputter} with the specified validation
     * information.
     * <p>
     * Note that the received path will just be added to the resulting file as
     * received, and it won't be read of checked in any way.
     * 
     * @param validation
     *            the validation type
     * @param validationPath
     *            path to the validation file
     */
    public XMLOutputter(final XMLValidationType validation,
            final String validationPath) {
        super();

        checkNotNull(validation, "Received a null pointer as validation type");
        checkNotNull(validationPath,
                "Received a null pointer as validation path");

        validationType = validation;
        valFilePath = validationPath;
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
    public final void output(final Document value, final OutputStream stream)
            throws Exception {

        checkNotNull(value, "Received a null pointer as value");
        checkNotNull(stream, "Received a null pointer as output stream");

        final Document doc;

        doc = value.clone();

        setValidation(doc);

        new org.jdom2.output.XMLOutputter(Format.getPrettyFormat()).output(doc,
                stream);
    }

    /**
     * Sends a {@code Document} through a {@code Writer}.
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
    public final void output(final Document value, final Writer writer)
            throws Exception {

        checkNotNull(value, "Received a null pointer as value");
        checkNotNull(writer, "Received a null pointer as writer");

        final Document doc;

        doc = value.clone();

        setValidation(doc);

        new org.jdom2.output.XMLOutputter(Format.getPrettyFormat()).output(doc,
                writer);
    }

    /**
     * Sets the validation data to be stored on the resulting XML structure.
     * <p>
     * Note that the path won't be checked in any way, it will just be stored as
     * received on the {@code Document} validation information before sending
     * it.
     * 
     * @param validation
     *            the validation type
     * @param path
     *            path to the validation file
     */
    public final void setValidation(final XMLValidationType validation,
            final String path) {

        checkNotNull(validation, "Received a null pointer as validation type");
        checkNotNull(path, "Received a null pointer as validation path");

        validationType = validation;
        valFilePath = path;
    }

    /**
     * Returns the name of the element which will register the validation file.
     * <p>
     * This is used for DTD validation.
     * 
     * @return the DTD validation file element name
     */
    private final String getDTDValidatorName() {
        return ELEMENT_VALID;
    }

    /**
     * Returns the path to the validation file.
     * <p>
     * This file won't be read or checked in any way, it will just be stored in
     * the {@code Document} as part of the validation info.
     * 
     * @return the path to the validation file.
     */
    private final String getValidationPath() {
        return valFilePath;
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
     * This is used for XSD validation.
     * 
     * @return the XSD validation file attribute
     */
    private final String getXSDAttribute() {
        return XSD_ATTRIBUTE;
    }

    /**
     * Returns the validation namespace.
     * <p>
     * This is used for XSD validation.
     * 
     * @return the validation file namespace
     */
    private final Namespace getXSDNamespace() {
        return Namespace.getNamespace(NAMESPACE_PREFIX, NAMESPACE_URI);
    }

    /**
     * Sets the validation data into the specified {@code Document}.
     * <p>
     * How the data will be stores depends on the type of validation being used,
     * and in case of applying no validation the {@code Document} won't be
     * touched.
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
