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
package com.wandrell.testing.pattern.test.unit.service.application;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.service.application.ApplicationInfoService;
import com.wandrell.pattern.service.application.PropertiesApplicationInfoService;

/**
 * Unit tests for {@link PropertiesApplicationInfoService}.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Acquiring the application name returns the expected value.</li>
 * <li>Acquiring the application version returns the expected value.</li>
 * <li>Acquiring the author name returns the expected value.</li>
 * <li>Acquiring the author email returns the expected value.</li>
 * <li>Acquiring the download URI returns the expected value.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @see PropertiesApplicationInfoService
 */
public final class UnitPropertiesFileApplicationInfoService {

    /**
     * Expected author result.
     */
    private static final String    AUTHOR  = "wandrell";
    /**
     * Expected author email result.
     */
    private static final String    EMAIL   = "wandrell@wandrell.com";
    /**
     * Expected application name result.
     */
    private static final String    TITLE   = "The application";
    /**
     * Expected URL for the URI result.
     */
    private static final String    URL     = "www.wandrell.com";
    /**
     * Expected version result.
     */
    private static final String    VERSION = "1.2.3";
    /**
     * Service being tested.
     */
    private ApplicationInfoService service;

    /**
     * Default constructor.
     */
    public UnitPropertiesFileApplicationInfoService() {
        super();
    }

    @BeforeClass
    public final void initialize() {
        final Properties properties;

        properties = new Properties();
        properties.put(PropertiesApplicationInfoService.KEY_DOWNLOAD_URL, URL);
        properties.put(PropertiesApplicationInfoService.KEY_AUTHOR, AUTHOR);
        properties
                .put(PropertiesApplicationInfoService.KEY_AUTHOR_EMAIL, EMAIL);
        properties.put(PropertiesApplicationInfoService.KEY_TITLE, TITLE);
        properties.put(PropertiesApplicationInfoService.KEY_VERSION, VERSION);

        service = new PropertiesApplicationInfoService(properties);
    }

    /**
     * Tests that acquiring the application name returns the expected value.
     */
    @Test
    public final void testGetApplicationName_ReturnsExpected() {
        Assert.assertEquals(service.getApplicationName(), TITLE);
    }

    /**
     * Tests that acquiring the author name returns the expected value.
     */
    @Test
    public final void testGetAuthor_ReturnsExpected() {
        Assert.assertEquals(service.getAuthor(), AUTHOR);
    }

    /**
     * Tests that acquiring the author email returns the expected value.
     */
    @Test
    public final void testGetAuthorEmail_ReturnsExpected() {
        Assert.assertEquals(service.getAuthorEmail(), EMAIL);
    }

    /**
     * Tests that acquiring the download URI returns the expected value.
     */
    @Test
    public final void testGetDownloadURI_ReturnsExpected() {
        Assert.assertEquals(service.getDownloadURI().toString(), URL);
    }

    /**
     * Tests that acquiring the application version returns the expected value.
     */
    @Test
    public final void testGetVersion_ReturnsExpected() {
        Assert.assertEquals(service.getVersion(), VERSION);
    }

}
