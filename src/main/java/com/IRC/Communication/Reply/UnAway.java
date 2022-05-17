package com.IRC.Communication.Reply;

import com.IRC.Communication.Prefix;

public class UnAway extends Reply {
    public UnAway() {
        super(null);
    }
    public UnAway(Prefix prefix) {
        super(prefix);
    }

    public final int replyNumber() {
        return 305;
    }

    public static UnAway parse(String replyString) {
        return new UnAway();
    }

    public String toString() {
        return super.toString() + " :You are no longer marked as being away";
    }
}
