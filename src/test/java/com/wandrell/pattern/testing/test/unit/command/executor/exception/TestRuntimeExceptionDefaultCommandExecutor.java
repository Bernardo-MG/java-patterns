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

package com.wandrell.pattern.testing.test.unit.command.executor.exception;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.command.Command;
import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.pattern.command.DefaultCommandExecutor;
import com.wandrell.pattern.command.ResultCommand;
import com.wandrell.pattern.command.UndoableCommand;

/**
 * Unit tests for {@link DefaultCommandExecutor}, checking that exceptions
 * thrown by commands are thrown again by the executor as a
 * {@code RuntimeException}.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>When a {@code Command} throws an {@code Exception} during it's execution
 * it is wrapped into a {@code RuntimeException}.</li>
 * <li>When a {@code Command} throws an {@code RuntimeException} during it's
 * execution it is thrown again.</li>
 * <li>When a {@code ResultCommand} throws an {@code Exception} during it's
 * execution it is wrapped into a {@code RuntimeException}.</li>
 * <li>When a {@code ResultCommand} throws an {@code RuntimeException} during
 * it's execution it is thrown again.</li>
 * <li>When a {@code ResultCommand} throws an {@code Exception} during it's
 * undoing it is wrapped into a {@code RuntimeException}.</li>
 * <li>When a {@code ResultCommand} throws an {@code RuntimeException} during
 * it's undoing it is thrown again.</li>
 * </ol>
 * <p>
 * Mocked {@code Command} and {@code ResultCommand} instances are used for the
 * tests.
 * 
 * @author Bernardo Martínez Garrido
 * @see DefaultCommandExecutor
 */
public final class TestRuntimeExceptionDefaultCommandExecutor {

    /**
     * Executor being tested.
     * <p>
     * It is created once for all the tests.
     */
    private CommandExecutor executor;

    /**
     * Default constructor.
     */
    public TestRuntimeExceptionDefaultCommandExecutor() {
        super();
    }

    /**
     * Creates the executor being tested before any test is run.
     */
    @BeforeClass
    public final void initialize() {
        executor = new DefaultCommandExecutor();
    }

    /**
     * Tests that when a {@code Command} throws an {@code Exception} during it's
     * execution it is wrapped into a {@code RuntimeException}.
     * 
     * @throws Exception
     *             always as part of the test
     */
    @Test(expectedExceptions = RuntimeException.class)
    public final void
            testExecute_Command_ExceptionThrown_ThrowsRuntimeException()
                    throws Exception {
        final Command command;

        command = Mockito.mock(Command.class);

        try {
            Mockito.doThrow(Exception.class).when(command).execute();
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }

        executor.execute(command);
    }

    /**
     * Tests that when a {@code Command} throws an {@code RuntimeException}
     * during it's execution it is thrown again.
     * 
     * @throws Exception
     *             always as part of the test
     */
    @Test(expectedExceptions = RuntimeException.class)
    public final void
            testExecute_Command_RuntimeExceptionThrown_ThrowsRuntimeException()
                    throws Exception {
        final Command command;

        command = Mockito.mock(Command.class);

        try {
            Mockito.doThrow(RuntimeException.class).when(command).execute();
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }

        executor.execute(command);
    }

    /**
     * Tests that when a {@code ResultCommand} throws an {@code Exception}
     * during it's execution it is wrapped into a {@code RuntimeException}.
     * 
     * @throws Exception
     *             always as part of the test
     */
    @Test(expectedExceptions = RuntimeException.class)
    public final void
            testExecute_ResultCommand_ExceptionThrown_ThrowsRuntimeException()
                    throws Exception {
        final ResultCommand<?> command;

        command = Mockito.mock(ResultCommand.class);

        try {
            Mockito.doThrow(Exception.class).when(command).execute();
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }

        executor.execute(command);
    }

    /**
     * Tests that when a {@code ResultCommand} throws an
     * {@code RuntimeException} during it's execution it is thrown again.
     * 
     * @throws Exception
     *             always as part of the test
     */
    @Test(expectedExceptions = RuntimeException.class)
    public final void
            testExecute_ResultCommand_RuntimeExceptionThrown_ThrowsRuntimeException()
                    throws Exception {
        final ResultCommand<?> command;

        command = Mockito.mock(ResultCommand.class);

        try {
            Mockito.doThrow(RuntimeException.class).when(command).execute();
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }

        executor.execute(command);
    }

    /**
     * Tests that when a {@code ResultCommand} throws an {@code Exception}
     * during it's undoing it is wrapped into a {@code RuntimeException}.
     * 
     * @throws Exception
     *             always as part of the test
     */
    @Test(expectedExceptions = RuntimeException.class)
    public final void testUndo_Command_ExceptionThrown_ThrowsRuntimeException()
            throws Exception {
        final UndoableCommand command;

        command = Mockito.mock(UndoableCommand.class);

        try {
            Mockito.doThrow(Exception.class).when(command).undo();
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }

        executor.undo(command);
    }

    /**
     * Tests that when a {@code ResultCommand} throws a {@code RuntimeException}
     * during it's undoing it is thrown again.
     * 
     * @throws Exception
     *             always as part of the test
     */
    @Test(expectedExceptions = RuntimeException.class)
    public final void
            testUndo_Command_RuntimeExceptionThrown_ThrowsRuntimeException()
                    throws Exception {
        final UndoableCommand command;

        command = Mockito.mock(UndoableCommand.class);

        try {
            Mockito.doThrow(RuntimeException.class).when(command).undo();
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }

        executor.undo(command);
    }

}
