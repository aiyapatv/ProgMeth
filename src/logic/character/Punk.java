package logic.character;

import Utils.Config;
import logic.monsters.Monster;

public class Punk extends BaseCharacter{
    public Punk() {
        super(Config.PUNKMAXHP , Config.PUNKPOWER ,Config.PUNKDEFENSE,Config.PUNKMAGICPOWER,Config.PUNKMAGICDEFENSE);
    }

    @Override
    public void attack(Monster target) {

    }


    public String toString() {
        return "Punk";
    }
}
