package Utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Images {
    public static ImageView setImageViewSize(Image image, int height, int width){
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }
}
