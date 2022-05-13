package com.IRC.Client;

import com.IRC.Communication.Reply.Reply;

public interface MessageCallback {
    void handleMessage(Reply reply, Exception error);
}
