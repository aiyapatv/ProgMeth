package Scenes;

import Utils.FrameRate;
import Utils.Images;
import Utils.ToolKit;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class BattleScene extends Scene {
    private static GridPane root;
    private static boolean isEnd = true;
    private static Rectangle blockPlayer;
    private static GridPane fightPane;
    private static Button attackButton;
    private static Button inventoryButton;
    private static Button escapeButton;
    private static HBox actionBox;
    private static HBox inventoryBox;
    private static Button backtoactionButton;

    public BattleScene(Stage stage) {
        super(createBattleScene(stage), 800, 600);
    }
    private static GridPane createBattleScene(Stage stage){
        root = new GridPane(2,2);
        root.setBackground(new Background(new BackgroundImage(ToolKit.loadImage("background/bg12.jpg"), null, null,null,new BackgroundSize(800,600,false,false,false,false))));
        root.setPadding(new Insets(10));
        root.setGridLinesVisible(true);

        setConstraint();

        initializeStatusBar();
        initializeTurnBased();
        initializeFightPane();
        initializeActionBar(stage);

        return root;
    }
    private static void initializeStatusBar(){
        HBox boxStatus = new HBox();
        StackPane stack = new StackPane();
        Rectangle block = new Rectangle(100,100, null);

//      int num = ChooseScene.getNumber();
        ImageView imageView = Images.setImageViewSize(ToolKit.loadImage("character/c"+ 1 +".png"), 50, 50);
        ImageView imageView1 = Images.setImageViewSize(ToolKit.loadImage("element/ui1.png"), 60, 60);
        ImageView imageView3 = Images.setImageViewSize(ToolKit.loadImage("element/ui2.png"), 30, 100);
        stack.getChildren().addAll(block, imageView1, imageView);
        boxStatus.getChildren().addAll(stack , imageView3 );
        root.add(boxStatus,0,2);
    }

    private static void initializeTurnBased(){
        HBox turnBased = new HBox();
        turnBased.setAlignment(Pos.CENTER);
        turnBased.setSpacing(5);

        for(int i = 0;i < 7; i++){
            final int num = i + 1;
            StackPane stack = new StackPane();
            Rectangle block = new Rectangle(50,50,null);
            block.setStroke(Color.BLACK);
            ImageView imageView = Images.setImageViewSize(ToolKit.loadImage("character/c"+ 1 +"_1.png"), 50, 50);
            stack.getChildren().addAll(block, imageView);

            turnBased.getChildren().add(stack);
        }
        root.add(turnBased, 1, 0);
    }

    private static void initializePlayerSide(){
        blockPlayer = new Rectangle(80,80);
        ImagePattern player = new ImagePattern(ToolKit.loadImage("character/c"+ 1 +"_3.png"));
        blockPlayer.setFill(player);
        showModelPlayer();
        fightPane.add(blockPlayer , 0,1);
    }

    private static void initializeMonsterSide(){
        GridPane monster = new GridPane();
        monster.setGridLinesVisible(true);

        Rectangle rectangle1 = new Rectangle(60,60);
        Rectangle rectangle2 = new Rectangle(60,60);
        Rectangle rectangle3 = new Rectangle(60,60);
        ImagePattern monster1 = new ImagePattern(ToolKit.loadImage("monster/m1_i_1.png"));
        ImagePattern monster2 = new ImagePattern(ToolKit.loadImage("monster/m8_i_1.png"));
        ImagePattern monster3 = new ImagePattern(ToolKit.loadImage("monster/m6_i_1.png"));
        rectangle1.setFill(monster1);
        rectangle2.setFill(monster2);
        rectangle3.setFill(monster3);

        showModelMonster(rectangle1,1);
        showModelMonster(rectangle2,8);
        showModelMonster(rectangle3,6);

        monster.add(rectangle1,0,0);
        monster.add(rectangle2,1,1);
        monster.add(rectangle3,2,2);

        monster.setAlignment(Pos.BOTTOM_RIGHT);

        fightPane.add(monster , 1,0);
    }

    private static void initializeFightPane(){
        fightPane = new GridPane();
        fightPane.setGridLinesVisible(true);
        initializePlayerSide();
        initializeMonsterSide();

        fightPane.getRowConstraints().addAll(ToolKit.setRowCon(75,VPos.CENTER),ToolKit.setRowCon(25,null));
        fightPane.getColumnConstraints().addAll(ToolKit.setColumnCon(50,null) , ToolKit.setColumnCon(50,null));

        root.add(fightPane,1,1);

    }
    private static void showModelPlayer(){
        int num = ChooseScene.getNumber();
        ImagePattern image2 = new ImagePattern(ToolKit.loadImage("character/c" + num + "_" + 4 +".png"));
        ImagePattern image3 = new ImagePattern(ToolKit.loadImage("character/c" + num + "_" + 3 +".png"));

        Thread playerMoving = new Thread(() -> {
            FrameRate frameRate = new FrameRate(500);
            while (isEnd) {
                ImagePattern currentImage;
                if(frameRate.getFrame() == 1) currentImage = image2;
                else currentImage = image3;
                Platform.runLater(() ->
                        blockPlayer.setFill(currentImage)
                );
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        playerMoving.start();
    }
    private static void showModelMonster(Rectangle rectangle,int num){
        ImagePattern image2 = new ImagePattern(ToolKit.loadImage("monster/m" + num + "_i_1.png"));
        ImagePattern image3 = new ImagePattern(ToolKit.loadImage("monster/m" + num + "_i_2.png"));

        Thread monsterMoving = new Thread(() -> {
            FrameRate frameRate = new FrameRate(500);
            while (isEnd) {
                ImagePattern currentImage;
                if(frameRate.getFrame() == 1) currentImage = image2;
                else currentImage = image3;
                Platform.runLater(() ->
                        rectangle.setFill(currentImage)
                );
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        monsterMoving.start();
    }

    private static Button initializeAttackButton(){
        attackButton = new Button("Attack");
        return attackButton;
    }
    private static Button initializeInventoryButton(){
        inventoryButton = new Button("Inventory");
        inventoryButton.setOnMouseClicked(event -> {
            initializeInventoryList();
        });
        return inventoryButton;
    }
    private static void initializeBackToActionButton(){
        backtoactionButton = new Button("Back");
        inventoryBox.getChildren().add(backtoactionButton);

        backtoactionButton.setOnMouseClicked(event -> {
            root.getChildren().remove(root.getChildren().size() - 1);
        });
    }
    private static void initializeInventoryList(){
        inventoryBox = new HBox();
        inventoryBox.setBackground(Background.fill(Color.WHITESMOKE));
        root.add(inventoryBox,1,2);
        initializeBackToActionButton();
    }
    private static Button initializeEscapeButton(Stage stage){
        escapeButton = new Button("Escape");
        escapeButton.setOnMouseClicked(event -> {
            stage.setScene(new EscapeScene(stage));
        });
        return escapeButton;
    }

    private static void initializeActionBar(Stage stage){
        actionBox = new HBox();
        actionBox.getChildren().addAll(initializeAttackButton(),initializeInventoryButton(), initializeEscapeButton(stage));

        root.add(actionBox,1,2);
    }


    private static void setConstraint(){
        root.getColumnConstraints().addAll(ToolKit.setColumnCon(30,HPos.CENTER),
                ToolKit.setColumnCon(50,HPos.CENTER) , ToolKit.setColumnCon(20,HPos.CENTER));

        root.getRowConstraints().addAll(ToolKit.setRowCon(20,VPos.BOTTOM),
                ToolKit.setRowCon(60,VPos.CENTER),ToolKit.setRowCon(20,VPos.BOTTOM));
    }
}
