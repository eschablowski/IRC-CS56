package com.IRC.Client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

import com.IRC.Communication.Command.Command;
import com.IRC.Communication.Reply.Reply;

import org.riversun.promise.Promise;

public class Client {
  private Socket socket;

  public void connect(String hostname, int port) throws IOException {
    this.socket.connect(new InetSocketAddress(hostname, port));
  }

  public Client(Socket socket) {
    this.receiveHandler = new ReceiveHandler(socket, (Command) -> {
    });
    this.socket = socket;
  }

  private ReceiveHandler receiveHandler;

  public Promise sendCommand(Command command) {
    return new Promise((action, data) -> {
      this.receiveHandler.addCommand(command, (List<Reply> replies, Exception ex) -> {
        if (ex == null) {
          action.resolve(replies);
        } else {
          action.reject(ex);
        }
      });
    });
  }
}