package com.IRC.Controllers;

import java.io.IOException;

import com.IRC.Application;
import com.IRC.Communication.Command.Quit;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class Main {
    @FXML
    private HBox mainContent;

    private Node currentScreen;

    public void setMainContent(Node node) {
        this.mainContent.getChildren().clear();
        this.mainContent.getChildren().add(node);
        this.currentScreen = node;
    }

    @FXML
    private void onQuit() {
        Application app = Application.getApplication();
        app.getClient().sendCommand(new Quit());
        app.quit();
    }

    @FXML
    private void onLogout() {
        Application app = Application.getApplication();
        app.getClient().sendCommand(new Quit());
        try {
            app.loadScene("login.fxml");
        } catch (IOException ex) {
            // Since we needed to have "login.fxml" to get here, print and quit, nothing else to do.
            System.out.println(ex);
            app.quit(); 
        }
    }

    @FXML
    private void onOperator() {
        Application app = Application.getApplication();
        app.getClient().sendCommand(new Quit());
    }

    public Node getCurrentScreen() {
        return currentScreen;
    }
}
