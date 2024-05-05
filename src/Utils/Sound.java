package Utils;

import Scenes.GameScene;
import Scenes.StartScene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class Sound {
    private static MediaPlayer mediaPlayer;
    public static void backgroundSound(String path){
        URL gameMusic = StartScene.class.getResource(path) ;
        mediaPlayer = new MediaPlayer(new Media(gameMusic.toString()));
        mediaPlayer.play();
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
