package com.IRC.Communication.Command;

import com.IRC.Communication.Prefix;

public class Message extends Command {
    private String[] receivers;
    private String message;

    public Message(String[] receivers, String message) {
        super(null);
        this.receivers = receivers;
        this.message = message;
    }

    public Message(String[] receivers, String message, Prefix prefix) {
        super(prefix);
        this.receivers = receivers;
        this.message = message;
    }

    public static Message parse(String command) {
        String[] params = command.split(" ", 2);
        String[] receivers = params[0].split(",");
        return new Message(receivers, params[1].substring(1));
    }

    public String toString() {
        return super.toString() + String.join(",", this.receivers) + " " + this.message;
    }

    public final String commandName() {
        return "PRIVMSG";
    }

    public String[] getReceivers() {
        return receivers;
    }

    public String getMessage() {
        return message;
    }
}
