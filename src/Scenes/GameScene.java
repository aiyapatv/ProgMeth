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
    public GameScene(Stage stage) {
        super(createGameScene(stage), 800, 600);
    }
    public static AnchorPane createGameScene(Stage stage){
        AnchorPane root = HexagonBoard.getInstance();
        Button test = new Button();
        test.setOnMouseClicked(event -> {
            stage.setScene(StartScene.getInstance(stage));
        });
        root.getChildren().add(test);
        return root;
    }
    public static GameScene getInstance(Stage stage){
        if(instance == null){
            instance = new GameScene(stage);
        }
        return instance;
    }
}
