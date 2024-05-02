package logic.monsters.tank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;
public class fullAtkTank extends Monster implements atk , magicAtk , spAtk {
    public fullAtkTank() {
        super(Config.TANKMONSTERMAXHP , Config.ATKTYPE3 ,
                Config.TANKMONSTERDEF , Config.MAGICTYPE3  ,Config.TANKMONSTERMAGICDEF);
        setPicture("m16_i_1");
        setPicture2("m16_i_2");
    }

    @Override
    public void attack(BaseCharacter target) {
        target.decreaseHpDef(this.getAtk());
    }

    @Override
    public void magicAttack(BaseCharacter target) {
        target.decreaseHpMagicDef(this.getMagicAtk());
    }

    @Override
    public void specialAttack(BaseCharacter target) {
        target.decreaseHpDef(this.getAtk() /2);
        target.decreaseHpMagicDef(this.getMagicAtk()/2);
    }

}
