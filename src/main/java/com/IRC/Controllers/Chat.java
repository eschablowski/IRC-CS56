package com.IRC.Controllers;

import com.IRC.Application;
import com.IRC.Client.Client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Chat {
    @FXML
    private ListView<Label> chatroomList;
    @FXML
    private ListView<Label> messagesList;
    @FXML
    private TextField messageText;

    @FXML
    private void initialize() {
        Client client = Application.getApplication().getClient();
        //client.sendMessage().then((action, data))
    }
}
