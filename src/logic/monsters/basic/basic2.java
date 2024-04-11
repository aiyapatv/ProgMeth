package logic.monsters.basic;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;

public class basic2 extends Monster implements  atk , spatk{
    public basic2() {
        super(Config.BASEMONSTER_MAXHP + 6, Config.atktype1 +2,
                Config.basemonster_def , Config.magictype1 ,Config.basemonster_magicdef);
    }

    @Override
    public void attack(BaseCharacter target) {
        target.decreaseHp_def(this.getAtk());
    }


    //switch power and magicpower 1 turn
    @Override
    public void special_attack(BaseCharacter target) {
        int magic = target.getMagicpower() ;
        int atk = target.getPower() ;
        target.setPower(magic);
        target.setMagicpower(atk);

    }
}
