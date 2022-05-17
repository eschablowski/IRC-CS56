package com.IRC.Communication.Reply;

import com.IRC.Communication.Prefix;

public class Inviting extends Reply {
    String channel;
    String nick;

    public Inviting(String channel, String nick) {
        super(null);
        this.channel = channel;
        this.nick = nick;
    }

    public Inviting(String channel, String nick, Prefix prefix) {
        super(prefix);
        this.channel = channel;
        this.nick = nick;
    }

    public final int replyNumber() {
        return 341;
    }

    public static Version parse(String replyString) {
        String[] split = replyString.split(":", 2);
        return new Version(split[0], split[1]);
    }

    public String toString() {
        return super.toString() + this.channel + " " + this.nick;
    }
}
