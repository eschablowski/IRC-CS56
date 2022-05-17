package com.IRC.Communication.Reply;

import com.IRC.Communication.Prefix;

public class Topic extends Reply {
    private String channel;
    public String getChannel() {
        return this.channel;
    }
    private String topic;
    public String getTopic() {
        return this.topic;
    }

    public Topic(String channel, String topic) {
        super(null);
        this.channel = channel;
        this.topic = topic;
    }

    public Topic(String channel, String topic, Prefix prefix) {
        super(prefix);
        this.channel = channel;
        this.topic = topic;
    }

    public static Topic parse(String replyString) {
        String[] split = replyString.split(" :", 2);
        return new Topic(split[0], split[1]);
    }

    public final int replyNumber() {
        return 332;
    }

    public String toString() {
        return super.toString() + " " + this.channel + " :" + this.topic;
    }
}
