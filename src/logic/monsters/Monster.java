package logic.monsters;

import logic.character.BaseCharacter;
import logic.game.GameController;

public abstract class Monster {
    private int maxHp;
    private String picture ;
    private String picture2;
    private int Hp;
    private int atk;
    private int magicAtk;
    private int def;
    private int magicDef ;
    private int  level ;
    private  int time = GameController.getInstance().getTurn();

    private int checkless(int number) {
        if (number < 0) number = 0;
        return number;
    }

    public Monster() {
        this.maxHp = 30;
        this.Hp = maxHp;
        this.atk = 20;
        this.def = 5;
    }

    public Monster(int maxHP, int atk, int def , int magicAtk , int magicDef) {
        this.maxHp = checkless(maxHP);
        this.Hp = checkless(maxHP);
        this.atk = checkless(atk);
        this.def = checkless(def);
        this.magicAtk = checkless(magicAtk);
        this.magicDef = checkless(magicDef) ;
    }

    public boolean isDie(){
        if (this.getHp() <= 0 ){
            return true ;
        }
        else {
            return false ;
        }
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }

    public String toString() {
        return "<" + this.getClass().getSimpleName() + ">" +
                "hp=<" + Hp +
                "> atk=<" + atk +
                "> def=<" + def +
                "> magicDef=<" + magicDef +
                "> magicAtk=<" + magicAtk +
                ">";
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = checkless(maxHp);
    }

    public int getHp() {
        return Hp;
    }

    public void setHp(int hp) {
        if (hp > maxHp) {
            this.Hp = maxHp;
        } else if (hp < 0) {
            this.Hp = 0;
        } else {
            this.Hp = hp;
        }
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = checkless(atk);
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = checkless(def);
    }

    public int getMagicAtk() {
        return magicAtk;
    }

    public void setMagicAtk(int magicAtk) {
        this.magicAtk =checkless(magicAtk) ;
    }

    public int getMagicDef() {
        return magicDef;
    }
    public void setMagicDef(int magicDef) {
        this.magicDef = checkless(magicDef);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }
}
