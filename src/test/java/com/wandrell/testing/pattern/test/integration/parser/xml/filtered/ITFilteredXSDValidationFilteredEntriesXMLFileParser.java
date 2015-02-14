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
package com.wandrell.testing.pattern.test.integration.parser.xml.filtered;

import com.wandrell.pattern.parser.xml.AbstractAttributesFilterXMLFileParser;
import com.wandrell.pattern.parser.xml.FilteredEntriesXMLFileParser;
import com.wandrell.pattern.parser.xml.XMLValidationType;
import com.wandrell.testing.pattern.framework.conf.XMLConf;
import com.wandrell.testing.pattern.framework.test.integration.parser.xml.AbstractITParseAbstractAttributesFilterXMLFileParser;
import com.wandrell.testing.pattern.framework.util.ResourceUtils;

/**
 * Integration tests for {@link FilteredEntriesXMLFileParser} implementing
 * {@code AbstractITParseAbstractAttributesFilterXMLFileParser} and XSD
 * validation.
 * <p>
 * The validation file applies default values for several of the entries
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @see FilteredEntriesXMLFileParser
 */
public final class ITFilteredXSDValidationFilteredEntriesXMLFileParser extends
        AbstractITParseAbstractAttributesFilterXMLFileParser<Integer> {

    /**
     * Generates the parser to be tested.
     * 
     * @return the parser to be tested
     */
    private static final AbstractAttributesFilterXMLFileParser buildParser() {
        final AbstractAttributesFilterXMLFileParser parser;

        parser = new FilteredEntriesXMLFileParser(
                XMLValidationType.XSD,
                ResourceUtils
                        .getClassPathInputStream(XMLConf.FILTERED_VALIDATION_XSD),
                XMLConf.NODE_ROOT_FILTER);

        return parser;
    }

    /**
     * Default constructor.
     */
    public ITFilteredXSDValidationFilteredEntriesXMLFileParser() {
        super(buildParser(), XMLConf.FILTERED_WITH_VALIDATION_XSD, 6, 4, 3, 1,
                2, 9);
    }

}
