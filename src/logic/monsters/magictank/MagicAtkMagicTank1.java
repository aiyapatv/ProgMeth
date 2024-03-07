package logic.monsters.magictank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;

public class MagicAtkMagicTank1 extends Monster implements magicatk {
    public MagicAtkMagicTank1() {
        super(Config.magictankmonster_maxhp, Config.atktype2 ,
                Config.magictankmonster_def , Config.magictype2 ,Config.magictankmonster_magicdef);
    }

    @Override
    public void magic_attack(BaseCharacter target) {
        target.decreaseHp_magicdef(this.getMagicatk());
    }


}
