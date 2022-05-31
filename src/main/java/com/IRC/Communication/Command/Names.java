package com.IRC.Communication.Command;

import com.IRC.Communication.Prefix;

public class Names extends Command {
    private String[] channels;

    public Names() {
        super(null);
        this.channels = new String[0];
    }

    public Names(String[] channels) {
        super(null);
        this.channels = channels;
    }

    public Names(Prefix prefix) {
        super(prefix);
        this.channels = new String[0];
    }

    public Names(String[] channels, Prefix prefix) {
        super(prefix);
        this.channels = channels;
    }

    public static Names parse(String command) {
        String[] params = command.split(",");
        return new Names(params);
    }

    public String toString() {
        return super.toString() + " " + String.join(",", this.channels);
    }

    public final String commandName() {
        return "NAMES";
    }

    public String[] getChannels() {
        return this.channels;
    }
}
