package logic.monsters.magictank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;

public class MagicAtkMagicTank extends Monster implements MagicAtk, SpAtk {

    public MagicAtkMagicTank() {
        //magicatkmagintank1
        super(Config.MAGICTANKMONSTERMAXHP, Config.ATKTYPE2 ,
                Config.MAGICTANKMONSTERDEF , Config.MAGICTYPE2 ,Config.MAGICTANKMONSTERMAGICDEF);
        setLevel(1);
        setPicture("m8_i_1");
        setPicture2("m8_i_2");
        //magicatkmagintank2
        if(getTime() >= 7);
        {
            setMaxHp(this.getMaxHp() + 10);
            setHp(this.getMaxHp());
            setMagicAtk(this.getAtk() + 4);
            setMagicDef(this.getMagicDef()+2);
            setLevel(2);
            setPicture("m12_i_1");
            setPicture2("m12_i_2");
        }
    }

    @Override
    public void magicAttack(BaseCharacter target) {
        target.decreaseHpMagicDef(this.getMagicAtk());
    }

    @Override
    public void specialAttack(BaseCharacter target) {
        if (getLevel() == 1){
         return;
        }
        else {
            this.setMagicDef(this.getMagicDef() + 2);
        }
    }
}
