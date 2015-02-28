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
 * Provides interfaces and basic implementations for outputters.
 * <p>
 * An outputter sends a data structure through an output operation to, for
 * example, write it into a file.
 * <p>
 * This is done through the {@link com.wandrell.pattern.outputter.Outputter
 * Outputter} interface, which offers methods to send the data through an
 * {@code OutputStream} or an {@code OutputWriter}.
 * <p>
 * Basic implementations of this interface, based on third party libraries, are
 * offered. These are meant as a way to easily handle file formats, reducing the
 * configuration needs to a minimum, and hiding the actual API being used.
 * <p>
 * The outputters are stored in packages named after the format they can send.
 * The {@code xml} package contains outputters for XML data, while the
 * {@code yaml} contains them for YAML data.
 */
package com.wandrell.pattern.outputter;

