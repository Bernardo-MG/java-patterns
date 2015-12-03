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

import java.io.Reader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;

/**
 * Abstract {@link Parser} for XML files, allowing filtering the contents based
 * on boolean attributes. The contents will be parsed into a JDOM 2
 * {@code Document}.
 * <p>
 * It does not offer an actual filtering process, that's a detail to be taken
 * care by implementations.
 * <p>
 * This class just stores two collections, which are the names of the boolean
 * attributes to be used for filtering the file. They are divided between
 * required ones, those which need to be set to {@code true}, and the rejected
 * ones, those which need to be set to {@code false}.
 * <p>
 * All the nodes whose attributes do not fit this criteria are expected be
 * ignored when parsing the XML file. What happens if a node lacks any of the
 * attributes will depend on the implementation.
 * <p>
 * Another important implementation detail is handling how the filter is
 * created. While the exact workings of it are left open, an abstract method,
 * {@link #onAttributesChange}, is offered as a way to react when the attributes
 * are modified.
 * 
 * @author Bernardo Martínez Garrido
 */
public abstract class AbstractAttributesFilterXMLFileParser
        implements Parser<Reader, Document> {

    /**
     * Attributes which should be set as {@code false}. Otherwise, the node will
     * be ignored.
     * <p>
     * There is no reason to store each attribute more than once, so a set is
     * used.
     */
    private final Collection<String> attrRejected = new LinkedHashSet<String>();
    /**
     * Attributes which should be set as {@code true}. Otherwise, the node will
     * be ignored.
     * <p>
     * There is no reason to store each attribute more than once, so a set is
     * used.
     */
    private final Collection<String> attrRequired = new LinkedHashSet<String>();

    /**
     * Constructs an {@code AbstractAttributesFilterXMLFileParser}.
     */
    public AbstractAttributesFilterXMLFileParser() {
        super();
    }

    /**
     * Adds an attribute which should be set to {@code false} on the parsed
     * nodes, or else these nodes will be ignored.
     * 
     * @param attribute
     *            an attribute the read nodes should have set to {@code false}
     */
    public final void addRejectedAttribute(final String attribute) {
        checkNotNull(attribute, "Received a null pointer as attribute");

        if (attribute.length() > 0) {
            getRejectedAttributesModifiable().add(attribute);
            onAttributesChange();
        }
    }

    /**
     * Adds an attribute which should be set to {@code true} on the parsed
     * nodes, or else these nodes will be ignored.
     * 
     * @param attribute
     *            an attribute the read nodes should have set to {@code true}
     */
    public final void addRequiredAttribute(final String attribute) {
        checkNotNull(attribute, "Received a null pointer as attribute");

        if (attribute.length() > 0) {
            getRequiredAttributesModifiable().add(attribute);
            onAttributesChange();
        }
    }

    /**
     * Removes all the rejected attributes used for filtering.
     * <p>
     * These are the attributes which should be set to {@code false} on the
     * parsed nodes, or else these nodes will be ignored.
     */
    public final void clearRejectedAttributes() {
        getRejectedAttributesModifiable().clear();
        onAttributesChange();
    }

    /**
     * Removes all the required attributes used for filtering.
     * <p>
     * These are the attributes which should be set to {@code true} on the
     * parsed nodes, or else these nodes will be ignored.
     */
    public final void clearRequiredAttributes() {
        getRequiredAttributesModifiable().clear();
        onAttributesChange();
    }

    /**
     * Returns a non modifiable collection containing all the rejected
     * attributes.
     * <p>
     * These are the attributes which should be set to {@code false} on the
     * parsed nodes, or else these nodes will be ignored.
     * <p>
     * This collection is unmodifiable. To change the stored rejected attributes
     * use the {@link #addRejectedAttribute}, {@link #removeRejectedAttribute}
     * or {@link #removeRejectedAttribute}.
     * 
     * 
     * @return the attributes which should be set as {@code false}
     */
    public final Collection<String> getRejectedAttributes() {
        return Collections
                .unmodifiableCollection(getRejectedAttributesModifiable());
    }

    /**
     * Returns a non modifiable collection containing all the required
     * attributes.
     * <p>
     * These are the attributes which should be set to {@code true} on the
     * parsed nodes, or else these nodes will be ignored.
     * <p>
     * This collection is unmodifiable. To change the stored required attributes
     * use the {@link #addRequiredAttribute}, {@link #removeRequiredAttribute}
     * or {@link #removeRequiredAttribute}.
     * 
     * @return the attributes which should be set as {@code true}
     */
    public final Collection<String> getRequiredAttributes() {
        return Collections
                .unmodifiableCollection(getRequiredAttributesModifiable());
    }

    /**
     * Removes one of the rejected attributes.
     * 
     * @param attribute
     *            the attribute to remove from the rejected ones
     */
    public final void removeRejectedAttribute(final String attribute) {
        getRejectedAttributesModifiable().remove(attribute);

        onAttributesChange();
    }

    /**
     * Removes one of the required attributes.
     * 
     * @param attribute
     *            the attribute to remove from the required ones
     */
    public final void removeRequiredAttribute(final String attribute) {
        getRequiredAttributesModifiable().remove(attribute);

        onAttributesChange();
    }

    /**
     * Returns the modifiable collection containing all the rejected attributes.
     * <p>
     * These are the attributes which should be set to {@code false} on the
     * nodes, or otherwise the node will be ignored.
     * 
     * @return the modifiable rejected attributes collection
     */
    protected final Collection<String> getRejectedAttributesModifiable() {
        return attrRejected;
    }

    /**
     * Returns the modifiable collection containing all the required attributes.
     * <p>
     * These are the attributes which should be set to {@code true} on the
     * nodes, or otherwise the node will be ignored.
     * 
     * @return the modifiable required attributes collection
     */
    protected final Collection<String> getRequiredAttributesModifiable() {
        return attrRequired;
    }

    /**
     * Signals that changes have occurred to the attributes used for filtering.
     * <p>
     * This method is called after adding and removing both required and
     * rejected attributes.
     */
    protected abstract void onAttributesChange();

}
