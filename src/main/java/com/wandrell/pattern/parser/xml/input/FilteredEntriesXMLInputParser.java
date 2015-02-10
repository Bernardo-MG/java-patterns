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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Collection;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.MoreObjects;
import com.wandrell.pattern.parser.xml.XMLValidationType;

/**
 * Implementation of {@code AbstractFilteredAttributesXMLInputParser} using the
 * which filters only nodes with a set name.
 * <p>
 * All the nodes with a specific name are acquired through an XPath query, then
 * filtered according to the attributes they have.
 * <p>
 * Any node not having all the required attributes set as {@code true}, or
 * having any of the rejected attributes set as {@code true} is rejected.
 * <p>
 * Nodes not having any of these attributes are ignored. It should be noted that
 * this parser accepts validation files, which may set default values to the
 * attributes.
 * <p>
 * After being filtered all these nodes, and their subnodes, will be combined
 * into a single {@code Document} and processed as normal.
 * <p>
 * This parser has been created to generate test parameters from an XML file,
 * and is indirectly used by {@link com.wandrell.util.TestUtils TestUtils}.
 * <p>
 * Due to the way the filter works, it can fit into any process which requires
 * generating a collection of values from several nodes.
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @param <V>
 *            the type to be parsed from the input
 */
public final class FilteredEntriesXMLInputParser<V> extends
        AbstractAttributesFilterXMLInputParser<V> {

    /**
     * Logger being used to log the generated XPath expression.
     */
    private static final Logger            LOGGER = LoggerFactory
                                                          .getLogger(FilteredEntriesXMLInputParser.class);
    /**
     * Base parser handling the creation of the {@code Document}.
     * <p>
     * This is just to use inheritance by composition, and does nothing apart
     * from returning the {@code Document} it generates.
     * <p>
     * The parser is based on the SAX API, allowing to use validation, which is
     * required if default attribute values are to be used.
     */
    private final SAXInputParser<Document> baseParser;
    /**
     * The processor in charge of parsing the filtered nodes.
     */
    private final JDOMDocumentDecoder<V>   documentDecoder;
    /**
     * The XPath expression as a string.
     */
    private String                         expression;
    /**
     * The name of the nodes to filter.
     * <p>
     * All the nodes with this name will be acquires with an XPath query to be
     * filtered.
     */
    private final String                   nodeName;
    /**
     * The XPath expression.
     */
    private XPathExpression<Element>       xpath;

    /**
     * Returns the logger being used by this class.
     * <p>
     * It will be used to log the generated XPath expression.
     * 
     * @return the logger being used
     */
    private static final Logger getLogger() {
        return LOGGER;
    }

    /**
     * Constructs a parser which will filter nodes with the specified name, and
     * use the specified processor.
     * 
     * @param node
     *            the name of the nodes to filter
     * @param decoder
     *            the processor for parsing the created {@code Document}
     */
    public FilteredEntriesXMLInputParser(final String node,
            final JDOMDocumentDecoder<V> decoder) {
        super();

        checkNotNull(node, "Received a null pointer as node");
        checkNotNull(decoder, "Received a null pointer as decoder");

        nodeName = node;

        this.documentDecoder = decoder;

        // The parser receives a processor which just returns the generated
        // document
        baseParser = new SAXInputParser<Document>(
                new JDOMDocumentDecoder<Document>() {

                    @Override
                    public final Document decode(final Document doc) {
                        return doc;
                    }

                });
    }

    /**
     * Constructs a parser which will filter nodes with the specified name, and
     * use the specified processor, applying the specified validation.
     * 
     * @param validation
     *            the validation type
     * @param validationStream
     *            the validation file stream
     * @param node
     *            the name of the nodes to filter
     * @param decoder
     *            the decoder for parsing the {@code Document}
     */
    public FilteredEntriesXMLInputParser(final XMLValidationType validation,
            final InputStream validationStream, final String node,
            final JDOMDocumentDecoder<V> decoder) {
        super();

        checkNotNull(node, "Received a null pointer as node");
        checkNotNull(decoder, "Received a null pointer as processor");

        nodeName = node;

        this.documentDecoder = decoder;

        // The parser receives a processor which just returns the generated
        // document
        baseParser = new SAXInputParser<Document>(validation, validationStream,
                new JDOMDocumentDecoder<Document>() {

                    @Override
                    public final Document decode(final Document doc) {
                        return doc;
                    }

                });
    }

    @Override
    public final XMLValidationType getValidationType() {
        return getBaseParser().getValidationType();
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

        return getDocumentDecoder()
                .decode(filter(getBaseParser().read(stream)));
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

        return getDocumentDecoder()
                .decode(filter(getBaseParser().read(reader)));
    }

    @Override
    public final void setValidation(final XMLValidationType type,
            final InputStream validationStream) {
        getBaseParser().setValidation(type, validationStream);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("node", nodeName)
                .add("rejected attributes", getRejectedAttributes())
                .add("required attributes", getRequiredAttributes()).toString();
    }

    /**
     * Builds the XPath predicate filtering the nodes based on the attributes.
     * 
     * @return the XPath predicate filtering the attributes
     */
    private final String buildAttributesPredicate() {
        final StringBuffer predicate; // Buffer for creating the predicate

        predicate = new StringBuffer();

        // Required attributes
        for (final String attribute : getRequiredAttributesModifiable()) {
            if (predicate.length() > 0) {
                predicate.append(" and ");
            }
            predicate.append(String.format("@%s='%s'", attribute, "true"));
        }

        // Rejected attributes
        for (final String attribute : getRejectedAttributesModifiable()) {
            if (predicate.length() > 0) {
                predicate.append(" and ");
            }
            predicate.append(String.format("@%s='%s'", attribute, "false"));
        }

        return predicate.toString();
    }

    /**
     * Filters the received {@code Document}.
     * <p>
     * All the nodes resulting from the XPath query are used to create a new
     * {@code Document}, which will be the one returned.
     * 
     * @param doc
     *            the {@code Document} to parse
     * @return the filtered {@code Document}
     */
    private final Document filter(final Document doc) {
        final Collection<Element> nodes;  // Nodes resulting from the filter
        final Element root;               // Root for the new document

        checkNotNull(doc, "Received a null pointer as document");
        checkArgument(doc.hasRootElement(), "Received a document with no root");

        // Filters the nodes
        nodes = getXPathExpression().evaluate(doc.getRootElement());

        // Logs the result
        if (getLogger().isDebugEnabled()) {
            getLogger().debug(
                    String.format(
                            "Executed XPath expression '%s'. Got %d nodes",
                            getExpression(), nodes.size()));
        }

        // Builds a new tree with the nodes
        root = new Element("root");
        for (final Element node : nodes) {
            node.detach();
        }
        root.setContent(nodes);

        return new Document(root);
    }

    /**
     * Returns the base parser which creates the {@code Document}.
     * <p>
     * This is just to implement inheritance by composition.
     * 
     * @return the base parser
     */
    private final SAXInputParser<Document> getBaseParser() {
        return baseParser;
    }

    /**
     * Returns the module in charge of parsing the {@code Document}.
     * 
     * @return the module in charge of parsing the {@code Document}
     */
    private final JDOMDocumentDecoder<V> getDocumentDecoder() {
        return documentDecoder;
    }

    /**
     * Generates the XPath expression for filtering the nodes.
     * <p>
     * This is created combining the nodes name and the required and rejected
     * attributes.
     * <p>
     * The required attributes should be set to {@code true}, the rejected ones
     * should be set to {@code false}.
     * <p>
     * All the nodes which do not fit the attributes criteria, or don't have the
     * correct name, will be ignored.
     * 
     * @return the XPath expression used to filter the nodes
     */
    private final String getExpression() {
        final String attrbFilter; // Predicate filtering the attributes

        if (expression == null) {
            // Builds the target from the attributes
            attrbFilter = buildAttributesPredicate();

            // Builds final expression
            if (attrbFilter.length() > 0) {
                // The predicates are combined with the node name predicate
                expression = String.format("//%s[%s]", getFilteredNodeName(),
                        attrbFilter);
            } else {
                // There are no predicates
                // The expression only filters by node name
                expression = String.format("//%s", getFilteredNodeName());
            }
        }

        return expression;
    }

    /**
     * The name of the nodes to filter.
     * <p>
     * All nodes not having this name will be ignored.
     * 
     * @return the name of the nodes to filter
     */
    private final String getFilteredNodeName() {
        return nodeName;
    }

    /**
     * Returns the XPath expression.
     * 
     * @return the XPath expression
     */
    private final XPathExpression<Element> getXPathExpression() {
        if (xpath == null) {
            xpath = XPathFactory.instance().compile(getExpression(),
                    Filters.element());
        }

        return xpath;
    }

    /**
     * The data used the generate the XPath query has changed, and the XPath
     * data should be built again.
     * <p>
     * For this it is only needed to set the XPath expression to {@code null},
     * so when they are required the class will have to build them.
     */
    @Override
    protected final void statusChanged() {
        expression = null;
        xpath = null;
    }

}
