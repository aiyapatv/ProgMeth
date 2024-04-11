package logic.monsters.basic;

import Utils.Config;
import logic.monsters.Monster;
import logic.able.* ;
import logic.character.* ;
public class basic1 extends Monster implements atk {
    public basic1() {
        super(Config.BASEMONSTER_MAXHP, Config.atktype1 ,
                Config.basemonster_def , Config.magictype1 ,Config.basemonster_magicdef);
    }

    public void attack(BaseCharacter target) {
        target.decreaseHp_def(this.getAtk());
    }


}
