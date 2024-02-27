package Board;

import Utils.ToolKit;
import Utils.Images;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class HexagonTile extends StackPane {
    private int number;
    private boolean isWalked;

    public HexagonTile(int number, boolean isWalked, String image, int size){
        setNumber(number);
        setIsWalked(false);
        createHexagon(image, size);
    }

    public void createHexagon(String image, int size){
        ImageView hexagonImage = Images.setImageViewSize(ToolKit.loadImage(image), size, size);
        getChildren().add(hexagonImage);
        getChildren().add(new Label(Integer.toString(number)));
        setOnMouseEntered(mouseEvent -> {
            hexagonImage.setEffect(new Glow(0.5));
        });
        setOnMouseExited(mouseEvent -> {
            hexagonImage.setEffect(null);
        });
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isWalked() {
        return isWalked;
    }

    public void setIsWalked(boolean walked) {
        isWalked = walked;
    }

}
