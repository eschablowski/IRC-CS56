package com.IRC.Communication.Command;

import com.IRC.Communication.Prefix;

public class Operator extends Command {
    private String username;
    private String password;

    public Operator(String username, String password) {
        super(null);
        this.username = username;
        this.password = password;
    }

    public Operator(String username, String password, Prefix prefix) {
        super(prefix);
        this.username = username;
        this.password = password;
    }

    public static Operator parse(String command) {
        String[] params = command.split(" ");
        return new Operator(params[0], params[1]);
    }

    public String toString() {
        return super.toString() + " " + this.username + " " + this.password;
    }

    public final String commandName() {
        return "OPER";
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
