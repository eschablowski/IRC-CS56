package com.IRC.Communication.Reply;

import com.IRC.Communication.Prefix;

public class UserHost extends Reply {
    private UserInfo[] info;

    public UserInfo[] getInfo() {
        return this.info;
    }

    public UserHost(UserInfo[] info) {
        super(null);
        this.info = info;
    }

    public UserHost(UserInfo[] info, Prefix prefix) {
        super(prefix);
        this.info = info;
    }

    public static UserHost parse(String replyString) {
        String[] replies = replyString.split(":", 2)[1].split(" ");
        UserInfo[] info = new UserInfo[replies.length];
        for (int i = 0; i < replies.length; i++) {
            info[i] = new UserInfo(replies[i]);
        }
        return new UserHost(info);
    }

    public final int replyNumber() {
        return 302;
    }

    public String toString() {
        String params = " :";
        for (UserInfo info : this.info) {
            params += info.getNickname();
            if (info.isOperator())
                params += "*";
            params += "=";
            params += info.hasAwayMessage() ? "-" : "+";
            params += info.getHostname();
            params += " ";
        }
        return super.toString() + params.substring(0, params.length() - 1);
    }

    public static class UserInfo {
        UserInfo(String info) {
            final String[] splitInfo = info.split("=", 2);
            this.nickname = splitInfo[0];
            if (this.nickname.endsWith("*")) {
                this.nickname = this.nickname.substring(0, this.nickname.length() - 1);
                this.operator = true;
            } else {
                this.operator = false;
            }
            this.awayMessage = splitInfo[1].charAt(0) == '-';
            this.hostname = splitInfo[1].substring(1);
        }

        private String nickname;
        private String hostname;
        private boolean operator;
        private boolean awayMessage;

        public String getNickname() {
            return this.nickname;
        }

        public String getHostname() {
            return this.hostname;
        }

        public boolean isOperator() {
            return this.operator;
        }

        public boolean hasAwayMessage() {
            return this.awayMessage;
        }
    }
}
