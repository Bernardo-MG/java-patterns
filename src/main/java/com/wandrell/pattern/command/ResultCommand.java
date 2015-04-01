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
package com.wandrell.pattern.command;

/**
 * Extension of {@link Command} which generates a value during the execution.
 * <p>
 * It works the same way as the basic {@code Command}, just that the
 * {@link #getResult() getResult} method allows retrieving a value generated
 * when calling the {@link #execute() execute} method.
 * <p>
 * Note that if an exception occurs during the command's execution then the
 * {@code getResult} value may be invalid.
 * 
 * @author Bernardo Martínez Garrido
 * @param <V>
 *            the type being generated during the execution
 */
public interface ResultCommand<V> extends Command {

    /**
     * Returns the value generated during the execution. This value should be
     * closely related to the command's job.
     * <p>
     * For example, a division would return just the divided value, not an
     * status value indicating if it was a valid division or not.
     * <p>
     * If any error has occurred during the execution, then the value returned
     * may be invalid.
     * 
     * @return a value generated during the execution
     */
    public V getResult();

}
