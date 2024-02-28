package Scenes;

import Utils.ToolKit;
import Utils.FrameRate;
import Utils.Images;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoadingScene extends Scene {
    private static VBox root = new VBox();
    private static boolean isStart = false;

    public LoadingScene(Stage stage) {
        super(root, 800, 600);
        createLoadingScene();
        prepareGameScene(stage);
    }

    public static void createLoadingScene() {
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        new Thread(() -> {
            FrameRate frameRate = new FrameRate(500);
            int num = ChooseScene.getNumber();
            ImageView image1 = Images.setImageViewSize(ToolKit.loadImage("character/c" + num + "_3.png"),50,50);
            ImageView image2 = Images.setImageViewSize(ToolKit.loadImage("character/c" + num + "_4.png"),50,50);
            while (!isStart) {
                Platform.runLater(() -> {
                    root.getChildren().clear();
                    int frame = frameRate.getFrame();
                    if(frame == 1){
                        root.getChildren().addAll(image1, createLoadingText(frame));
                    }else{
                        root.getChildren().addAll(image2, createLoadingText(frame));
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

    private static Text createLoadingText(int dotNum){
        Text text = new Text();
        text.setText("Loading" + ".".repeat(dotNum));
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
    public static void setRoot(VBox root) {
        LoadingScene.root = root;
    }

    public static boolean isIsStart() {
        return isStart;
    }

    public static void setIsStart(boolean isStart) {
        LoadingScene.isStart = isStart;
    }
}
