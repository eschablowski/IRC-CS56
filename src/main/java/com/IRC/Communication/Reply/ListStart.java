package com.IRC.Communication.Reply;

import com.IRC.Communication.Prefix;

public class ListStart extends Reply {
    public ListStart() {
        super(null);
    }

    public ListStart(Prefix prefix) {
        super(prefix);
    }

    public final int replyNumber() {
        return 321;
    }

    public static ListStart parse(String replyString) {
        return new ListStart();
    }

    public String toString() {
        return super.toString() + " Channel :Users Name";
    }

    @Override
    public boolean isLastReply() {
        return false;
    }
}
