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

package com.wandrell.pattern.testing.test.integration.parser.xml.exception;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.io.IOUtils;
import org.jdom2.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.wandrell.pattern.conf.XMLValidationType;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.ValidatedXMLFileParser;
import com.wandrell.pattern.testing.util.ResourceUtils;
import com.wandrell.pattern.testing.util.conf.TestContextConfig;

/**
 * Integration tests for {@link ValidatedXMLFileParser}, checking that
 * exceptions are thrown when validation files are invalid.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>An {@code Exception} is thrown when reading a XML file using an empty DTD
 * validation file.</li>
 * <li>An {@code Exception} is thrown when reading a XML file using an empty XSD
 * validation file.</li>
 * 
 * @author Bernardo Martínez Garrido
 * @see ValidatedXMLFileParser
 */
@ContextConfiguration(TestContextConfig.XML)
public final class ITExceptionBadValidationFileValidatedXMLFileParser
        extends AbstractTestNGSpringContextTests {

    /**
     * Path to the integers XML file.
     */
    @Value("${xml.integer.path}")
    private String xmlIntegerPath;

    /**
     * Default constructor.
     */
    public ITExceptionBadValidationFileValidatedXMLFileParser() {
        super();
    }

    /**
     * Tests that an {@code Exception} is thrown when reading a XML file using
     * an empty validation file, when using DTD validation.
     */
    @Test(expectedExceptions = Exception.class)
    public final void testParse_DTD_Empty() {
        final Parser<Reader, Document> parser;    // Tested parser

        parser = new ValidatedXMLFileParser(XMLValidationType.DTD,
                IOUtils.toBufferedReader(
                        new InputStreamReader(IOUtils.toInputStream(""))));

        parser.parse(ResourceUtils.getClassPathReader(xmlIntegerPath));
    }

    /**
     * Tests that an {@code Exception} is thrown when reading a XML file using
     * an empty validation file, when using XSD validation.
     */
    @Test(expectedExceptions = Exception.class)
    public final void testParse_XSD_Empty() {
        final Parser<Reader, Document> parser;    // Tested parser

        parser = new ValidatedXMLFileParser(XMLValidationType.XSD,
                IOUtils.toBufferedReader(
                        new InputStreamReader(IOUtils.toInputStream(""))));

        parser.parse(new BufferedReader(new InputStreamReader(
                ResourceUtils.getClassPathInputStream(xmlIntegerPath))));
    }

}
