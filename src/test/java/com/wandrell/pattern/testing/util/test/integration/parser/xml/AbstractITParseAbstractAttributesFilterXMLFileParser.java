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

package com.wandrell.pattern.testing.util.test.integration.parser.xml;

import java.io.Reader;

import org.jdom2.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.AbstractAttributesFilterXMLFileParser;
import com.wandrell.pattern.testing.util.ResourceUtils;
import com.wandrell.pattern.testing.util.conf.XMLConf;

/**
 * Abstract integration tests for {@link AbstractAttributesFilterXMLFileParser}.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>The returned entries change after changing the filtered attributes.</li>
 * <li>Applying no filter returns all the entries.</li>
 * <li>Rejecting a not existing attribute returns no entries.</li>
 * <li>Requiring a not existing attribute returns no entries.</li>
 * <li>Requiring and rejecting the same attribute returns no entries.</li>
 * <li>Rejecting the attribute 1 returns the correct amount of entries.</li>
 * <li>Rejecting the attribute 1 and 2 returns the correct amount of entries.
 * </li>
 * <li>Requiring the attribute 1 returns the correct amount of entries.</li>
 * <li>Requiring the attribute 1 and 2 returns the correct amount of entries.
 * </li>
 * <li>Requiring the attribute 1 and rejecting the attribute 2 returns the
 * correct amount of entries.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see InputParser
 */
public abstract class AbstractITParseAbstractAttributesFilterXMLFileParser<V>
extends AbstractTestNGSpringContextTests{

    /**
     * Amount of entries after filtering based on rejecting a not existing
     * attribute.
     */
    private static final Integer                        NO_NOT_EXISTING               = 0;
    /**
     * Amount of entries after filtering based on rejecting and requiring the
     * same attribute.
     */
    private static final Integer                        WITH_ATTRIBUTE1_NO_ATTRIBUTE1 = 0;
    /**
     * Amount of entries after filtering based on requiring a not existing
     * attribute.
     */
    private static final Integer                        WITH_NOT_EXISTING             = 0;
    /**
     * Amount of entries after filtering based on rejecting the attribute 1.
     */
	@Value("${count.no1}")
    private  Integer                               noAttribute1;
    /**
     * Amount of entries after filtering based on rejecting the attribute 1 and
     * the attribute 2.
     */
	@Value("${count.no1no2}")
    private  Integer                               noAttribute1NoAttribute2;
    /**
     * Parser being tested.
     */
	@Autowired
	@Qualifier("parser")
    private  AbstractAttributesFilterXMLFileParser parser;
    /**
     * Parser which counts the {@code Document} root nodes.
     */
    private final Parser<Document, Integer>             parserNodes;
    /**
     * Path to the test values file.
     */
	@Autowired
	@Qualifier("xmlPath")
    private String                                path;
    /**
     * Amount of entries after applying no filter.
     */
	@Value("${count.total}")
    private  Integer                               total;
    /**
     * Amount of entries after filtering based on requiring the attribute 1.
     */
	@Value("${count.with1}")
    private  Integer                               withAttribute1;
    /**
     * Amount of entries after filtering based on requiring the attribute 1 and
     * rejecting the attribute 2.
     */
	@Value("${count.with1No2}")
    private  Integer                               withAttribute1NoAttribute2;
    /**
     * Amount of entries after filtering based on requiring the attribute 1 and
     * the attribute 2.
     */
	@Value("${count.with1With2}")
    private  Integer                               withAttribute1WithAttribute2;

    {
        parserNodes = new Parser<Document, Integer>() {

            @Override
            public Integer parse(final Document doc) {
                return doc.getRootElement().getChildren().size();
            }

        };
    }

    /**
     * Constructs the test with the specified counts.
     * 
     * @param parser
     *            parser being tested
     */
    public AbstractITParseAbstractAttributesFilterXMLFileParser() {
        super();
    }

    /**
     * Resets the parser state before each test.
     */
    @BeforeMethod
    public final void clear() {
        getParser().clearRejectedAttributes();
        getParser().clearRequiredAttributes();
    }

    /**
     * Returns the parser being tested.
     * 
     * @return the parser being tested
     */
    protected final AbstractAttributesFilterXMLFileParser getParser() {
        return parser;
    }

    /**
     * Returns the path to the test data file.
     * 
     * @return the path to the test data file
     */
    protected final String getPath() {
        return path;
    }

    /**
     * Tests that the returned entries change after changing the filtered
     * attributes.
     */
    @Test
    public final void testParse_FilterChanges_Adapts() {
        Reader r; // Reader for the test data

        r = ResourceUtils.getClassPathReader(getPath());

        Assert.assertEquals(parserNodes.parse(getParser().parse(r)), total);

        getParser().addRequiredAttribute(XMLConf.ATTRIBUTE_ATTRIBUTE1);
        getParser().addRejectedAttribute(XMLConf.ATTRIBUTE_ATTRIBUTE1);

        r = ResourceUtils.getClassPathReader(getPath());
        Assert.assertEquals(parserNodes.parse(getParser().parse(r)),
                WITH_ATTRIBUTE1_NO_ATTRIBUTE1);
    }

    /**
     * Tests that applying no filter returns all the entries.
     */
    @Test
    public final void testParse_NoFilter_ReturnsAll() {
        final Reader r; // Reader for the test data

        r = ResourceUtils.getClassPathReader(getPath());

        Assert.assertEquals(parserNodes.parse(getParser().parse(r)), total);
    }

    /**
     * Tests that rejecting the attribute 1 returns the correct amount of
     * entries.
     */
    @Test
    public final void testParse_Rejects1_ReturnsPart() {
        final Reader r; // Reader for the test data

        r = ResourceUtils.getClassPathReader(getPath());

        getParser().addRejectedAttribute(XMLConf.ATTRIBUTE_ATTRIBUTE1);

        Assert.assertEquals(parserNodes.parse(getParser().parse(r)),
                noAttribute1);
    }

    /**
     * Tests that rejecting the attribute 1 and 2 returns the correct amount of
     * entries.
     */
    @Test
    public final void testParse_Rejects1And2_ReturnsPart() {
        final Reader r; // Reader for the test data

        r = ResourceUtils.getClassPathReader(getPath());

        getParser().addRejectedAttribute(XMLConf.ATTRIBUTE_ATTRIBUTE1);
        getParser().addRejectedAttribute(XMLConf.ATTRIBUTE_ATTRIBUTE2);

        Assert.assertEquals(parserNodes.parse(getParser().parse(r)),
                noAttribute1NoAttribute2);
    }

    /**
     * Tests that rejecting a not existing attribute returns no entries.
     */
    @Test
    public final void testParse_RejectsNotExisting_ReturnsNone() {
        final Reader r; // Reader for the test data

        r = ResourceUtils.getClassPathReader(getPath());

        getParser().addRejectedAttribute(XMLConf.ATTRIBUTE_NOT_EXISTING);

        Assert.assertEquals(parserNodes.parse(getParser().parse(r)),
                NO_NOT_EXISTING);
    }

    /**
     * Tests that requiring the attribute 1 returns the correct amount of
     * entries.
     */
    @Test
    public final void testParse_Requires1_ReturnsPart() {
        final Reader r; // Reader for the test data

        r = ResourceUtils.getClassPathReader(getPath());

        getParser().addRequiredAttribute(XMLConf.ATTRIBUTE_ATTRIBUTE1);

        Assert.assertEquals(parserNodes.parse(getParser().parse(r)),
                withAttribute1);
    }

    /**
     * Tests that requiring the attribute 1 and 2 returns the correct amount of
     * entries.
     */
    @Test
    public final void testParse_Requires1And2_ReturnsPart() {
        final Reader r; // Reader for the test data

        r = ResourceUtils.getClassPathReader(getPath());

        getParser().addRequiredAttribute(XMLConf.ATTRIBUTE_ATTRIBUTE1);
        getParser().addRequiredAttribute(XMLConf.ATTRIBUTE_ATTRIBUTE2);

        Assert.assertEquals(parserNodes.parse(getParser().parse(r)),
                withAttribute1WithAttribute2);
    }

    /**
     * Tests that requiring and rejecting the same attribute returns no entries.
     */
    @Test
    public final void testParse_Requires1Rejects1_ReturnsNone() {
        final Reader r; // Reader for the test data

        r = ResourceUtils.getClassPathReader(getPath());

        getParser().addRequiredAttribute(XMLConf.ATTRIBUTE_ATTRIBUTE1);
        getParser().addRejectedAttribute(XMLConf.ATTRIBUTE_ATTRIBUTE1);

        Assert.assertEquals(parserNodes.parse(getParser().parse(r)),
                WITH_ATTRIBUTE1_NO_ATTRIBUTE1);
    }

    /**
     * Tests that requiring the attribute 1 and rejecting the attribute 2
     * returns the correct amount of entries.
     */
    @Test
    public final void testParse_Requires1Rejects2_ReturnsPart() {
        final Reader r; // Reader for the test data

        r = ResourceUtils.getClassPathReader(getPath());

        getParser().addRequiredAttribute(XMLConf.ATTRIBUTE_ATTRIBUTE1);
        getParser().addRejectedAttribute(XMLConf.ATTRIBUTE_ATTRIBUTE2);

        Assert.assertEquals(parserNodes.parse(getParser().parse(r)),
                withAttribute1NoAttribute2);
    }

    /**
     * Tests that requiring a not existing attribute returns no entries.
     */
    @Test
    public final void testParse_RequiresNotExisting_ReturnsNone() {
        final Reader r; // Reader for the test data

        r = ResourceUtils.getClassPathReader(getPath());

        getParser().addRequiredAttribute(XMLConf.ATTRIBUTE_NOT_EXISTING);

        Assert.assertEquals(parserNodes.parse(getParser().parse(r)),
                WITH_NOT_EXISTING);
    }

}
