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

package com.wandrell.pattern.testing.test.integration.parser.xml.filtered;

import org.springframework.test.context.ContextConfiguration;

import com.wandrell.pattern.parser.xml.FilteredEntriesXMLFileParser;
import com.wandrell.pattern.testing.util.conf.TestContextConfig;
import com.wandrell.pattern.testing.util.test.integration.parser.xml.filtered.AbstractITParseAbstractAttributesFilterXMLFileParser;

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
@ContextConfiguration(TestContextConfig.PARSER_XML_FILTERED_NO_VALIDATION_FULL)
public final class ITFilteredNoValidationFullFilteredEntriesXMLFileParser
        extends AbstractITParseAbstractAttributesFilterXMLFileParser<Integer> {

    /**
     * Default constructor.
     */
    public ITFilteredNoValidationFullFilteredEntriesXMLFileParser() {
        super();
    }

}
