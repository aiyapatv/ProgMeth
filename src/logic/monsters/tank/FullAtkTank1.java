package logic.monsters.tank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;
public class FullAtkTank1 extends Monster implements atk , magicatk , spatk {
    public FullAtkTank1() {
        super(Config.tankmonster_maxhp   , Config.atktype3 ,
                Config.tankmonster_def , Config.magictype3  ,Config.tankmonster_magicdef);
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
        target.decreaseHp_def(this.getAtk() /2);
        target.decreaseHp_magicdef(this.getMagicatk()/2);
    }

}
