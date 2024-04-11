package logic.monsters.basic;

import Utils.Config;
import logic.able.magicatk;
import logic.character.BaseCharacter;
import logic.monsters.Monster;

public class magicbasic1 extends Monster implements logic.able.magicatk {

    public magicbasic1() {
        super(Config.BASEMONSTER_MAXHP, Config.atktype2 ,
                Config.basemonster_def , Config.magictype2 ,Config.basemonster_magicdef);
    }
    @Override
    public void magic_attack(BaseCharacter target) {
        target.decreaseHp_magicdef(this.getMagicatk());
    }
}
