package logic.monsters.tank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.*;

public class magicAtkTank extends Monster implements magicatk , spatk {
    public magicAtkTank() {
        super(Config.tankmonster_maxhp , Config.atktype2 ,
                Config.tankmonster_def , Config.magictype2 ,Config.tankmonster_magicdef);
        setLevel(1);
        setPicture("m11_i_1");
        setPicture2("m11_i_2");
        if (getTime() == 34 )
        {
            setMaxHp(this.getMaxHp() + 5);
            setHp(this.getMaxHp());
            setMagicAtk(this.getAtk() + 3);
            setDef(this.getMagicDef()+3);
            setLevel(2);
            setPicture("m19_i_1");
            setPicture2("m19_i_2");
        }
    }

    @Override
    public void magic_attack(BaseCharacter target) {
        target.decreaseHpMagicDef(this.getMagicAtk());
    }

    @Override
    public void special_attack(BaseCharacter target) {
        if (getLevel() ==1 )
        {
            return;
        } else if (getLevel() == 2 ) {
            this.setMagicAtk(this.getMagicAtk() + 2 );
        }
    }
}
