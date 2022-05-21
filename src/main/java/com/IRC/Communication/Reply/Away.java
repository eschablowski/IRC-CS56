package com.IRC.Communication.Reply;

import com.IRC.Communication.Prefix;

public class Away extends Reply {
    String nick;
    String awayMessage;

    public Away(String nick) {
        super(null);
        this.nick = nick;
        this.awayMessage = "";
    }

    public Away(String nick, String awayMessage) {
        super(null);
        this.nick = nick;
        this.awayMessage = awayMessage;
    }

    public Away(String nick, Prefix prefix) {
        super(prefix);
        this.nick = nick;
        this.awayMessage = "";
    }

    public Away(String nick, String awayMessage, Prefix prefix) {
        super(prefix);
        this.nick = nick;
        this.awayMessage = awayMessage;
    }

    public final int replyNumber() {
        return 301;
    }

    public static Away parse(String replyString) {
        String[] split = replyString.split(":", 2);
        return new Away(split[0], split[1]);
    }

    public String toString() {
        return super.toString() + this.nick + " :" + this.awayMessage;
    }
}
