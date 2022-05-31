package com.IRC.Communication.Reply;

import com.IRC.Communication.Prefix;

public class NamesEnd extends Reply {
    String channel;

    public NamesEnd(String channel) {
        super(null);
        this.channel = channel;
    }

    public NamesEnd(String channel, Prefix prefix) {
        super(prefix);
        this.channel = channel;
    }

    public final int replyNumber() {
        return 366;
    }

    public static NamesEnd parse(String replyString) {
        return new NamesEnd(replyString.split(" ")[0]);
    }

    public String toString() {
        return super.toString() + " " + this.channel + " : End of /NAMES list";
    }

    @Override
    public boolean isLastReply() {
        return true;
    }
}
