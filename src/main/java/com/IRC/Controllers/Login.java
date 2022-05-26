package com.IRC.Controllers;

import java.io.IOException;

import com.IRC.Application;
import com.IRC.Client.Client;
import com.IRC.Communication.Command.Quit;
import com.IRC.Communication.Command.Password;
import com.IRC.Communication.Command.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Login {

    @FXML
    private TextField serverInput;
    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private TextField nicknameInput;

    @FXML
    private Label errorMessage;

    @FXML
    private void onLogin() {
        Application app = Application.getApplication();
        Client client = app.getClient();

        try {
            String[] server = this.serverInput.toString().split(":");
            int port = 194;
            if (server.length > 1)
                port = Integer.parseInt(server[1]);
            client.connect(server[0], port);
        } catch (IOException ex) {
            this.errorMessage.setText("Cannot connect to server");
            return;
        }

        client.sendCommand(new Password(this.passwordInput.getText()))
                .then(client.sendCommand(new Quit(this.nicknameInput.getText())))
                .then(client.sendCommand(
                        new User(this.usernameInput.getText(), this.serverInput.getText(), "", "realName")))
                .then((action, replies) -> {
                    try {
                        app.loadScene("chat.fxml");
                    } catch (IOException ioException) {
                        app.quit();
                    }
                }, (action, ex) -> {
                    this.errorMessage.setText("Invalid Username, Password or Nickname");
                });
    }
}
