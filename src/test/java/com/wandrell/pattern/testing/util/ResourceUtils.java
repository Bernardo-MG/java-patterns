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

package com.wandrell.pattern.testing.util;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

/**
 * Utilities class for acquiring project resources.
 * <p>
 * It serves to acquire project resources as an {@code URL} or
 * {@code InputStream}.
 * <p>
 * For this the methods make use of the class loader, so in the end they are
 * hiding a call to
 * {@code ResourceUtils.class.getClassLoader().getResource(path)}.
 * 
 * @author Bernardo Martínez Garrido
 */
public final class ResourceUtils {

    /**
     * Creates an {@code InputStream} pointing to the file specified by the
     * path, if it exists.
     * <p>
     * If any problem occurs during this process a {@code RuntimeException} is
     * thrown, or an {@code IllegalArgumentException} if the file is not found.
     * 
     * @param path
     *            the path to transform
     * @return an {@code InputStream} pointing to the path
     */
    public static final InputStream getClassPathInputStream(final String path) {
        final URL url;                  // URL parsed from the path
        final InputStream stream;       // Stream for the file

        url = getClassPathURL(path);

        checkArgument(url != null,
                String.format("The path %s is invalid", path));

        try {
            stream = new BufferedInputStream(url.openStream());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        return stream;
    }

    /**
     * Creates a {@code Reader} pointing to the file specified by the path, if
     * it exists.
     * <p>
     * If any problem occurs during this process a {@code RuntimeException} is
     * thrown, or an {@code IllegalArgumentException} if the file is not found.
     * 
     * @param path
     *            the path to transform
     * @return an {@code InputStream} pointing to the path
     */
    public static final Reader getClassPathReader(final String path) {
        return new BufferedReader(
                new InputStreamReader(getClassPathInputStream(path)));
    }

    /**
     * Creates an {@code URL} pointing to the file specified by the path, if it
     * exists.
     * 
     * @param path
     *            the path to transform
     * @return an URL pointing inside the class path
     */
    private static final URL getClassPathURL(final String path) {
        checkNotNull(path, "Received a null pointer as path");

        return ResourceUtils.class.getClassLoader().getResource(path);
    }

    /**
     * Private constructor to avoid instantiation.
     * <p>
     * No initialization is allowed, so only a private constructor exists.
     */
    private ResourceUtils() {
        super();
    }

}
