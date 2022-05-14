package com.IRC.Communication.Reply;

public class UserHost extends Reply {
    UserInfo[] info;

    public static UserHost parse(String replyString) {
        String[] replies = replyString.split(":", 2)[1].split(" ");
        UserHost userHost = new UserHost();
        userHost.info = new UserInfo[replies.length];
        for(int i = 0; i < replies.length; i++) {
            userHost.info[i] = userHost.new UserInfo(replies[i]);
        }
        return userHost;
    }

    public final int replyNumber() {
        return 302;
    }

    public class UserInfo {
        UserInfo(String info) {
            final String[] splitInfo = info.split("=", 2);
            this.nickname = splitInfo[0];
            if(this.nickname.endsWith("*")) {
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
