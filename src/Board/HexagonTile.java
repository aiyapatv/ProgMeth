package Board;

import Utils.Download;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class HexagonTile extends StackPane {
    private int number;
    private boolean isWalked;

    public HexagonTile(int number, boolean isWalked, String image){
        setNumber(number);
        setIsWalked(false);
        createHexagon(image);
    }

    public void createHexagon(String image){
        ImageView hexagonImage = new ImageView(Download.loadImage(image));
        hexagonImage.setFitWidth(50);
        hexagonImage.setFitHeight(50);
        getChildren().add(hexagonImage);
        getChildren().add(new Label(Integer.toString(number)));
        setOnMouseEntered(mouseEvent -> {
            hexagonImage.setEffect(new Glow(500));
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
