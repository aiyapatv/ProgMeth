package logic.character;

import Utils.Config;

public class Glasses extends BaseCharacter{
    public Glasses() {
        super(Config.GLASSESMAXHP);
    }



    @Override
    public void attack() {

    }


    public String toString() {
        return "Glasses";
    }
}
