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
package com.wandrell.pattern.testing.test.unit.parser.xml.filtered;

import java.io.Reader;

import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.xml.AbstractAttributesFilterXMLFileParser;

/**
 * Unit tests for {@link AbstractAttributesFilterXMLFileParser} checking that
 * the attributes methods work correctly.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Empty strings are rejected when trying to add them as rejected attributes
 * </li>
 * <li>Empty strings are rejected when trying to add them as required attributes
 * </li>
 * <li>Already added strings are rejected when trying to add them as rejected
 * attributes</li>
 * <li>Already added strings are rejected when trying to add them as required
 * attributes</li>
 * <li>Rejected attributes are correctly removed</li>
 * <li>Required attributes are correctly removed</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see AbstractAttributesFilterXMLFileParser
 */
public final class TestAttributesAbstractAttributesFilterXMLFileParser {

    /**
     * Attribute being used for the tests.
     */
    private static final String                   ATTRIBUTE = "attribute";
    /**
     * Parser being tested.
     * <p>
     * It is created once for all the tests.
     */
    private AbstractAttributesFilterXMLFileParser parser;

    /**
     * Default constructor.
     */
    public TestAttributesAbstractAttributesFilterXMLFileParser() {
        super();
    }

    /**
     * Clears all the attributes before each method to restore the basic state.
     */
    @BeforeMethod
    public final void clear() {
        parser.clearRejectedAttributes();
        parser.clearRequiredAttributes();
    }

    /**
     * Creates the parser being tested before any test is run.
     */
    @BeforeTest
    public final void initialize() {
        parser = new AbstractAttributesFilterXMLFileParser() {

            @Override
            public final Document parse(final Reader reader) {
                return new Document();
            }

            @Override
            protected final void onAttributesChange() {}

        };
    }

    /**
     * Tests that empty strings are rejected when trying to add them as rejected
     * attributes.
     */
    @Test
    public final void testAddRejectedAttribute_EmptyString_Rejected() {
        Assert.assertEquals(parser.getRejectedAttributes().size(), 0);

        parser.addRejectedAttribute("");

        Assert.assertEquals(parser.getRejectedAttributes().size(), 0);
    }

    /**
     * Tests that already added strings are rejected when trying to add them as
     * rejected attributes.
     */
    @Test
    public final void testAddRejectedAttribute_Existing_Rejected() {
        Assert.assertEquals(parser.getRejectedAttributes().size(), 0);

        parser.addRejectedAttribute(ATTRIBUTE);
        parser.addRejectedAttribute(ATTRIBUTE);

        Assert.assertEquals(parser.getRejectedAttributes().size(), 1);
    }

    /**
     * Tests that empty strings are rejected when trying to add them as required
     * attributes.
     */
    @Test
    public final void testAddRequiredAttribute_EmptyString_Rejected() {
        Assert.assertEquals(parser.getRequiredAttributes().size(), 0);

        parser.addRequiredAttribute("");

        Assert.assertEquals(parser.getRequiredAttributes().size(), 0);
    }

    /**
     * Tests that already added strings are rejected when trying to add them as
     * required attributes.
     */
    @Test
    public final void testAddRequiredAttribute_Existing_Rejected() {
        Assert.assertEquals(parser.getRequiredAttributes().size(), 0);

        parser.addRequiredAttribute(ATTRIBUTE);
        parser.addRequiredAttribute(ATTRIBUTE);

        Assert.assertEquals(parser.getRequiredAttributes().size(), 1);
    }

    /**
     * Tests that rejected attributes are correctly removed.
     */
    @Test
    public final void testRemoveRejectedAttribute() {
        parser.addRejectedAttribute(ATTRIBUTE);

        Assert.assertEquals(parser.getRejectedAttributes().size(), 1);

        parser.removeRejectedAttribute(ATTRIBUTE);

        Assert.assertEquals(parser.getRejectedAttributes().size(), 0);
    }

    /**
     * Tests that required attributes are correctly removed.
     */
    @Test
    public final void testRemoveRequiredAttribute() {
        parser.addRequiredAttribute(ATTRIBUTE);

        Assert.assertEquals(parser.getRequiredAttributes().size(), 1);

        parser.removeRequiredAttribute(ATTRIBUTE);

        Assert.assertEquals(parser.getRequiredAttributes().size(), 0);
    }

}
