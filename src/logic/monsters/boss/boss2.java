package logic.monsters.boss;

import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.*;
public class boss2 extends Monster implements atk , magicatk , spatk {
    public boss2() {
        super(100, 7 ,
                10 , 7 , 10);
    }

    public void attack(BaseCharacter target) {
        target.decreaseHp_def(this.getAtk());
    }

    @Override
    public void magic_attack(BaseCharacter target) {
        target.decreaseHp_magicdef(this.getMagicatk()) ;
    }

    //attack and delete def and magic def for 1 turn
    @Override
    public void special_attack(BaseCharacter target) {
        target.decreaseHp_def(this.getAtk()/3) ;
        target.setDefense(0);
        target.setMagic_defense(0);
    }

   //this skill is for all game
    //poison
    //the end of turn decreaseHp = poison_stat then decrease poison_stat 1
    public void skill1(BaseCharacter target){
        target.setPoison_status(target.getPoison_status() + 2);
    }


    //summon basicmonster
    public void skill2(){
        //todo
    }

}
