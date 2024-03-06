package logic.monsters.tank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;

public class tank2 extends Monster implements atk , spatk{
    public tank2() {
        super(Config.tankmonster_maxhp + 5, Config.atktype1 + 2,
                Config.tankmonster_def +1, Config.magictype1 ,Config.tankmonster_magicdef);
    }

    @Override
    public void attack(BaseCharacter target) {
        target.decreaseHp_def(this.getAtk());
    }

    //decrease def target for 1 turn
    @Override
    public void special_attack(BaseCharacter target) {
        int def = target.getDefense()  ;
        target.setDefense(def - 1);
    }
}
