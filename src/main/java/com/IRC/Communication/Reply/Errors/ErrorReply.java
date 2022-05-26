package com.IRC.Communication.Reply.Errors;

import com.IRC.Communication.Prefix;
import com.IRC.Communication.Reply.Reply;

public abstract class ErrorReply extends Reply {
    protected ErrorReply(Prefix prefix) {
        super(prefix);
    }
    public String toString() {
        return super.toString();
    }
    public boolean isLastReply() {
        return true;
    }
}
