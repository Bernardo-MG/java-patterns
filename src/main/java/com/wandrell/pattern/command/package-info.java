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
 * The command, in the shape of the {@link com.wandrell.pattern.command.Command
 * Command} and the {@link com.wandrell.pattern.command.ResultCommand
 * ResultCommand}, encapsulates pieces of code to be executed at a later time.
 * In a way, it can be seen as a function which can be stored and transmitted
 * like any object.
 * <p>
 * It is the {@link com.wandrell.pattern.command.CommandExecutor
 * CommandExecutor}'s job to take care of running those commands. Additionally,
 * it also handles any other required operation, such as setting up the
 * environment or catching exceptions.
 * <p>
 * There is a default basic implementation of this executor interface, the
 * {@link com.wandrell.pattern.command.DefaultCommandExecutor
 * DefaultCommandExecutor}, which does little more than calling the command's
 * {@code execute} method, but there are no default implementations of the
 * command interfaces, as these are meant to be created on a case-by-case basis,
 * fitting concrete needs.
 */
package com.wandrell.pattern.command;

