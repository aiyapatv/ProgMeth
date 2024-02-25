package Monsters;

public class Boss_Monster extends Monster {

//    private final int DEBUFF = 1;
//    private final int HEAL = 10;

    public Boss_Monster(int maxHP, int atk, int def , int magicatk , int magicdef) {
        super(maxHP, atk, def , magicatk ,magicdef);
    }


    public void attack(Monster m) {
        //todo //
    }

//    public void heal(Monster m) {
//        int hp = m.getHp();
//        m.setHp(hp + HEAL);
//    }

}
