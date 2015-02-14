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
 * This pattern allows to transform one structure to another, both containing
 * the same or similar data. And it is represented by the {@link Parser}
 * interface.
 * <p>
 * Usually this is required when reading the contents of a file into the
 * application, in which case a {@code Reader}, {@code InputStream} or similar
 * class is used as the input.
 * <p>
 * If the file being parsed is of a common data type, such as XML or JSON, this
 * interface also serves to hide the actual implementation being used, working
 * as an adapter.
 * <p>
 * Parsers can be used to parse the result of another parser, creating a chain
 * of parser, which can help to simplify complex parsing procedures. The basic
 * implementations contained in the library are meant to be the first step in
 * such a chain.
 * <p>
 * The {@code xml} package contains parsers for XML files.
 */
package com.wandrell.pattern.parser;

