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
package com.wandrell.pattern.testing.util.conf;

/**
 * Configuration class storing info for XML tests.
 * 
 * @author Bernardo Martínez Garrido
 */
public final class XMLConf {

    public static final String ATTRIBUTE_ATTRIBUTE1                  = "attribute1";
    public static final String ATTRIBUTE_ATTRIBUTE2                  = "attribute2";
    public static final String ATTRIBUTE_NOT_EXISTING                = "abc12345";
    public static final String DTD_VALIDATION                        = "files/xml/validation/dtd_validation.dtd";
    public static final String EMPTY                                 = "files/xml/empty.xml";
    public static final String FILTERED_FULL                         = "files/xml/filtered/filtered_validation_full.xml";
    public static final String FILTERED_NO_VALIDATION                = "files/xml/filtered/filtered_no_validation.xml";
    public static final String FILTERED_NO_VALIDATION_FULL           = "files/xml/filtered/filtered_no_validation_full.xml";
    public static final String FILTERED_VALIDATION_DTD               = "files/xml/filtered/filtered_validation.dtd";
    public static final String FILTERED_VALIDATION_XSD               = "files/xml/filtered/filtered_validation.xsd";
    public static final String FILTERED_WITH_VALIDATION_DTD          = "files/xml/filtered/filtered_validation_a_dtd.xml";
    public static final String FILTERED_WITH_VALIDATION_XSD          = "files/xml/filtered/filtered_validation_a.xml";
    public static final String FILTERED_WITH_VALIDATION_XSD_DEFAULTS = "files/xml/filtered/filtered_validation_b.xml";
    public static final String INTEGER_EXPECTED                      = "files/xml/integer_expected.xml";
    public static final String INTEGER_EXPECTED_DTD                  = "files/xml/integer_expected_dtd.xml";
    public static final String INTEGER_EXPECTED_XSD                  = "files/xml/integer_expected_xsd.xml";
    public static final String INTEGER_NO_VALIDATES                  = "files/xml/validation/xsd_validation_no_validates.xml";
    public static final String INTEGER_READ                          = "files/xml/integer_read.xml";
    public static final String NODE_NODE                             = "node";
    public static final String NODE_ROOT                             = "root";
    public static final String NODE_ROOT_FILTER                      = "node";
    public static final String NODE_VALUE                            = "value";
    public static final String VALIDATED_DTD                         = "files/xml/validation/dtd_validation.xml";
    public static final String VALIDATED_XSD                         = "files/xml/validation/xsd_validation.xml";
    public static final String XSD_VALIDATION                        = "files/xml/validation/xsd_validation.xsd";

    /**
     * Private constructor.
     */
    private XMLConf() {
        super();
    }

}
