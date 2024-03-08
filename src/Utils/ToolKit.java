package Utils;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class ToolKit {
    public static Image loadImage(String imagePath){
        String image_path = ClassLoader.getSystemResource(imagePath).toString();
        return new Image(image_path);
    }

    public static Font loadFont(String fontPath, int Size){
        String font_path = ClassLoader.getSystemResource(fontPath).toExternalForm();
        return Font.loadFont(font_path, Size);
    }
    public static Button createButton(String string, String imagePath, String imagePath2, int FontSize) {
        Button button = new Button(string);
        Font buttonFont = ToolKit.loadFont("font/pixeboyFont.ttf", FontSize);
        button.setFont(buttonFont);
        button.setOnMouseEntered(event -> {
            button.setCursor(Cursor.HAND);
            if ( imagePath2 != null ) button.setStyle("-fx-background-color: transparent;" + "-fx-background-image: url(" + imagePath2 + ");" + "-fx-background-size: contain;");
        });
        button.setOnMouseExited(event -> {
            button.setCursor(Cursor.DEFAULT);
            button.setStyle("-fx-background-color: transparent;" + "-fx-background-image: url(" + imagePath + ");" + "-fx-background-size: contain;");

        });
        button.setAlignment(Pos.CENTER);
        button.setPrefWidth(180);
        button.setPrefHeight(35);
        button.setStyle("-fx-background-color: transparent;" + "-fx-background-image: url(" + imagePath + ");" + "-fx-background-size: contain;");
        return button;
    }

    public static ColumnConstraints setColumnCon(int num , HPos hPos ){
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(num);
        columnConstraints.setHalignment(hPos);
        return columnConstraints;
    }

    public static RowConstraints setRowCon(int num , VPos vPos){
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(num);
        rowConstraints.setValignment(vPos);
        return rowConstraints;
    }
}

