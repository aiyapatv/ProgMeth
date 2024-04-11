package logic.monsters.basic;

import Utils.Config;
import logic.able.magicatk;
import logic.character.BaseCharacter;
import logic.game.GameController;
import logic.monsters.Monster;
import logic.able.* ;

public class magicbasic1 extends Monster implements magicatk , spatk  {


    public magicbasic1() {
        //magicbasic 1
        super(Config.BASEMONSTER_MAXHP, Config.atktype2 ,
                Config.basemonster_def , Config.magictype2 ,Config.basemonster_magicdef);
        setLevel(1);
        //magicbasic2
        if (getTime() == 21){
            setMaxHP(this.getMaxHp() + 4);
            setHp(this.getMaxHp());
            setMagicatk(this.getMagicatk() + 3);
            setLevel(2); ;

        }
    }
    @Override
    public void magic_attack(BaseCharacter target) {
        target.decreaseHp_magicdef(this.getMagicatk());
    }

    @Override
    public void special_attack(BaseCharacter target) {
        if (getLevel()==1){
            return;
        }
        else {
            int magic_def = target.getMagic_defense() ;
            int def = target.getDefense() ;
            target.setDefense(magic_def);
            target.setMagic_defense(def);
        }
    }
}
