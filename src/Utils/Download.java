package Utils;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class Download {
    public static Image loadImage(String imagePath){
        String image_path = ClassLoader.getSystemResource(imagePath).toString();
        return new Image(image_path);
    }

    public static Font loadFont(String fontPath, int Size){
        String font_path = ClassLoader.getSystemResource(fontPath).toExternalForm();
        return Font.loadFont(font_path, Size);
    }
}
