package Board;

import javafx.application.Platform;
import javafx.scene.CacheHint;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HexagonBoard extends AnchorPane {
    private static HexagonBoard instance;
    private static final int HEXAGONSIZE = 120;

    public HexagonBoard(Stage stage){
        HexagonTile tile;
        boolean isWalked;
        for(int i = 9;i >= -1;i--) {
            double top = (double) (i * HEXAGONSIZE) / 2;
            if(i%2 == 1 || i == -1) {
                for (int l = -1; l <= 4; l++) {
                    isWalked = false;
                    if(i == -1 || i == 9 || l == 4){
                        isWalked = true;
                    }
                    double left = (l+0.125) * HEXAGONSIZE * 3 / 2.0;
                    tile = new HexagonTile("element/test3.png", HEXAGONSIZE, stage, isWalked);
                    AnchorPane.setTopAnchor(tile, top);
                    AnchorPane.setLeftAnchor(tile, left);
                    getChildren().add(tile);
                }
            }else{
                for (int l = -1; l < 4; l++) {
                    isWalked = false;
                    if(l == -1){
                        isWalked = true;
                    }
                    double left = HEXAGONSIZE * 3 / 4 + (l + 0.125) * HEXAGONSIZE * 3 / 2.0;
                    tile = new HexagonTile("element/test3.png", HEXAGONSIZE, stage, isWalked);
                    AnchorPane.setTopAnchor(tile, top);
                    AnchorPane.setLeftAnchor(tile, left);
                    getChildren().add(tile);
                }
            }
        }
    }
}
