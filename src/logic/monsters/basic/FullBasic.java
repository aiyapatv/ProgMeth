package logic.monsters.basic;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;
public class FullBasic extends Monster implements Atk, MagicAtk, SpAtk {

    public FullBasic() {
        super(Config.BASEMONSTERMAXHP , Config.ATKTYPE3 ,
                Config.BASEMONSTERDEF , Config.MAGICTYPE3 ,Config.BASEMONSTERMAGICDEF);
        setPicture("m10_i_1");
        setPicture2("m10_i_2");
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
        target.decreaseHpMagicDef(this.getMagicAtk()/2);
        target.decreaseHpDef(this.getAtk()/2);
    }
}
