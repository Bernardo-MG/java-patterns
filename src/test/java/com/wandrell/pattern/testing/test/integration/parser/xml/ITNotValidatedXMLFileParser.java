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

import java.io.Reader;

import org.jdom2.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.NotValidatedXMLFileParser;
import com.wandrell.pattern.testing.util.ResourceUtils;
import com.wandrell.pattern.testing.util.conf.TestContextConfig;

/**
 * Integration tests for {@link NotValidatedXMLFileParser} testing that it reads
 * XML files correctly.
 * <p>
 * The configuration values for the test will be injected through Spring.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Parsing a XML file returns the expected value.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see NotValidatedXMLFileParser
 */
@ContextConfiguration(TestContextConfig.XML)
public final class ITNotValidatedXMLFileParser
        extends AbstractTestNGSpringContextTests {

    /**
     * Value node name.
     * <p>
     * Injected through Spring from a properties file.
     */
    @Value("${xml.node.value}")
    private String nodeValue;
    /**
     * Path to the integers XML file.
     * <p>
     * Injected through Spring from a properties file.
     */
    @Value("${xml.integer.path}")
    private String xmlPath;

    /**
     * Default constructor.
     */
    public ITNotValidatedXMLFileParser() {
        super();
    }

    /**
     * Tests that parsing a XML file returns the expected value.
     */
    @Test
    public final void testParse() {
        final Parser<Reader, Document> parser; // Parser tested
        final Reader reader; // Reader to the test file
        final Integer value; // Tested result from the parsed file

        parser = new NotValidatedXMLFileParser();

        reader = ResourceUtils.getClassPathReader(xmlPath);
        value = Integer.parseInt(
                parser.parse(reader).getRootElement().getChildText(nodeValue));

        Assert.assertEquals(value, (Integer) 1);
    }

}
