package logic.monsters.basic;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;
public class fullbasic extends Monster implements atk , magicatk ,spatk {

    public fullbasic() {
        super(Config.BASEMONSTER_MAXHP , Config.atktype3 ,
                Config.basemonster_def , Config.magictype3 ,Config.basemonster_magicdef);
        setPicture("m10_i_1");
        setPicture2("m10_i_2");
    }

    @Override
    public void attack(BaseCharacter target) {
        target.decreaseHp_def(this.getAtk());

    }

    @Override
    public void magic_attack(BaseCharacter target) {
        target.decreaseHp_magicdef(this.getMagicatk());
    }

    @Override
    public void special_attack(BaseCharacter target) {
        target.decreaseHp_magicdef(this.getMagicatk()/2);
        target.decreaseHp_def(this.getAtk()/2);
    }
}
