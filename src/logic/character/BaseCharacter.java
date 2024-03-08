package logic.character;

public abstract class BaseCharacter {
    protected int maxHp;
    protected int hp;
    protected int power;
    protected int def;

    //Params
    public BaseCharacter(int maxHp , int power , int def){
        setMaxHp(maxHp);
        setPower(power);
        setDef(def);

    }
    public void decreaseHp(int amount){
        setHp(getHp()-amount);
    }

    //Abstract Function

    public abstract void attack();


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


    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = Math.max(def , 0);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(maxHp,0);
    }
}
