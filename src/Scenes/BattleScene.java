package Scenes;

import Utils.Images;
import Utils.ToolKit;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BattleScene extends Scene {
    private static GridPane root;

    public BattleScene(Stage stage) {
        super(createBattleScene(stage), 800, 600);
    }
    private static GridPane createBattleScene(Stage stage){
        root = new GridPane(2,2);
        root.setBackground(new Background(new BackgroundImage(ToolKit.loadImage("background/Background3.png"), null, null,null,new BackgroundSize(800,600,false,false,false,false))));
        root.setPadding(new Insets(10));
        root.setGridLinesVisible(true);

        initializeStatusBar();
        initializeTurnBased();


        return root;
    }
    private static void initializeStatusBar(){
        HBox boxStatus = new HBox();
        StackPane stack = new StackPane();
        Rectangle block = new Rectangle(100,100, null);

//      int num = ChooseScene.getNumber();
        ImageView imageView = Images.setImageViewSize(ToolKit.loadImage("character/c"+ 1 +".png"), 80, 80);
        ImageView imageView1 = Images.setImageViewSize(ToolKit.loadImage("element/ui1.png"), 100, 100);
        ImageView imageView3 = Images.setImageViewSize(ToolKit.loadImage("element/ui2.png"), 50, 200);
        stack.getChildren().addAll(block, imageView1, imageView);
        boxStatus.getChildren().addAll(stack , imageView3 );
        root.add(boxStatus,0,2);
    }

    private static void initializeTurnBased(){
        HBox turnBased = new HBox();

        for(int i = 0;i < 7; i++){
            final int num = i + 1;
            StackPane stack = new StackPane();
            Rectangle block = new Rectangle(20,20,Color.BEIGE);
            ImageView imageView = Images.setImageViewSize(ToolKit.loadImage("character/c"+ 1 +"_1.png"), 20, 20);
            stack.getChildren().addAll(block, imageView);

            turnBased.getChildren().add(stack);
        }
        root.add(turnBased, 1, 0);
    }
}
