package com.IRC.Communication.Reply;

import com.IRC.Communication.Prefix;

public class NoTopic extends Reply {
    public NoTopic() {
        super(null);
    }
    public NoTopic(Prefix prefix) {
        super(prefix);
    }

    public final int replyNumber() {
        return 331;
    }

    public static NoTopic parse(String replyString) {
        return new NoTopic();
    }

    public String toString() {
        return super.toString() + " :No topic is set";
    }
}
