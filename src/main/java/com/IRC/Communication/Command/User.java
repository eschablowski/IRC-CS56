package com.IRC.Communication.Command;

import com.IRC.Communication.Prefix;

public class User extends Command {
    private String username;
    private String hostname;
    private String serverName;
    private String realName;

    public User(String username, String hostname, String serverName, String realName) {
        super(null);
        this.username = username;
        this.hostname = hostname;
        this.serverName = serverName;
        this.realName = realName;
    }

    public User(String username, String hostname, String serverName, String realName, Prefix prefix) {
        super(prefix);
        this.username = username;
        this.hostname = hostname;
        this.serverName = serverName;
        this.realName = realName;
    }

    public static User parse(String command) {
        String[] params = command.split(" ", 4);
        return new User(params[0], params[1], params[2], params[3].substring(1));
    }

    public String toString() {
        return String.join(" ", super.toString(), this.username, this.hostname, this.serverName, this.realName);
    }

    public final String commandName() {
        return "USER";
    }

    public String getUsername() {
        return username;
    }

    public String getHostname() {
        return hostname;
    }

    public String getServerName() {
        return serverName;
    }

    public String getRealName() {
        return realName;
    }
}
