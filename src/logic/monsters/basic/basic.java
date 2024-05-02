package logic.monsters.basic;

import Utils.Config;
import logic.monsters.Monster;
import logic.able.* ;
import logic.character.* ;
public class basic extends Monster implements atk , spAtk{

    public basic() {
        //basic1
        super(Config.BASEMONSTERMAXHP, Config.ATKTYPE1 ,
                Config.BASEMONSTERDEF , Config.MAGICTYPE1 ,Config.BASEMONSTERMAGICDEF);
        setLevel(1);
        setPicture("m7_i_1");
        setPicture2("m7_i_2");
        //basic2
        if (getTime() == 21){
            setMaxHp(this.getMaxHp() + 6);
            setHp(this.getMaxHp());
            setAtk(this.getAtk() + 2);
            setLevel(2);
            setPicture("m15_i_1");
            setPicture2("m15_i_2");
        }
    }

    @Override
    public void attack(BaseCharacter target) {
        target.decreaseHpDef(this.getAtk());
    }


    //switch power and magicPower 1 turn
    @Override
    public void specialAttack(BaseCharacter target) {
        if (getLevel() == 1){
            return;
        }
        else {
            int magic = target.getMagicPower() ;
            int atk = target.getPower() ;
            target.setPower(magic);
            target.setMagicPower(atk);
        }

    }
}
