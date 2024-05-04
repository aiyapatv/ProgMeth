package logic.monsters.basic;

import Utils.Config;
import logic.monsters.Monster;
import logic.able.* ;

public class Buff extends Monster implements BuffFriend {
    public Buff() {
        super(Config.BASEMONSTERMAXHP , Config.ATKTYPE3 ,
                Config.BASEMONSTERDEF , Config.MAGICTYPE3 ,Config.BASEMONSTERMAGICDEF);
        setLevel(1);
        setPicture("m14_i_1");
        setPicture2("m14_i_2");
    }

    @Override
    public void buff(Monster target) {
        target.setAtk(target.getAtk() + 3);
        target.setMagicAtk(target.getMagicAtk() + 3);
    }
}
