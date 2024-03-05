package logic.monsters.magictank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;

public class Magictank3 extends Monster {

    public Magictank3() {
        super(Config.magictankmonster_maxhp +6 , Config.atktype1 +3 ,
                Config.magictankmonster_def , Config.magictype1 ,Config.magictankmonster_magicdef + 1);
    }

    public void attack(BaseCharacter target) {
        target.decreaseHp_def(this.getAtk());
    }


    @Override
    public void magic_attack(BaseCharacter target) {
        return;
    }

    @Override
    public void special_attack(BaseCharacter target) {
        int magicpower = target.getMagicpower()  ;
        target.setPower(magicpower - 2);
    }
}
