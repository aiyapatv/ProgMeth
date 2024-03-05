package logic.character;

import Utils.Config;

public class Detective extends BaseCharacter{
    public Detective() {
        super(Config.DetectiveHp);
    }

    @Override
    public int getPower() {
        return Config.DetectivePower;
    }

    @Override
    public int getDefense() {
        return Config.DetectiveDefense;
    }

    @Override
    public void attack() {

    }

    public String toString() {
        return "Detective";
    }
}
