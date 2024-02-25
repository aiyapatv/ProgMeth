package Scenes;

import Utils.Download;
import Utils.FrameRate;
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
            while (!isStart) {
                ImageView image1 = new ImageView(Download.loadImage("element/813.png"));
                ImageView image2 = new ImageView(Download.loadImage("element/913.png"));
                image1.setFitHeight(50);
                image1.setFitWidth(50);
                image2.setFitHeight(50);
                image2.setFitWidth(50);
                Platform.runLater(() -> {
                    root.getChildren().clear();
                    int frame = frameRate.getFrame();
                    if(frame == 1){
                        root.getChildren().addAll(image1, createLoadingText(frame));
                    }else{
                        root.getChildren().addAll(image2, createLoadingText(frame));
                    }
                });
            }
        }).start();
    }

    private static Text createLoadingText(int dotNum){
        Text text = new Text();
        text.setText("Loading" + ".".repeat(dotNum));
        text.setFont(Download.loadFont("font/Pixeboy-z8XGD.ttf", 30));
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
