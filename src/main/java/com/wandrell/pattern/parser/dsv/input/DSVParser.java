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
package com.wandrell.pattern.parser.dsv.input;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedList;

import au.com.bytecode.opencsv.CSVReader;

import com.wandrell.pattern.parser.Parser;

/**
 * Implementation of {@link InputParser} for Delimiter Separated Values files.
 * <p>
 * This job is done in two steps. First a collection of string arrays is
 * generated from the file, and then passed to an instance of {@link Parser},
 * which will generate the actual return value.
 * <p>
 * Behind the scenes it uses {@link CSVReader}.
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @param <V>
 *            the type to be parsed from the input
 */
public final class DSVParser implements Parser<Reader, Collection<String[]>> {

    /**
     * Delimiter separating the values on the file.
     */
    private final Character delimiter;

    /**
     * Constructs a parser with the specified separator and parser.
     * 
     * @param separator
     *            the separator used on the DSV file
     */
    public DSVParser(final Character separator) {
        super();

        checkNotNull(separator,
                "Received a null pointer as document line separator");

        delimiter = separator;
    }

    @Override
    public final Collection<String[]> parse(final Reader input)
            throws Exception {
        final Collection<String[]> result;
        String[] line;

        result = new LinkedList<>();
        try (final CSVReader csvreader = new CSVReader(input, getDelimiter())) {
            while ((line = csvreader.readNext()) != null) {
                result.add(line);
            }
        }

        return result;
    }

    private final Character getDelimiter() {
        return delimiter;
    }

}
