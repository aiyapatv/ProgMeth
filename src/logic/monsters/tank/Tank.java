package logic.monsters.tank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;

//basic at
//no special
public class Tank extends Monster implements Atk, SpAtk {
    public Tank() {
        //tank1
        super(Config.TANKMONSTERMAXHP, Config.ATKTYPE1,
                Config.TANKMONSTERDEF , Config.MAGICTYPE1 ,Config.TANKMONSTERMAGICDEF);
        setLevel(1);
        setPicture("m23_i_1");
        setPicture2("m23_i_2");
        //tank2
        if (getTime() == 65)
        {
            setMaxHp(this.getMaxHp() + 5);
            setHp(this.getMaxHp());
            setAtk(this.getAtk() + 2);
            setDef(this.getMagicDef()+2);
            setLevel(2);

            setPicture("m13_i_1");
            setPicture2("m13_i_2");
            //tank3
        } else if (getTime() == 98) {
            setMaxHp(this.getMaxHp() + 8);
            setHp(this.getMaxHp());
            setAtk(this.getAtk() + 4);
            setDef(this.getMagicDef()+4);
            setLevel(3);
            setPicture("m4_i_1");
            setPicture2("m4_i_2");
        }
    }

    @Override
    public void attack(BaseCharacter target) {
        target.decreaseHpDef(this.getAtk());
    }

    @Override
    public void specialAttack(BaseCharacter target) {
        if (getLevel() ==1 )
        {
            return;
        }
        //decrease def target for 1 turn
        else if (getLevel() == 2 )
        {
            int def = target.getDefense()  ;
            target.setDefense(def - 4);
        }
        //decrease power target for  turn
        else if (getLevel() == 3) {
            int power = target.getPower()  ;
            target.setPower(power - 8);
        }
    }
}
