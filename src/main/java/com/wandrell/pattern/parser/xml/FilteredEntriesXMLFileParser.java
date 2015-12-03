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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Reader;
import java.util.Collection;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import com.google.common.base.MoreObjects;
import com.wandrell.pattern.conf.XMLValidationType;

/**
 * Implementation of {@link AbstractAttributesFilterXMLFileParser} which filters
 * only nodes having an specific name. The resulting data is returned as a JDOM
 * 2 {@code Document}.
 * <p>
 * All the nodes with this name are acquired through an XPath query. Then those
 * not not having a certain combination of attributes will be filtered out, and
 * won't appear on the resulting {@code Document}.
 * <p>
 * This filtering process is simple. All those not having all the required
 * attributes set as {@code true}, or having any of the rejected attributes set
 * as {@code true} will be removed from the result. Along any node missing any
 * of the attributes.
 * <p>
 * It should be noted that this parser accepts validation files, which can be
 * used to set default values on the attributes.
 * 
 * @author Bernardo Martínez Garrido
 */
public final class FilteredEntriesXMLFileParser
        extends AbstractAttributesFilterXMLFileParser {

    /**
     * Base parser handling the creation of the {@code Document}.
     * <p>
     * This is just to use inheritance by composition, and does nothing apart
     * from returning the {@code Document} it generates, and handling
     * validation.
     */
    private final XMLFileParser      baseParser = new XMLFileParser();
    /**
     * The XPath expression as a string.
     * <p>
     * This is created from the filter data and then transformed into an XPath
     * structure.
     */
    private String                   expression;
    /**
     * The name of the nodes to filter.
     * <p>
     * This is used to create the XPath query, as it will return only those
     * nodes with this name.
     */
    private final String             nodeName;
    /**
     * The XPath expression.
     * <p>
     * This is created from the expression string, and used to filter the data
     * read from the XML file.
     */
    private XPathExpression<Element> xpath;

    /**
     * Constructs a {@code FilteredEntriesXMLFileParser} which will only pick
     * nodes with the specified name.
     * 
     * @param node
     *            the name of the nodes to filter
     */
    public FilteredEntriesXMLFileParser(final String node) {
        super();

        checkNotNull(node, "Received a null pointer as node");

        nodeName = node;
    }

    /**
     * Constructs a {@code FilteredEntriesXMLFileParser} which will only pick
     * nodes with the specified name, after applying the specified validation.
     * 
     * @param validation
     *            the validation type to apply
     * @param validationFile
     *            reader for the validation file
     * @param node
     *            the name of the nodes to filter
     */
    public FilteredEntriesXMLFileParser(final XMLValidationType validation,
            final Reader validationFile, final String node) {
        super();

        checkNotNull(node, "Received a null pointer as node");

        nodeName = node;

        setValidation(validation, validationFile);
    }

    /**
     * Returns the XML validation being used, or that no validation is being
     * applied.
     * 
     * @return the XML validation being used
     */
    public final XMLValidationType getValidationType() {
        return getBaseParser().getValidationType();
    }

    /**
     * Parses the XML file from the input into a JDOM2 {@code Document}.
     * <p>
     * This will contain only those nodes that pass the filter. Which means,
     * only those with the expected name and attributes.
     * 
     * @param input
     *            {@code Reader} for the XML file
     * @return a {@code Document} with the XML contents
     */
    @Override
    public final Document parse(final Reader input) {
        return filter(getBaseParser().parse(input));
    }

    /**
     * Sets the validation to be used, along the validation source.
     * 
     * @param type
     *            the validation type
     * @param file
     *            reader for the validation file
     */
    public final void setValidation(final XMLValidationType type,
            final Reader file) {
        getBaseParser().setValidation(type, file);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("node", nodeName)
                .add("rejected attributes", getRejectedAttributes())
                .add("required attributes", getRequiredAttributes()).toString();
    }

    /**
     * Builds the attributes XPath predicate for the filter.
     * <p>
     * The predicate filters out all those nodes not having the expected
     * attributes.
     * 
     * @return the XPath predicate to filter the attributes
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
     * Filters the received {@code Document}, returning a new {@code Document}
     * containing only those nodes with the expected name and attributes.
     * <p>
     * For this an XPath expression is built and used.
     * <p>
     * The filter will always use and apply the latest filter data.
     * 
     * @param document
     *            the {@code Document} to parse
     * @return the filtered {@code Document}
     */
    private final Document filter(final Document document) {
        final Collection<Element> nodes;  // Nodes resulting from the filter
        final Element root;               // Root for the new document
        final String rootName;            // Name for the root element

        checkNotNull(document, "Received a null pointer as document");
        checkArgument(document.hasRootElement(),
                "Received a document with no root");

        // Acquires the root name
        rootName = document.getRootElement().getName();

        // Filters the nodes
        nodes = getXPathExpression().evaluate(document.getRootElement());

        // Nodes are detached from the original Document
        for (final Element node : nodes) {
            node.detach();
        }

        // Builds a new tree with the nodes
        root = new Element(rootName);
        root.setContent(nodes);

        return new Document(root);
    }

    /**
     * Returns the base parser.
     * <p>
     * This is used to create the {@code Document} and handle validation, and
     * serves to implement inheritance by composition.
     * 
     * @return the base parser
     */
    private final XMLFileParser getBaseParser() {
        return baseParser;
    }

    /**
     * Generates the XPath expression for filtering the nodes. It will be used
     * to create an XPath object.
     * <p>
     * This will search for those nodes having the expected name and attributes.
     * All the ones not fitting this criteria will be ignored.
     * <p>
     * The required attributes should be set to {@code true}, and the rejected
     * ones should be set to {@code false}.
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
     * The expected node name.
     * <p>
     * All nodes not having this name will be ignored.
     * 
     * @return the name of the nodes to filter
     */
    private final String getFilteredNodeName() {
        return nodeName;
    }

    /**
     * Returns the {@code XPathExpression} used for filtering.
     * <p>
     * If this is the first time the method is called, or the filtering data has
     * changed, then the expression will be rebuilt and compiled.
     * <p>
     * Otherwise the same expression will be reused.
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
     * Signals that the data used the generate the XPath query has changed, and
     * the XPath expression should be built again.
     * <p>
     * The {@code XPathExpression} instance will be set to {@code null}, so it
     * is built again the next time it is required.
     */
    @Override
    protected final void onAttributesChange() {
        expression = null;
        xpath = null;
    }

}
