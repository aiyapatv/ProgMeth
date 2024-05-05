package Scenes;

import Board.HexagonBoard;
import Utils.Sound;
import Utils.ToolKit;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.game.GameController;

public class GameScene extends Scene {
    private static GameScene instance;

    private static Text turn;
    public GameScene(Stage stage) {
        super(createGameScene(stage), 800, 600);
        Sound.getMediaPlayer().stop();
        setCursor(new ImageCursor(ToolKit.loadImage("character/c" + ChooseScene.getNumber() + "_" + 1 +".png"),100,0));
        addKeyEventHandler(stage);
    }
    public static AnchorPane createGameScene(Stage stage){
        AnchorPane root = new HexagonBoard(stage);
        turn = new Text();
        turn.setFont(ToolKit.loadFont(40));
        AnchorPane.setTopAnchor(turn, 10.0);
        AnchorPane.setLeftAnchor(turn, 10.0);
        turn.setText("Turn: " + GameController.getInstance().getTurn());
        root.getChildren().addAll(turn);

        return root;
    }
    public static GameScene getInstance(Stage stage){
        if(instance == null){
            instance = new GameScene(stage);
        }
        return instance;
    }

    public static void setInstance (GameScene gameScene){
        instance = gameScene;
    }

    public static void updateTurn() {
        turn.setText("Turn: " + GameController.getInstance().getTurn());
    }

    private void addKeyEventHandler(Stage stage) {
        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.setScene(PauseScene.getInstance(stage));
                Sound.effectSound("/sound/SwordHit.mp3");
            }
        });
    }
}
