package logic.character;

import Utils.Config;

public class Detective extends BaseCharacter{
    public Detective() {
        super(Config.DETECTIVEMAXHP);
    }

    @Override
    public void attack() {

    }

    public String toString() {
        return "Detective";
    }
}
