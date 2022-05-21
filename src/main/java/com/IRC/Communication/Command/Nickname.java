package com.IRC.Communication.Command;

import com.IRC.Communication.Prefix;

public class Nickname extends Command {
    private String nick;
    private int hopCount;

    public String getPassword() {
        return this.nick;
    }

    public int getHopCount() {
        return this.hopCount;
    }

    public Nickname getIncremented() {
        return new Nickname(this.nick, this.hopCount + 1);
    }

    Nickname(String nick) {
        super(null);
        this.nick = nick;
        this.hopCount = 0;
    }

    Nickname(String nick, int hopCount) {
        super(null);
        this.nick = nick;
        this.hopCount = hopCount;
    }

    Nickname(String nick, Prefix prefix) {
        super(prefix);
        this.nick = nick;
        this.hopCount = 0;
    }

    Nickname(String nick, int hopCount, Prefix prefix) {
        super(prefix);
        this.nick = nick;
        this.hopCount = hopCount;
    }

    public static Nickname parse(String command) {
        return new Nickname(command);
    }

    public String toString() {
        return super.toString() + " " + this.nick + " " + this.hopCount;
    }

    public final String commandName() {
        return "NICK";
    }
}
