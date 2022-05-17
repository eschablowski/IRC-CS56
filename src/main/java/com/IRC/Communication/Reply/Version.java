package com.IRC.Communication.Reply;

import com.IRC.Communication.Prefix;

public class Version extends Reply {
    private String version;
    private String debugLevel;
    private String server;
    private String comments;

    public String getVersion() {
        return version;
    }

    public String getDebugLevel() {
        return debugLevel;
    }

    public String getServer() {
        return server;
    }

    public String getComments() {
        return comments;
    }

    public Version(String version, String debugLevel, String server) {
        super(null);
        this.version = version;
        this.debugLevel = debugLevel;
        this.server = server;
        this.comments = "";
    }

    public Version(String version, String debugLevel, String server, String comments) {
        super(null);
        this.version = version;
        this.debugLevel = debugLevel;
        this.server = server;
        this.comments = comments;
    }

    public Version(String version, String debugLevel, String server, Prefix prefix) {
        super(prefix);
        this.version = version;
        this.debugLevel = debugLevel;
        this.server = server;
        this.comments = "";
    }

    public Version(String version, String debugLevel, String server, String comments, Prefix prefix) {
        super(prefix);
        this.version = version;
        this.debugLevel = debugLevel;
        this.server = server;
        this.comments = comments;
    }

    public final int replyNumber() {
        return 351;
    }

    public static Version parse(String replyString) {
        String[] split = replyString.split(" ", 3);
        String[] versionSplit = split[0].split(".");
        String debugLevel = versionSplit[versionSplit.length - 1];
        String version = split[0].substring(0, split[0].length() - debugLevel.length());

        return new Version(version, debugLevel, split[1], split[2].substring(1));
    }

    public String toString() {
        return super.toString() + " " + this.version + "." + this.debugLevel + " " + this.server + ": " + this.comments;
    }
}
