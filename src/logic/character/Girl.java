package logic.character;

import Utils.Config;
import logic.monsters.Monster;

public class Girl extends BaseCharacter {
    public Girl() {
        super(Config.GIRLMAXHP , Config.GIRLPOWER , Config.GIRLDEFENSE,Config.GIRLMAGICPOWER,Config.GIRLMAGICDEFENSE);
    }

    @Override
    public void attack(Monster target) {

    }
    @Override
    public String toString() {
        return "Girl";
    }
}
