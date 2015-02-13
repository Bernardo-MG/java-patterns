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
package com.wandrell.testing.pattern.test.integration.parser.xml.read.filtered;

import com.wandrell.pattern.ResourceUtils;
import com.wandrell.pattern.parser.xml.XMLValidationType;
import com.wandrell.pattern.parser.xml.input.AbstractAttributesFilterXMLParser;
import com.wandrell.pattern.parser.xml.input.FilteredEntriesXMLInputParser;
import com.wandrell.testing.pattern.framework.conf.XMLConf;
import com.wandrell.testing.pattern.framework.test.integration.parser.xml.read.AbstractITReadAbstractAttributesFilterXMLInputParser;

/**
 * Integration tests for {@link FilteredEntriesXMLInputParser} implementing
 * {@code AbstractITReadAbstractAttributesFilterXMLInputParser} and XSD
 * validation.
 * <p>
 * The validation file applies default values for several of the entries
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @see FilteredEntriesXMLInputParser
 */
public final class ITReadFilteredDTDValidationFilteredXMLInputParser extends
        AbstractITReadAbstractAttributesFilterXMLInputParser<Integer> {

    /**
     * Generates the parser to be tested.
     * 
     * @return the parser to be tested
     */
    private static final AbstractAttributesFilterXMLParser buildParser() {
        final AbstractAttributesFilterXMLParser parser;

        parser = new FilteredEntriesXMLInputParser(
                XMLValidationType.DTD,
                ResourceUtils
                        .getClassPathInputStream(XMLConf.FILTERED_VALIDATION_DTD),
                XMLConf.NODE_ROOT_FILTER);

        return parser;
    }

    /**
     * Default constructor.
     */
    public ITReadFilteredDTDValidationFilteredXMLInputParser() {
        super(buildParser(), XMLConf.FILTERED_WITH_VALIDATION_DTD, 6, 4, 3, 1,
                2, 9);
    }

}
