package logic.character;

import Utils.Config;

public class Viking extends BaseCharacter{
    public Viking(){
        super(Config.FIGHTERMAXHP , Config.FIGHTERPOWER , Config.FIGHTERDEFENSE);
    }

    @Override
    public void attack() {

    }


    public String toString() {
        return "Viking";
    }
}
