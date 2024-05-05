package Scenes;

import Utils.Sound;
import Utils.ToolKit;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PauseScene extends Scene {
    private VBox root;

    private static PauseScene instance;

    public PauseScene(Stage stage) {
        super(new VBox(), 800, 600);
        root = (VBox) getRoot();
        createPauseScene(stage);
        addKeyEventHandler(stage);
    }

    public void createPauseScene(Stage stage) {
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.setBackground(new Background(new BackgroundImage(ToolKit.loadImage("background/bgPauseScene.png"), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,null,null)));

        Text pauseText = new Text("Game Paused");
        pauseText.setFont(ToolKit.loadFont(50));

        Label back = new Label("Press ESC to Continue");
        back.setFont(ToolKit.loadFont(25));

        Button resumeButton = ToolKit.createButton("MAIN MENU", "button/yellowResize1.png", "button/yellowResize2.png", 25);
        resumeButton.setOnAction(event -> {
            Sound.getMediaPlayer().stop();
            Sound.backgroundSound("/sound/StartScene.mp3");
            stage.setScene(StartScene.getInstance(stage));
            Sound.backgroundSound("/sound/ClickButton.mp3");
        });

        Button quitButton = ToolKit.createButton("Quit", "button/yellowResize1.png", "button/yellowResize2.png", 25);
        quitButton.setOnAction(event -> {
            stage.close();
            Sound.backgroundSound("/sound/ClickButton.mp3");
        });

        root.getChildren().addAll(pauseText, back, resumeButton, quitButton);
    }

    public static PauseScene getInstance(Stage stage){
        if(instance == null) instance = new PauseScene(stage);
        return instance;
    }

    private void addKeyEventHandler(Stage stage) {
        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.setScene(GameScene.getInstance(stage));
                Sound.backgroundSound("/sound/ClickButton.mp3");
            }
        });
    }
}
