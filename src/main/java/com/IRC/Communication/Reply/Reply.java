package com.IRC.Communication.Reply;

public abstract class Reply {
    static Reply parse(String replyString) {
        return new ReplyNone(); // implement actual replies here
    }
}
