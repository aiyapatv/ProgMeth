package Scenes;

import Utils.FrameRate;
import Utils.Images;
import Utils.Random;
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
import logic.character.BaseCharacter;
import logic.game.GameController;
import logic.monsters.Monster;
import logic.monsters.basic.basic;
import logic.monsters.basic.buff;
import logic.monsters.basic.fullBasic;
import logic.monsters.basic.magicBasic;
import logic.monsters.magictank.fullAtkMagicTank;
import logic.monsters.magictank.magicAtkMagicTank;
import logic.monsters.magictank.magicTank;
import logic.monsters.tank.fullAtkTank;
import logic.monsters.tank.magicAtkTank;
import logic.monsters.tank.tank;
import logic.potion.*;

import java.util.ArrayList;
import java.util.Objects;

public class BattleScene extends Scene {
    private static GridPane root;
    private static boolean isEnd;
    private static boolean isHit;
    private static Rectangle blockPlayer;
    private static GridPane fightPane;
    private static Button attackButton;
    private static Button inventoryButton;
    private static HBox potionButton;
    private static Button escapeButton;
    private static HBox actionBox;
    private static VBox inventoryBox;
    private static VBox monsterBox;
    private static HBox monsterButton;
    private static Button backtoactionButton;
    private static Rectangle monsterBlock;
    private static Rectangle effectBlock;
    private static Rectangle heal;
    private static int totalMonster;
    private static int hpPlayer;
    private static ArrayList<Monster> allMonster;
    private static ArrayList<Rectangle> allMonPic;
    private static ArrayList<Rectangle> allEffect;
    private static Text Hp;
    private static ImageView hpBar;
    private static HBox boxStatus;
    private static StackPane stack;
    private static Monster randomMonster;
    private static Stage stage;

    public BattleScene(Stage stage) {
        super(createBattleScene(stage), 800, 600);
        BattleScene.stage = stage;
    }
    private static GridPane createBattleScene(Stage stage){
        isEnd = false;
        isHit = false;
        root = new GridPane(2,2);
        root.setBackground(new Background(new BackgroundImage(ToolKit.loadImage("background/bg12.jpg"), null, null,null,new BackgroundSize(800,600,false,false,false,false))));
        root.setPadding(new Insets(10));
        root.setGridLinesVisible(true);

        setConstraint();

        initializeStatusBar();
        initializeFightPane();
        initializeActionBar(stage);

        return root;
    }

    private static void initializeStatusBar(){
        boxStatus = new HBox();
        stack = new StackPane();
        Rectangle block = new Rectangle(100,100, null);
        VBox description = new VBox();
        Text name = new Text(GameController.getInstance().getCharacter().toString());
        Hp = new Text("Hp " + GameController.getInstance().getCharacter().getHp() +"/"+ GameController.getInstance().getCharacter().getMaxHp());
        Hp.setFont(ToolKit.loadFont(20));
        description.getChildren().addAll(name , Hp);

        ImageView character = Images.setImageViewSize(ToolKit.loadImage("character/c"+ ChooseScene.getNumber() +".png"), 50, 50);
        ImageView imageView1 = Images.setImageViewSize(ToolKit.loadImage("element/ui1.png"), 60, 60);
        ImageView imageView3 = Images.setImageViewSize(ToolKit.loadImage("element/ui2.png"), 30, 100);
        hpBar = new ImageView();
        hpPlayer = (GameController.getInstance().getCharacter().getHp()) / (GameController.getInstance().getCharacter().getMaxHp())* 100 ;

        if ( hpPlayer == 0 ) {
            hpBar = Images.setImageViewSize(ToolKit.loadImage("asset/health0%.png"), 30, 100);
        } else if ( hpPlayer > 0 && hpPlayer <= 20 ){
            hpBar = Images.setImageViewSize(ToolKit.loadImage("asset/health20%.png"), 30, 100);
        } else if ( hpPlayer > 20 && hpPlayer <= 40 ){
            hpBar = Images.setImageViewSize(ToolKit.loadImage("asset/health40%.png"), 30, 100);
        } else if ( hpPlayer > 40 && hpPlayer <= 60 ){
            hpBar = Images.setImageViewSize(ToolKit.loadImage("asset/health60%.png"), 30, 100);
        } else if ( hpPlayer > 60 && hpPlayer <= 80 ){
            hpBar = Images.setImageViewSize(ToolKit.loadImage("asset/health80%.png"), 30, 100);
        } else if ( hpPlayer > 80 && hpPlayer <= 100) {
            hpBar = Images.setImageViewSize(ToolKit.loadImage("asset/health100%.png"), 30, 100);
        }

        stack.getChildren().addAll(block, imageView1, character,hpBar);
        boxStatus.getChildren().addAll(stack ,imageView3 , description);
        root.add(boxStatus,0,2);
    }

    private static void updateStatusBar(){
        Hp.setText("Hp " + GameController.getInstance().getCharacter().getHp() +"/"+ GameController.getInstance().getCharacter().getMaxHp());
        hpPlayer = (GameController.getInstance().getCharacter().getHp()) / (GameController.getInstance().getCharacter().getMaxHp())* 100 ;

        if ( hpPlayer == 0 ) {
            hpBar = Images.setImageViewSize(ToolKit.loadImage("asset/health0%.png"), 30, 100);
        } else if ( hpPlayer > 0 && hpPlayer <= 20 ){
            hpBar = Images.setImageViewSize(ToolKit.loadImage("asset/health20%.png"), 30, 100);
        } else if ( hpPlayer > 20 && hpPlayer <= 40 ){
            hpBar = Images.setImageViewSize(ToolKit.loadImage("asset/health40%.png"), 30, 100);
        } else if ( hpPlayer > 40 && hpPlayer <= 60 ){
            hpBar = Images.setImageViewSize(ToolKit.loadImage("asset/health60%.png"), 30, 100);
        } else if ( hpPlayer > 60 && hpPlayer <= 80 ){
            hpBar = Images.setImageViewSize(ToolKit.loadImage("asset/health80%.png"), 30, 100);
        } else if ( hpPlayer > 80 && hpPlayer <= 100) {
            hpBar = Images.setImageViewSize(ToolKit.loadImage("asset/health100%.png"), 30, 100);
        }
        stack.getChildren().remove(stack.getChildren().size()-1);
        stack.getChildren().add(hpBar);
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
        totalMonster = Random.randomMonsterAmount();

        allMonster = new ArrayList<>(totalMonster);
        allMonPic = new ArrayList<>(totalMonster);
        allEffect = new ArrayList<>(totalMonster);
        for (int i = 1;i <= totalMonster; i++){
            monsterBlock = new Rectangle(60,60);
            effectBlock = new Rectangle(50,50);
            effectBlock.setFill(null);

            if ( totalMonster == 1){
                randomMonster = Random.randomMonsterImage();
                while ( randomMonster instanceof buff ){
                    randomMonster = Random.randomMonsterImage();
                }
            } else {
                randomMonster = Random.randomMonsterImage();
            }

            ImagePattern imageMonster = new ImagePattern(ToolKit.loadImage("monster/" + randomMonster.getPicture() + ".png"));
            monsterBlock.setFill(imageMonster);

            allMonPic.add(monsterBlock);
            allMonster.add(randomMonster);
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

    private static void monsterTurn(){
        if (!isEnd){
            VBox text = new VBox();
            text.setBackground(Background.fill(Color.WHITESMOKE));
            text.setSpacing(5);
            Text monTurn = new Text("Monster Turn");
            monTurn.setFont(ToolKit.loadFont(30));
            text.getChildren().addAll(monTurn);
            root.add(text,1,2);
            text.setOnMouseClicked( mouseEvent -> {
                root.getChildren().remove(root.getChildren().size() - 1);
            });

            BaseCharacter player = GameController.getInstance().getCharacter();

            for (int i = 0; i< allMonster.size(); i++) {
                int random;
                Monster monster = allMonster.get(i);
                if ( monster instanceof basic){
                    random = Random.randomMonsterAtk(2);
                    if ( random == 1 ){
                        ((basic) monster).attack(player);
                    } else{
                        ((basic) monster).special_attack(player);
                    }
                } else if ( monster instanceof magicTank ){
                    random = Random.randomMonsterAtk(2);
                    if ( random == 1 ){
                        ((magicTank) monster).attack(player);
                    } else{
                        ((magicTank) monster).special_attack(player);
                    }
                } else if ( monster instanceof tank ){
                    random = Random.randomMonsterAtk(2);
                    if ( random == 1 ){
                        ((tank) monster).attack(player);
                    } else{
                        ((tank) monster).special_attack(player);
                    }
                } else if ( monster instanceof magicBasic){
                    random = Random.randomMonsterAtk(2);
                    if ( random == 1 ){
                        ((magicBasic) monster).magic_attack(player);
                    } else{
                        ((magicBasic) monster).special_attack(player);
                    }
                } else if ( monster instanceof magicAtkMagicTank){
                    random = Random.randomMonsterAtk(2);
                    if ( random == 1 ){
                        ((magicAtkMagicTank) monster).magic_attack(player);
                    } else{
                        ((magicAtkMagicTank) monster).special_attack(player);
                    }
                } else if ( monster instanceof magicAtkTank){
                    random = Random.randomMonsterAtk(2);
                    if ( random == 1 ){
                        ((magicAtkTank) monster).magic_attack(player);
                    } else{
                        ((magicAtkTank) monster).special_attack(player);
                    }
                } else if ( monster instanceof buff){
                    for ( int l = 0; l<allMonster.size(); l++){
                        if ( l != i){
                            ((buff) monster).buff(allMonster.get(l));
                        }
                    }
                } else if ( monster instanceof fullBasic) {
                    random = Random.randomMonsterAtk(3);
                    if ( random == 1 ){
                        ((fullBasic) monster).attack(player);
                    } else if ( random == 2 ){
                        ((fullBasic) monster).magic_attack(player);
                    } else {
                        ((fullBasic) monster).special_attack(player);
                    }
                } else if ( monster instanceof fullAtkMagicTank) {
                    random = Random.randomMonsterAtk(3);
                    if ( random == 1 ){
                        ((fullAtkMagicTank) monster).attack(player);
                    } else if ( random == 2 ){
                        ((fullAtkMagicTank) monster).magic_attack(player);
                    } else {
                        ((fullAtkMagicTank) monster).special_attack(player);
                    }
                } else {
                    random = Random.randomMonsterAtk(3);
                    if ( random == 1 ){
                        ((fullAtkTank) monster).attack(player);
                    } else if ( random == 2 ){
                        ((fullAtkTank) monster).magic_attack(player);
                    } else {
                        ((fullAtkTank) monster).special_attack(player);
                    }
                }
            }
            updateStatusBar();
            playerDie();
        }
    }

    private static void monsterDie(){
         for ( int i = 0 ; i<allMonster.size(); i++){
             if (  allMonster.get(i).getHp() <= 0 ){
                 allMonster.remove(i);
                 allMonPic.remove(i);
                 allEffect.remove(i);
                 i--;
             }
         }
    }

    private static void playerDie(){
        if (GameController.getInstance().getCharacter().getHp() <= 0){
            actionBox.getChildren().clear();
            VBox text = new VBox();
            text.setSpacing(5);
            Text win = new Text("You Die !!!(Click to Continue)");
            win.setFont(ToolKit.loadFont(30));
            text.getChildren().addAll(win);
            actionBox.getChildren().add(text);
            actionBox.setOnMouseClicked(mouseEvent -> {
                stage.setScene(GameScene.getInstance(stage));
            });
            isEnd = true;
        }
    }

    private static void endBattle(){
        if ( allMonster.isEmpty()){
            actionBox.getChildren().clear();
            VBox text = new VBox();
            text.setSpacing(5);
            Text win = new Text("You Win !!!(Click to Continue)");
            win.setFont(ToolKit.loadFont(30));
            text.getChildren().addAll(win);

            for ( int i = 0; i<Random.randomPotionAmount(); i++ ){
                Text potion = new Text("You Got " + Random.randomDropPotion().getName());
                potion.setFont(ToolKit.loadFont(20));
                text.getChildren().add(potion);
            }

            actionBox.getChildren().add(text);
            actionBox.setOnMouseClicked(mouseEvent -> {
                stage.setScene(GameScene.getInstance(stage));
            });
            isEnd = true;
        }
    }

    private static void showModelPlayer(){
        ImagePattern image2 = new ImagePattern(ToolKit.loadImage("character/c" + ChooseScene.getNumber() + "_" + 4 +".png"));
        ImagePattern image3 = new ImagePattern(ToolKit.loadImage("character/c" + ChooseScene.getNumber() + "_" + 3 +".png"));

        Thread playerMoving = new Thread(() -> {
            FrameRate frameRate = new FrameRate(500,2);
            while (!isEnd) {
                ImagePattern currentImage;
                if(frameRate.getFrame() == 1) currentImage = image2;
                else currentImage = image3;
                Platform.runLater(() ->
                        blockPlayer.setFill(currentImage)
                );
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        playerMoving.start();
    }

    private static void showModelMonster(Rectangle rectangle,Monster randomMonster){
        ImagePattern image2 = new ImagePattern(ToolKit.loadImage("monster/" + randomMonster.getPicture() + ".png"));
        ImagePattern image3 = new ImagePattern(ToolKit.loadImage("monster/" + randomMonster.getPicture2() + ".png"));

        Thread monsterMoving = new Thread(() -> {
            FrameRate frameRate = new FrameRate(500,2);
            while (!isEnd && !randomMonster.isDie()) {
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
            Platform.runLater(() -> {
                rectangle.setFill(null);
            });
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

        for ( int i = 0; i < allMonster.size(); i++){
            Monster monster = allMonster.get(i);
            monsterButton = new HBox();
            Button btn = ToolKit.createButton("Monster"+(i+1), "button/red1.png","button/red2.png",20);
            setButtonPref(btn, 90 , 30);

            int hpMon = monster.getHp() / monster.getMaxHp() * 100;
            ImageView hpMonBar = new ImageView();
            if ( hpMon == 0 ) {
                hpMonBar = Images.setImageViewSize(ToolKit.loadImage("asset/health0%.png"), 30, 100);
            } else if ( hpMon > 0 && hpMon <= 20 ){
                hpMonBar = Images.setImageViewSize(ToolKit.loadImage("asset/health20%.png"), 30, 100);
            } else if ( hpMon> 20 && hpMon <= 40 ){
                hpMonBar = Images.setImageViewSize(ToolKit.loadImage("asset/health40%.png"), 30, 100);
            } else if ( hpMon > 40 && hpMon <= 60 ){
                hpMonBar = Images.setImageViewSize(ToolKit.loadImage("asset/health60%.png"), 30, 100);
            } else if ( hpMon > 60 && hpMon <= 80 ){
                hpMonBar = Images.setImageViewSize(ToolKit.loadImage("asset/health80%.png"), 30, 100);
            } else if ( hpMon > 80 && hpMon <= 100) {
                hpMonBar = Images.setImageViewSize(ToolKit.loadImage("asset/health100%.png"), 30, 100);
            }

            monsterButton.getChildren().addAll(btn,hpMonBar);
            monsterBox.getChildren().add(monsterButton);

            int count = i;
            btn.setOnMouseClicked( event -> {
                showAttackEffect(allEffect.get(count) , allMonPic.get(count),"a1" , "a2" , "a3");
                isHit = false;
                root.getChildren().remove(root.getChildren().size() - 1);
                GameController.getInstance().getCharacter().attack(monster);
                monsterDie();
                monsterBox.getChildren().remove(count);
                endBattle();
                monsterTurn();
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
            while (!isHit) {
                ImagePattern currentImage;
                if(frameRate.getFrame() % 2 == 1) monsterBlock.setEffect(new ColorAdjust(100,100,100,100));
                else monsterBlock.setEffect(null);
                if(frameRate.getFrame() == 1) currentImage = image2;
                else if(frameRate.getFrame() == 2) currentImage = image3;
                else if(frameRate.getFrame() == 3) currentImage = image4;
                else {
                    currentImage = null;
                    isHit = true;
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
            monsterBlock.setEffect(null);
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

    private static void initializePotionButton(BasePotion potion, Runnable usePotion){
        potionButton = new HBox();
        Button btn1 = ToolKit.createButton(potion.getName(), "button/yellow1.png","button/yellow2.png",20);
        setButtonPref(btn1 , 90 , 30);
        int amount;
        if (Objects.equals(potion.getName(), "Pill")){
            amount = GameController.getInstance().getPill();
        } else if(Objects.equals(potion.getName(), "StrengthPotion")){
            amount = GameController.getInstance().getStrengthPotion();
        } else if(Objects.equals(potion.getName(), "UltimatePotion")){
            amount = GameController.getInstance().getUltimatePotion();
        }else {
            amount = GameController.getInstance().getHealingPotion();
        }
        Text amountPotion = new Text("x" + amount);
        amountPotion.setFont(ToolKit.loadFont(20));
        potionButton.getChildren().addAll(btn1 , amountPotion);
        inventoryBox.getChildren().addAll(potionButton);

        btn1.setOnMouseClicked( event -> {
            showAttackEffect(heal ,blockPlayer ,"h1" , "h2" , "h3");
            isHit = false;
            root.getChildren().remove(root.getChildren().size() - 1);
            usePotion.run();
            updateStatusBar();
            monsterTurn();
        });
    }

    private static void initializeInventoryList(){
        inventoryBox = new VBox();
        inventoryBox.setBackground(Background.fill(Color.WHITESMOKE));

        if ( GameController.getInstance().getPill() > 0 ){
            initializePotionButton(new Pill(), (() -> new Pill().usePotion()));
        }
        if ( GameController.getInstance().getHealingPotion() > 0 ){
            initializePotionButton(new HealingPotion(), (() -> new HealingPotion().usePotion()));
        }
        if ( GameController.getInstance().getStrengthPotion() > 0 ){
            initializePotionButton(new StrengthPotion(), (() -> new StrengthPotion().usePotion()));
        }
        if ( GameController.getInstance().getUltimatePotion() > 0 ){
            initializePotionButton(new UltimatePotion(), (() -> new UltimatePotion().usePotion()));
        }

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
            stage.setScene(GameScene.getInstance(stage));
            isEnd = true;
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

}
