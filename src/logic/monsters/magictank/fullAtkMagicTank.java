package logic.monsters.magictank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;

public class fullAtkMagicTank extends Monster implements atk, magicAtk ,spAtk{
    public fullAtkMagicTank() {
        super(Config.MAGICTANKMONSTERMAXHP, Config.ATKTYPE3 ,
                Config.MAGICTANKMONSTERDEF , Config.MAGICTYPE3 ,Config.MAGICTANKMONSTERMAGICDEF);
        setPicture("m1_i_1");
        setPicture2("m1_i_2");
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
        target.decreaseHpMagicDef(this.getMagicAtk() /2 );
        target.decreaseHpDef(this.getAtk()/2);
    }
}
