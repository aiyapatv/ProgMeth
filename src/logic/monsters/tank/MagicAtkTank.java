package logic.monsters.tank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.*;

public class MagicAtkTank extends Monster implements MagicAtk, SpAtk {
    public MagicAtkTank() {
        super(Config.TANKMONSTERMAXHP , Config.ATKTYPE2 ,
                Config.TANKMONSTERDEF , Config.MAGICTYPE2 ,Config.TANKMONSTERMAGICDEF);
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
    public void magicAttack(BaseCharacter target) {
        target.decreaseHpMagicDef(this.getMagicAtk());
    }

    @Override
    public void specialAttack(BaseCharacter target) {
        if (getLevel() ==1 )
        {
            target.setAttackStat(0);
        } else if (getLevel() == 2 ) {
            this.setMagicAtk(this.getMagicAtk() + 2 );
        }
    }
}
