package logic.monsters.basic;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;

public class buff extends Monster implements buffFriend {
    public buff() {
        super(Config.BASEMONSTERMAXHP , Config.ATKTYPE3 ,
                Config.BASEMONSTERDEF , Config.MAGICTYPE3 ,Config.BASEMONSTERMAGICDEF);
        setPicture("m14_i_1");
        setPicture2("m14_i_2");
    }

    @Override
    public void buff(Monster target) {
        target.setAtk(target.getAtk() + this.getAtk());
        target.setMagicAtk(target.getMagicAtk() + this.getMagicAtk());
        target.setDef(target.getDef() + this.getDef());
        target.setMagicAtk(target.getMagicDef() + this.getMagicDef());
    }
}
