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
package com.wandrell.pattern.testing.test.unit.parser.xml.exception;

import java.io.Reader;

import org.jdom2.Document;

import com.wandrell.pattern.conf.XMLValidationType;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.ValidatedXMLFileParser;
import com.wandrell.pattern.testing.util.ResourceUtils;
import com.wandrell.pattern.testing.util.conf.XMLConf;
import com.wandrell.pattern.testing.util.test.unit.parser.xml.exception.AbstractUnitExceptionParseXMLReaderParser;

/**
 * Unit tests for {@link ValidatedXMLFileParser} implementing
 * {@code AbstractUnitExceptionParseXMLReaderParser} and using DTD validation.
 * 
 * @author Bernardo Martínez Garrido
 * @see ValidatedXMLFileParser
 */
public final class TestExceptionDTDValidationValidatedXMLFileParser
        extends AbstractUnitExceptionParseXMLReaderParser<Integer> {

    /**
     * Generates the parser to be tested.
     * 
     * @return the parser to be tested
     */
    private static final Parser<Reader, Document> buildParser() {
        return new ValidatedXMLFileParser(XMLValidationType.DTD,
                ResourceUtils.getClassPathReader(XMLConf.DTD_VALIDATION));
    }

    /**
     * Default constructor.
     */
    public TestExceptionDTDValidationValidatedXMLFileParser() {
        super(buildParser());
    }

}
