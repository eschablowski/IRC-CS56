package com.IRC.Client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.IRC.Communication.Command.Message;
import com.IRC.Communication.Command.Notice;
import com.IRC.Communication.Command.Command;
import com.IRC.Communication.Reply.Reply;
import com.IRC.Communication.Reply.Errors.ErrorReply;

import com.IRC.Util.ClassUtil;
import org.riversun.promise.Promise;

public class Client {
  private Settings settings;
  private Socket socket;
  private HashMap<String, Chatroom> chatrooms;
  private LinkedList<String> receivedCommands;

  public HashMap<String, Chatroom> getChatrooms() {
    return this.chatrooms;
  }

  public void connect(String hostname, int port) throws IOException {
    this.socket.connect(new InetSocketAddress(hostname, port));
  }

  public Client(Socket socket) {
    this.receiveHandler = new ReceiveHandler(socket, (command) -> {
      if (ClassUtil.isType(Message.class, command)) {
        try {
          Message message = Message.class.cast(command);
          for (String receiver : message.getReceivers()) {
            if (message.getPrefix() != null && this.settings.getUser().getNickname() == receiver) {
              String sender = message.getPrefix().getServer_nick();
              if (!this.chatrooms.containsKey(sender))
                this.chatrooms.get(sender).getMessages().add(MessageList.MessageType.MESSAGE,
                    Message.class.cast(command).getMessage());
              this.chatrooms.get(sender).notifyAll();
            } else if (this.chatrooms.containsKey(receiver)) {
              this.chatrooms.get(receiver).getMessages().add(MessageList.MessageType.MESSAGE,
                  Message.class.cast(command).getMessage());
              this.chatrooms.get(receiver).notifyAll();
            }
          }
        } catch (ClassCastException ex) {
          // Cannot happen, as we checked for this in the if.
        }
      } else if (ClassUtil.isType(Notice.class, command)) {
        try {
          Notice message = Notice.class.cast(command);
          for (String receiver : message.getReceivers()) {
            if (message.getPrefix() != null && this.settings.getUser().getNickname() == receiver) {
              String sender = message.getPrefix().getServer_nick();
              if (!this.chatrooms.containsKey(sender))
                this.chatrooms.get(sender).getMessages().add(MessageList.MessageType.MESSAGE,
                    Notice.class.cast(command).getMessage());
              this.chatrooms.get(sender).notifyAll();
            } else if (this.chatrooms.containsKey(receiver)) {
              this.chatrooms.get(receiver).getMessages().add(MessageList.MessageType.MESSAGE,
                  Notice.class.cast(command).getMessage());
              this.chatrooms.get(receiver).notifyAll();
            }
          }
        } catch (ClassCastException ex) {
          // Cannot happen, as we checked for this in the if.
        }
      }
      this.receivedCommands.add(command.toString());
      if (this.receivedCommands.size() > 200) // Make sure we only keep the last 200 commands to mitigate memory hogging
        this.receivedCommands.removeFirst();
      this.receivedCommands.notifyAll();
    });
    this.socket = socket;
  }

  private ReceiveHandler receiveHandler;

  public Promise sendCommand(Command command) {
    return new Promise((action, data) -> {
      this.receiveHandler.addCommand(command, (List<Reply> replies, Exception ex) -> {
        try {
          ErrorReply.class.cast(replies.get(replies.size() - 1)); // Check if we ended with an error.
          action.reject(replies);
        } catch (ClassCastException ccex) { // Not an ErrorReply
          if (ex == null) {
            action.resolve(replies);
          } else {
            action.reject(ex);
          }
        }
      });
    });
  }
}