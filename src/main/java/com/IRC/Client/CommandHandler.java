package com.IRC.Client;

import com.IRC.Communication.Command.Command;

public interface CommandHandler {
    public void run(Command command);
}
