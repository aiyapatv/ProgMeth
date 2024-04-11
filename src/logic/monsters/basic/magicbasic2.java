package logic.monsters.basic;

import Utils.Config;
import logic.able.* ;
import logic.character.BaseCharacter;
import logic.monsters.Monster;

public class magicbasic2 extends Monster implements magicatk , spatk {
    public magicbasic2() {
        super(Config.BASEMONSTER_MAXHP + 4, Config.atktype2  ,
                Config.basemonster_def , Config.magictype2 + 3  ,Config.basemonster_magicdef);
    }
    @Override
    public void magic_attack(BaseCharacter target) {
        target.decreaseHp_magicdef(this.getMagicatk());
    }
    //switch magic_def and def
    @Override
    public void special_attack(BaseCharacter target) {
        int magic_def = target.getMagic_defense() ;
        int def = target.getDefense() ;
        target.setDefense(magic_def);
        target.setMagic_defense(def);

    }
}

