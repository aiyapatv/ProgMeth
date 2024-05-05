package logic.monsters.boss;

import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.Atk ;
import logic.able.SpAtk ;

public class Boss1 extends Monster implements Atk, SpAtk {
    public Boss1() {
        super(70, 20 ,
                7 , 5 , 7);
        setPicture("m2_i_1");
        setPicture2("m2_i_2");
    }

    public void attack(BaseCharacter target) {
        target.decreaseHpDef(this.getAtk());
    }


    //attack and heal itself
    @Override
    public void specialAttack(BaseCharacter target) {
        target.decreaseHpDef(this.getAtk());
        int hp = this.getHp();
        this.setHp(hp + target.decreaseHpDef(this.getAtk()));
    }

    //this skill is ef for 1 turn
    //if target hp is even hele itself 3
    //else +2 atk
    public void skill1(BaseCharacter target){
        if (target.getHp()%2 == 0){
            this.setHp(this.getHp() + 20);
        }
        else {
            this.setAtk(this.getAtk() + 7);
        }
    }

    //switch hp to atk for 1 turn
    public void skill2(){
        if (this.getHp() - 10 <= 0)
        {
            return ;
        }
        else {
            this.setHp(this.getHp() - 10);
            this.setAtk(this.getAtk() + 10);
        }
    }
}
