package com.IRC.Communication.Reply;

import com.IRC.Communication.Prefix;

public class NowAway extends Reply {
    public NowAway() {
        super(null);
    }
    public NowAway(Prefix prefix) {
        super(prefix);
    }

    public final int replyNumber() {
        return 306;
    }

    public static NowAway parse(String replyString) {
        return new NowAway();
    }

    public String toString() {
        return super.toString() + " :You have been marked as being away";
    }
}
