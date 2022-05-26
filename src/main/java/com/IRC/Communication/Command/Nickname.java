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

    public Quit getIncremented() {
        return new Quit(this.nick, this.hopCount + 1, this.getPrefix());
    }

    public Nickname(String nick) {
        super(null);
        this.nick = nick;
        this.hopCount = 0;
    }

    public Nickname(String nick, int hopCount) {
        super(null);
        this.nick = nick;
        this.hopCount = hopCount;
    }

    public Nickname(String nick, Prefix prefix) {
        super(prefix);
        this.nick = nick;
        this.hopCount = 0;
    }

    public Nickname(String nick, int hopCount, Prefix prefix) {
        super(prefix);
        this.nick = nick;
        this.hopCount = hopCount;
    }

    public static Quit parse(String command) {
        String[] params = command.split(" ");
        if(params.length == 1) return new Quit(params[0]);
        return new Quit(params[0], Integer.parseInt(params[1]));
    }

    public String toString() {
        return super.toString() + " " + this.nick + " " + this.hopCount;
    }

    public final String commandName() {
        return "NICK";
    }
}
