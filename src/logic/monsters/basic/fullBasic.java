package logic.monsters.basic;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;
public class fullBasic extends Monster implements atk , magicatk ,spatk {

    public fullBasic() {
        super(Config.BASEMONSTER_MAXHP , Config.atktype3 ,
                Config.basemonster_def , Config.magictype3 ,Config.basemonster_magicdef);
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
