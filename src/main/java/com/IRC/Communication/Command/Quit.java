package com.IRC.Communication.Command;

import com.IRC.Communication.Prefix;

public class Quit extends Command {
    private String message;

    public String getMessage() {
        return this.message;
    }

    public Quit() {
        super(null);
        this.message = null;
    }

    public Quit(String message) {
        super(null);
        this.message = message;
    }

    public Quit(Prefix prefix) {
        super(prefix);
        this.message = null;
    }

    public Quit(String message, Prefix prefix) {
        super(prefix);
        this.message = message;
    }

    public static Quit parse(String command) {
        String[] params = command.split(":");
        if (params.length == 1)
            return new Quit();
        return new Quit(params[1]);
    }

    public String toString() {
        if (this.message == null)
            return super.toString();
        return super.toString() + " :" + this.message;
    }

    public final String commandName() {
        return "QUIT";
    }
}
