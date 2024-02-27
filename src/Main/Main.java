package Main;

import Utils.FrameRate;
import Utils.ToolKit;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import Scenes.StartScene;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(ToolKit.loadImage("element/Logo.jpg"));
        stage.setResizable(false);
        stage.setTitle("Game");
        Scene sceneStart = new StartScene(stage);
        stage.setScene(sceneStart);
        stage.show();
    }
}