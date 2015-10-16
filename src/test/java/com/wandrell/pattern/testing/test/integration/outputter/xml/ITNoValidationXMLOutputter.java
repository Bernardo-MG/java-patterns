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
package com.wandrell.pattern.testing.test.integration.outputter.xml;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.custommonkey.xmlunit.XMLUnit;
import org.jdom2.Document;
import org.jdom2.Element;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.outputter.Outputter;
import com.wandrell.pattern.outputter.xml.XMLOutputter;
import com.wandrell.pattern.testing.util.ResourceUtils;
import com.wandrell.pattern.testing.util.conf.XMLConf;

/**
 * Integration tests for {@link XMLOutputter}, checking that XML files with no
 * validation are created.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>When creating a XML file with an {@code OutputStream} or {@code Writer}
 * it has the correct structure.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see XMLOutputter
 */
public final class ITNoValidationXMLOutputter {

    /**
     * Random number generator.
     * <p>
     * Used to avoid name collisions when creating test files.
     */
    private static final Random random        = new Random();
    /**
     * Template path for the tests.
     */
    private static final String TEMPLATE_PATH = "target/test_write_Stream_";
    /**
     * Outputter being tested.
     */
    private Outputter<Document> outputter;
    /**
     * Value to send.
     */
    private Document            value;

    /**
     * Default constructor.
     */
    public ITNoValidationXMLOutputter() {
        super();
    }

    /**
     * Tests that when creating a XML file with an {@code OutputStream} it has
     * the correct structure.
     * 
     * @throws Exception
     *             never, this is a required declaration
     */
    @Test
    public final void testWrite_OutputStream_EqualsExpected() throws Exception {
        final Path path;           // Path to the output file
        final OutputStream stream; // Stream to the output file

        path = Paths.get(TEMPLATE_PATH + getRandomID() + ".xml")
                .toAbsolutePath();

        stream = new BufferedOutputStream(new FileOutputStream(path.toFile()));

        outputter.output(value, stream);

        assertEquals(path);
    }

    /**
     * Tests that when creating a XML file with a {@code Writer} it has the
     * correct structure.
     * 
     * @throws Exception
     *             never, this is a required declaration
     */
    @Test
    public final void testWrite_OutputWriter_EqualsExpected() throws Exception {
        final Path path;     // Path to the output file
        final Writer writer; // Stream to the output file

        path = Paths.get(TEMPLATE_PATH + getRandomID() + ".xml")
                .toAbsolutePath();

        writer = new BufferedWriter(new FileWriter(path.toFile()));
        outputter.output(value, writer);

        assertEquals(path);
    }

    /**
     * Asserts that the generated file is equal to the expected file.
     * 
     * @param path
     *            path to the file to check
     * @throws Exception
     *             never, this is a required declaration
     */
    private final void assertEquals(final Path path) throws Exception {
        final InputStream streamTest; // Stream to the created file
        final Reader readerExpected;  // Reader to the expected file

        streamTest = new FileInputStream(path.toFile());
        readerExpected = ResourceUtils
                .getClassPathReader(XMLConf.INTEGER_EXPECTED);

        Assert.assertTrue(XMLUnit
                .compareXML(readerExpected, new InputStreamReader(streamTest))
                .identical());
    }

    /**
     * Generates a random ID for the test files.
     * 
     * @return a random ID for the test files
     */
    private final Integer getRandomID() {
        return random.nextInt(Integer.MAX_VALUE);
    }

    /**
     * Creates the outputter and value being tested before any test is run.
     */
    @BeforeClass
    private final void initialize() {
        value = new Document();
        value.addContent(new Element(XMLConf.NODE_ROOT));
        value.getRootElement()
                .addContent(new Element(XMLConf.NODE_VALUE).addContent("1"));

        outputter = new XMLOutputter();
    }

}
