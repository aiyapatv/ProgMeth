package logic.character;

import Utils.Config;

public class Knight extends BaseCharacter{
    public Knight() {
        super(Config.FIGHTERMAXHP , Config.FIGHTERPOWER ,Config.FIGHTERDEFENSE);
    }

    @Override
    public void attack() {

    }


    public String toString() {
        return "Knight";
    }
}
