package logic.monsters.basic;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;

public class buff extends Monster implements buffFriend {
    public buff() {
        super(Config.basemonster_maxhp , Config.atktype3 ,
                Config.basemonster_def , Config.magictype3 ,Config.basemonster_magicdef);
    }

    @Override
    public void buff(Monster target) {
        target.setAtk(target.getAtk() + this.getAtk());
        target.setMagicatk(target.getMagicatk() + this.getMagicatk());
        target.setDef(target.getDef() + this.getDef());
        target.setMagicatk(target.getMagicdef() + this.getMagicdef());
    }
}
