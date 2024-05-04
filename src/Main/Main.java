package Main;

import Scenes.PauseScene;
import Utils.FrameRate;
import Utils.ToolKit;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import Scenes.StartScene;

import java.io.File;
import java.net.URL;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(ToolKit.loadImage("element/Logo.jpg"));
        stage.setResizable(false);
        stage.setTitle("Game");
        PauseScene.getInstance(stage);
        Scene sceneStart = StartScene.getInstance(stage);
        stage.setScene(sceneStart);
        stage.show();
    }

    public static void backgroundSound(String path){
        URL gameMusic = StartScene.class.getResource(path) ;
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(gameMusic.toString()));
        mediaPlayer.play();
    }
}