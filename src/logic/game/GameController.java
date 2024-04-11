package logic.game;

import Board.HexagonBoard;
import Board.HexagonTile;
import Scenes.ChooseScene;
import Scenes.GameScene;
import Utils.ToolKit;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import logic.character.*;
import logic.monsters.Monster;
import logic.potion.BasePotion;
import logic.potion.HealingPotion;

import java.util.ArrayList;

public class GameController {
    private static GameController instance;
    private BaseCharacter character;
    private int turn;
    private int healingPotion;
    private int pill;
    private int strengthPotion;
    private int ultimatePotion;
    public GameController(){
        turn = 0;
        healingPotion = 0;
        pill = 0;
        strengthPotion = 0;
        ultimatePotion = 0;
    }

    public static GameController getInstance() {
        if(instance == null) instance = new GameController();
        return instance;
    }

    public static void setInstance(GameController instance) {
        GameController.instance = instance;
    }

    public BaseCharacter getCharacter() {
        return character;
    }

    public void setCharacter(BaseCharacter character) {
        this.character = character;
    }

    public void defineCharacter(int num){
        if ( num == 1 ) setCharacter(new Boy());
        else if ( num == 2 ) setCharacter(new Blonde());
        else if ( num == 3 ) setCharacter(new Glasses());
        else if ( num == 4 ) setCharacter(new Detective());
        else if ( num == 5 ) setCharacter(new Girl());
        else if ( num == 6 ) setCharacter(new Punk());
        else if ( num == 7 ) setCharacter(new Knight());
        else if ( num == 8 ) setCharacter(new Viking());
        else if (num == 9) setCharacter(new Wizard());
    }

    public int getTurn(){
        return turn;
    }

    public void increaseTurn(){
        turn++;
    }
    public int getHealingPotion() {
        return healingPotion;
    }

    public void setHealingPotion(int healingPotion) {
        this.healingPotion = healingPotion;
    }

    public int getPill() {
        return pill;
    }

    public void setPill(int pill) {
        this.pill = pill;
    }

    public int getStrengthPotion() {
        return strengthPotion;
    }

    public void setStrengthPotion(int strengthPotion) {
        this.strengthPotion = strengthPotion;
    }

    public int getUltimatePotion() {
        return ultimatePotion;
    }

    public void setUltimatePotion(int ultimatePotion) {
        this.ultimatePotion = ultimatePotion;
    }
}
