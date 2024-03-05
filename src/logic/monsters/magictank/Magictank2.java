package logic.monsters.magictank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;

public class Magictank2 extends Monster {

    public Magictank2() {
        super(Config.magictankmonster_maxhp +5 , Config.atktype1 +1 ,
                Config.magictankmonster_def , Config.magictype1 ,Config.magictankmonster_magicdef + 2);
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
        int magic_def = target.getMagic_defense()  ;
        target.setMagic_defense(magic_def - 1);
    }
}
