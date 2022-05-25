package com.IRC.Communication.Command;

import com.IRC.Communication.Prefix;

public class Password extends Command {
    private String password;

    public String getPassword() {
        return this.password;
    }

    public Password(String password) {
        super(null);
        this.password = password;
    }

    public Password(String password, Prefix prefix) {
        super(prefix);
        this.password = password;
    }

    public static Password parse(String command) {
        return new Password(command);
    }

    public String toString() {
        return super.toString() + " " + this.password;
    }

    public final String commandName() {
        return "PASS";
    }
}
