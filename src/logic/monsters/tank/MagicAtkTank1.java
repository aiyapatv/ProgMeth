package logic.monsters.tank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;

public class MagicAtkTank1 extends Monster {
    public MagicAtkTank1() {
        super(Config.tankmonster_maxhp , Config.atktype2 ,
                Config.tankmonster_def , Config.magictype2 ,Config.tankmonster_magicdef);
    }

    @Override
    public void attack(BaseCharacter target) {
        return;
    }

    @Override
    public void magic_attack(BaseCharacter target) {
        target.decreaseHp_magicdef(this.getMagicatk());
    }

    @Override
    public void special_attack(BaseCharacter target) {
        return;
    }
}
