package com.IRC.Communication;

public class Prefix {
    String server_nick;
    String user;
    String host;

    Prefix(String prefixString) {
        this.server_nick = null;
        this.user = null;
        this.host = null;

        String[] split = prefixString.split("!", 2);
        server_nick = split[0];
        if (split.length > 1) {
            split = split[1].split("@");
            this.user = split[0];
            if (split.length > 1) {
                this.host = split[1];
            }
        }
    }

    public String toString() {
        String prefix = ":" + this.server_nick;
        if (this.user != null) {
            prefix += this.user;
        }
        if (this.host != null) {
            prefix += this.host;
        }
        return prefix;
    }

    public String getServer_nick() {
        return server_nick;
    }

    public String getUser() {
        return user;
    }

    public String getHost() {
        return host;
    }
}
