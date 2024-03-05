package logic.monsters.magictank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;

public class Magictank1 extends Monster {
    public Magictank1() {
        super(Config.magictankmonster_maxhp, Config.atktype1 ,
                Config.magictankmonster_def , Config.magictype1 ,Config.magictankmonster_magicdef);
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
        return;
    }

}
