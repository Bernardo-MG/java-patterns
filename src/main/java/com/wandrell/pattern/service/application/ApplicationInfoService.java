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

import java.net.URI;

/**
 * Service for acquiring an application's information. This is the kind of info
 * which would be shown on the "about" dialog.
 * <p>
 * The main use of this interface is storing that general information which may
 * be used to identify the program or the people who worked on it, but that is
 * not vital for the application's correct work.
 * <p>
 * If any of the methods is unable to return a valid value, it is expected to
 * return {@code null}, and not throw an exception or give any random value.
 * <p>
 * What is a valid value may depend between implementations and applications. It
 * is possible for the service to be localized, and would for example return the
 * title in the current language, or it may just return a key to be used by the
 * localization framework.
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 */
public interface ApplicationInfoService {

    /**
     * Returns the application's name.
     * 
     * @return the application's name
     */
    public String getApplicationName();

    /**
     * Returns the application's author.
     * <p>
     * It will only show one author, no support for a list of them right now.
     * 
     * @return the application's author
     */
    public String getAuthor();

    /**
     * Returns the application's author email.
     * <p>
     * It will only show one author's email, no support for a list of them right
     * now.
     * 
     * @return the author's email
     */
    public String getAuthorEmail();

    /**
     * Returns the application's download URI.
     * <p>
     * This can be the project's page, a repository or anything at all. It is
     * not required to be a link to a file, just the preferred download source.
     * 
     * @return the download uri
     */
    public URI getDownloadURI();

    /**
     * Returns the application's version.
     * 
     * @return the application's version
     */
    public String getVersion();

}
