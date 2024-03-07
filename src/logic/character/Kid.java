package logic.character;

import Utils.Config;

public class Kid extends BaseCharacter{

    public Kid() {
        super(Config.KIDMAXHP);
    }

    @Override
    public void attack() {

    }
    @Override
    public String toString() {
        return "Kid";
    }
}
