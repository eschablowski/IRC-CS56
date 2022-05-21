package com.IRC.Communication.Reply;

import com.IRC.Communication.Prefix;

public class ListEnd extends Reply {
    public ListEnd() {
        super(null);
    }

    public ListEnd(Prefix prefix) {
        super(prefix);
    }

    public final int replyNumber() {
        return 323;
    }

    public static ListEnd parse(String replyString) {
        return new ListEnd();
    }

    public String toString() {
        return super.toString() + " : End of /LIST";
    }

    @Override
    public boolean isLastReply() {
        return false;
    }
}
