package Board;

import Scenes.BattleScene;
import Scenes.ChooseScene;
import Scenes.GameScene;
import Utils.Sound;
import Utils.ToolKit;
import Utils.Images;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.game.GameController;
import logic.monsters.Monster;

public class HexagonTile extends StackPane {
    private boolean isWalked;
    private final ImageView charImage;

    public HexagonTile(String image, int size, Stage stage, boolean isWalked){
        charImage = Images.setImageViewSize(ToolKit.loadImage("character/c"+ ChooseScene.getNumber() +"_1.png"), 50, 50);
        setIsWalked(isWalked);
        createHexagon(image, size, stage);
    }

    public void createHexagon(String image, int size, Stage stage){
        ImageView hexagonImage = Images.setImageViewSize(ToolKit.loadImage(image), size, size);
        getChildren().add(hexagonImage);
        if(!isWalked) {
            setOnMouseEntered(mouseEvent -> {
                hexagonImage.setEffect(new Glow(0.5));
            });
            setOnMouseClicked(mouseEvent -> {
                setOnMouseEntered(null);
                setOnMouseExited(null);
                hexagonImage.setEffect(new Glow(0.5));
                setOnMouseClicked(null);
                GameController.getInstance().increaseTurn();
                GameScene.updateTurn();
                Monster.setTime(GameController.getInstance().getTurn());
                stage.setScene(new BattleScene(stage));

            });
            setOnMouseExited(mouseEvent -> {
                hexagonImage.setEffect(new InnerShadow(5, Color.GRAY));
            });
        }else{
            hexagonImage.setEffect(new InnerShadow(100,Color.GRAY));
        }
    }

    public boolean isWalked() {
        return isWalked;
    }

    public void setIsWalked(boolean walked) {
        isWalked = walked;
    }
}
