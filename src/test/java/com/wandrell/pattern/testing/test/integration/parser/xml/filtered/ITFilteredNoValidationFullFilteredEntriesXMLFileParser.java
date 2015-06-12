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
package com.wandrell.pattern.testing.test.integration.parser.xml.filtered;

import com.wandrell.pattern.parser.xml.AbstractAttributesFilterXMLFileParser;
import com.wandrell.pattern.parser.xml.FilteredEntriesXMLFileParser;
import com.wandrell.pattern.testing.util.basetest.integration.parser.xml.AbstractITParseAbstractAttributesFilterXMLFileParser;
import com.wandrell.pattern.testing.util.conf.XMLConf;

/**
 * Integration tests for {@link FilteredEntriesXMLFileParser} implementing
 * {@code AbstractITParseAbstractAttributesFilterXMLFileParser} and no
 * validation.
 * <p>
 * This test is called 'full' because all the entries contain all the
 * attributes.
 * 
 * @author Bernardo Martínez Garrido
 * @see FilteredEntriesXMLFileParser
 */
public final class ITFilteredNoValidationFullFilteredEntriesXMLFileParser
        extends AbstractITParseAbstractAttributesFilterXMLFileParser<Integer> {

    /**
     * Generates the parser to be tested.
     * 
     * @return the parser to be tested
     */
    private static final AbstractAttributesFilterXMLFileParser buildParser() {
        final AbstractAttributesFilterXMLFileParser parser;

        parser = new FilteredEntriesXMLFileParser(XMLConf.NODE_ROOT_FILTER);

        return parser;
    }

    /**
     * Default constructor.
     */
    public ITFilteredNoValidationFullFilteredEntriesXMLFileParser() {
        super(buildParser(), XMLConf.FILTERED_NO_VALIDATION_FULL, 6, 4, 3, 1,
                2, 9);
    }

}
