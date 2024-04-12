package logic.monsters.tank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;
public class fullAtkTank extends Monster implements atk , magicatk , spatk {
    public fullAtkTank() {
        super(Config.tankmonster_maxhp   , Config.atktype3 ,
                Config.tankmonster_def , Config.magictype3  ,Config.tankmonster_magicdef);
        setPicture("m16_i_1");
        setPicture2("m16_i_2");
    }

    @Override
    public void attack(BaseCharacter target) {
        target.decreaseHpDef(this.getAtk());
    }

    @Override
    public void magic_attack(BaseCharacter target) {
        target.decreaseHpMagicDef(this.getMagicAtk());
    }

    @Override
    public void special_attack(BaseCharacter target) {
        target.decreaseHpDef(this.getAtk() /2);
        target.decreaseHpMagicDef(this.getMagicAtk()/2);
    }

}
