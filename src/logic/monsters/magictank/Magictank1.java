package logic.monsters.magictank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;
public class Magictank1 extends Monster implements atk {
    public Magictank1() {
        super(Config.magictankmonster_maxhp, Config.atktype1 ,
                Config.magictankmonster_def , Config.magictype1 ,Config.magictankmonster_magicdef);
    }

    public void attack(BaseCharacter target) {
        target.decreaseHp_def(this.getAtk());
    }


}
