package logic.monsters.tank;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;

//basic at
//no special
public class tank1 extends Monster {
    public tank1() {
        super(Config.tankmonster_maxhp, Config.atktype1,
                Config.tankmonster_def , Config.magictype1 ,Config.tankmonster_magicdef);
    }

    @Override
    public void attack(BaseCharacter target) {
        target.decreaseHp_def(this.getAtk());
    }

    @Override
    public void magic_attack(BaseCharacter target) {
    return;
    }

    @Override
    public void special_attack(BaseCharacter target) {
    return;
    }
}
