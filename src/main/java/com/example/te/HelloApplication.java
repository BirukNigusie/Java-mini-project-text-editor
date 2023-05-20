package com.example.te;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage Stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        BorderPane root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add("style.css");
        Stage.setScene(scene);
        Stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}