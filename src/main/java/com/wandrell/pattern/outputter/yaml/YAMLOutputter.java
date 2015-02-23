/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015 the original author or authors.
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
import java.io.Writer;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.outputter.Outputter;

/**
 * Implementation of {@code Outputter} for creating YAML files.
 * <p>
 * For this an {@code Object is received and then sent
 * through an IO operation.
 * <p>
 * The SnakeYAML library is being used for this job.
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 */
public final class YAMLOutputter implements Outputter<Object> {

    /**
     * YAML file contents builder.
     */
    private final Yaml yaml;

    {
        // The YAML builder is initialized.
        final DumperOptions options; // Options for the YAML file

        // An indented style is used for the output file
        options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        yaml = new Yaml(options);
    }

    /**
     * Constructs a YAML outputter.
     */
    public YAMLOutputter() {
        super();
    }

    /**
     * Sends an object through an {@code OutputStream}.
     * <p>
     * The object will be transformed into an YAML text file.
     * 
     * @param value
     *            object to send
     * @param stream
     *            {@code OutputStream} to receive the resulting YAML text
     * @throws Exception
     *             when there's any problem on the outputting process
     */
    @Override
    public final void send(final Object value, final OutputStream stream) {
        send(value, new BufferedWriter(new OutputStreamWriter(stream)));
    }

    /**
     * Sends an object through an {@code OutputStream}.
     * <p>
     * The object will be transformed into an YAML text file.
     * 
     * @param value
     *            object to send
     * @param writer
     *            {@code Writer} to receive the resulting YAML text
     * @throws Exception
     *             when there's any problem on the writing process
     */
    @Override
    public final void send(final Object value, final Writer writer) {
        getYaml().dump(value, writer);
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
