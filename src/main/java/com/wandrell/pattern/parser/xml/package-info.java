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
 * Provides implementations of {@link com.wandrell.pattern.parser.Parser Parser}
 * for XML files.
 * <p>
 * {@link NotValidatedXMLFileParser} is a faster parser, where no validation is
 * applied, while {@link ValidatedXMLFileParser} applies XSD or DTD validation
 * files, but in exchange can be slower.
 * <p>
 * While these two are offered as is, they are not required.
 * {@link XMLFileParser} takes care of the speed problem, as it is composed by
 * both parsers, and will switch from one to another depending on if validation
 * is needed or not.
 * <p>
 * Additionally there is the {@link FilteredEntriesXMLFileParser}, which filters
 * the file's contents, returning only a part of it.
 * <p>
 * They all depend on the JDOM2 library, as the value returned from by the
 * parsers is a {@link org.jdom2.Document Document}.
 */
package com.wandrell.pattern.parser.xml;

