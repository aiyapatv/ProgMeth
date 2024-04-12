package logic.monsters.basic;

import Utils.Config;
import logic.able.magicatk;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;

public class magicBasic extends Monster implements magicatk , spatk  {
    public magicBasic() {
        //magicbasic 1
        super(Config.BASEMONSTER_MAXHP, Config.atktype2 ,
                Config.basemonster_def , Config.magictype2 ,Config.basemonster_magicdef);
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
    public void magic_attack(BaseCharacter target) {
        target.decreaseHpMagicDef(this.getMagicAtk());
    }

    @Override
    public void special_attack(BaseCharacter target) {
        if (getLevel()==1){
            return;
        }
        else {
            int magicDef = target.getMagicDefense() ;
            int def = target.getDefense() ;
            target.setDefense(magicDef);
            target.setMagicDefense(def);
        }
    }
}
