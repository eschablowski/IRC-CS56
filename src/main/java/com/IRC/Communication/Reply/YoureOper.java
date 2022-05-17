package com.IRC.Communication.Reply;

import com.IRC.Communication.Prefix;

public class YoureOper extends Reply {
    public YoureOper() {
        super(null);
    }
    public YoureOper(Prefix prefix) {
        super(prefix);
    }
    public static YoureOper parse(String replyString) {
        return new YoureOper();
    }

    public final int replyNumber() {
        return 381;
    }
    public String toString() {
        return super.toString() + " :You are now an IRC operator";
    }
}
