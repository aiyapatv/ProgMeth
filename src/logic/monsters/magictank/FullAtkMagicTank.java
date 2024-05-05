package logic.monsters.magictank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.Atk ;
import logic.able.MagicAtk ;
import logic.able.SpAtk ;

public class FullAtkMagicTank extends Monster implements Atk, MagicAtk, SpAtk {
    public FullAtkMagicTank() {
        super(Config.MAGICTANKMONSTERMAXHP, Config.ATKTYPE3 ,
                Config.MAGICTANKMONSTERDEF , Config.MAGICTYPE3 ,Config.MAGICTANKMONSTERMAGICDEF);
        setLevel(1);
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
