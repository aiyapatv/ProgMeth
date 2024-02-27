package Scenes;

import Utils.ToolKit;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.*;
//import main.MusicController;

public class StartScene extends Scene {
    private static GridPane root;
    private static ImageView logoImageView;
    private static Button btnNewGame;
    private static Button btnLoadGame;
    private static Button btnSetting;
    private static Button btnHowToPlay;
    private static Button btnExit;
    private static VBox centerBox;


    public StartScene(Stage stage) {
        super(createIntroScene(stage), 800, 600);
    }
    private static GridPane createIntroScene(Stage stage){
        root = new GridPane();
        root.setBackground(new Background(new BackgroundImage(ToolKit.loadImage("background/Map.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(800, 600,false,false, false,false))));
        root.setPadding(new Insets(100, 20, 20, 70));
        root.setVgap(10);
        root.setHgap(50);

        initializeCenterBox(stage);
        root.setAlignment(Pos.BASELINE_LEFT);

        return root;
    }
    private static ImageView initializeLogo(){
        logoImageView = new ImageView(ToolKit.loadImage("element/characterB.png"));
        logoImageView.setFitHeight(150);
        logoImageView.setFitWidth(300);

        return logoImageView;
    }

    private static Button initializeNewGameButton(Stage stage){
        btnNewGame = ToolKit.createButton("New Game", "element/shortBox.png",25);
        btnNewGame.setOnMouseClicked(event -> {
            stage.setScene(new ChooseScene(stage));
        });
        return btnNewGame;
    }

    private static Button initializeLoadGameButton(){
        btnLoadGame = ToolKit.createButton("Load Game", "element/shortBox.png",25);
        return btnLoadGame;
    }

    private static Button initializeSettingButton(){
        btnSetting = ToolKit.createButton("Setting", "element/shortBox.png", 25);
        return btnSetting;
    }

    private static Button initializeHowToPlayButton(){
         btnHowToPlay = ToolKit.createButton("How To Play", "element/shortBox.png", 25);
         return btnHowToPlay;
    }

    private static Button initializeExitButton(Stage stage){
        btnExit = ToolKit.createButton("Exit", "element/shortBox.png", 25);
        btnExit.setOnMouseClicked(event -> {
            stage.close();
        });

        return btnExit;
    }

    private static void initializeCenterBox(Stage stage){
        centerBox = new VBox(10);
        centerBox.setSpacing(15);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(initializeLogo(), initializeNewGameButton(stage), initializeLoadGameButton(),initializeSettingButton(), initializeHowToPlayButton(), initializeExitButton(stage));
        root.add(centerBox, 0, 1);
    }




    private static Background createBackground(){
        return new Background((BackgroundFill) null);
    }

}