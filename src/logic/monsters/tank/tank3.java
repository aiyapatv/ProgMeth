package logic.monsters.tank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;

public class tank3 extends Monster implements atk , spatk {

    public tank3() {
        super(Config.tankmonster_maxhp + 3, Config.atktype1 +1,
                Config.tankmonster_def +2, Config.magictype1 ,Config.tankmonster_magicdef);
    }

    @Override
    public void attack(BaseCharacter target) {
        target.decreaseHp_def(this.getAtk());
    }

    //decrease power target for  turn
    @Override
    public void special_attack(BaseCharacter target) {
        int power = target.getPower()  ;
        target.setPower(power - 2);
    }
}
