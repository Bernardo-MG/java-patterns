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

import org.springframework.test.context.ContextConfiguration;
import com.wandrell.pattern.parser.xml.ValidatedXMLFileParser;
import com.wandrell.pattern.testing.util.conf.TestContextConfig;
import com.wandrell.pattern.testing.util.test.integration.parser.xml.exception.AbstractITExceptionNoValidatesValidatedXMLFileParser;

/**
 * Integration tests for {@link ValidatedXMLFileParser} using DTD validation.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Reading a file which doesn't validate throws a {@code Exception} .</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see ValidatedXMLFileParser
 */
@ContextConfiguration(TestContextConfig.PARSER_XML_DTD_VALIDATION_NO_VALIDATES)
public final class ITExceptionNoValidatesDTDValidatedXMLFileParser extends
AbstractITExceptionNoValidatesValidatedXMLFileParser {

    /**
     * Default constructor.
     */
    public ITExceptionNoValidatesDTDValidatedXMLFileParser() {
        super();
    }

}
