package Monsters;

public abstract class Monster {
    private int maxHP;
    private int Hp;
    private int atk;

    private int magicatk;
    private int def;

    private int magicdef ;

    private int checkless(int number) {
        if (number < 0) {
            return 0;
        } else return number;
    }

    public Monster() {
        this.maxHP = 30;
        this.Hp = maxHP;
        this.atk = 20;
        this.def = 5;
    }

    public Monster(int maxHP, int atk, int def , int magicatk , int magicdef) {
        this.maxHP = checkless(maxHP);
        this.Hp = checkless(maxHP);
        this.atk = checkless(atk);
        this.def = checkless(def);
        this.magicatk = checkless(magicatk);
        this.magicdef = checkless(magicdef) ;
    }

    abstract public void attack(Monster m) ;

    public String getType() {
        return this.getClass().getSimpleName();
    }

    public String toString() {
        return "<" + this.getClass().getSimpleName() + ">" +
                "hp=<" + Hp +
                "> atk=<" + atk +
                "> def=<" + def +
                "> magicdef=<" + magicdef +
                "> magicatk=<" + magicatk +
                ">";
    }

    public int getMaxHp() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = checkless(maxHP);
    }

    public int getHp() {
        return Hp;
    }

    public void setHp(int hp) {
        if (hp > maxHP) {
            this.Hp = maxHP;
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

    public int getMaxHP() {
        return maxHP;
    }

    public int getMagicatk() {
        return magicatk;
    }

    public void setMagicatk(int magicatk) {
        this.magicatk =checkless(magicatk) ;
    }

    public int getMagicdef() {
        return magicdef;
    }

    public void setMagicdef(int magicdef) {
        this.magicdef = checkless(magicdef);
    }
}
