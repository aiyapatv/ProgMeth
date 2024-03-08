package logic.game;

import Board.HexagonBoard;
import Scenes.ChooseScene;
import Scenes.GameScene;
import logic.character.*;
import logic.monsters.Monster;

import java.util.ArrayList;

public class GameController {
    private static GameController instance;
    private BaseCharacter character;
    private ArrayList<Monster> monsters;
    private HexagonBoard hexagonBoard;
    public GameController(){
        setHexagonBoard(new HexagonBoard());
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

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }

    public HexagonBoard getHexagonBoard() {
        return hexagonBoard;
    }

    public void setHexagonBoard(HexagonBoard hexagonBoard) {
        this.hexagonBoard = hexagonBoard;
    }
}
