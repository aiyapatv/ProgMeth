package logic.character;

import Utils.Config;
import logic.monsters.Monster;

public class Knight extends BaseCharacter{
    public Knight() {
        super(Config.KNIGHTMAXHP , Config.KNIGHTPOWER ,Config.KNIGHTPOWER,Config.KNIGHTMAGICPOWER,Config.KNIGHTMAGICDEFENSE);
    }

    @Override
    public String toString() {
        return "Knight";
    }
}
