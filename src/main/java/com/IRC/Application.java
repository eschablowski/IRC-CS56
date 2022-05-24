package com.IRC;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    private static Application app;
    private Stage stage;

    public static Application getApplication() {
        return Application.app;
    }

    public void quit() {
        this.stage.close();
    }

    public void loadScene(String filename) throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource(filename));
        Scene scene = new Scene(loader.load());
        this.stage.setScene(scene);
        this.stage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Application.app = this;
        this.stage = stage;
        loadScene("login.fxml");
    }

    public static void main() {
        launch();
    }
}
