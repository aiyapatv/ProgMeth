package Scenes;

import Utils.Download;
import Utils.FrameRate;
import Utils.Images;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChooseScene extends Scene {

    private static GridPane root;

    private static GridPane charTable;
    private static StackPane selectedBlock;
    private static Rectangle selectedChar;
    private static Text selectedName;
    private static boolean isSelected = false;
    private static Thread charMoving;
    public ChooseScene(Stage stage) {
        super(createChooseScene(stage), 800, 600);
    }

    private static GridPane createChooseScene(Stage stage){
        root = new GridPane(10,10);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        root.setPadding(new Insets(10));
        root.setGridLinesVisible(true);
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(40);
        c1.setHalignment(HPos.CENTER);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(60);
        c2.setHalignment(HPos.CENTER);
        root.getColumnConstraints().addAll(c1, c2);
        initializeHeader();
        initializePreviewChar();
        initializeCharTable();
        return root;
    }
    private static void initializeCharTable(){
        charTable = new GridPane(2,2);
        charTable.setAlignment(Pos.CENTER);
        for(int i = 0;i < 9; i++){
            final int num = i + 1;
            StackPane stack = new StackPane();
            Rectangle block = new Rectangle(100,100,Color.LIGHTBLUE);
            ImageView imageView = Images.setImageViewSize(Download.loadImage("character/c"+ num +".png"), 100, 100);
            stack.getChildren().addAll(block, imageView);
            stack.setOnMouseClicked(event -> {
                selectChar(stack, block);
                showCharModel(num);
            });
            charTable.add(stack, i % 3, i / 3);
        }
        root.add(charTable, 1, 1);
    }

    private static void selectChar(StackPane stack, Rectangle block){
        if(selectedBlock != null) {
            ((Rectangle)selectedBlock.getChildren().get(0)).setStroke(null);
            isSelected = false;
            try {
                charMoving.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        isSelected = true;
        block.setStrokeType(StrokeType.INSIDE);
        block.setStroke(Color.RED);
        block.setStrokeWidth(5);
        selectedBlock = stack;
    }

    private static void initializeHeader(){
        Text header = new Text("Select Character");
        header.setFont(Download.loadFont("font/pixeboyFont.ttf", 50));
        root.add(header, 0, 0, 2, 1);
    }

    private static void initializePreviewChar(){
        VBox selectedBlock = new VBox(10);
        selectedChar = new Rectangle(100,100,null);
        selectedName = new Text();
        selectedBlock.setAlignment(Pos.CENTER);
        selectedBlock.getChildren().addAll(selectedChar,selectedName);
        root.add(selectedBlock, 0, 1);
    }
    private static void showCharModel(int num){
        ImagePattern image2 = new ImagePattern(Download.loadImage("character/c" + num + "_" + 4 +".png"));
        ImagePattern image3 = new ImagePattern(Download.loadImage("character/c" + num + "_" + 5 +".png"));
        selectedName.setFont(Download.loadFont("font/pixeboyFont.ttf", 50));
        selectedName.setText("Test");
        charMoving = new Thread(() -> {
            FrameRate frameRate = new FrameRate(500);
            while (isSelected) {
                ImagePattern currentImage;
                if(frameRate.getFrame() == 1) currentImage = image2;
                else currentImage = image3;
                Platform.runLater(() ->
                        selectedChar.setFill(currentImage)
                );
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        charMoving.start();
    }
}
