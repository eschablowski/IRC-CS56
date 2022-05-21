package com.IRC.Communication.util;

public enum ChannelModes {
    OPERATOR("o"),
    PRIVATE("p"),
    SECRET("s"),
    INVITE_ONLY("i"),
    TOPIC_LOCK("t"),
    NO_NEW_MESSAGES("n"),
    MODERATED("m"),
    USER_LIMIT("u"),
    BAN_MASK("b"),
    SET_SPEAKING("v"),
    SET_KEY("k");

    private String flag;

    ChannelModes(String flag) {
        this.flag = flag;
    }

    public String toString() {
        return this.flag;
    }

    public static ChannelModes parse(String flag) {
        for (ChannelModes mode : ChannelModes.values()) {
            if (mode.flag == flag)
                return mode;
        }
        return null;
    }
}
