package Scenes;

import Utils.Sound;
import Utils.ToolKit;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.skin.SliderSkin;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class SettingScene extends Scene {

    private static int volume = 100;
    public SettingScene(Stage stage) {
        super(createSettingScene(stage), 800, 600);
        addKeyEventHandler(stage);
    }

    public static AnchorPane createSettingScene(Stage stage) {
        AnchorPane root = new AnchorPane();
        root.setBackground(new Background(new BackgroundFill(Color.DARKGRAY,null,null)));
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        // Label for volume control
        Label volumeLabel = new Label("Volume: " + volume);
        volumeLabel.setFont(ToolKit.loadFont(30));

        // Slider for volume adjustment
        Slider volumeSlider = new Slider(0, 100, 100); // min, max, default
        volumeSlider.setMaxWidth(300);
        volumeSlider.setStyle("-fx-background: #000000; -fx-control-inner-background: #D6CFC7;");

        // Add listener to the slider to update volume value
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Update volume value
            volume = newValue.intValue();
            // Adjust media player volume accordingly
            Sound.getMediaPlayer().setVolume(volume/100.0);

            Platform.runLater(() -> {
                volumeLabel.setText("Volume: " + volume);
            });
            // You can implement this part according to your MediaPlayer implementation
        });

        // Add components to the VBox
        vbox.getChildren().addAll(volumeLabel, volumeSlider);
        Label back = new Label("Press ESC to return to the start scene");
        back.setFont(ToolKit.loadFont(20));
        // Set the position of the VBox in the AnchorPane
        vbox.getChildren().add(back);
        AnchorPane.setTopAnchor(vbox, 0.0);
        AnchorPane.setBottomAnchor(vbox, 0.0);
        AnchorPane.setLeftAnchor(vbox, 0.0);
        AnchorPane.setRightAnchor(vbox, 0.0);

        // Add the VBox to the root AnchorPane
        root.getChildren().add(vbox);
        return root;
    }

    private void addKeyEventHandler(Stage stage) {
        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.setScene(StartScene.getInstance(stage));
            }
        });
    }
}
