package logic.character;

import Utils.Config;
import logic.monsters.Monster;

public class Wizard extends BaseCharacter{
    public Wizard(){
        super(Config.WIZARDMAXHP , Config.WIZARDPOWER , Config.WIZARDDEFENSE,Config.WIZARDMAGICPOWER,Config.WIZARDMAGICDEFENSE);
    }
    @Override
    public String toString() {
        return "Wizard";
    }
}
