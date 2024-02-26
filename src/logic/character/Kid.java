package logic.character;

import utils.Config;

public class Kid extends BaseCharacter{

    public Kid() {
        super(Config.KidHp);
    }

    @Override
    public int getPower() {
        return Config.KidPower;
    }

    @Override
    public int getDefense() {
        return Config.KidDefense;
    }

    @Override
    public void attack() {

    }
    @Override
    public String toString() {
        return "Kid";
    }
}
