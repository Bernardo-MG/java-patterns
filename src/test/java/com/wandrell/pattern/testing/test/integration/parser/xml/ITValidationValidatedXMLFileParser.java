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

package com.wandrell.pattern.testing.test.integration.parser.xml;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

import org.jdom2.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.conf.XMLValidationType;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.ValidatedXMLFileParser;
import com.wandrell.pattern.testing.util.ResourceUtils;
import com.wandrell.pattern.testing.util.conf.TestContextConfig;

/**
 * Integration tests for {@link ValidatedXMLFileParser} testing that it reads
 * XML files correctly.
 * <p>
 * The configuration values for the test will be injected through Spring.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Parsing a XSD-validated XML file returns the expected value.</li>
 * <li>Parsing a DTD-validated XML file returns the expected value.</li>
 * <li>Parsing a XML file with no validation returns the expected value.</li>
 * <li>Parsing after changing the validation information returns the expected
 * value.</li>
 * <li>Parsing after setting the validation information returns the expected
 * value.</li>
 * <li>Parsing after removing the validation information returns the expected
 * value.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see ValidatedXMLFileParser
 */
@ContextConfiguration(TestContextConfig.XML)
public final class ITValidationValidatedXMLFileParser
        extends AbstractTestNGSpringContextTests {

    /**
     * Path to the DTD file.
     * <p>
     * Injected through Spring from a properties file.
     */
    @Value("${xml.dtd.path}")
    private String                          dtdPath;
    /**
     * Value node name.
     * <p>
     * Injected through Spring from a properties file.
     */
    @Value("${xml.node.value}")
    private String                          nodeValue;
    /**
     * Parser to generate the tested value from the {@code Document}.
     */
    private final Parser<Document, Integer> parserDoc;
    /**
     * Path to the DTD validated XML file.
     * <p>
     * Injected through Spring from a properties file.
     */
    @Value("${xml.validated.dtd.path}")
    private String                          xmlDtdPath;
    /**
     * Path to the integers XML file.
     * <p>
     * Injected through Spring from a properties file.
     */
    @Value("${xml.integer.path}")
    private String                          xmlPath;
    /**
     * Path to the XSD validated XML file.
     * <p>
     * Injected through Spring from a properties file.
     */
    @Value("${xml.validated.xsd.path}")
    private String                          xmlXsdPath;
    /**
     * Path to the XSD file.
     * <p>
     * Injected through Spring from a properties file.
     */
    @Value("${xml.xsd.path}")
    private String                          xsdPath;

    {
        parserDoc = new Parser<Document, Integer>() {

            @Override
            public final Integer parse(final Document doc) {
                final Integer value;

                value = Integer
                        .parseInt(doc.getRootElement().getChildText(nodeValue));

                return value;
            }

        };
    }

    /**
     * Default constructor.
     */
    public ITValidationValidatedXMLFileParser() {
        super();
    }

    /**
     * Tests that parsing a DTD-Validated XML file returns the expected value.
     */
    @Test
    public final void testParse_DTD() {
        final Parser<Reader, Document> parser; // Parser tested
        final Reader reader; // Reader to the test file
        final Integer value; // Tested result from the parsed file

        parser = new ValidatedXMLFileParser(XMLValidationType.DTD,
                ResourceUtils.getClassPathReader(dtdPath));

        reader = ResourceUtils.getClassPathReader(xmlDtdPath);
        value = parserDoc.parse(parser.parse(reader));

        Assert.assertEquals(value, (Integer) 1);
    }

    /**
     * Tests that parsing a XML file with no validation returns the expected
     * value.
     */
    @Test
    public final void testParse_None() {
        final ValidatedXMLFileParser parser; // Parser tested
        final Integer value; // Tested result from the parsed file
        final Reader reader; // Reader to the test file

        parser = new ValidatedXMLFileParser();

        reader = new BufferedReader(new InputStreamReader(
                ResourceUtils.getClassPathInputStream(xmlPath)));

        value = parserDoc.parse(parser.parse(reader));

        Assert.assertEquals(value, (Integer) 1);
    }

    /**
     * Tests that parsing after setting the validation information returns the
     * expected value.
     */
    @Test
    public final void testParse_None_XSD() {
        final ValidatedXMLFileParser parser; // Parser tested
        final Integer value; // Tested result from the parsed file
        Reader reader; // Reader to the test file

        parser = new ValidatedXMLFileParser();

        reader = ResourceUtils.getClassPathReader(xmlPath);

        parserDoc.parse(parser.parse(reader));

        parser.setValidation(XMLValidationType.DTD,
                ResourceUtils.getClassPathReader(dtdPath));

        reader = ResourceUtils.getClassPathReader(xmlDtdPath);

        value = parserDoc.parse(parser.parse(reader));

        Assert.assertEquals(value, (Integer) 1);
    }

    /**
     * Tests that parsing a XSD-validated XML file returns the expected value.
     */
    @Test
    public final void testParse_XSD() {
        final Parser<Reader, Document> parser; // Parser tested
        final Reader reader; // Reader to the test file
        final Integer value; // Tested result from the parsed file

        parser = new ValidatedXMLFileParser(XMLValidationType.XSD,
                ResourceUtils.getClassPathReader(xsdPath));

        reader = ResourceUtils.getClassPathReader(xmlXsdPath);
        value = parserDoc.parse(parser.parse(reader));

        Assert.assertEquals(value, (Integer) 1);
    }

    /**
     * Tests that parsing after changing the validation information returns the
     * expected value.
     */
    @Test
    public final void testParse_XSD_DTD() {
        final ValidatedXMLFileParser parser; // Parser tested
        final Integer value; // Tested result from the parsed file
        Reader reader; // Reader to the test file

        parser = new ValidatedXMLFileParser(XMLValidationType.XSD,
                ResourceUtils.getClassPathReader(xsdPath));

        reader = ResourceUtils.getClassPathReader(xmlXsdPath);

        parserDoc.parse(parser.parse(reader));

        parser.setValidation(XMLValidationType.DTD,
                ResourceUtils.getClassPathReader(dtdPath));

        reader = ResourceUtils.getClassPathReader(xmlDtdPath);

        value = parserDoc.parse(parser.parse(reader));

        Assert.assertEquals(value, (Integer) 1);
    }

    /**
     * Tests that parsing after removing the validation information returns the
     * expected value.
     */
    @Test
    public final void testParse_XSD_None() {
        final ValidatedXMLFileParser parser; // Parser tested
        final Integer value; // Tested result from the parsed file
        Reader reader; // Reader to the test file

        parser = new ValidatedXMLFileParser(XMLValidationType.XSD,
                ResourceUtils.getClassPathReader(xsdPath));

        reader = ResourceUtils.getClassPathReader(xmlXsdPath);

        parserDoc.parse(parser.parse(reader));

        parser.setValidation(XMLValidationType.NONE, null);

        reader = ResourceUtils.getClassPathReader(xmlPath);

        value = parserDoc.parse(parser.parse(reader));

        Assert.assertEquals(value, (Integer) 1);
    }

}
