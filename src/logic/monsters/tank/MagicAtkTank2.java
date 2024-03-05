package logic.monsters.tank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;

public class MagicAtkTank2 extends Monster {

    public MagicAtkTank2() {
        super(Config.tankmonster_maxhp +5  , Config.atktype2 ,
                Config.tankmonster_def +1, Config.magictype2 +2 ,Config.tankmonster_magicdef);
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
        this.setMagicatk(this.getMagicatk() + 2 );
    }
}
