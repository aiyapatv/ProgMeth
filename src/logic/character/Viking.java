package logic.character;

import Utils.Config;
import logic.monsters.Monster;

public class Viking extends BaseCharacter{
    public Viking(){
        super(Config.VIKINGMAXHP , Config.VIKINGPOWER , Config.VIKINGDEFENSE,Config.VIKINGMAGICPOWER,Config.VIKINGMAGICDEFENSE);
    }

    @Override
    public void attack(Monster target) {

    }


    public String toString() {
        return "Viking";
    }
}
