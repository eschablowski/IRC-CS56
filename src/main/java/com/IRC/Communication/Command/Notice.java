package com.IRC.Communication.Command;

import com.IRC.Communication.Prefix;

public class Notice extends Command {
    private String[] receivers;
    private String message;

    public Notice(String[] receivers, String message) {
        super(null);
        this.receivers = receivers;
        this.message = message;
    }

    public Notice(String[] receivers, String message, Prefix prefix) {
        super(prefix);
        this.receivers = receivers;
        this.message = message;
    }

    public static Notice parse(String command) {
        String[] params = command.split(" ", 2);
        String[] receivers = params[0].split(",");
        return new Notice(receivers, params[1].substring(1));
    }

    public String toString() {
        return super.toString() + String.join(",", this.receivers) + " " + this.message;
    }

    public final String commandName() {
        return "NOTICE";
    }

    public String[] getReceivers() {
        return receivers;
    }

    public String getMessage() {
        return message;
    }
}
