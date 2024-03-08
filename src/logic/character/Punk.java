package logic.character;

import Utils.Config;

public class Punk extends BaseCharacter{
    public Punk() {
        super(Config.GLASSESMAXHP , Config.GLASSESPOWER ,Config.GLASSESDEFENSE);
    }

    @Override
    public void attack() {

    }


    public String toString() {
        return "Punk";
    }
}
