package logic.monsters.magictank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;
public class MagicTank extends Monster implements Atk, SpAtk {


    public MagicTank() {
        //Magictank1
        super(Config.MAGICTANKMONSTERMAXHP, Config.ATKTYPE1 ,
                Config.MAGICTANKMONSTERDEF , Config.MAGICTYPE1 ,Config.MAGICTANKMONSTERMAGICDEF);
        setLevel(1);
        setPicture("m9_r_1");
        setPicture2("m9_r_2");
        //Magictank2
        if (getTime() == 21){
            setMaxHp(this.getMaxHp() + 5);
            setHp(this.getMaxHp());
            setAtk(this.getAtk() + 2);
            setMagicDef(this.getMagicDef()+2);
            setLevel(2);
            setPicture("m5_i_1");
            setPicture2("m5_i_2");
        }
        //Magictank3
        else if (getTime() == 23){
            setMaxHp(this.getMaxHp() + 6);
            setHp(this.getMaxHp());
            setAtk(this.getAtk() + 4);
            setMagicDef(this.getMagicDef()+4);
            setLevel(3);
            setPicture("m22_i_1");
            setPicture2("m22_i_2");
        }
    }

    public void attack(BaseCharacter target) {
        target.decreaseHpDef(this.getAtk());
    }


    @Override
    public void specialAttack(BaseCharacter target) {
        if (getLevel() == 1 )
        {
            target.setAttackStat(0);
        }
        else if (getLevel() == 2)
        {
            int magic_def = target.getMagicDefense()  ;
            target.setMagicDefense(magic_def - 1);
        }
        else if (getLevel() == 3 )
        {
            int magicPower = target.getMagicPower()  ;
            target.setPower(magicPower - 2);
        }
    }
}
