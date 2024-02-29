package Board;

import Utils.ToolKit;
import Utils.Images;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class HexagonTile extends StackPane {
    private int q;
    private int r;
    private boolean isWalked;

    public HexagonTile(int q, int r,boolean isWalked, String image, int size){
        setQ(q);
        setR(r);
        setIsWalked(false);
        createHexagon(image, size);
    }

    public void createHexagon(String image, int size){
        ImageView hexagonImage = Images.setImageViewSize(ToolKit.loadImage(image), size, size);
        getChildren().add(hexagonImage);
        getChildren().add(new Label(Integer.toString((Math.abs(q) + Math.abs(-q-r) + Math.abs(-r))/2)));
        setOnMouseEntered(mouseEvent -> {
            hexagonImage.setEffect(new Glow(0.5));
        });
        setOnMouseExited(mouseEvent -> {
            hexagonImage.setEffect(null);
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

}
