package com.IRC;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.IRC.Client.Client;

public class Application extends javafx.application.Application {
    private static Application app;
    private Stage stage;
    private Client client;

    public static Application getApplication() {
        return Application.app;
    }

    public Client getClient() {
        return this.client;
    }

    public void quit() {
        this.stage.close();
    }

    public void loadScene(String filename) throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource(filename));
        Scene scene = new Scene(loader.load(), 300, 200);
        this.stage.setScene(scene);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Application.app = this;
        this.stage = stage;
        this.client = new Client(new java.net.Socket());
        this.loadScene("login.fxml");
        this.stage.show();
    }

    public static void main() {
        launch();
    }
}
