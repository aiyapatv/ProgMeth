package logic.monsters.basic;

import Utils.Config;
import logic.monsters.Monster;
import logic.able.* ;
import logic.character.* ;
public class basic extends Monster implements atk , spatk{




    public basic() {
        //basic1
        super(Config.BASEMONSTER_MAXHP, Config.atktype1 ,
                Config.basemonster_def , Config.magictype1 ,Config.basemonster_magicdef);
        setLevel(1);
        //basic2
        if (getTime() == 21){
            setMaxHP(this.getMaxHp() + 6);
            setHp(this.getMaxHp());
            setAtk(this.getAtk() + 2);
            setLevel(2); ;
        }
    }

    public void attack(BaseCharacter target) {
        target.decreaseHp_def(this.getAtk());
    }


    //switch power and magicpower 1 turn
    @Override
    public void special_attack(BaseCharacter target) {
        if (getLevel() == 1){
            return;
        }
        else {
            int magic = target.getMagicpower() ;
            int atk = target.getPower() ;
            target.setPower(magic);
            target.setMagicpower(atk);
        }

    }
}
