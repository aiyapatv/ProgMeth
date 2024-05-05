package logic.monsters.boss;

import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.Atk ;
import logic.able.MagicAtk ;
import logic.able.SpAtk ;
public class Boss2 extends Monster implements Atk, MagicAtk, SpAtk {
    public Boss2() {
        super(70, 10,
                5, 10 , 5);
        setPicture("m6_i_1");
        setPicture2("m6_i_2");
    }

    public void attack(BaseCharacter target) {
        target.decreaseHpDef(this.getAtk());
    }

    @Override
    public void magicAttack(BaseCharacter target) {
        target.decreaseHpMagicDef(this.getMagicAtk()) ;
    }

    //attack and delete def and magic def for 1 turn
    @Override
    public void specialAttack(BaseCharacter target) {
        target.decreaseHpDef(this.getAtk()/3) ;
        target.setDefense(0);
        target.setMagicDefense(0);
    }

    //incress 2 time of atk and magic atk for all game
    public void skill1(){
        setAtk(this.getAtk()*2);
        setMagicAtk(this.getMagicAtk()*2);
    }

}
