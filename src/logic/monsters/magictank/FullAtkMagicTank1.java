package logic.monsters.magictank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;

public class FullAtkMagicTank1 extends Monster implements atk, magicatk ,spatk{
    public FullAtkMagicTank1() {
        super(Config.magictankmonster_maxhp, Config.atktype3 ,
                Config.magictankmonster_def , Config.magictype3 ,Config.magictankmonster_magicdef);
        setPicture("m1_i_1");
        setPicture2("m1_i_2");
    }

    public void attack(BaseCharacter target) {
        target.decreaseHp_def(this.getAtk());
    }


    @Override
    public void magic_attack(BaseCharacter target) {
        target.decreaseHp_magicdef(this.getMagicatk());
    }

    @Override
    public void special_attack(BaseCharacter target) {
        target.decreaseHp_magicdef(this.getMagicatk() /2 );
        target.decreaseHp_def(this.getAtk()/2);
    }
}
