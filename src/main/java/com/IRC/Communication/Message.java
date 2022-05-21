package com.IRC.Communication;

import com.IRC.Communication.Command.Command;
import com.IRC.Communication.Command.Exceptions.UnknownCommandException;
import com.IRC.Communication.Reply.Reply;
import com.IRC.Communication.Reply.Exceptions.UnknownReplyException;

import java.lang.Character;

import javax.annotation.Nullable;

public abstract class Message {
    protected Message(Prefix prefix) {
        this.prefix = prefix;
    }
    @Nullable
    private Prefix prefix;
    public Prefix getPrefix() {
        return this.prefix;
    }
    public static Message parse(String messageString) throws UnknownReplyException, UnknownCommandException {
        Prefix prefix = null;
        if(messageString.charAt(0) == ':') {
            prefix = new Prefix(messageString.split(" ", 2)[0].substring(1));
            messageString = messageString.split(" ", 2)[1];
        }
        Message message;
        if(Character.isDigit(messageString.charAt(0))) {
            message = Reply.parse(messageString);
        } else {
            message = Command.parse(messageString);
        }
        message.prefix = prefix;
        return message;
    }
    public String toString() {
        if(prefix == null) return "";
        return prefix.toString() + " ";
    }
}
