package logic.character;

public abstract class BaseCharacter {
    private int hp;

    private int defense ;

    private int magic_defense ;

    private int power ;

    private int magicpower;

    private int poison_status = 0 ;

    //Params
    public BaseCharacter(int hp){
        setHp(hp);
    }
    public int decreaseHp_def(int amount ){
        int def = this.getDefense() ;
        int hp = this.getHp() ;
        int b = (Math.max(1 , def - amount)) ;
        int a =  Math.max(0 , hp - (Math.max(1 , def - amount))) ;
        setHp(a) ;
        return b ;
    }

    public int decreaseHp_magicdef(int amount ){
        int magicdef = this.getMagic_defense() ;
        int hp = this.getHp() ;
        int b = (Math.max(1 , magicdef - amount)) ;
        int a = Math.max(0 , hp - (Math.max(1 , magicdef - amount))) ;
        setHp(a);
        return b ;
    }


    //Abstract Function
    public abstract void attack();


    //Getter,Setter
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = Math.max(hp,0);
    }

    //geter setter


    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = Math.max(0 , defense);
    }

    public int getMagic_defense() {
        return magic_defense;
    }

    public void setMagic_defense(int magic_defense) {
        this.magic_defense = Math.max(0, magic_defense) ;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = Math.max(0, power) ;
    }

    public int getMagicpower() {
        return magicpower;
    }

    public void setMagicpower(int magicpower) {
        this.magicpower = Math.max(0, magicpower) ;
    }

    public int getPoison_status() {
        return poison_status;
    }

    public void setPoison_status(int poison_status) {
        this.poison_status = poison_status;
    }
}
