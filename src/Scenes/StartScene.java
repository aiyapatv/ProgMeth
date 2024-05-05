package Scenes;

import Utils.Sound;
import Utils.ToolKit;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.*;
import logic.game.GameController;
//import main.MusicController;

public class StartScene extends Scene{
    private static Scene instance;
    private static GridPane root;
    private static ImageView logoImageView;
    private static Button btnNewGame;
    private static Button btnSetting;
    private static Button btnHowToPlay;
    private static Button btnExit;
    private static VBox centerBox;


    public StartScene(Stage stage) {
        super(createIntroScene(stage), 800, 600);
    }
    private static GridPane createIntroScene(Stage stage){
        root = new GridPane();
        root.setBackground(new Background(new BackgroundImage(ToolKit.loadImage("background/bgStartScene.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(800, 600,false,false, false,false))));
        root.setPadding(new Insets(100, 20, 20, 70));
        root.setVgap(10);
        root.setHgap(50);
        initializeCenterBox(stage);
        root.setAlignment(Pos.BASELINE_LEFT);

        Sound.backgroundSound("/sound/StartScene.mp3");

        return root;
    }
    private static ImageView initializeLogo(){
        logoImageView = new ImageView(ToolKit.loadImage("element/Logo.jpg"));
        logoImageView.setFitHeight(200);
        logoImageView.setFitWidth(200);

        return logoImageView;
    }

    private static Button initializeNewGameButton(Stage stage){
        btnNewGame = ToolKit.createButton("New Game", "button/yellowResize1.png", "button/yellowResize2.png",25);
        btnNewGame.setOnMouseClicked(event -> {
            GameController.setInstance(new GameController());
            stage.setScene(new ChooseScene(stage));

            Sound.backgroundSound("/sound/ClickButton.mp3");
        });
        return btnNewGame;
    }

    private static Button initializeSettingButton(Stage stage){
        btnSetting = ToolKit.createButton("Setting", "button/yellowResize1.png", "button/yellowResize2.png", 25);
        btnSetting.setOnMouseClicked(event -> {
            stage.setScene(new SettingScene(stage));

            Sound.backgroundSound("/sound/ClickButton.mp3");
        });
        return btnSetting;
    }

    private static Button initializeHowToPlayButton(Stage stage){

         btnHowToPlay = ToolKit.createButton("How To Play", "button/yellowResize1.png", "button/yellowResize2.png", 25);
         btnSetting.setOnMouseClicked(event -> {

            Sound.backgroundSound("/sound/ClickButton.mp3");
         });
         return btnHowToPlay;
    }

    private static Button initializeExitButton(Stage stage){
        btnExit = ToolKit.createButton("Exit", "button/yellowResize1.png", "button/yellowResize2.png", 25);
        btnExit.setOnMouseClicked(event -> {
            stage.close();

            Sound.backgroundSound("/sound/ClickButton.mp3");
        });
        return btnExit;
    }
    private static void initializeCenterBox(Stage stage){
        centerBox = new VBox(10);
        centerBox.setSpacing(15);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(
                initializeLogo(),
                initializeNewGameButton(stage),initializeSettingButton(stage), initializeHowToPlayButton(stage), initializeExitButton(stage));
        root.add(centerBox, 0, 1);
    }

    public static Scene getInstance(Stage stage){
        if(instance == null){
            instance = new StartScene(stage);
        }
        return instance;
    }
    private static Background createBackground(){
        return new Background((BackgroundFill) null);
    }

}

