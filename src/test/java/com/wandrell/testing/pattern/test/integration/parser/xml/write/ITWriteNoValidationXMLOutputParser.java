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
package com.wandrell.testing.pattern.test.integration.parser.xml.write;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

import com.wandrell.pattern.ResourceUtils;
import com.wandrell.pattern.parser.OutputParser;
import com.wandrell.pattern.parser.xml.output.XMLOutputParser;
import com.wandrell.testing.pattern.framework.conf.XMLConf;

/**
 * Integration tests for {@link XMLOutputParser}, checking XML files with no
 * validation are created.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>When creating a XML file with an {@code OutputStream} or {@code Writer}
 * it has the correct structure.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @see XMLOutputParser
 */
public final class ITWriteNoValidationXMLOutputParser {

    /**
     * Random number generator.
     * <p>
     * Used to avoid name collisions when creating test files.
     */
    private static final Random    random        = new Random();
    /**
     * Template path for the tests.
     */
    private static final String    TEMPLATE_PATH = "target/test_write_Stream_";
    /**
     * Parser being tested.
     */
    private OutputParser<Document> parser;
    /**
     * Value to parse.
     */
    private Document               value;

    /**
     * Default constructor.
     */
    public ITWriteNoValidationXMLOutputParser() {
        super();
    }

    /**
     * Tests that when creating a XML file with an {@code OutputStream} it has
     * the correct structure.
     */
    @Test
    public final void testWrite_OutputStream_EqualsExpected() {
        final Path path;
        final OutputStream stream;

        path = Paths.get(TEMPLATE_PATH + getRandomID() + ".xml")
                .toAbsolutePath();

        try {
            stream = new BufferedOutputStream(new FileOutputStream(
                    path.toFile()));

            parser.write(stream, value);
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }

        assertEquals(path);
    }

    /**
     * Tests that when creating a XML file with a {@code Writer} it has the
     * correct structure.
     */
    @Test
    public final void testWrite_OutputWriter_EqualsExpected() {
        final Path path;
        final Writer writer;

        path = Paths.get(TEMPLATE_PATH + getRandomID() + ".xml")
                .toAbsolutePath();

        try {
            writer = new BufferedWriter(new FileWriter(path.toFile()));
            parser.write(writer, value);
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }

        assertEquals(path);
    }

    /**
     * Asserts that the generated file is equal to the expected file.
     * 
     * @param path
     *            path to the file to check
     */
    private final void assertEquals(final Path path) {
        final InputStream streamTest;
        final InputStream streamExpected;

        try {
            streamTest = new FileInputStream(path.toFile());
            streamExpected = ResourceUtils
                    .getClassPathInputStream(XMLConf.INTEGER_EXPECTED);

            Assert.assertTrue(XMLUnit.compareXML(
                    new InputStreamReader(streamExpected),
                    new InputStreamReader(streamTest)).identical());
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
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
     * Creates the parser being tested before any test is run.
     */
    @BeforeClass
    private final void initialize() {
        value = new Document();
        value.addContent(new Element(XMLConf.NODE_ROOT));
        value.getRootElement().addContent(
                new Element(XMLConf.NODE_VALUE).addContent("1"));

        parser = new XMLOutputParser();
    }

}
