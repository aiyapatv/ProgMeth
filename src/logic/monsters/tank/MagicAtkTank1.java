package logic.monsters.tank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.*;

public class MagicAtkTank1 extends Monster implements magicatk  {
    public MagicAtkTank1() {
        super(Config.tankmonster_maxhp , Config.atktype2 ,
                Config.tankmonster_def , Config.magictype2 ,Config.tankmonster_magicdef);
    }


    @Override
    public void magic_attack(BaseCharacter target) {
        target.decreaseHp_magicdef(this.getMagicatk());
    }

}
