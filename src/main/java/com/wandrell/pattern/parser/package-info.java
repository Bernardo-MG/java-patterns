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
 * Provides interfaces and basic implementations for the parser pattern.
 * <p>
 * The parser transforms a data structure into another, both containing the same
 * or similar data. In the case any data is lost during the process this is
 * called a lossfull parsing, otherwise it is a lossless parsing.
 * <h2>Interfaces</h2>
 * <p>
 * The interface {@link com.wandrell.pattern.parser.Parser Parser} offers a
 * single method, which receives an input type and returns an output type.
 * <h2>Implementations</h2>
 * <p>
 * Default implementations exists for parsing XML files.
 * <p>
 * The {@link com.wandrell.pattern.parser.xml.XMLFileParser XMLFileParser} takes
 * a {@code Reader}, pointing to an XML file, and returns a JDOM 2
 * {@code Document}. It is capable of applying validation, which may be from an
 * XSD or DTD file.
 */
package com.wandrell.pattern.parser;

