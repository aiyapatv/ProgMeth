package Utils;

import Scenes.StartScene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class Sound {
    public static void backgroundSound(String path){
        URL gameMusic = StartScene.class.getResource(path) ;
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(gameMusic.toString()));
        mediaPlayer.play();
    }

}
