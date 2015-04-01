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
 * Provides interfaces and basic implementations for the command and command
 * executor patterns.
 * <p>
 * The command is a behavioral pattern which encapsulates a series of
 * instructions inside an object, so they can be executed when required, not
 * needing to know the actual code being run.
 * <p>
 * In practice, this is like a macro which can be sent through a network, stored
 * or chained to other commands to be executed in order, or even backwards to
 * undo them.
 * <p>
 * They may be executed manually, but to increase decoupling a command executor
 * can be used. It additionally can handle any other required operation, such as
 * setting up the environment or catching exceptions.
 * <h2>Command</h2>
 * <p>
 * The basic command takes the shape of the
 * {@link com.wandrell.pattern.command.Command Command}. This does nothing
 * expect being executed.
 * <p>
 * The {@link com.wandrell.pattern.command.ResultCommand ResultCommand} creates
 * an object during it's execution, which may be acquired through the
 * {@link com.wandrell.pattern.command.ResultCommand#getResult() getResult}
 * method.
 * <p>
 * When it is needed to undo the command
 * {@link com.wandrell.pattern.command.UndoableCommand UndoableCommand} should
 * be used, and the {@link com.wandrell.pattern.command.UndoableCommand#undo()
 * undo} method implemented.
 * <h2>Command Executor</h2>
 * <p>
 * Command executors are implemented from the
 * {@link com.wandrell.pattern.command.CommandExecutor CommandExecutor}
 * interface.
 * <p>
 * This offers methods for executing and undoing commands, and in the case a
 * {@code ResultCommand} is executed this will return the value generated from
 * said command.
 * <h2>Implementations</h2>
 * <p>
 * Only the {@code CommandExecutor} has a basic implementation, the
 * {@link com.wandrell.pattern.command.DefaultCommandExecutor
 * DefaultCommandExecutor}, which does little more than receiving a
 * {@code Command} and calling its methods.
 * <p>
 * Commands are meant to be created on a case-by-case basis, to fit concrete
 * needs, so no default implementation is offered for them.
 */
package com.wandrell.pattern.command;

