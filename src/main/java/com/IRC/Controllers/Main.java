package com.IRC.Controllers;

import com.IRC.Application;

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
        app.quit();
    }

    @FXML
    private void onPreferences() {
    }
}
