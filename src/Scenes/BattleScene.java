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
        blockPlayer = new Rectangle(90,90);
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
        ImagePattern monster1 = new ImagePattern(ToolKit.loadImage("monster/m1_i_0.png"));
        ImagePattern monster2 = new ImagePattern(ToolKit.loadImage("monster/m8_i_0.png"));
        ImagePattern monster3 = new ImagePattern(ToolKit.loadImage("monster/m6_i_0.png"));
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

        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(50);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(50);

        RowConstraints r1 = new RowConstraints();
        r1.setPercentHeight(69);
        r1.setValignment(VPos.CENTER);
        RowConstraints r3 = new RowConstraints();
        r3.setPercentHeight(31);

        fightPane.getRowConstraints().addAll(r1,r3);
        fightPane.getColumnConstraints().addAll(c1,c2);

        root.add(fightPane,1,1);

    }
    private static void showModelPlayer(){
        int num = 1 ;
//        int num = ChooseScene.getNumber();
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

    private static void setConstraint(){
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(30);
        c1.setHalignment(HPos.CENTER);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(50);
        c2.setHalignment(HPos.CENTER);
        ColumnConstraints c3 = new ColumnConstraints();
        c3.setPercentWidth(20);
        c3.setHalignment(HPos.CENTER);

        RowConstraints r1 = new RowConstraints();
        r1.setPercentHeight(20);
        RowConstraints r2 = new RowConstraints();
        r2.setPercentHeight(70);
        RowConstraints r3 = new RowConstraints();
        r3.setPercentHeight(10);
        r1.setValignment(VPos.BOTTOM);
        r2.setValignment(VPos.CENTER);
        r3.setValignment(VPos.BOTTOM);

        root.getColumnConstraints().addAll(c1, c2 , c3);
        root.getRowConstraints().addAll(r1,r2,r3);
    }
}
