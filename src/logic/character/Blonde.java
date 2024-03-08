package logic.character;

import Utils.Config;

public class Blonde extends BaseCharacter{
    public Blonde() {
        super(Config.KIDMAXHP , Config.KIDPOWER , Config.KIDDEFENSE);
    }

    @Override
    public void attack() {

    }
    @Override
    public String toString() {
        return "Blonde";
    }
}
