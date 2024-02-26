package logic.character;

import utils.Config;

public class Wizard extends BaseCharacter{
    public Wizard(){
        super(Config.WizardHp);
    }

    @Override
    public int getPower() {
        return Config.WizardPower;
    }

    @Override
    public int getDefense() {
        return Config.WizardDefense;
    }

    @Override
    public void attack() {
    }


    public String toString() {
        return "Wizard";
    }
}
