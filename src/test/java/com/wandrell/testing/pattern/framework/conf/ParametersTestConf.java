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
package com.wandrell.testing.pattern.framework.conf;

/**
 * Configuration class storing the paths for test parameters generation files.
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 */
public final class ParametersTestConf {

    /**
     * Properties with an invalid decoder.
     */
    public static final String PROPERTIES_BAD_DECODER   = "files/parameters/test_parameters_bad_decoder.properties";
    /**
     * Properties with an invalid parameters file.
     */
    public static final String PROPERTIES_BAD_PATH_FILE = "files/parameters/test_parameters_bad_path_file.properties";
    /**
     * Basic working properties.
     */
    public static final String PROPERTIES_DEFAULT       = "files/parameters/test_parameters_default.properties";

    /**
     * Private constructor.
     */
    private ParametersTestConf() {
        super();
    }

}
