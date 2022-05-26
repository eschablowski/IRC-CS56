package com.IRC.Client;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

import com.IRC.Communication.Message;
import com.IRC.Communication.Command.Command;
import com.IRC.Communication.Reply.Reply;

public class ReceiveHandler implements Runnable {
    private Socket socket;
    private boolean active;

    private LinkedList<MessageInfo> messages;

    private CommandHandler commandHandler;

    public ReceiveHandler(Socket socket, CommandHandler commandHandler) {
        this.socket = socket;
        this.commandHandler = commandHandler;
        this.active = false;
        this.messages = new LinkedList<MessageInfo>();
        new Thread(this).start();
    }

    public void addCommand(Command command, ReplyCallback callback) {
        this.messages.add(new MessageInfo(command, callback));
        this.socket.notify();
    }

    @Override
    public void run() {
        try {
            String line = "";
            while (true) {
                byte[] bytes = new byte[this.socket.getInputStream().available()];
                if (bytes.length != 0) {
                    this.socket.getInputStream().read(bytes);
                    line += bytes;
                    line.replaceAll("\r", "");
                    int newline = line.indexOf("\n");
                    if (newline != -1) {
                        try {
                            Message message = Message.parse(line.substring(0, newline));
                            try {
                                Reply reply = Reply.class.cast(message);
                                this.messages.get(0).replies.add(reply);
                                if (reply.isLastReply()) {
                                    this.messages.remove().runCallback();
                                    this.active = false;
                                }
                            } catch (ClassCastException ex) {
                                Command command = Command.class.cast(message);
                                this.commandHandler.run(command);
                            }
                        } catch (Exception ex) {
                            this.messages.get(0).runCallback(ex);
                        }
                    }
                }
                if (!this.active && !this.messages.isEmpty()) {
                    this.socket.getOutputStream().write(((this.messages.get(0).command.toString() + "\n").getBytes()));
                    this.active = true;
                }
                try {
                    this.socket.wait(100);
                } catch (InterruptedException ex) {
                    // Expected interrupt
                }
                ;
            }
        } catch (IOException ex) {
            // Finish thread when the Socket is closed.
            return;
        }
    }

    private class MessageInfo {
        private Command command;
        private LinkedList<Reply> replies;
        private ReplyCallback callback;

        public MessageInfo(Command command, ReplyCallback callback) {
            this.command = command;
            this.replies = new LinkedList<Reply>();
            this.callback = callback;
        }

        public void runCallback() {
            this.callback.run(this.replies, null);
        }

        public void runCallback(Exception ex) {
            this.callback.run(this.replies, ex);
        }
    }
}
