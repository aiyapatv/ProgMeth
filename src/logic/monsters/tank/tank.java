package logic.monsters.tank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;

//basic at
//no special
public class tank extends Monster implements atk  , spatk{
    public tank() {
        //tank1
        super(Config.tankmonster_maxhp, Config.atktype1,
                Config.tankmonster_def , Config.magictype1 ,Config.tankmonster_magicdef);
        setLevel(1);
        //tank2
        if (getTime() == 65)
        {
            setMaxHP(this.getMaxHp() + 5);
            setHp(this.getMaxHp());
            setAtk(this.getAtk() + 2);
            setDef(this.getMagicdef()+2);
            setLevel(2);
            //tank3
        } else if (getTime() == 98) {

            setMaxHP(this.getMaxHp() + 8);
            setHp(this.getMaxHp());
            setAtk(this.getAtk() + 4);
            setDef(this.getMagicdef()+4);
            setLevel(3);
        }
    }

    @Override
    public void attack(BaseCharacter target) {
        target.decreaseHp_def(this.getAtk());
    }

    @Override
    public void special_attack(BaseCharacter target) {
        if (getLevel() ==1 )
        {
            return;
        }
        //decrease def target for 1 turn
        else if (getLevel() == 2 )
        {

            int def = target.getDefense()  ;
            target.setDefense(def - 1);
        }
        //decrease power target for  turn
        else if (getLevel() == 3) {
            int power = target.getPower()  ;
            target.setPower(power - 2);
        }
    }
}
