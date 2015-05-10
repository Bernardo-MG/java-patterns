# Command and command executor

A command is a series of instructions kept inside an object, so they can be executed when required.

This is a behavioral pattern which allows decoupling pieces of code, as for example a service may request a command to execute, not caring about the actual logic being run.

But it offers other additional uses, as it is possible to create a queue of commands to be executed one after the other. Or even create a bidirectional list, where it is possible to do and undo the commands.

To increase the decoupling a command executor can be used. This will not only execute the command, but may also take care of exceptions, dependency injection and any additional procedure the commands may require.

## Command

![Command class hierarchy tree](./images/command_class_tree.png)

The command pattern is implemented through a small hierarchy of interfaces which has at it's root the [Command][command] interface. This offers the _execute_ method, through which the encapsulated code can be called.

This makes it very easy to use:

```
command.execute();
```

But note that this same method can throw exceptions, which means that  executing a command most of the times will be closer to:

```
try {
   command.execute();
} catch (final Exception exception) {
   exception.printStackTrace();
}
```

This, of course, can be solved by using the command executor pattern, commented below, which is actually the preferred way to use this interface.

Other limitation to note is that by default there is no way to give parameters to it, so they should be set on the constructor, or through specific setters.

---

### Commands and return values

The basic command interface allows no way to acquire a value which may have been generated during the execution. This can be solved through the [ResultCommand][result_command] interface, thanks to it's _getResult_ method.

Of course, if the command has not been executed, or it has thrown an error, it may be possible for the returned value to be invalid.

Barring any problem, acquiring a command's value is very simple:

```
command.execute();

value = command.getResult();
```

### Undoing commands

When undoing a command is required the [UndoableCommand][undoable_command] interface should be used.

The logic behind it is very simple, whatever the _execute_ methods does, the _undo_ method reverses it.

Of course, it is the implementation's job to make sure this works as intended.

---

## Command Executor

![Command executor class hierarchy tree](./images/command_executor_class_tree.png)

The command executor is defined by the [CommandExecutor][command_executor] interface.

This offers three methods, one for each type of command.

- The _execute(Command)_ method receives a _Command_ and just executes it.
- The _execute(ResultCommand)_ method receives a _ResultCommand_, executes it and returns the value it has generated.
- The _undo_ method receives an _UndoableCommand_ and calls it's _undo_ method.

This is very simple as, after all, the executor is just meant to hide the command's use. But it can also become a centralized point where all the commands will be executed, allowing any additional operation, such as catching the exceptions or injecting dependencies.

It should be noted that the executor's _execute_ methods do not throw exceptions. So, what with a command is:

```
try {
   command.execute();
} catch (final Exception exception) {
   exception.printStackTrace();
}
```

With an executor is just:

```
executor.execute(command)
```

This is actually what the basic implementation of the interface, the [DefaultCommandExecutor][default_command_executor] serves for. It takes care of the exceptions thrown by the commands, logging them and throwing them again, but also hiding the exception catching blocks.

[command]: ./apidocs/com/wandrell/pattern/command/Command.html
[result_command]: ./apidocs/com/wandrell/pattern/command/ResultCommand.html
[undoable_command]: ./apidocs/com/wandrell/pattern/command/UndoableCommand.html
[command_executor]: ./apidocs/com/wandrell/pattern/command/CommandExecutor.html
[default_command_executor]: ./apidocs/com/wandrell/pattern/command/CommandExecutor.html