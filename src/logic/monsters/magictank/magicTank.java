package logic.monsters.magictank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;
public class magicTank extends Monster implements atk  , spatk{


    public magicTank() {
        //Magictank1
        super(Config.magictankmonster_maxhp, Config.atktype1 ,
                Config.magictankmonster_def , Config.magictype1 ,Config.magictankmonster_magicdef);
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
    public void special_attack(BaseCharacter target) {
        if (getLevel() == 1 )
        {
            return;
        }
        else if (getLevel() == 2)
        {
            int magic_def = target.getMagicDefense()  ;
            target.setMagicDefense(magic_def - 1);
        }
        else if (getLevel() == 3 )
        {
            int magicpower = target.getMagicPower()  ;
            target.setPower(magicpower - 2);
        }
    }
}
