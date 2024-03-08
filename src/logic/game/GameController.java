package logic.game;

import Board.HexagonBoard;
import Scenes.ChooseScene;
import Scenes.GameScene;
import logic.character.BaseCharacter;
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
