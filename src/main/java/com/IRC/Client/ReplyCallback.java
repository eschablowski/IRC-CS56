package com.IRC.Client;

import java.util.List;
import com.IRC.Communication.Reply.Reply;

public interface ReplyCallback {
    public void run(List<Reply> reply, Exception error);
}
