package logic.character;

public abstract class BaseCharacter {
    private int hp;

    //Params
    public BaseCharacter(int hp){
        setHp(hp);
    }
    public void decreaseHp(int amount){
        setHp(getHp()-amount);
    }

    //Abstract Function
    public abstract int getPower();
    public abstract int getDefense();
    public abstract void attack();


    //Getter,Setter
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = Math.max(hp,0);
    }


}
