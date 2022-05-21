package com.IRC.Communication.util;

public enum UserModes {
    INVISIBLE("i"),
    SERVER_NOTICES("s"),
    WALLOPS("w"),
    OPERATOR("o");

    private String flag;

    UserModes(String flag) {
        this.flag = flag;
    }

    public String toString() {
        return this.flag;
    }

    public static UserModes parse(String flag) {
        for (UserModes mode : UserModes.values()) {
            if (mode.flag == flag)
                return mode;
        }
        return null;
    }
}
