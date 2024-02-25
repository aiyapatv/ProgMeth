package Scenes;

import Board.HexagonTile;
import Utils.DownloadImage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
//import main.MusicController;

public class StartScene extends Scene {
    public StartScene(Stage stage) {
        super(createIntroScene(stage), 800, 600);
    }
    private static GridPane createIntroScene(Stage stage){

        //Pane
        GridPane root = new GridPane();
        root.setPadding(new Insets(100, 20, 20, 70));
        root.setVgap(10);
        root.setHgap(50);

        //Background
        BackgroundImage setBackground = new BackgroundImage(DownloadImage.loadImage("background/Map.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(800, 600,false,false, false,false));
        Background startSceneBackground = new Background(setBackground);
        root.setBackground(startSceneBackground);

        //Start new game
        Button btnNewGame = createButton("New Game", "element/shortBox.png",25);
        btnNewGame.setOnMouseClicked(event -> {
            stage.setScene(new LoadingScene(stage));
        });

        //Start save game
        Button btnLoadGame = createButton("Load Game", "element/shortBox.png",25);
//        btnLoadGame

        //Setting
        Button btnSetting = createButton("Setting", "element/shortBox.png", 25);

        //how to play
        Button btnHowToPlay = createButton("How To Play", "element/shortBox.png", 25);
//        btnHowToPlay.setOnAction(event -> rules.setVisible(!rules.isVisible()));

        //exit
        Button btnExit = createButton("Exit", "element/shortBox.png", 25);
        btnExit.setOnMouseClicked(event -> {
            stage.close();
        });

        //logo
        ImageView logoImageView = new ImageView(DownloadImage.loadImage("element/characterB.png"));
        logoImageView.setFitHeight(150);
        logoImageView.setFitWidth(300);

        VBox centerBox = new VBox(10);
        centerBox.setSpacing(15);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(logoImageView, btnNewGame, btnLoadGame, btnSetting, btnHowToPlay, btnExit);
        root.add(centerBox, 0, 1);
        root.setAlignment(Pos.BASELINE_LEFT);
        return root;
    }
    private static Button createButton(String string, String imagePath, int FontSize) {
        Button button = new Button(string);
        InputStream is = StartScene.class.getResourceAsStream("/font/Pixeboy-z8XGD.ttf");
        Font buttonFont = Font.loadFont(is, FontSize);
        button.setFont(buttonFont);
        button.setOnMouseEntered(event -> {
            button.setCursor(Cursor.HAND);
        });
        button.setOnMouseExited(event -> {
            button.setCursor(Cursor.DEFAULT);
        });
        button.setAlignment(Pos.CENTER);
        button.setPrefWidth(180);
        button.setPrefHeight(35);
        button.setStyle("-fx-background-color: transparent;" + "-fx-background-image: url(" + imagePath + ");" + "-fx-background-size: cover;");
        return button;
    }
}