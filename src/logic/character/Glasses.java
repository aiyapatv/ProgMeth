package logic.character;

import Utils.Config;
import logic.monsters.Monster;

public class Glasses extends BaseCharacter{
    public Glasses() {
        super(Config.GLASSESMAXHP , Config.GLASSESPOWER ,Config.GLASSESDEFENSE,Config.GLASSESMAGICPOWER,Config.GLASSESMAGICDEFENSE);
    }

    @Override
    public String toString() {
        return "Glasses";
    }
}
