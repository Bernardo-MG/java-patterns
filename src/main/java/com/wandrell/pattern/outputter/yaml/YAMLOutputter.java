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

package com.wandrell.pattern.outputter.yaml;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.outputter.Outputter;

/**
 * Implementation of {@link Outputter} for creating YAML files. Behind the
 * scenes this is based on the SnakeYAML library.
 * <p>
 * The data is just receives as an object, which will be automatically
 * transformed into the resulting data.
 * <p>
 * The easiest way to use this class is sending a {@code Map<String, Object>},
 * where the values may be other maps or collections, as that structure adapts
 * easily into a YAML tree and is easy to understand.
 * 
 * @author Bernardo Martínez Garrido
 */
public final class YAMLOutputter implements Outputter<Object> {

    /**
     * Encoding for the output stream.
     * <p>
     * If another encoding is required a {@code Writer} should be used.
     */
    private static final String ENCODING = "UTF-8";
    /**
     * Builder for the YAML file contents.
     */
    private final Yaml          yaml;

    {
        // The YAML builder is initialized in this block.
        final DumperOptions options; // Options for the YAML file

        // An indented style is used for the output file
        options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        yaml = new Yaml(options);
    }

    /**
     * Constructs a {@code YAMLOutputter}.
     */
    public YAMLOutputter() {
        super();
    }

    /**
     * Sends an object through an {@code OutputStream}.
     * <p>
     * The object will be transformed into a YAML text file.
     * <p>
     * This accepts only streams using the UTF-8 format. If you need any other
     * format use a formatted {@code Writer}.
     * 
     * @param value
     *            object to send
     * @param stream
     *            {@code OutputStream} to receive the resulting YAML text
     * @throws UnsupportedEncodingException
     *             if the stream is not in UTF-8 format
     */
    @Override
    public final void output(final Object value, final OutputStream stream)
            throws UnsupportedEncodingException {
        output(value, new BufferedWriter(
                new OutputStreamWriter(stream, getEncoding())));
    }

    /**
     * Sends an object through a {@code OutputStream}.
     * <p>
     * The object will be transformed into a YAML text file.
     * 
     * @param value
     *            object to send
     * @param writer
     *            {@code Writer} to receive the resulting YAML text
     */
    @Override
    public final void output(final Object value, final Writer writer) {
        getYaml().dump(value, writer);
    }

    /**
     * Returns the code for the charset used for the output stream.
     * 
     * @return the text format used on the files
     */
    private final String getEncoding() {
        return ENCODING;
    }

    /**
     * Returns the builder being used to generate the YAML file.
     * 
     * @return the YAML text builder
     */
    private final Yaml getYaml() {
        return yaml;
    }

}
