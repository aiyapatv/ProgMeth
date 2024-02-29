package Scenes;

import Utils.ToolKit;
import Utils.FrameRate;
import Utils.Images;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private static StackPane selectBlock;
    private static Rectangle selectedChar;
    private static Text selectedName;
    private static boolean isSelected = false;
    private static Button backButton;
    private static Button playButton;
    private static Thread charMoving;
    public static int number;
    public ChooseScene(Stage stage) {
        super(createChooseScene(stage), 800, 600);
    }

    private static GridPane createChooseScene(Stage stage){
        root = new GridPane(10,10);
        root.setBackground(new Background(new BackgroundImage(ToolKit.loadImage("background/Background3.png"), null, null,null,new BackgroundSize(800,600,false,false,false,false))));
//        root.setBackground(Background.fill(Color.WHITESMOKE));
        root.setPadding(new Insets(10));
        root.setGridLinesVisible(true);

        setConstraint();
        initializeHeader();
        initializePreviewChar();
        initializeCharTable();
        initializePlayButton(stage);
        initializeBackButton(stage);

        return root;
    }

    private static void initializeCharTable(){
        selectBlock = null;
        charTable = new GridPane(2,2);
        charTable.setAlignment(Pos.CENTER);
        for(int i = 0;i < 9; i++){
            final int num = i + 1;
            StackPane stack = new StackPane();
            Rectangle block = new Rectangle(100,100,Color.BEIGE);
            ImageView imageView = Images.setImageViewSize(ToolKit.loadImage("character/c"+ num +".png"), 100, 100);
            stack.getChildren().addAll(block, imageView);
            stack.setOnMouseClicked(event -> {
                selectChar(stack, block);
//                showCharModel(num);
            });
            charTable.add(stack, i % 3, i / 3);
        }
        root.add(charTable, 1, 1);
    }

    private static void selectChar(StackPane stack, Rectangle block){
        if(selectBlock != null) {
            ((Rectangle)selectBlock.getChildren().get(0)).setStroke(null);
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
        selectBlock = stack;
    }

    private static void initializeHeader(){
        Text header = new Text("Select Character");
        header.setFill(Color.WHITESMOKE);
        header.setFont(ToolKit.loadFont("font/pixeboyFont.ttf", 50));
        root.add(header, 0, 0, 2, 1);
    }

    private static void initializePreviewChar(){
        VBox selectBlock = new VBox(10);
        selectedChar = new Rectangle(100,100,null);
        selectedName = new Text();
        selectBlock.setAlignment(Pos.CENTER);
        selectBlock.getChildren().addAll(selectedChar,selectedName);
        root.add(selectBlock, 0, 1);
    }

    private static void showCharModel(int num){
        ImagePattern image2 = new ImagePattern(ToolKit.loadImage("character/c" + num + "_" + 1 +".png"));
        ImagePattern image3 = new ImagePattern(ToolKit.loadImage("character/c" + num + "_" + 2 +".png"));
        setNumber(num);
        selectedName.setFont(ToolKit.loadFont("font/pixeboyFont.ttf", 50));
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
            selectedChar.setFill(null);
        });
        charMoving.start();
    }

    private static void initializePlayButton(Stage stage){
        playButton = ToolKit.createButton("PLAY>>>", "element/shortBox.png",25);
        root.add(playButton,1,3);
        playButton.setOnMouseClicked( event -> {
            if(selectBlock != null) {
                stage.setScene(new LoadingScene(stage));
            }
        });
    }

    private static void initializeBackButton(Stage stage){
        backButton = ToolKit.createButton("<<<BACK", "element/shortBox.png",25);
        root.add(backButton,0,3);
        backButton.setOnMouseClicked( event -> {
            isSelected = false;
            stage.setScene(new StartScene(stage));
        });
    }

    private static void setConstraint(){
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(40);
        c1.setHalignment(HPos.CENTER);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(60);
        c2.setHalignment(HPos.CENTER);
        root.getColumnConstraints().addAll(c1, c2);
    }

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        ChooseScene.number = number;
    }
}
