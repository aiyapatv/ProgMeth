package logic.character;

import Utils.Config;

public class Wizard extends BaseCharacter{
    public Wizard(){
        super(Config.WizardHp);
    }


    @Override
    public void attack() {
    }


    public String toString() {
        return "Wizard";
    }
}
