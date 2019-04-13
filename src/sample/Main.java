package sample;

import constants.ApplicationConstants;
import helper.Utility;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hangman");

        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        Utility.createAppDirectory(ApplicationConstants.APP_FOLDER_DATA_PATH);
        launch(args);
    }
}
