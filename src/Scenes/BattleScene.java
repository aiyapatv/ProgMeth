package Scenes;

import Utils.FrameRate;
import Utils.Images;
import Utils.RandomMonster;
import Utils.ToolKit;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.game.GameController;

import java.util.ArrayList;

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
    private static Rectangle monsterBlock;
    private static Rectangle effectBlock;
    private static Rectangle heal;
    private static int totalMonster;
    private static ArrayList<Rectangle> allMonster;
    private static ArrayList<Rectangle> allEffect;
    private static int randomMonster;

    public BattleScene(Stage stage) {
        super(createBattleScene(stage), 800, 600);
    }

    private static BattleScene instance;

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
        VBox description = new VBox();

        Text name = new Text(GameController.getInstance().getCharacter().toString());
        Text maxHp = new Text("Hp " + GameController.getInstance().getCharacter().getHp() + "/100");
        description.getChildren().addAll(name , maxHp);

        ImageView character = Images.setImageViewSize(ToolKit.loadImage("character/c"+ ChooseScene.getNumber() +".png"), 50, 50);
        ImageView imageView1 = Images.setImageViewSize(ToolKit.loadImage("element/ui1.png"), 60, 60);
        ImageView imageView3 = Images.setImageViewSize(ToolKit.loadImage("element/ui2.png"), 30, 100);
        stack.getChildren().addAll(block, imageView1, character);
        boxStatus.getChildren().addAll(stack , imageView3 , description);
        root.add(boxStatus,0,2);
    }

    private static void initializeTurnBased(){
        HBox turnBased = new HBox();
        turnBased.setAlignment(Pos.CENTER);
        turnBased.setSpacing(5);

        for(int i = 0;i < 7; i++){
            StackPane stack = new StackPane();
            Rectangle block = new Rectangle(50,50,null);
            block.setStroke(Color.BLACK);
            ImageView imageView;
            if ( i%2 == 0){
                imageView = Images.setImageViewSize(ToolKit.loadImage("character/c"+ ChooseScene.getNumber() +"_1.png"), 50, 50);
            } else {
                imageView = Images.setImageViewSize(ToolKit.loadImage("character/c"+ 9 +"_1.png"), 50, 50);
            }
            stack.getChildren().addAll(block, imageView);

            turnBased.getChildren().add(stack);
        }
        root.add(turnBased, 1, 0);
    }

    private static void initializePlayerSide(){
        blockPlayer = new Rectangle(80,80);
        heal = new Rectangle(70,70);
        heal.setFill(null);

        ImagePattern player = new ImagePattern(ToolKit.loadImage("character/c"+ ChooseScene.getNumber() +"_3.png"));
        blockPlayer.setFill(player);
        showModelPlayer();
        fightPane.add(blockPlayer , 0,1);
        fightPane.add(heal , 0 ,1 );
    }

    private static void initializeMonsterSide(){
        GridPane monster = new GridPane();
        monster.setGridLinesVisible(true);

        for (int i = 0; i < 3; i++) {
            monster.getColumnConstraints().add(new ColumnConstraints(60));
            monster.getRowConstraints().add(new RowConstraints(60));
        }
        totalMonster = RandomMonster.randomMonsterAmount();

        allMonster = new ArrayList<>(totalMonster);
        allEffect = new ArrayList<>(totalMonster);
        for (int i = 1;i <= totalMonster; i++){
            monsterBlock = new Rectangle(60,60);
            effectBlock = new Rectangle(50,50);
            effectBlock.setFill(null);
            randomMonster = RandomMonster.randomMonsterImage();
            ImagePattern imageMonster = new ImagePattern(ToolKit.loadImage("monster/m" + randomMonster + "_i_1.png"));
            monsterBlock.setFill(imageMonster);

            allMonster.add(monsterBlock);
            allEffect.add(effectBlock);

            showModelMonster(monsterBlock,randomMonster);
            if(i == 3) {
                monster.add(monsterBlock, 0, 0);
                monster.add(effectBlock, 0, 0);
            }else{
                monster.add(monsterBlock, i, i);
                monster.add(effectBlock, i, i);
            }
        }

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
        ImagePattern image2 = new ImagePattern(ToolKit.loadImage("character/c" + ChooseScene.getNumber() + "_" + 4 +".png"));
        ImagePattern image3 = new ImagePattern(ToolKit.loadImage("character/c" + ChooseScene.getNumber() + "_" + 3 +".png"));

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
        attackButton = new Button();
        attackButton = ToolKit.createButton("Attack", "button/blue1.png","button/blue2.png",20);
        setButtonPref(attackButton , 90 , 30);
        attackButton.setOnMouseClicked(event -> {
            initializeMonsterList();
        });
        return attackButton;
    }

    private static void initializeMonsterList(){
        monsterBox = new VBox();
        monsterBox.setBackground(Background.fill(Color.WHITESMOKE));

        for ( int i = 0; i < totalMonster; i++){
            Button btn = ToolKit.createButton("Monster"+(i+1), "button/red1.png","button/red2.png",20);
            setButtonPref(btn, 90 , 30);

            monsterBox.getChildren().add(btn);
            int count = i;
            btn.setOnMouseClicked( event -> {
                showAttackEffect(allEffect.get(count) , allMonster.get(count),"a1" , "a2" , "a3");
                isHit = true;
                root.getChildren().remove(root.getChildren().size() - 1);
            });
        }
        root.add(monsterBox,1,2);
        initializeBackToActionButton(monsterBox);
    }

    private static void showAttackEffect(Rectangle effectBlock ,Rectangle monsterBlock, String string ,String string2 , String string3){
        ImagePattern image2 = new ImagePattern(ToolKit.loadImage("effect/" + string + ".png"));
        ImagePattern image3 = new ImagePattern(ToolKit.loadImage("effect/" + string2 + ".png"));
        ImagePattern image4 = new ImagePattern(ToolKit.loadImage("effect/" + string3 + ".png"));

        Thread monsterMoving = new Thread(() -> {
            FrameRate frameRate = new FrameRate(100,4);
            while (isHit) {
                ImagePattern currentImage;
                if(frameRate.getFrame() % 2 == 1) monsterBlock.setEffect(new ColorAdjust(100,100,100,100));
                else monsterBlock.setEffect(null);
                if(frameRate.getFrame() == 1) currentImage = image2;
                else if(frameRate.getFrame() == 2) currentImage = image3;
                else if(frameRate.getFrame() == 3) currentImage = image4;
                else {
                    currentImage = null;
                    isHit = false;
                }
                Platform.runLater(() -> {
                    effectBlock.setFill(currentImage);
                });
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            rectangle2.setEffect(null);
        });
        monsterMoving.start();
    }

    private static Button initializeInventoryButton(){
        inventoryButton = new Button();
        inventoryButton = ToolKit.createButton("Inventory", "button/blue1.png","button/blue2.png",20);
        setButtonPref(inventoryButton, 90 , 30);
        inventoryButton.setOnMouseClicked(event -> {
            initializeInventoryList();
        });
        return inventoryButton;
    }

    private static void initializeInventoryList(){
        inventoryBox = new VBox();
        inventoryBox.setBackground(Background.fill(Color.WHITESMOKE));

        Button btn1 = ToolKit.createButton("Heal", "button/yellow1.png","button/yellow2.png",20);
        setButtonPref(btn1 , 90 , 30);

        inventoryBox.getChildren().addAll(btn1);

        btn1.setOnMouseClicked( event -> {
            showAttackEffect(heal ,blockPlayer ,"h1" , "h2" , "h3");
            isHit = true;
            root.getChildren().remove(root.getChildren().size() - 1);
        });

        root.add(inventoryBox,1,2);
        initializeBackToActionButton(inventoryBox);
    }
    private static void initializeBackToActionButton(VBox vBox){
        backtoactionButton = ToolKit.createButton("Back", "button/blue1.png","button/blue2.png",20);
        setButtonPref(backtoactionButton , 90 , 30);
        vBox.getChildren().add(backtoactionButton);

        backtoactionButton.setOnMouseClicked(event -> {
            root.getChildren().remove(root.getChildren().size() - 1);
        });
    }

    private static Button initializeEscapeButton(Stage stage){
        escapeButton = ToolKit.createButton("Escape", "button/red1.png","button/red2.png",20);
        setButtonPref(escapeButton, 90 , 30);
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
    private static void setButtonPref(Button button ,int width , int height){
        button.setPrefWidth(width);
        button.setPrefHeight(height);
    }

    public static BattleScene getInstance(){
        return instance;
    }

}
