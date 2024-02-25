package Scenes;

import Utils.DownloadImage;
import Utils.FrameRate;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
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
        new Thread(() -> {
            FrameRate frameRate = new FrameRate(500);
            while (!isStart) {
                ImageView image1 = new ImageView(DownloadImage.loadImage("element/813.png"));
                ImageView image2 = new ImageView(DownloadImage.loadImage("element/913.png"));
                image1.setFitHeight(45);
                image1.setFitWidth(50);
                image2.setFitHeight(45);
                image2.setFitWidth(50);
                Platform.runLater(() -> {
                    root.getChildren().clear(); // Clear previous labels
                    if(frameRate.getFrame() == 1){
                        root.getChildren().add(image1);
                    }else{
                        root.getChildren().add(image2);
                    }
                });
            }
        }).start();
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
