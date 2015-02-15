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
package com.wandrell.pattern.service.application;

import static com.google.common.base.Preconditions.checkNotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Properties based implementation of {@link ApplicationInfoService}. All the
 * info to be returned by this class is stored on a properties file.
 * <p>
 * The data of the properties file is received as a {@code Properties} object by
 * the constructor, and is expected to not change during the execution of the
 * application.
 * <p>
 * This data will be read from specific keys, which should be stored, along a
 * valid value, on the {@code Properties}.
 * <p>
 * These keys and their expected values are as follow:
 * <table summary="Application info key-value pairs" border=1>
 * <tr>
 * <th>Key</th>
 * <th>Value</th>
 * </tr>
 * <tr>
 * <td>url.download</td>
 * <td>Application's download URL</td>
 * </tr>
 * <tr>
 * <td>author.name</td>
 * <td>Author's name</td>
 * </tr>
 * <tr>
 * <td>author.email</td>
 * <td>Author's email</td>
 * </tr>
 * <tr>
 * <td>application.title</td>
 * <td>Application's title</td>
 * </tr>
 * <tr>
 * <td>application.version</td>
 * <td>Application's version</td>
 * </tr>
 * </table>
 * <p>
 * All these pairs are required to be on the received {@code Properties}
 * instance for the service to work correctly.
 * <p>
 * To ease the use of this class, a template properties file can be found at the
 * path {@code src/files/application-info.properties} on this library.
 * <p>
 * It should be noted that while all these values are stored as strings, the SCM
 * URL string will be transformed into an URI, and so it should be parseable
 * into one.
 * <p>
 * Also, no kind of localization framework is included in this service. If, for
 * example, the title needs to be localized, then it should be so in the
 * received {@code Properties}, or the {@code getApplicationName()} should
 * return a key for the localization framework.
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 */
public final class PropertiesApplicationInfoService implements
        ApplicationInfoService {

    /**
     * Key for the application's author name.
     */
    public static final String KEY_AUTHOR;
    /**
     * Key for the application's author email.
     */
    public static final String KEY_AUTHOR_EMAIL;
    /**
     * Key for the application's SCM URL.
     * <p>
     * While this key's value is a string, it should be noted that it will be
     * converted into an URI, so it should be parseable into one.
     */
    public static final String KEY_DOWNLOAD_URL;
    /**
     * Key for the application's title.
     */
    public static final String KEY_TITLE;
    /**
     * Key for the application's version.
     */
    public static final String KEY_VERSION;
    /**
     * {@code Properties} containing the application's values.
     */
    private final Properties   appProp;

    static {
        // The properties keys are set here
        KEY_DOWNLOAD_URL = "url.download";
        KEY_AUTHOR = "author.name";
        KEY_AUTHOR_EMAIL = "author.email";
        KEY_TITLE = "application.title";
        KEY_VERSION = "application.version";
    }

    /**
     * Constructs an application info service with the specified executor and
     * properties table.
     * <p>
     * The executor will be used with the commands which handle the operations
     * internally, and has no special requirement.
     * <p>
     * The properties table should contain the key-value pairs specified on this
     * class' javadoc.
     * <p>
     * A template properties file with all the required keys can be found at the
     * path {@code src/files/application-info.properties} on this library
     * 
     * @param executor
     *            the {@code CommandExecutor} to internally handle the commands
     * @param properties
     *            the {@code Properties} with the application's info
     */
    public PropertiesApplicationInfoService(final Properties properties) {
        super();

        checkNotNull(properties, "Received a null pointer as properties");

        this.appProp = properties;
    }

    @Override
    public final String getApplicationName() {
        return getProperties().getProperty(KEY_TITLE);
    }

    @Override
    public final String getAuthor() {
        return getProperties().getProperty(KEY_AUTHOR);
    }

    @Override
    public final String getAuthorEmail() {
        return getProperties().getProperty(KEY_AUTHOR_EMAIL);
    }

    @Override
    public final URI getDownloadURI() {
        final String uri;       // String to generate the URI
        final URI result;       // Generated URI

        uri = getProperties().getProperty(KEY_DOWNLOAD_URL);

        // If the URI string is not null, it tries to build a URI instance
        if (uri == null) {
            result = null;
        } else {
            try {
                result = new URI(uri);
            } catch (final URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public final String getVersion() {
        return getProperties().getProperty(KEY_VERSION);
    }

    /**
     * Returns the properties table containing the application's info.
     * <p>
     * It is expected to contain all the key-value pairs specified on this
     * class' javadoc.
     * 
     * @return the {@code Properties} containing the application's info
     */
    private final Properties getProperties() {
        return appProp;
    }

}
