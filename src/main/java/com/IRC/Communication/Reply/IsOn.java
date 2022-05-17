package com.IRC.Communication.Reply;

import com.IRC.Communication.Prefix;

public class IsOn extends Reply {
    public IsOn(String[] nicks) {
        super(null);
        this.nicks = nicks;
    }
    public IsOn(String[] nicks, Prefix prefix) {
        super(prefix);
        this.nicks = nicks;
    }
    private String[] nicks;
    public String[] getNicks() {
        return this.nicks;
    }
    public final int replyNumber() {
        return 303;
    }
    public static IsOn parse(String replyString) {
        replyString = replyString.split(":", 2)[1];
        IsOn isOn = new IsOn(replyString.split(" "));
        return isOn;
    }
    public String toString() {
        return super.toString() + ":" + String.join(" ", this.nicks);
    }
}
