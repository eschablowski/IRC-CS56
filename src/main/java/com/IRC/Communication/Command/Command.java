package com.IRC.Communication.Command;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.util.HashMap;
import java.util.Set;

import com.IRC.Communication.Message;
import com.IRC.Communication.Prefix;
import com.IRC.Communication.Reply.Exceptions.UnknownReplyException;
import java.lang.reflect.InvocationTargetException;

public abstract class Command extends Message {
    protected Command(Prefix prefix) {
        super(prefix);
    } 
    private static HashMap<String, Class<Command>> orderedCommandsCache;

    protected static HashMap<String, Class<Command>> orderedCommands() {
        if (Command.orderedCommandsCache != null)
            return Command.orderedCommandsCache;
        final Set<Class<?>> commands = (new Reflections("com.IRC.Communication.Reply"))
                .get(Scanners.SubTypes.of(Command.class).asClass());
        for (Class<?> command : commands) {
            try {
                String commandName = (String) command.getMethod("replyNumber").invoke(command);
                if (!Command.class.isAssignableFrom(command))
                    break; // Skip any non-subclass subclasses
                @SuppressWarnings("unchecked")
                Class<Command> castReply = (Class<Command>) command; // Convert our generic Class to Class<Reply>, since we
                                                               // only look for subtypes of Reply this is OK.
                orderedCommandsCache.put(commandName, castReply);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
                // Basically Gloss Over all Errors, as they cannot happen
            }
        }
        return Command.orderedCommandsCache;
    }

    public static Command parse(String replyString) throws UnknownReplyException {
        String code = replyString.split(" ", 2)[0];
        if (!Command.orderedCommands().containsKey(code))
            throw new UnknownReplyException();
        Class<Command> commandClass = Command.orderedCommands().get(code);
        if (commandClass == null)
            throw new UnknownReplyException();
            Command command;
        try {
            command = (Command) commandClass.getMethod("parse", String.class).invoke(commandClass, replyString.split(" ", 2)[1]);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException exception) {
            throw new UnknownReplyException();
        }
        return command;
    }

    public abstract String commandName();
    public String toString() {
        return super.toString() + this.toString();
    }
}
