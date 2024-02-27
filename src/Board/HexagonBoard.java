package Board;

import javafx.application.Platform;
import javafx.scene.CacheHint;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;

public class HexagonBoard extends AnchorPane {
    private static HexagonBoard instance;
    private static final int HEXAGONSIZE = 94;

    public HexagonBoard(){
        for(int i = 13;i >= -1;i--) {
            if(i%2 == 1 || i == -1) {
                for (int l = -1; l <= 6; l++) {
                    HexagonTile tile = new HexagonTile((i * 6) + l,false, "element/test3.png", HEXAGONSIZE);
                    AnchorPane.setTopAnchor(tile, i * HEXAGONSIZE / 2.0);
                    AnchorPane.setLeftAnchor(tile, l * HEXAGONSIZE * 3 / 2.0);
                    getChildren().add(tile);
                }
            }else{
                for (int l = -1; l < 6; l++) {
                    HexagonTile tile = new HexagonTile((i * 5) + l, false, "element/test3.png", HEXAGONSIZE);
                    AnchorPane.setTopAnchor(tile, i * HEXAGONSIZE / 2.0);
                    AnchorPane.setLeftAnchor(tile, HEXAGONSIZE * 3 / 4 + l * HEXAGONSIZE * 3 / 2.0);
                    getChildren().add(tile);
                }
            }
        }
    }

    public static HexagonBoard getInstance(){
        if(instance == null){
            instance = new HexagonBoard();
        }
        return instance;
    }
}
