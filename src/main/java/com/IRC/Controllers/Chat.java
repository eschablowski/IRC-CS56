package com.IRC.Controllers;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashSet;

import com.IRC.Application;
import com.IRC.Client.Client;
import com.IRC.Client.MessageList;
import com.IRC.Communication.Command.Names;
import com.IRC.Communication.Command.User;
import com.IRC.Communication.Reply.Reply;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Chat {
    @FXML
    private ListView<String> chatroomList;
    @FXML
    private ListView<String> usersList;
    @FXML
    private ListView<MessageList.Message> messagesList;
    @FXML
    private TextField messageText;

    Thread messageUpdateThread;
    Thread chatroomUpdateThread;

    String currentChatroom;

    public Chat() {
        Client client = Application.getApplication().getClient();

        this.messageUpdateThread = new Thread(() -> {
            ObservableList<MessageList.Message> observableList = FXCollections.observableArrayList();
            while (true) {
                try {
                    if (this.currentChatroom == null)
                        this.currentChatroom.wait();
                } catch (InterruptedException ex) {
                }
                MessageList messages = client.getChatrooms().get(this.currentChatroom).getMessages();
                for (int i = observableList.size(); i < messages.size(); i++) { // Add all new messages
                    observableList.add(messages.get(i));
                }
                messagesList.setItems(observableList);
                try {
                    client.wait(1000);
                } catch (InterruptedException ex) {
                }
            }
        });

        this.chatroomUpdateThread = new Thread(() -> {
            while (true) {
                this.getAvailableChats();
                try {
                    client.wait(10000);
                } catch (InterruptedException ex) {
                }
            }
        });

        this.messageUpdateThread.start();
        this.chatroomUpdateThread.start();
    }

    private void getAvailableChats() {
        Client client = Application.getApplication().getClient();
        client.sendCommand(new Names()).then((action, rawReplies) -> {
            HashSet<String> channels = new HashSet<>();
            HashSet<String> users = new HashSet<>();
            try {
                @SuppressWarnings("unchecked")
                LinkedList<Reply> replies = (LinkedList<Reply>) rawReplies;
                for (Reply reply : replies) {
                    try {
                        com.IRC.Communication.Reply.Names names = com.IRC.Communication.Reply.Names.class.cast(reply);
                        channels.add(names.getChannel());
                        for (com.IRC.Communication.Reply.Names.User user : names.getUsers()) {
                            users.add(user.getNickname());
                        }
                    } catch (ClassCastException ex) {
                        // We probably have reached the end of the list, no problem.
                    }
                }
            } catch (ClassCastException ex) {
            }
            if (this.currentChatroom == null) {
                this.currentChatroom = ((String[]) channels.toArray())[0]; // Connect to the first channel
                this.currentChatroom.notifyAll();
            }
            this.chatroomList.setItems(FXCollections.observableArrayList((String[]) channels.toArray()));
            this.usersList.setItems(FXCollections.observableArrayList((String[]) users.toArray()));
        });
    }
}
