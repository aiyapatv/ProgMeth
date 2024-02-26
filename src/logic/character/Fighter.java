package logic.character;

import utils.Config;

public class Fighter extends BaseCharacter{
    public Fighter(){
        super(Config.FighterHp);
    }

    @Override
    public int getPower() {
        return Config.FighterPower;
    }

    @Override
    public int getDefense() {
        return Config.FighterDefense;
    }

    @Override
    public void attack() {

    }


    public String toString() {
        return "Fighter";
    }
}
