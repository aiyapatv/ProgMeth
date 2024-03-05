package Scenes;

import Utils.ToolKit;
import Utils.FrameRate;
import Utils.Images;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EscapeScene extends Scene {
    private static VBox root = new VBox();
    private static boolean isStart = false;

    public EscapeScene(Stage stage) {
        super(root, 800, 600);
        createEscapeScene();
        prepareGameScene(stage);
    }

    public static void createEscapeScene() {
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        new Thread(() -> {
            FrameRate frameRate = new FrameRate(500,2);
            int num = ChooseScene.getNumber();
            ImageView image1 = Images.setImageViewSize(ToolKit.loadImage("character/c" + num + "_5.png"),50,50);
            ImageView image2 = Images.setImageViewSize(ToolKit.loadImage("character/c" + num + "_6.png"),50,50);
            ImageView image3 = Images.setImageViewSize(ToolKit.loadImage("monster/m1_r_0.png"),50,50);
            ImageView image4 = Images.setImageViewSize(ToolKit.loadImage("monster/m1_r_1.png"),50,50);
            HBox h1 = new HBox();
            h1.getChildren().addAll(image1,image3);
            HBox h2 = new HBox();
            h2.getChildren().addAll(image2,image4);
            h2.setSpacing(30);
            h1.setAlignment(Pos.CENTER);
            h1.setSpacing(30);
            h2.setAlignment(Pos.CENTER);
            while (!isStart) {
                Platform.runLater(() -> {
                    root.getChildren().clear();
                    int frame = frameRate.getFrame();
                    if(frame == 1){
                        root.getChildren().addAll(h1, createEscapeText(frame));
                    }else{
                        root.getChildren().addAll(h2, createEscapeText(frame));
                    }
                });
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private static Text createEscapeText(int dotNum){
        Text text = new Text();
        text.setText("Escape" + "..".repeat(dotNum));
        text.setFont(ToolKit.loadFont("font/pixeboyFont.ttf", 30));
        return text;
    }


    private void prepareGameScene(Stage stage) {
        new Thread(() -> {
            GameScene gameScene = new GameScene(stage);
            Platform.runLater(() -> {
                stage.setScene(gameScene);
                isStart = true;
            });
        }).start();
    }
}
