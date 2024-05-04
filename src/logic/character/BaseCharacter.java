package logic.character;

import Utils.Sound;
import logic.monsters.Monster;

public abstract class BaseCharacter {
    protected int maxHp;
    protected int hp;
    protected int power;
    protected int defense ;
    protected int magicDefense ;
    private int magicPower;
    protected int poisonStatus = 0 ;
    protected int attackStat = 0;

    //Params
    public BaseCharacter(int maxHp , int power , int defense,int magicPower, int magicDefense){
        setMaxHp(maxHp);
        setPower(power);
        setHp(maxHp);
        setDefense(defense);
        setMagicPower(magicPower);
        setMagicDefense(magicDefense);
        setAttackStat(0);
    }
    public int decreaseHpDef(int amount){
        int def = this.getDefense() ;
        int hp = this.getHp() ;
        int b = (Math.max(1 , amount - def)) ;
        int a =  Math.max(0 , hp - (Math.max(1 , amount - def))) ;
        setHp(a) ;
        setAttackStat(hp-a);
        return b ;
    }

    public int decreaseHpMagicDef(int amount){
        int magicDef = this.getMagicDefense() ;
        int hp = this.getHp() ;
        int b = (Math.max(1 , amount - magicDef)) ;
        int a = Math.max(0 , hp - (Math.max(1 , amount - magicDef))) ;
        setHp(a);
        setAttackStat(hp-a);
        return b ;
    }

    //Attack & Magic Atk
    public void attack(Monster target){
        int atk = target.getDef()-getPower();
        if(atk<0){
            int remainHp = target.getHp()+atk ;
            target.setHp(remainHp);
        }
    };
    public void magicAttack(Monster target){
        int magicAtk = target.getMagicDef()-getMagicPower();
        if(magicAtk<0){
            int remainHp = target.getHp()+magicAtk ;
            target.setHp(remainHp);
        }
    };

    //Getter,Setter
    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = Math.max(maxHp,0);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = Math.max( power , 0);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if (hp>=getMaxHp()){
            hp = getMaxHp() ;
        }
        this.hp = Math.max(hp,0);
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = Math.max(0 , defense);
    }

    public int getMagicDefense() {
        return magicDefense;
    }

    public void setMagicDefense(int magicDefense) {
        this.magicDefense = Math.max(0, magicDefense) ;
    }

    public int getMagicPower() {
        return magicPower;
    }

    public void setMagicPower(int magicPower) {
        this.magicPower = Math.max(0, magicPower) ;
    }

    public int getAttackStat() {
        return attackStat;
    }

    public void setAttackStat(int attackStat) {
        this.attackStat = attackStat;
    }
}
