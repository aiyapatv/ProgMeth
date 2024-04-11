package Scenes;

import Board.HexagonBoard;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.game.GameController;

public class GameScene extends Scene {
    private static GameScene instance;

    private static Text turn;
    public GameScene(Stage stage) {
        super(createGameScene(stage), 800, 600);
    }
    public static AnchorPane createGameScene(Stage stage){
        AnchorPane root = new HexagonBoard(stage);
        turn = new Text();
        AnchorPane.setTopAnchor(turn, 50.0);
        AnchorPane.setLeftAnchor(turn, 50.0);
        turn.setText("Turn: " + GameController.getInstance().getTurn());
        Button test = new Button();
        test.setOnMouseClicked(event -> {
            stage.setScene(StartScene.getInstance(stage));
        });
        root.getChildren().addAll(test, turn);
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
}
