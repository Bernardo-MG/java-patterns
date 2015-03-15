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
 * Interface for the command pattern. This is the most basic type of command,
 * which doesn't return anything when executed.
 * <p>
 * A command is a behavioral design pattern which encapsulates a piece of code,
 * which this way can be stored and transmitted, until it is required to be
 * executed.
 * <p>
 * This execution is done through the {@link #execute() execute} method.
 * <p>
 * But this is not meant to be called manually. Instead a
 * {@link CommandExecutor} will receive the command and then call its
 * {@code execute} method. This way commands are kept isolated from the rest of
 * the code.
 * <p>
 * This type of command returns a value on execution. This should be closely
 * related to the command's job, for example, a command for reading a file will
 * return that file's contents, and not a control value indicating if the file
 * was read or not.
 * <p>
 * If there is no need to return any value on execution, then the
 * {@link Command} is preferred.
 * 
 * @author Bernardo Martínez Garrido
 * @see CommandExecutor
 * @see Command
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
     * The value to be returned is expected to be closely related to the
     * command's job, and not any kind of control value, such as an 'ok' status
     * flag.
     * 
     * @return a value generated during the execution
     * @throws Exception
     *             when any exception is thrown during the execution
     */
    public V execute() throws Exception;

}
