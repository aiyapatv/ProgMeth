package logic.monsters.magictank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;

public class MagicAtkMagicTank2 extends Monster implements magicatk , spatk {
    public MagicAtkMagicTank2() {
        super(Config.magictankmonster_maxhp  + 5, Config.atktype2 ,
                Config.magictankmonster_def  , Config.magictype2 +2 ,Config.magictankmonster_magicdef + 2);
    }



    @Override
    public void magic_attack(BaseCharacter target) {
        target.decreaseHp_magicdef(this.getMagicatk());
    }

    @Override
    public void special_attack(BaseCharacter target) {
        this.setMagicdef(this.getMagicdef() + 2);
    }
}
