package logic.character;

import Utils.Config;

public class Girl extends BaseCharacter {
    public Girl() {
        super(Config.KIDMAXHP , Config.KIDPOWER , Config.KIDDEFENSE);
    }

    @Override
    public void attack() {

    }
    @Override
    public String toString() {
        return "Girl";
    }
}
