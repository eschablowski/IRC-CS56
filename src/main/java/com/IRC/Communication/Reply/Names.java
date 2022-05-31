package com.IRC.Communication.Reply;

import com.IRC.Communication.Prefix;

public class Names extends Reply {
    private String channel;
    private User[] users;

    public Names(String channel, User[] users) {
        super(null);
        this.channel = channel;
        this.users = users;
    }

    public Names(String channel, User[] users, Prefix prefix) {
        super(prefix);
        this.channel = channel;
        this.users = users;
    }

    public final int replyNumber() {
        return 353;
    }

    @Override
    public boolean isLastReply() {
        return false;
    }

    public static Names parse(String replyString) {
        String[] nameStrings = replyString.split(" ");
        User[] users = new User[nameStrings.length - 1];
        for (int i = 1; i < nameStrings.length; i++) {
            boolean operator = false;
            boolean canSpeak = false;
            switch (nameStrings[i].charAt(0)) {
                case '@':
                    operator = true;
                    // fall through, as operators can always speak
                case '+':
                    canSpeak = true;
                    nameStrings[i] = nameStrings[i].substring(1); // remove quantifier
                default:
                    break;
            }
            users[i] = new User(nameStrings[i], operator, canSpeak);
        }
        return new Names(nameStrings[0], users);
    }

    public String toString() {
        String[] userStrings = new String[this.users.length];
        for (int i = 0; i < userStrings.length; i++) {
            if (this.users[i].operator) {
                userStrings[i] = "@" + this.users[i].nickname;
            } else if (this.users[i].canSpeak) {
                userStrings[i] = "+" + this.users[i].nickname;
            } else {
                userStrings[i] = this.users[i].nickname;
            }
        }
        return super.toString() + " " + this.channel + " " + String.join(" ", userStrings);
    }

    public String getChannel() {
        return channel;
    }

    public User[] getUsers() {
        return users;
    }

    public static class User {
        private boolean operator;
        private boolean canSpeak;
        private String nickname;

        public User(String nickname, boolean operator, boolean canSpeak) {
            this.nickname = nickname;
            this.canSpeak = canSpeak;
            this.operator = operator;
        }

        public boolean isOperator() {
            return operator;
        }

        public boolean canSpeak() {
            return canSpeak;
        }

        public String getNickname() {
            return nickname;
        }
    }
}
