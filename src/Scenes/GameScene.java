package Scenes;

import Board.HexagonBoard;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameScene extends Scene {
    public GameScene(Stage stage) {
        super(createGameScene(stage), 800, 600);
    }
    public static AnchorPane createGameScene(Stage stage){
        AnchorPane root = HexagonBoard.getInstance();
        return root;
    }
}
