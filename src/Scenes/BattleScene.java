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
    private static boolean isHit = true;
    private static Rectangle blockPlayer;
    private static GridPane fightPane;
    private static Button attackButton;
    private static Button inventoryButton;
    private static Button escapeButton;
    private static HBox actionBox;
    private static VBox inventoryBox;
    private static VBox monsterBox;
    private static Button backtoactionButton;
    private static Rectangle e1;
    private static Rectangle e2;
    private static Rectangle e3;
    private static Rectangle heal;

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

        int num = ChooseScene.getNumber();
        ImageView imageView = Images.setImageViewSize(ToolKit.loadImage("character/c"+ num +".png"), 50, 50);
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
        heal = new Rectangle(70,70);
        heal.setFill(null);

        ImagePattern player = new ImagePattern(ToolKit.loadImage("character/c"+ 1 +"_3.png"));
        blockPlayer.setFill(player);
        showModelPlayer();
        fightPane.add(blockPlayer , 0,1);
        fightPane.add(heal , 0 ,1 );
    }

    private static void initializeMonsterSide(){
        GridPane monster = new GridPane();
        monster.setGridLinesVisible(true);

        Rectangle rectangle1 = new Rectangle(60,60);
        e1 = new Rectangle(50,50);
        e1.setFill(null);

        Rectangle rectangle2 = new Rectangle(60,60);
        e2 = new Rectangle(50,50);
        e2.setFill(null);

        Rectangle rectangle3 = new Rectangle(60,60);
        e3 = new Rectangle(50,50);
        e3.setFill(null);

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
        monster.add(e1,0,0);

        monster.add(rectangle2,1,1);
        monster.add(e2,1,1);

        monster.add(rectangle3,2,2);
        monster.add(e3,2,2);

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
            FrameRate frameRate = new FrameRate(500,2);
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
            FrameRate frameRate = new FrameRate(500,2);
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
        attackButton.setOnMouseClicked(event -> {
            initializeMonsterList();
        });
        return attackButton;
    }
    private static void initializeMonsterList(){
        monsterBox = new VBox();
        monsterBox.setBackground(Background.fill(Color.WHITESMOKE));
        Button btn1 = new Button("Monster1");
        Button btn2 = new Button("Monster2");
        Button btn3 = new Button("Monster3");
        monsterBox.getChildren().addAll(btn1,btn2,btn3);

        btn1.setOnMouseClicked( event -> {
           showAttackEffect(e1 , "a1" , "a2" , "a3");
           isHit = true;
           root.getChildren().remove(root.getChildren().size() - 1);
        });
        btn2.setOnMouseClicked( event -> {
            showAttackEffect(e2, "a1" , "a2" , "a3");
            isHit = true;
            root.getChildren().remove(root.getChildren().size() - 1);
        });
        btn3.setOnMouseClicked( event -> {
            showAttackEffect(e3, "a1" , "a2" , "a3");
            isHit = true;
            root.getChildren().remove(root.getChildren().size() - 1);
        });
        root.add(monsterBox,1,2);
        initializeBackToActionButton(monsterBox);
    }

    private static void showAttackEffect(Rectangle rectangle , String string ,String string2 , String string3){
        ImagePattern image2 = new ImagePattern(ToolKit.loadImage("effect/" + string + ".png"));
        ImagePattern image3 = new ImagePattern(ToolKit.loadImage("effect/" + string2 + ".png"));
        ImagePattern image4 = new ImagePattern(ToolKit.loadImage("effect/" + string3 + ".png"));

        Thread monsterMoving = new Thread(() -> {
            FrameRate frameRate = new FrameRate(100,4);
            while (isHit) {
                ImagePattern currentImage;
                if(frameRate.getFrame() == 1) currentImage = image2;
                else if(frameRate.getFrame() == 2) currentImage = image3;
                else if(frameRate.getFrame() == 3) currentImage = image4;
                else{
                    currentImage = null;
                    isHit = false;
                }
                Platform.runLater(() -> {
                    rectangle.setFill(currentImage);
                });

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        monsterMoving.start();
    }

    private static Button initializeInventoryButton(){
        inventoryButton = new Button("Inventory");
        inventoryButton.setOnMouseClicked(event -> {
            initializeInventoryList();
        });
        return inventoryButton;
    }
    private static void initializeInventoryList(){
        inventoryBox = new VBox();
        inventoryBox.setBackground(Background.fill(Color.WHITESMOKE));

        Button btn1 = new Button("Heal");

        inventoryBox.getChildren().addAll(btn1);

        btn1.setOnMouseClicked( event -> {
            showAttackEffect(heal ,"h1" , "h2" , "h3");
            isHit = true;
            root.getChildren().remove(root.getChildren().size() - 1);
        });

        root.add(inventoryBox,1,2);
        initializeBackToActionButton(inventoryBox);
    }
    private static void initializeBackToActionButton(VBox vBox){
        backtoactionButton = new Button("Back");
        vBox.getChildren().add(backtoactionButton);

        backtoactionButton.setOnMouseClicked(event -> {
            root.getChildren().remove(root.getChildren().size() - 1);
        });
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
