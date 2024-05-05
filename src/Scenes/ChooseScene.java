package Scenes;

import Utils.Sound;
import Utils.ToolKit;
import Utils.FrameRate;
import Utils.Images;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import logic.character.BaseCharacter;
import logic.game.GameController;

public class ChooseScene extends Scene {

    private static GridPane root;
    private static StackPane selectBlock;
    private static Rectangle selectedChar;
    private static Text selectedName;
    private static boolean isSelected = false;
    private static Thread charMoving;
    private static Text maxHp;
    private static Text power;
    private static Text magicPower;
    private static Text defense;
    private static Text magicDefense;
    public static int number;
    public ChooseScene(Stage stage) {
        super(createChooseScene(stage), 800, 600);
    }

    private static GridPane createChooseScene(Stage stage){
        root = new GridPane(10,10);
        root.setBackground(new Background(new BackgroundImage(ToolKit.loadImage("background/bgChooseScene.png"), null, null,null,new BackgroundSize(800,600,false,false,false,false))));
        root.setPadding(new Insets(10));
        root.setGridLinesVisible(false);

        setConstraint();
        initializeHeader();
        initializePreviewChar();
        initializeCharTable();
        initializePlayButton(stage);
        initializeBackButton(stage);
        initializeAttributeBox();

        return root;
    }

    private static void initializeCharTable(){
        selectBlock = null;
        GridPane charTable = new GridPane(2,2);
        charTable.setAlignment(Pos.CENTER);
        for(int i = 0;i < 9; i++){
            final int num = i + 1;
            StackPane stack = new StackPane();
            Rectangle block = new Rectangle(100,100,Color.BEIGE);
            ImageView imageView = Images.setImageViewSize(ToolKit.loadImage("character/c"+ num +".png"), 100, 100);
            stack.getChildren().addAll(block, imageView);
            stack.setOnMouseClicked(event -> {
                if(number != num){
                    setNumber(num);
                    selectChar(stack, block);
                    showCharModel();
                    GameController.getInstance().defineCharacter(num);
                }
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
        header.setFont(ToolKit.loadFont( 50));
        root.add(header, 0, 0, 2, 1);
    }

    private static void initializePreviewChar(){
        VBox selectBlock = new VBox(10);
        selectedChar = new Rectangle(100,100,null);
        initializeName();
        selectBlock.setAlignment(Pos.CENTER);
        selectBlock.getChildren().addAll(selectedChar,selectedName);
        root.add(selectBlock, 0, 1);
    }
    public static void initializeName(){
        selectedName = new Text();
        selectedName.setFont(ToolKit.loadFont(50));
        selectedName.setText(getName());
    }
    public static String getName(){
        BaseCharacter character = GameController.getInstance().getCharacter();
        String name = "";
        if (character != null){
            name = character.toString();
        }
        return name;
    }

    private static void showCharModel(){
        ImagePattern image2 = new ImagePattern(ToolKit.loadImage("character/c" + getNumber() + "_" + 1 +".png"));
        ImagePattern image3 = new ImagePattern(ToolKit.loadImage("character/c" + getNumber() + "_" + 2 +".png"));
        charMoving = new Thread(() -> {
            FrameRate frameRate = new FrameRate(500,2);
            while (isSelected) {
                ImagePattern currentImage;
                selectedName.setText(getName());
                maxHp.setText("Hp: " + GameController.getInstance().getCharacter().getMaxHp());
                power.setText("Power: " + GameController.getInstance().getCharacter().getPower());
                magicPower.setText("MagicPower: " + + GameController.getInstance().getCharacter().getMagicPower());
                defense.setText("Defense: " + GameController.getInstance().getCharacter().getDefense());
                magicDefense.setText("MagicDefense: " + GameController.getInstance().getCharacter().getMagicDefense());

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
            isSelected = false;
        });
        charMoving.start();
    }

    private static void initializePlayButton(Stage stage){
        Button playButton = ToolKit.createButton("PLAY>>>", "button/yellowResize1.png", "button/yellowResize2.png",25);
        root.add(playButton,1,3);
        playButton.setOnMouseClicked( event -> {
            if(selectBlock != null) {
                isSelected = false;
                selectBlock = null;
                stage.setScene(new LoadingScene(stage));

                Sound.effectSound("/sound/ClickButton.mp3");
            }
        });
    }

    private static void initializeBackButton(Stage stage){
        Button backButton = ToolKit.createButton("<<<BACK", "button/yellowResize1.png", "button/yellowResize2.png",25);
        root.add(backButton,0,3);
        backButton.setOnMouseClicked( event -> {
            isSelected = false;
            stage.setScene(new StartScene(stage));
            Sound.getMediaPlayer().stop();
            Sound.effectSound("/sound/ClickButton.mp3");
        });
    }
    private static void initializeAttributeBox(){
        VBox attributeBox = new VBox();
        Font font = ToolKit.loadFont( 35);
        maxHp = new Text("Hp: " );
        power = new Text("Power: ");
        defense = new Text("Defense: " );
        magicPower = new Text("MagicPower: ");
        magicDefense = new Text("MagicDefense: " );
        maxHp.setFont(font);
        power.setFont(font);
        defense.setFont(font);
        magicPower.setFont(font);
        magicDefense.setFont(font);

        attributeBox.getChildren().addAll(maxHp , power , defense ,magicPower, magicDefense);
        if ( GameController.getInstance().getCharacter() != null){
            maxHp.setText("Hp: " + GameController.getInstance().getCharacter().getMaxHp());
            power.setText("Power: " + GameController.getInstance().getCharacter().getPower());
            defense.setText("Defense: " + GameController.getInstance().getCharacter().getDefense());
            magicPower.setText("MagicPower: " + + GameController.getInstance().getCharacter().getMagicPower());
            magicDefense.setText("MagicDefense: " + GameController.getInstance().getCharacter().getMagicDefense());
        }
        root.add(attributeBox ,0,2);
    }

    private static void setConstraint(){
        root.getColumnConstraints().addAll(ToolKit.setColumnCon(40,HPos.CENTER),
                ToolKit.setColumnCon(60,HPos.CENTER));
    }

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        ChooseScene.number = number;
    }

}
