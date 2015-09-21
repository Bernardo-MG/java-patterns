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
package com.wandrell.pattern.parser;

/**
 * Interface for the parser pattern.
 * <p>
 * This pattern allows to transform a structure received as input to another
 * one, both containing the same or similar data. If no data is lost during this
 * process, then it is said to be a lossless parsing, otherwise it is a lossfull
 * one, but that is an implementation detail, not handled by the interface.
 * <p>
 * Usually this interface is used as part of a file reading process, where a
 * {@code Parser} which receives a {@code Reader} for the file and returns a
 * model object. But it can be adapted to any job where it is required to
 * transform data.
 * <p>
 * While this is perfectly good use, it is preferable to use parsers for the
 * smallest possible job and then chain their results. In the previous example
 * reading the file and parsing its contents are two different sections on the
 * parsing process.
 * <p>
 * So it is possible to create a parser which receives the {@code Reader} and
 * returns a {@code Map} with the contents, and then a second which receives
 * this {@code Map} and returns a model object. The value returned from the
 * first is given to the second, which then creates the final one.
 * <p>
 * This way the parsers become easier to maintain, and if possible also
 * reusable.
 * 
 * @author Bernardo Martínez Garrido
 * @param <I>
 *            the input type to be parsed
 * @param <O>
 *            the output type parsed from the input
 */
public interface Parser<I, O> {

    /**
     * Parses the input into an instance of the output type.
     * <p>
     * During the parsing process it is possible to find errors which stop the
     * parsing process completely. In those case an {@code Exception} is thrown.
     * 
     * @param input
     *            object to parse
     * @return the input parsed into a new object
     */
    public O parse(final I input);

}
