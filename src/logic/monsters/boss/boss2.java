package logic.monsters.boss;

import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.*;
public class boss2 extends Monster implements atk , magicAtk , spAtk {
    public boss2() {
        super(100, 4 ,
                7, 4 , 7);
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

   //this skill is for all game
    //poison
    //the end of turn decreaseHp = poison_stat then decrease poison_stat 1
    public void skill1(BaseCharacter target){
        target.setPoison_status(target.getPoison_status() + 2);
    }


    //incress 2 time of atk and magic atk for all game
    public void skill2(){
        setAtk(this.getAtk()*2);
        setMagicAtk(this.getMagicAtk()*2);
    }

}
