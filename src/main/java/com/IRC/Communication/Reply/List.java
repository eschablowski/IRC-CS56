package com.IRC.Communication.Reply;

import com.IRC.Communication.Prefix;

public class List extends Reply {
    private String channel;
    private int users;
    private String topic;

    public List(String channel, int users, String topic) {
        super(null);
    }

    public List(String channel, int users, String topic, Prefix prefix) {
        super(prefix);
    }

    public final int replyNumber() {
        return 322;
    }

    public static List parse(String replyString) {
        String[] split = replyString.split(" ");
        return new List(split[0], Integer.parseInt(split[1]), split[2].substring(1));
    }

    public String toString() {
        return super.toString() + " " + this.channel + " " + this.users + " :" + this.topic;
    }

    @Override
    public boolean isLastReply() {
        return false;
    }

    public String getChannel() {
        return channel;
    }

    public int getUsers() {
        return users;
    }

    public String getTopic() {
        return topic;
    }
}
