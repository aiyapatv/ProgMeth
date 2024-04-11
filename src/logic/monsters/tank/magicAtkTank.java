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
        if (getTime() == 34 )
        {
            setMaxHP(this.getMaxHp() + 5);
            setHp(this.getMaxHp());
            setMagicatk(this.getAtk() + 3);
            setDef(this.getMagicdef()+3);
            setLevel(2);
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
