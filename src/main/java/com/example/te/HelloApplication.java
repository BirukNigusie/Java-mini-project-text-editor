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
        Image logo = new Image("C:\\Users\\ASHE\\Desktop\\javaminiproject\\logo.png");
        Stage.getIcons().add(logo);
        ImageView logoImageView = new ImageView(logo);
        logoImageView.setFitWidth(500);
        logoImageView.setFitHeight(500);
        // Create a new Image object with the scaled logo image
        WritableImage logoImage = logoImageView.snapshot(new SnapshotParameters(), null);
        BorderPane root = loader.load();
        Scene scene = new Scene(root, 900, 700);
        Stage.setScene(scene);
        Stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}