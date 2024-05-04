package logic.monsters.basic;

import Utils.Config;
import logic.able.MagicAtk;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;

public class MagicBasic extends Monster implements MagicAtk, SpAtk {
    public MagicBasic() {
        //magicbasic 1
        super(Config.BASEMONSTERMAXHP, Config.ATKTYPE2 ,
                Config.BASEMONSTERDEF , Config.MAGICTYPE2 ,Config.BASEMONSTERMAGICDEF);
        setLevel(1);
        setPicture("m17_i_1");
        setPicture2("m17_i_2");
        //magicbasic2
        if (getTime() == 21){
            setMaxHp(this.getMaxHp() + 4);
            setHp(this.getMaxHp());
            setMagicAtk(this.getMagicAtk() + 3);
            setLevel(2); ;
            setPicture("m18_i_1");
            setPicture2("m18_i_2");
        }
    }

    @Override
    public void magicAttack(BaseCharacter target) {
        target.decreaseHpMagicDef(this.getMagicAtk());
    }

    @Override
    public void specialAttack(BaseCharacter target) {
        if (getLevel()==1){
            target.setAttackStat(0);
        }
        else {
            int magicDef = target.getMagicDefense() ;
            int def = target.getDefense() ;
            target.setDefense(magicDef);
            target.setMagicDefense(def);
        }
    }
}
