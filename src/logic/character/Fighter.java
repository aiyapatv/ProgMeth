package logic.character;

import Utils.Config;

public class Fighter extends BaseCharacter{
    public Fighter(){
        super(Config.FIGHTERMAXHP);
    }


    @Override
    public void attack() {

    }


    public String toString() {
        return "Fighter";
    }
}
