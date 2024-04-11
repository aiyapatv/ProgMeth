package logic.monsters.tank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.*;

public class MagicAtkTank1 extends Monster implements magicatk , spatk {
    public MagicAtkTank1() {
        super(Config.tankmonster_maxhp , Config.atktype2 ,
                Config.tankmonster_def , Config.magictype2 ,Config.tankmonster_magicdef);
        setLevel(1);
        setPicture("m11_i_1");
        setPicture2("m11_i_2");
        if (getTime() == 34 )
        {
            setMaxHP(this.getMaxHp() + 5);
            setHp(this.getMaxHp());
            setMagicatk(this.getAtk() + 3);
            setDef(this.getMagicdef()+3);
            setLevel(2);
            setPicture("m19_i_1");
            setPicture2("m19_i_2");
        }
    }


    @Override
    public void magic_attack(BaseCharacter target) {
        target.decreaseHp_magicdef(this.getMagicatk());
    }

    @Override
    public void special_attack(BaseCharacter target) {
        if (getLevel() ==1 )
        {
            return;
        } else if (getLevel() == 2 ) {
            this.setMagicatk(this.getMagicatk() + 2 );
        }
    }
}
