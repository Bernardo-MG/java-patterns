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
package com.wandrell.pattern.parser.xml.input;

import java.io.InputStream;

import com.wandrell.pattern.parser.InputParser;
import com.wandrell.pattern.parser.xml.XMLValidationType;

/**
 * {@code InputParser} for parsing XML files with validation.
 * <p>
 * The validation methods accepted are indicated by the
 * {@code XMLValidationType} enumeration, and also includes not using any kind
 * of validation method.
 * 
 * @author Bernardo Martínez Garrido
 *
 * @param <V>
 *            the type to be parsed from the input
 */
public interface XMLValidatedInputParser<V> extends InputParser<V> {

    /**
     * Returns the validation type being used.
     * 
     * @return the XML validation type being used
     */
    public XMLValidationType getValidationType();

    /**
     * Sets the validation to be used, along the validation source.
     * 
     * @param type
     *            the validation type
     * @param validationStream
     *            stream for the validation file
     */
    public void setValidation(final XMLValidationType type,
            final InputStream validationStream);

}
