package logic.monsters.basic;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;
public class fullBasic extends Monster implements atk , magicatk ,spatk {

    public fullBasic() {
        super(Config.BASEMONSTER_MAXHP , Config.atktype3 ,
                Config.basemonster_def , Config.magictype3 ,Config.basemonster_magicdef);
        setPicture("m10_i_1");
        setPicture2("m10_i_2");
    }

    @Override
    public void attack(BaseCharacter target) {
        target.decreaseHpDef(this.getAtk());
    }

    @Override
    public void magic_attack(BaseCharacter target) {
        target.decreaseHpMagicDef(this.getMagicAtk());
    }

    @Override
    public void special_attack(BaseCharacter target) {
        target.decreaseHpMagicDef(this.getMagicAtk()/2);
        target.decreaseHpDef(this.getAtk()/2);
    }
}
