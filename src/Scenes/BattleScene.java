package Scenes;

import Utils.*;
import javafx.animation.PauseTransition;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.character.BaseCharacter;
import logic.game.GameController;
import logic.monsters.Monster;
import logic.monsters.basic.Basic;
import logic.monsters.basic.Buff;
import logic.monsters.basic.FullBasic;
import logic.monsters.basic.MagicBasic;
import logic.monsters.boss.Boss1;
import logic.monsters.boss.Boss2;
import logic.monsters.magictank.FullAtkMagicTank;
import logic.monsters.magictank.MagicAtkMagicTank;
import logic.monsters.magictank.MagicTank;
import logic.monsters.tank.FullAtkTank;
import logic.monsters.tank.MagicAtkTank;
import logic.monsters.tank.Tank;
import logic.potion.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BattleScene extends Scene {
    private static GridPane root;
    private static boolean isEnd;
    private static Rectangle blockPlayer;
    private static GridPane fightPane;
    private static StackPane actionBox;
    private static VBox inventoryBox;
    private static VBox monsterBox;
    private static Rectangle heal;
    private static double hpPlayer;
    private static ArrayList<Monster> allMonster;
    private static ArrayList<Rectangle> allMonPic;
    private static ArrayList<Rectangle> allEffect;
    private static Text Hp;
    private static Text missAtk;
    private static Boolean isMagicAtk;
    private static ImageView newHpBar;
    private static Integer Turn;
    private static VBox description;
    private static Stage stage;
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public BattleScene(Stage stage) {
        super(createBattleScene(stage), 800, 600);
        BattleScene.stage = stage;
    }
    private static GridPane createBattleScene(Stage stage){
        isEnd = false;
        isMagicAtk = false;
        Turn = GameController.getInstance().getTurn();
        root = new GridPane(2,2);
        if ( Turn == 20 ){
            root.setBackground(new Background(new BackgroundImage(ToolKit.loadImage("background/bg21.png"), null, null,null,new BackgroundSize(800,600,false,false,false,false))));
        } else {
            root.setBackground(new Background(new BackgroundImage(ToolKit.loadImage("background/bg12.jpg"), null, null,null,new BackgroundSize(800,600,false,false,false,false))));
        }
        root.setPadding(new Insets(10));

        setConstraint();
        initializeStatusBar();
        initializeFightPane();
        initializeActionBar(stage);
        chooseAttackType();

        Sound.backgroundSound("/sound/StartScene.mp3");

        return root;
    }

    private static void initializeStatusBar(){
        HBox boxStatus = new HBox();
        StackPane stack = new StackPane();
        Rectangle block = new Rectangle(100,100, null);

        description = new VBox();
        Font font = ToolKit.loadFont(20);
        Text name = new Text(GameController.getInstance().getCharacter().toString());
        name.setFont(font);
        Hp = new Text("Hp " + GameController.getInstance().getCharacter().getHp() +"/"+ GameController.getInstance().getCharacter().getMaxHp());
        Hp.setFont(font);

        ImageView character = Images.setImageViewSize(ToolKit.loadImage("character/c"+ ChooseScene.getNumber() +".png"), 50, 50);
        ImageView imageView1 = Images.setImageViewSize(ToolKit.loadImage("element/ui1.png"), 60, 60);
        ImageView hpBar = new ImageView();
        hpPlayer = ((double)GameController.getInstance().getCharacter().getHp()) / (GameController.getInstance().getCharacter().getMaxHp())* 100 ;

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

        description.getChildren().addAll(name , Hp,hpBar);
        description.setAlignment(Pos.CENTER);
        stack.getChildren().addAll(block, imageView1, character);
        boxStatus.getChildren().addAll(stack, description);
        root.add(boxStatus,0,0);
    }

    private static void updateStatusBar(){
        int lastHp = GameController.getInstance().getCharacter().getHp();
        int lastMaxHp = GameController.getInstance().getCharacter().getMaxHp();
        Hp.setText("Hp " + lastHp +"/"+ lastMaxHp);
        hpPlayer = ((double) lastHp / lastMaxHp) * 100;

        if ( hpPlayer == 0 ) {
            newHpBar = Images.setImageViewSize(ToolKit.loadImage("asset/health0%.png"), 30, 100);
        } else if ( hpPlayer > 0 && hpPlayer <= 20 ){
            newHpBar = Images.setImageViewSize(ToolKit.loadImage("asset/health20%.png"), 30, 100);
        } else if ( hpPlayer > 20 && hpPlayer <= 40 ){
            newHpBar = Images.setImageViewSize(ToolKit.loadImage("asset/health40%.png"), 30, 100);
        } else if ( hpPlayer > 40 && hpPlayer <= 60 ){
            newHpBar = Images.setImageViewSize(ToolKit.loadImage("asset/health60%.png"), 30, 100);
        } else if ( hpPlayer > 60 && hpPlayer <= 80 ){
            newHpBar = Images.setImageViewSize(ToolKit.loadImage("asset/health80%.png"), 30, 100);
        } else if ( hpPlayer > 80 && hpPlayer <= 100) {
            newHpBar = Images.setImageViewSize(ToolKit.loadImage("asset/health100%.png"), 30, 100);
        }
        description.getChildren().remove(description.getChildren().size()-1);
        description.getChildren().add(newHpBar);
    }

    private static void initializePlayerSide(){
        Font font = ToolKit.loadFont( 45);
        missAtk = new Text("");
        missAtk.setFont(font);
        blockPlayer = new Rectangle(80,80);
        heal = new Rectangle(70,70);
        heal.setFill(null);
        ImagePattern player = new ImagePattern(ToolKit.loadImage("character/c"+ ChooseScene.getNumber() +"_3.png"));
        blockPlayer.setFill(player);
        showModelPlayer();
        fightPane.add(blockPlayer , 0,1);
        fightPane.add(heal , 0 ,1 );
        fightPane.add(missAtk , 0 ,1 );
    }

    private static void initializeMonsterSide(){
        GridPane monster = new GridPane();
        monster.setGridLinesVisible(false);

        for (int i = 0; i < 3; i++) {
            monster.getColumnConstraints().add(new ColumnConstraints(60));
            monster.getRowConstraints().add(new RowConstraints(60));
        }
        int totalMonster = 0;
        Monster randomMonster;
        if ( Turn == 20 ){
            totalMonster = 1;

            allMonster = new ArrayList<>(totalMonster);
            allMonPic = new ArrayList<>(totalMonster);
            allEffect = new ArrayList<>(totalMonster);

            Rectangle monsterBlock = new Rectangle(60,60);
            Rectangle effectBlock = new Rectangle(50,50);
            effectBlock.setFill(null);

            randomMonster = Random.randomBossMonsterImage();

            ImagePattern imageMonster = new ImagePattern(ToolKit.loadImage("monster/" + randomMonster.getPicture() + ".png"));
            monsterBlock.setFill(imageMonster);

            allMonPic.add(monsterBlock);
            allMonster.add(randomMonster);
            allEffect.add(effectBlock);

            showModelMonster(monsterBlock,randomMonster);
            monster.add(monsterBlock, 1, 1);
            monster.add(effectBlock, 1, 1);

        }
        else {
            totalMonster = Random.randomMonsterAmount();

            allMonster = new ArrayList<>(totalMonster);
            allMonPic = new ArrayList<>(totalMonster);
            allEffect = new ArrayList<>(totalMonster);
            for (int i = 1;i <= totalMonster; i++){
                Rectangle monsterBlock = new Rectangle(60,60);
                Rectangle effectBlock = new Rectangle(50,50);
                effectBlock.setFill(null);

                if ( totalMonster == 1){
                    randomMonster = Random.randomMonsterImage();
                    while ( randomMonster instanceof Buff ){
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
        }

        monster.setAlignment(Pos.BOTTOM_RIGHT);
        fightPane.add(monster , 1,0);
    }

    private static void initializeFightPane(){
        fightPane = new GridPane();
        fightPane.setGridLinesVisible(false);
        initializePlayerSide();
        initializeMonsterSide();

        fightPane.getRowConstraints().addAll(ToolKit.setRowCon(75,VPos.CENTER),ToolKit.setRowCon(25,null));
        fightPane.getColumnConstraints().addAll(ToolKit.setColumnCon(50,null) , ToolKit.setColumnCon(50,null));

        root.add(fightPane,1,1);
    }

    private static void delayAndContinue(Runnable task, long delayInMillis) {
        executor.execute(() -> {
            try {
                Thread.sleep(delayInMillis);
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void monsterTurn(){
        if (!isEnd){
            BaseCharacter player = GameController.getInstance().getCharacter();
            for (int i = 0; i< allMonster.size(); i++) {
                int random;
                Monster monster = allMonster.get(i);
                if ( monster instanceof Basic){
                    random = Random.randomMonsterAtk(2);
                    if ( random == 1 ){
                        ((Basic) monster).attack(player);
                    } else{
                        ((Basic) monster).specialAttack(player);
                    }
                } else if ( monster instanceof MagicTank){
                    random = Random.randomMonsterAtk(2);
                    if ( random == 1 ){
                        ((MagicTank) monster).attack(player);
                    } else{
                        ((MagicTank) monster).specialAttack(player);
                    }
                } else if ( monster instanceof Tank){
                    random = Random.randomMonsterAtk(2);
                    if ( random == 1 ){
                        ((Tank) monster).attack(player);
                    } else{
                        ((Tank) monster).specialAttack(player);
                    }
                } else if ( monster instanceof MagicBasic){
                    random = Random.randomMonsterAtk(2);
                    if ( random == 1 ){
                        ((MagicBasic) monster).magicAttack(player);
                    } else{
                        ((MagicBasic) monster).specialAttack(player);
                    }
                } else if ( monster instanceof MagicAtkMagicTank){
                    random = Random.randomMonsterAtk(2);
                    if ( random == 1 ){
                        ((MagicAtkMagicTank) monster).magicAttack(player);
                    } else{
                        ((MagicAtkMagicTank) monster).specialAttack(player);
                    }
                } else if ( monster instanceof MagicAtkTank){
                    random = Random.randomMonsterAtk(2);
                    if ( random == 1 ){
                        ((MagicAtkTank) monster).magicAttack(player);
                    } else{
                        ((MagicAtkTank) monster).specialAttack(player);
                    }
                } else if ( monster instanceof Buff){
                    for ( int l = 0; l<allMonster.size(); l++){
                        if ( l != i){
                            ((Buff) monster).buff(allMonster.get(l));
                        }
                    }
                } else if ( monster instanceof FullBasic) {
                    random = Random.randomMonsterAtk(3);
                    if ( random == 1 ){
                        ((FullBasic) monster).attack(player);
                    } else if ( random == 2 ){
                        ((FullBasic) monster).magicAttack(player);
                    } else {
                        ((FullBasic) monster).specialAttack(player);
                    }
                } else if ( monster instanceof FullAtkMagicTank) {
                    random = Random.randomMonsterAtk(3);
                    if ( random == 1 ){
                        ((FullAtkMagicTank) monster).attack(player);
                    } else if ( random == 2 ){
                        ((FullAtkMagicTank) monster).magicAttack(player);
                    } else {
                        ((FullAtkMagicTank) monster).specialAttack(player);
                    }
                } else if ( monster instanceof Boss1) {
                    random = Random.randomMonsterAtk(4);
                    if ( random == 1 ){
                        ((Boss1) monster).attack(player);
                    } else if ( random == 2 ){
                        ((Boss1) monster).skill1(player);
                    } else if ( random == 3 ){
                        ((Boss1) monster).skill2();
                    }  else{
                        ((Boss1) monster).specialAttack(player);
                    }
                } else if ( monster instanceof Boss2){
                    random = Random.randomMonsterAtk(4);
                    if ( random == 1 ){
                        ((Boss2) monster).attack(player);
                    } else if ( random == 2 ){
                        ((Boss2) monster).magicAttack(player);
                    } else if ( random == 3 ){
                        ((Boss2) monster).skill1();
                    } else {
                        ((Boss2) monster).specialAttack(player);
                    }
                }
                else {
                    random = Random.randomMonsterAtk(3);
                    if ( random == 1 ){
                        ((FullAtkTank) monster).attack(player);
                    } else if ( random == 2 ){
                        ((FullAtkTank) monster).magicAttack(player);
                    } else {
                        ((FullAtkTank) monster).specialAttack(player);
                    }
                }

                delayAndContinue(() -> {
                    showAttackEffect(heal ,blockPlayer ,"a1" , "a2" , "a3");
                    if (player.getAttackStat() == 0 ){
                        missAtk.setText("Miss");
                    }
                }, 500);

                delayAndContinue(() -> {
                    missAtk.setText("");
                }, 700);

            }
            delayAndContinue(() -> {
                Platform.runLater(() -> {
                    BattleScene.updateStatusBar();
                    playerDie();
                });
            }, 700);

            delayAndContinue(() -> {
                Platform.runLater(() -> {
                    root.getChildren().remove(root.getChildren().size() - 1);
                });
            }, 1000);
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
                stage.setScene(new StartScene(stage));
            });
            isEnd = true;
        }
    }

    private static void endBattle(){
        if ( allMonster.isEmpty()){
            if ( Turn == 20 ){
                actionBox.getChildren().clear();
                StackPane text = new StackPane();
                Text win = new Text("You are already kill Boss !!!(Click to Exit)");
                win.setFont(ToolKit.loadFont(30));
                win.setTextAlignment(TextAlignment.CENTER);
                text.getChildren().addAll(win);
                actionBox.getChildren().add(text);
                actionBox.setOnMouseClicked(mouseEvent -> {
                    stage.setScene(new StartScene(stage));
                });
                isEnd = true;

            } else {
                actionBox.getChildren().clear();
                VBox text = new VBox();
                text.setAlignment(Pos.CENTER);
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

        } else {
            StackPane monsterTurnEnd = new StackPane();
            initializeActionImageBox(monsterTurnEnd);
            Text monTurn = new Text("Monster Turn");
            monTurn.setFont(ToolKit.loadFont(30));
            monTurn.setTextAlignment(TextAlignment.CENTER);
            monsterTurnEnd.getChildren().addAll(monTurn);
            root.add(monsterTurnEnd,1,2);
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
        Button attackButton = new Button();
        attackButton = ToolKit.createButton("Attack", "button/blu1.png","button/blu2.png",20);
        setButtonPref(attackButton , 150 , 30);
        attackButton.setOnMouseClicked(event -> {
            initializeMonsterList();
        });
        return attackButton;
    }

    private static void chooseAttackType(){
        StackPane chooseBox =  new StackPane();
        initializeActionImageBox(chooseBox);

        Button powerBtn = ToolKit.createButton("Power", "button/re1.png","button/re2.png",20);
        Button magicPowerBtn = ToolKit.createButton("MagicPower", "button/re1.png","button/re2.png",20);
        setButtonPref(powerBtn, 90 , 30);
        setButtonPref(magicPowerBtn, 150 , 30);

        powerBtn.setOnMouseClicked(event -> {
            isMagicAtk = false;
            root.getChildren().remove(root.getChildren().size()-1);
        });

        magicPowerBtn.setOnMouseClicked(event -> {
            isMagicAtk = true;
            root.getChildren().remove(root.getChildren().size()-1);
        });
        HBox chooseBtn = new HBox();
        Insets margin = new Insets(30);
        StackPane.setMargin(chooseBtn, margin);

        chooseBtn.getChildren().addAll(powerBtn,magicPowerBtn);
        chooseBox.getChildren().add(chooseBtn);
        root.add(chooseBox,1,2);
    }

    private static void initializeMonsterList(){
        StackPane bar = new StackPane();
        HBox keepBox = new HBox();
        keepBox.setAlignment(Pos.CENTER);

        initializeActionImageBox(bar);

        monsterBox = new VBox();

        int round = 0;
        for ( int i = 0; i < allMonster.size(); i++){

            if ( allMonster.size() == 3 && i == 0 && round == 0){
                i+=2;
            }
            if ( i == 2 && round >= 1) break;

            Monster monster = allMonster.get(i);
            HBox monsterButton = new HBox();
            Button btn = ToolKit.createButton("Mon Lv."+ monster.getLevel(), "button/re1.png","button/re2.png",20);
            setButtonPref(btn, 90 , 30);

            double hpMon = (double) monster.getHp() / monster.getMaxHp() * 100;
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
            PauseTransition pauseTransition = new PauseTransition();
            int count = i;
            btn.setOnMouseClicked( event -> {

                showAttackEffect(allEffect.get(count) , allMonPic.get(count),"a1" , "a2" , "a3");
                root.getChildren().remove(root.getChildren().size() - 1);

                if (isMagicAtk){
                    GameController.getInstance().getCharacter().magicAttack(monster);
                } else {
                    GameController.getInstance().getCharacter().attack(monster);
                }

                monsterDie();
                monsterBox.getChildren().remove(count);
                endBattle();
                pauseTransition.setDuration(new Duration(500));
                pauseTransition.setOnFinished(e -> {
                    monsterTurn();
                });
                pauseTransition.play();
            });
            if ( allMonster.size() == 3 && i == 2) {
                i-=3;
                round++;
            }
        }
        Insets margin;
        if ( allMonster.size() == 3 ){
            margin = new Insets(12);
        } else {
            margin = new Insets(20);
        }
        StackPane.setMargin(keepBox, margin);

        VBox backBox = new VBox();
        initializeBackToActionButton(backBox);
        keepBox.getChildren().addAll(monsterBox,backBox);
        bar.getChildren().add(keepBox);
        root.add(bar,1,2);

    }

    private static void showAttackEffect(Rectangle effectBlock ,Rectangle monsterBlock, String string ,String string2 , String string3){
        ImagePattern image2 = new ImagePattern(ToolKit.loadImage("effect/" + string + ".png"));
        ImagePattern image3 = new ImagePattern(ToolKit.loadImage("effect/" + string2 + ".png"));
        ImagePattern image4 = new ImagePattern(ToolKit.loadImage("effect/" + string3 + ".png"));

        Thread monsterMoving = new Thread(() -> {
            for (int i = 1;i <= 4;i++) {
                ImagePattern currentImage;
                if(i % 2 == 1) monsterBlock.setEffect(new ColorAdjust(100,100,100,100));
                else monsterBlock.setEffect(null);
                if(i == 1) currentImage = image2;
                else if(i == 2) currentImage = image3;
                else if(i == 3) currentImage = image4;
                else {
                    currentImage = null;
                }
                Platform.runLater(() -> {
                    effectBlock.setFill(currentImage);
                });
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            monsterBlock.setEffect(null);
        });
        monsterMoving.start();
    }

    private static Button initializeInventoryButton(){
        Button inventoryButton = new Button();
        inventoryButton = ToolKit.createButton("Inventory", "button/blu1.png","button/blu2.png",20);
        setButtonPref(inventoryButton, 170 , 30);
        inventoryButton.setOnMouseClicked(event -> {
            initializeInventoryList();
        });
        return inventoryButton;
    }

    private static void initializePotionButton(BasePotion potion, Runnable usePotion){
        HBox potionButton = new HBox();
        Button btn1 = ToolKit.createButton(potion.getName(), "button/yel1.png","button/yel2.png",20);
        setButtonPref(btn1, 200 , 30);
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

        PauseTransition pauseTransition = new PauseTransition();

        btn1.setOnMouseClicked( event -> {
            showAttackEffect(heal ,blockPlayer ,"h1" , "h2" , "h3");
            root.getChildren().remove(root.getChildren().size() - 1);
            usePotion.run();
            updateStatusBar();

            delayAndContinue(() -> {
                Platform.runLater(() -> {
                    endBattle();
                    pauseTransition.setDuration(new Duration(500));
                    pauseTransition.setOnFinished(e -> {
                        monsterTurn();
                    });
                    pauseTransition.play();
                });
            }, 1000);

        });
    }

    private static void initializeInventoryList(){
        StackPane bar = new StackPane();
        initializeActionImageBox(bar);

        HBox keepBox = new HBox();
        keepBox.setAlignment(Pos.CENTER);

        inventoryBox = new VBox();

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
        Insets margin = new Insets(12);
        StackPane.setMargin(keepBox, margin);

        VBox backBox = new VBox();
        initializeBackToActionButton(backBox);
        keepBox.getChildren().addAll(inventoryBox,backBox);
        bar.getChildren().add(keepBox);
        root.add(bar,1,2);

    }

    private static void initializeBackToActionButton(VBox vBox){
        Button backtoactionButton = ToolKit.createButton("Back", "button/blu1.png","button/blu2.png",20);
        setButtonPref(backtoactionButton , 150 , 30);
        vBox.getChildren().add(backtoactionButton);

        backtoactionButton.setOnMouseClicked(event -> {
            root.getChildren().remove(root.getChildren().size() - 1);
        });
    }

    private static Button initializeEscapeButton(Stage stage){
        Button escapeButton = ToolKit.createButton("Escape", "button/re1.png","button/re2.png",20);
        setButtonPref(escapeButton, 150 , 30);
        escapeButton.setOnMouseClicked(event -> {
            stage.setScene(GameScene.getInstance(stage));
            isEnd = true;

        });
        return escapeButton;
    }

    private static void initializeActionBar(Stage stage){
        actionBox = new StackPane();
        initializeActionImageBox(actionBox);

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(initializeAttackButton(),initializeInventoryButton(), initializeEscapeButton(stage));

        Insets margin = new Insets(30);
        StackPane.setMargin(buttonBox, margin);

        actionBox.getChildren().add(buttonBox);
        root.add(actionBox, 1, 2);
    }

    private static void initializeActionImageBox(StackPane stackPane){
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(ToolKit.loadImage("gui/board6.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        stackPane.setBackground(background);
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
