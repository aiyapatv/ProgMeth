package logic.monsters.magictank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;
public class magicTank extends Monster implements atk  , spatk{


    public magicTank() {
        //Magictank1
        super(Config.magictankmonster_maxhp, Config.atktype1 ,
                Config.magictankmonster_def , Config.magictype1 ,Config.magictankmonster_magicdef);
        setLevel(1);
        //Magictank2
        if (getTime() == 21){
            setMaxHP(this.getMaxHp() + 5);
            setHp(this.getMaxHp());
            setAtk(this.getAtk() + 2);
            setMagicdef(this.getMagicdef()+2);
            setLevel(2);
        }
        //Magictank3
        else if (getTime() == 23){
            setMaxHP(this.getMaxHp() + 6);
            setHp(this.getMaxHp());
            setAtk(this.getAtk() + 4);
            setMagicdef(this.getMagicdef()+4);
            setLevel(3);
        }
    }

    public void attack(BaseCharacter target) {
        target.decreaseHp_def(this.getAtk());
    }


    @Override
    public void special_attack(BaseCharacter target) {
        if (getLevel() == 1 )
        {
            return;
        }
        else if (getLevel() == 2)
        {
            int magic_def = target.getMagic_defense()  ;
            target.setMagic_defense(magic_def - 1);
        }
        else if (getLevel() == 3 )
        {
            int magicpower = target.getMagicpower()  ;
            target.setPower(magicpower - 2);
        }
    }
}
