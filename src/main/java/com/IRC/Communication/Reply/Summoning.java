package com.IRC.Communication.Reply;

import com.IRC.Communication.Prefix;

public class Summoning extends Reply {
    String user;

    public Summoning(String user) {
        super(null);
        this.user = user;
    }

    public Summoning(String user, Prefix prefix) {
        super(prefix);
        this.user = user;
    }

    public final int replyNumber() {
        return 341;
    }

    public static Summoning parse(String replyString) {
        String[] split = replyString.split(" :", 2);
        return new Summoning(split[0]);
    }

    public String toString() {
        return super.toString() + this.user + " :Summoning user to IRC";
    }
}
