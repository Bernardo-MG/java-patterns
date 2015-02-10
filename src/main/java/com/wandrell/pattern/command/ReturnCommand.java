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
 * Interface for implementing the command pattern. In this case this is a
 * command which returns a value.
 * <p>
 * For commands which do not return values use the {@link Command}.
 * <p>
 * Commands are meant to encapsulate code, which will then be executed through a
 * {@link CommandExecutor}. The executor is also expected to handle any
 * exception thrown by the command.
 * 
 * @author Bernardo Martínez Garrido
 * @version 0.1.0
 * @see CommandExecutor
 * @param <V>
 *            the type being returned
 */
public interface ReturnCommand<V> {

    /**
     * Executes the command and returns a value.
     * <p>
     * Any exception caused during the execution is expected to be let spread,
     * and not be caught inside the command.
     * <p>
     * The value to be returned is expected to be tightly related to the
     * command's job.
     * 
     * @return a value generated during the execution
     * @throws Exception
     *             when any exception is thrown during the execution
     */
    public V execute() throws Exception;

}
