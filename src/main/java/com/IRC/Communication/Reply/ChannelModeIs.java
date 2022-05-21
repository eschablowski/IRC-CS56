package com.IRC.Communication.Reply;

import com.IRC.Communication.Prefix;
import com.IRC.Communication.util.ChannelModes;

public class ChannelModeIs extends Reply {
    String channel;
    ChannelModes mode;
    String[] modeParameters;

    public ChannelModeIs(String channel, ChannelModes mode, String[] modeParams) {
        super(null);
        this.channel = channel;
        this.mode = mode;
        this.modeParameters = modeParams;
    }

    public ChannelModeIs(String channel, ChannelModes mode, String[] modeParams, Prefix prefix) {
        super(prefix);
        this.channel = channel;
        this.mode = mode;
        this.modeParameters = modeParams;
    }

    public final int replyNumber() {
        return 324;
    }

    public static ChannelModeIs parse(String replyString) {
        String[] split = replyString.split(" ", 3);
        return new ChannelModeIs(split[0], ChannelModes.parse(split[1]), split[2].split(" "));
    }

    public String toString() {
        return super.toString() + " " + this.channel + " " + this.mode.toString() + " "
                + String.join(" ", this.modeParameters);
    }
}
