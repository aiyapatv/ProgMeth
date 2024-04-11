package Scenes;

import Utils.ToolKit;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.tools.Tool;

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

        Text pauseText = new Text("Game Paused");
        pauseText.setFont(ToolKit.loadFont(50));

        Button resumeButton = ToolKit.createButton("MAIN MENU", "element/shortBox.png",null, 25);
        resumeButton.setOnAction(event -> {
            stage.setScene(StartScene.getInstance(stage));
        });

        Button quitButton = ToolKit.createButton("Quit", "element/shortBox.png",null, 25);
        quitButton.setOnAction(event -> {
            stage.close();
        });

        root.getChildren().addAll(pauseText, resumeButton, quitButton);
    }

    public static PauseScene getInstance(Stage stage){
        if(instance == null) instance = new PauseScene(stage);
        return instance;
    }

    private void addKeyEventHandler(Stage stage) {
        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.setScene(GameScene.getInstance(stage));
            }
        });
    }
}
