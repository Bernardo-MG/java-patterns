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
/**
 * Provides implementations of {@link com.wandrell.pattern.parser.InputParser
 * InputParser} for XML files.
 * <p>
 * Two main implementations are offered, {@link SAXInputParser} based on the SAX
 * API, and {@code JDOMStAXInputParser} based on the StAX API. Both make use of
 * the JDOM library.
 * <p>
 * The first allows the use of validation files, while the second does not, but
 * in exchange it is expected to be faster.
 * <p>
 * Additionally to this, there is the {@code FilteredEntriesXMLInputParser},
 * which allows to filter the XML file, parsing only a selected group of nodes,
 * generated from applying a filter to the parsed XML file.
 */
package com.wandrell.pattern.parser.xml;

