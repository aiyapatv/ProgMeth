package logic.character;

import Utils.Config;

public class Wizard extends BaseCharacter{
    public Wizard(){
        super(Config.WIZARDMAXHP);
    }

    @Override
    public int getPower() {
        return Config.WIZARDPOWER;
    }

    @Override
    public int getDefense() {
        return Config.WIZARDDEFENSE;
    }

    @Override
    public void attack() {
    }


    public String toString() {
        return "Wizard";
    }
}
