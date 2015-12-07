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

package com.wandrell.pattern.command;

/**
 * Interface for the command pattern. This is a behavioral design pattern which
 * encapsulates a piece of code so it can be handled like any object, sending or
 * storing it until needed.
 * <p>
 * Such encapsulated code is executed calling the {@link #execute() execute}
 * method. Note that if any exception is caused by the execution it is expected
 * to be let spread out of the method, and not be caught inside.
 * <p>
 * If the code requires parameters for its execution then these should be set on
 * the command's constructor, or through setter methods on the concrete
 * implementation.
 * <p>
 * There are extensions of {@code Command} for more complex cases. More exactly
 * there is one for those cases where it is required to retrieve a value after
 * it's execution ({@link ResultCommand}), and when it is required to allow
 * undoing the command ({@link UndoableCommand}).
 * 
 * @author Bernardo Martínez Garrido
 * @see ResultCommand
 * @see UndoableCommand
 */
public interface Command {

    /**
     * Executes the command. This will run the code being encapsulated.
     * <p>
     * Any exception caused during the execution is expected to be let spread,
     * and not be caught inside the command.
     * 
     * @throws Exception
     *             when any exception is thrown during the execution
     */
    public void execute() throws Exception;

}
