package Monsters;

public class Magictank_Monster extends Monster {

    public Magictank_Monster(int maxHP, int atk, int def , int magicatk , int magicdef) {
        super(maxHP, atk, def , magicatk ,magicdef);
    }

    public void attack(Monster m) {
        //todo//
    }

//    public void buff() {
//        int atk = this.getAtk();
//        int def = this.getDef();
//        this.setAtk(atk + POWER_UP);
//        this.setDef(def + POWER_UP);
//    }

}
