package logic.monsters.magictank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.game.GameController;
import logic.monsters.Monster;
import logic.able.* ;

public class MagicAtkMagicTank1 extends Monster implements magicatk , spatk {



    public MagicAtkMagicTank1() {
        //magicatkmagintank1
        super(Config.magictankmonster_maxhp, Config.atktype2 ,
                Config.magictankmonster_def , Config.magictype2 ,Config.magictankmonster_magicdef);
        setLevel(1);
        setPicture("m8_i_1");
        setPicture2("m8_i_2");
        //magicatkmagintank2
        if (getTime() == 12);
        {
            setMaxHP(this.getMaxHp() + 5);
            setHp(this.getMaxHp());
            setMagicatk(this.getAtk() + 2);
            setMagicdef(this.getMagicdef()+2);
            setLevel(2);
            setPicture("m12_i_1");
            setPicture2("m12_i_2");
        }
    }

    @Override
    public void magic_attack(BaseCharacter target) {
        target.decreaseHp_magicdef(this.getMagicatk());
    }


    @Override
    public void special_attack(BaseCharacter target) {
        if (getLevel() == 1){
         return;
        }
        else {
            this.setMagicdef(this.getMagicdef() + 4);
        }
    }
}
