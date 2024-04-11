package Board;

import Scenes.BattleScene;
import Scenes.ChooseScene;
import Utils.ToolKit;
import Utils.Images;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.game.GameController;

public class HexagonTile extends StackPane {
    private int q;
    private int r;
    private double top;
    private double left;
    private boolean isWalked;
    private final ImageView charImage;

    public HexagonTile(double top, double left, int q, int r, String image, int size, Stage stage){
        charImage = Images.setImageViewSize(ToolKit.loadImage("character/c"+ ChooseScene.getNumber() +"_1.png"), 50, 50);
        setTop(top);
        setLeft(left);
        setQ(q);
        setR(r);
        setIsWalked(false);
        createHexagon(image, size, stage);
    }

    public void createHexagon(String image, int size, Stage stage){
        ImageView hexagonImage = Images.setImageViewSize(ToolKit.loadImage(image), size, size);
        getChildren().add(hexagonImage);
        hexagonImage.setEffect(new InnerShadow(5,Color.GRAY));
        setOnMouseEntered(mouseEvent -> {
            hexagonImage.setEffect(new Glow(0.5));
        });
        setOnMouseClicked(mouseEvent -> {
            setOnMouseEntered(null);
            setOnMouseExited(null);
            hexagonImage.setEffect(new Glow(0.5));
            setOnMouseClicked(null);
            stage.setScene(new BattleScene(stage));
        });
        setOnMouseExited(mouseEvent -> {
            hexagonImage.setEffect(new InnerShadow(5,Color.GRAY));
        });
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public boolean isWalked() {
        return isWalked;
    }

    public void setIsWalked(boolean walked) {
        isWalked = walked;
    }

    public void setTop(double top) {
        this.top = top;
    }

    public void setLeft(double left) {
        this.left = left;
    }
}
