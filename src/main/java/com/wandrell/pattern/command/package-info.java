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
/**
 * Provides interfaces and basic implementations for the command and executor
 * pattern.
 * <p>
 * This pattern consists of two type of objects: the commands, which encapsulate
 * pieces of code; and the executor, which runs these commands calling an
 * execution method ({@code execute()} in the case of the provided interfaces),
 * but also takes care of any additional operation their execution may require,
 * such as catching exceptions, injecting dependencies or setting up their
 * environment.
 * <p>
 * Two kinds of command interfaces are provided: the
 * {@link com.wandrell.pattern.command.Command}, with a {@code void}
 * {@link com.wandrell.pattern.command.Command#execute() execute()} method, and the
 * {@link com.wandrell.pattern.command.ReturnCommand}, which returns a value when
 * it's {@link com.wandrell.pattern.command.ReturnCommand#execute() execute()}
 * method is called.
 * <p>
 * The executor interface is the
 * {@link com.wandrell.pattern.command.CommandExecutor}, making use of
 * {@code execute} methods to run any of the two mentioned commands: one void
 * {@link com.wandrell.pattern.command.CommandExecutor#execute(Command)
 * execute(Command)} for executing a {@code Command}, and one
 * {@link com.wandrell.pattern.command.CommandExecutor#execute(ReturnCommand)
 * execute(ReturnCommand)} for executing a {@code ReturnCommand} and return the
 * same value this command returned.
 * <p>
 * No default general-use implementation of the command interfaces is provided,
 * as they are expected to be implemented in a case-by-case basis. But there are
 * a few commands, used for specific jobs, in their own sub-packages which may
 * serve as examples.
 * <p>
 * For the executor, on the other hand, there is a basic implementation, the
 * {@link com.wandrell.pattern.command.DefaultCommandExecutor}, implementing the
 * execution methods in the most basic way.
 */
package com.wandrell.pattern.command;

