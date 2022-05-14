package com.IRC.Communication.Reply;

public class None extends Reply {
    public final int replyNumber() {
        return 300;
    }
    public static None parse(String replyString) {
        return new None();
    }
}
