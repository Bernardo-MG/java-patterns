/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015 the original author or authors.
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
 * Extension of {@link Command} which allows reversing the process done during
 * the execution.
 * <p>
 * This is done calling the {@link #undo() undo} method after the
 * {@link #execute() execute} method call. Note that if any exception is caused
 * by the undoing it is expected to be let spread out of the method, and not be
 * caught inside.
 * <p>
 * Note that if the command's execution has caused an exception, then it may not
 * be possible to undo it.
 * <p>
 * Also, if the command has not been executed, then undoing it is expected to do
 * nothing.
 * <p>
 * A simple example of this procedure would be a command which during it's
 * execution adds a 1 to a number it stores. Undoing the command would subtract
 * 1 from said stored number.
 * 
 * @author Bernardo Martínez Garrido
 */
public interface UndoableCommand extends Command {

    /**
     * Undoes the work done during the execution.
     * <p>
     * If the {@code Command} has not been executed when this method is called
     * then this call is expected to do nothing at all.
     * <p>
     * If any error has occurred during the execution, then it may not be
     * possible to undo the command's execution.
     * <p>
     * Also, if the command has not been executed undoing it is expected to do
     * nothing.
     * 
     * @throws Exception
     *             when any exception is thrown during the undoing
     */
    public void undo() throws Exception;

}
