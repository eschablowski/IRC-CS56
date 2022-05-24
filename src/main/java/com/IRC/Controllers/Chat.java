package com.IRC.Controllers;

import java.io.IOException;

import com.IRC.Application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Chat {

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
        try {
            app.loadScene("chat.fxml");
        } catch (IOException ioException) {
            app.quit();
        }
    }
}
