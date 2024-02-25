package Board;

import javafx.scene.layout.AnchorPane;

public class HexagonBoard extends AnchorPane {
    private static HexagonBoard instance;

    public HexagonBoard(){

        for(int i = 22;i >= 0;i--) {
            if(i%2 == 1) {
                for (int l = 0; l <= 10; l++) {
                    HexagonTile tile = new HexagonTile((int) ((i-1)*10.5)+10+l,false, "element/test3.png");
                    AnchorPane.setTopAnchor(tile, i * 25.0);
                    AnchorPane.setLeftAnchor(tile, l * 37.5 * 2);
                    getChildren().add(tile);
                }
            }else{
                for (int l = 0; l < 10; l++) {
                    HexagonTile tile = new HexagonTile((int) (i*10.5)+l, false, "element/test3.png");
                    AnchorPane.setTopAnchor(tile, i * 25.0);
                    AnchorPane.setLeftAnchor(tile, 37.5 + l * 37.5 * 2);
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
