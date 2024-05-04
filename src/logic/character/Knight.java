package logic.character;

import Utils.Config;
import logic.monsters.Monster;

public class Knight extends BaseCharacter{
    public Knight() {
        super(Config.KNIGHTMAXHP , Config.KNIGHTPOWER ,Config.KNIGHTDEFENSE,Config.KNIGHTMAGICPOWER,Config.KNIGHTMAGICDEFENSE);
    }

    @Override
    public String toString() {
        return "Knight";
    }
}
