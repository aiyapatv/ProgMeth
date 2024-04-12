package logic.monsters.magictank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;

public class fullAtkMagicTank extends Monster implements atk, magicatk ,spatk{
    public fullAtkMagicTank() {
        super(Config.magictankmonster_maxhp, Config.atktype3 ,
                Config.magictankmonster_def , Config.magictype3 ,Config.magictankmonster_magicdef);
        setPicture("m1_i_1");
        setPicture2("m1_i_2");
    }

    @Override
    public void attack(BaseCharacter target) {
        target.decreaseHpDef(this.getAtk());
    }

    @Override
    public void magic_attack(BaseCharacter target) {
        target.decreaseHpMagicDef(this.getMagicAtk());
    }

    @Override
    public void special_attack(BaseCharacter target) {
        target.decreaseHpMagicDef(this.getMagicAtk() /2 );
        target.decreaseHpDef(this.getAtk()/2);
    }
}
