package com.IRC.Client;

public class Settings {
    private UserSettings user;
    private ConnectionSettings connection;

    public Settings() {
        this.user = new UserSettings();
        this.connection = new ConnectionSettings();
    }

    public UserSettings getUser() {
        return user;
    }

    public ConnectionSettings getConnection() {
        return connection;
    }

    public static class UserSettings {
        private String nickname;
        private String awayMessage;
        private String realName;

        public UserSettings() {
            this.realName = "";
            this.awayMessage = "";
        }

        public String getAwayMessage() {
            return awayMessage;
        }

        public void setAwayMessage(String awayMessage) {
            this.awayMessage = awayMessage;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }

    public static class ConnectionSettings {
        private String nickname;
        private String username;
        private String password;
        private String hostname;
        private String servername;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }

        public String getServername() {
            return servername;
        }

        public void setServername(String servername) {
            this.servername = servername;
        }
    }
}
