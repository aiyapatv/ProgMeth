package Board;

import javafx.application.Platform;
import javafx.scene.CacheHint;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HexagonBoard extends AnchorPane {
    private static HexagonBoard instance;
    private static final int HEXAGONSIZE = 94;

    public HexagonBoard(Stage stage){
        HexagonTile tile;
        int q;
        int r = 6;
        for(int i = 13;i >= -1;i--) {
            double top = i * HEXAGONSIZE / 2;
            if(i%2 == 1 || i == -1) {
                q = -7;
                for (int l = -1; l <= 6; l++, q += 2) {
                    double left = l * HEXAGONSIZE * 3 / 2.0;
                    tile = new HexagonTile(top, left, q,r - l,"element/test3.png", HEXAGONSIZE, stage);
                    AnchorPane.setTopAnchor(tile, top);
                    AnchorPane.setLeftAnchor(tile, left);
                    getChildren().add(tile);
                }
            }else{
                q = -6;
                r--;
                for (int l = -1; l < 6; l++, q += 2) {
                    double left = HEXAGONSIZE * 3 / 4 + l * HEXAGONSIZE * 3 / 2.0;
                    tile = new HexagonTile(top, left, q,r - l,"element/test3.png", HEXAGONSIZE, stage);
                    AnchorPane.setTopAnchor(tile, top);
                    AnchorPane.setLeftAnchor(tile, left);
                    getChildren().add(tile);
                }
            }
        }
    }
}
