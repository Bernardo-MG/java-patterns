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
/**
 * Provides implementations of {@link com.wandrell.pattern.parser.Parser Parser}
 * for XML files.
 * <p>
 * {@link com.wandrell.pattern.parser.xml.NotValidatedXMLFileParser
 * NotValidatedXMLFileParser} is the faster parser in the package, but it does
 * not allows validation to be applied.
 * <p>
 * {@link com.wandrell.pattern.parser.xml.ValidatedXMLFileParser
 * ValidatedXMLFileParser} does allow to apply an XML validation process, but
 * the parsing can be slower.
 * <p>
 * Usually one or the other will be picked depending on if it is needed to apply
 * validation or not. To ease this a default XML parser,
 * {@link com.wandrell.pattern.parser.xml.XMLFileParser XMLFileParser}, takes
 * care of making this choice. It is composed by both parsers and picks one or
 * the other depending on if it is needed to apply validation or not.
 * <p>
 * Additionally there is the
 * {@link com.wandrell.pattern.parser.xml.FilteredEntriesXMLFileParser
 * FilteredEntriesXMLFileParser} which filters an XML file contents, returning
 * only part of it.
 */
package com.wandrell.pattern.parser.xml;
