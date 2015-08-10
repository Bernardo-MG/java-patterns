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
 * LIABILITY, WHETHER IN AN ACTI/ON OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTI/ON WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */
/**
 * Provides interfaces and basic implementations for the outputter pattern.
 * <p>
 * An outputter hides an output I/O operation to favor decoupling, so data can
 * be sent through an output operation without having to worry about the
 * concrete implementations or API being used.
 * <p>
 * If a data structure has to be saved into a file it can be sent to an
 * outputter, which will take care of saving it into the required format. This
 * way if this format needs to be changed, all that is needed is swapping one
 * outputter for another.
 * <h2>Interfaces</h2>
 * <p>
 * The {@link com.wandrell.pattern.outputter.Outputter Outputter} interface
 * represents this pattern.
 * <p>
 * It offers methods to send a data structure through an I/O operation, the
 * difference between these methods is that one requires an {@code OutputStream}
 * while the other uses an {@code OutputWriter}. In both cases the received
 * output object is expected to be a single use object, which will be closed,
 * and so can't be reused, after the operation is finished.
 * <h2>Implementations</h2>
 * <p>
 * Some basic implementations for this interface, based on third party
 * libraries, are offered. These are meant as a way to easily handle various
 * common file formats, as they take care of setting a common basic
 * configuration
 * <p>
 * These outputters are stored in packages named after the format they can
 * handle.
 * <p>
 * The {@link com.wandrell.pattern.outputter.xml.XMLOutputter} can save data
 * into an XML file, while the
 * {@link com.wandrell.pattern.outputter.yaml.YAMLOutputter YAMLOutputter} can
 * do the same on a YAML file.
 */
package com.wandrell.pattern.outputter;
