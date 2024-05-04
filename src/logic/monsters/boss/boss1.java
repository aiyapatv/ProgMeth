package logic.monsters.boss;

import Utils.Config;
import logic.character.BaseCharacter;
import logic.monsters.Monster;
import logic.able.* ;

public class boss1 extends Monster implements atk , spAtk {
    public boss1() {
        super(150, 10 ,
                15 , 5 , 15);
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
    public int skill2(int amount){
        if (this.getHp() - amount <= 0)
        {
            return 0;
        }
        else {
            this.setHp(this.getHp() - amount);
            this.setAtk(this.getAtk() + amount);
            return amount ;
        }
    }
}
