package logic.character;

import Utils.Config;

public class Boy extends BaseCharacter{
    public Boy() {
        super(Config.KIDMAXHP , Config.KIDPOWER , Config.KIDDEFENSE);
    }

    @Override
    public void attack() {

    }
    @Override
    public String toString() {
        return "Boy";
    }
}
