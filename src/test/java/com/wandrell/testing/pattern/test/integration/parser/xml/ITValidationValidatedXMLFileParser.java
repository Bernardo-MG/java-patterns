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
package com.wandrell.testing.pattern.test.integration.parser.xml;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.ValidatedXMLFileParser;
import com.wandrell.pattern.parser.xml.XMLValidationType;
import com.wandrell.testing.pattern.framework.conf.XMLConf;
import com.wandrell.testing.pattern.framework.util.ResourceUtils;

/**
 * Integration tests for {@link ValidatedXMLFileParser} with XSD validation.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Parsing a XSD-validated XML file returns the expected value.</li>
 * <li>Parsing a DTD-validated XML file returns the expected value.</li>
 * <li>Parsing after changing the validation information returns the expected
 * value.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @see ValidatedXMLFileParser
 */
public final class ITValidationValidatedXMLFileParser {

    /**
     * Parser to generate the tested value from the {@code Document}.
     */
    private final Parser<Document, Integer> parserDoc;

    {
        parserDoc = new Parser<Document, Integer>() {

            @Override
            public final Integer parse(final Document doc) {
                final Integer value;

                value = Integer.parseInt(doc.getRootElement().getChildText(
                        XMLConf.NODE_VALUE));

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
     * 
     * @throws Exception
     *             never, this is a required declaration
     */
    @Test
    public final void testParse_DTD() throws Exception {
        final Parser<Reader, Document> parser;  // Parser tested
        final Reader reader; // Reader to the test file
        final Integer value; // Tested result from the parsed file

        parser = new ValidatedXMLFileParser(XMLValidationType.DTD,
                ResourceUtils.getClassPathInputStream(XMLConf.DTD_VALIDATION));

        reader = new BufferedReader(new InputStreamReader(
                ResourceUtils.getClassPathInputStream(XMLConf.VALIDATED_DTD)));
        value = parserDoc.parse(parser.parse(reader));

        Assert.assertEquals(value, (Integer) 1);
    }

    /**
     * Tests that parsing a XSD-validated XML file returns the expected value.
     * 
     * @throws Exception
     *             never, this is a required declaration
     */
    @Test
    public final void testParse_XSD() throws Exception {
        final Parser<Reader, Document> parser;  // Parser tested
        final Reader reader; // Reader to the test file
        final Integer value; // Tested result from the parsed file

        parser = new ValidatedXMLFileParser(XMLValidationType.XSD,
                ResourceUtils.getClassPathInputStream(XMLConf.XSD_VALIDATION));

        reader = new BufferedReader(new InputStreamReader(
                ResourceUtils.getClassPathInputStream(XMLConf.VALIDATED_XSD)));
        value = parserDoc.parse(parser.parse(reader));

        Assert.assertEquals(value, (Integer) 1);
    }

    /**
     * Tests that parsing after changing the validation information returns the
     * expected value.
     * 
     * @throws Exception
     *             never, this is a required declaration
     */
    @Test
    public final void testParse_XSDToDTD() throws Exception {
        final ValidatedXMLFileParser parser;  // Parser tested
        final Reader reader; // Reader to the test file
        final Integer value; // Tested result from the parsed file

        parser = new ValidatedXMLFileParser(XMLValidationType.XSD,
                ResourceUtils.getClassPathInputStream(XMLConf.XSD_VALIDATION));

        parser.setValidation(XMLValidationType.DTD,
                ResourceUtils.getClassPathInputStream(XMLConf.DTD_VALIDATION));

        reader = new BufferedReader(new InputStreamReader(
                ResourceUtils.getClassPathInputStream(XMLConf.VALIDATED_DTD)));
        value = parserDoc.parse(parser.parse(reader));

        Assert.assertEquals(value, (Integer) 1);
    }

}
