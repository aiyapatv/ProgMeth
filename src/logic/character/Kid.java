package logic.character;

import Utils.Config;

public class Kid extends BaseCharacter{

    public Kid() {
        super(Config.KIDMAXHP);
    }

    @Override
    public int getPower() {
        return Config.KIDPOWER;
    }

    @Override
    public int getDefense() {
        return Config.KIDDEFENSE;
    }

    @Override
    public void attack() {

    }
    @Override
    public String toString() {
        return "Kid";
    }
}
