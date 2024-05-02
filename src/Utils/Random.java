package Utils;

import logic.game.GameController;
import logic.monsters.Monster;
import logic.monsters.basic.basic;
import logic.monsters.basic.buff;
import logic.monsters.basic.fullBasic;
import logic.monsters.basic.magicBasic;
import logic.monsters.magictank.fullAtkMagicTank;
import logic.monsters.magictank.magicAtkMagicTank;
import logic.monsters.magictank.magicTank;
import logic.monsters.tank.fullAtkTank;
import logic.monsters.tank.magicAtkTank;
import logic.monsters.tank.tank;
import logic.potion.*;

public class Random {
    private static final java.util.Random rng = new java.util.Random();
    private static int MAX_NUMBER;

    public static Monster randomMonsterImage(){
        MAX_NUMBER  = 10;
        int randomNum = rng.nextInt(MAX_NUMBER) + 1;
        if ( randomNum == 1 ){
            return new basic();
        } else if ( randomNum == 2 ) {
            return new buff();
        } else if ( randomNum == 3 ) {
            return new fullBasic();
        } else if ( randomNum == 4 ) {
            return new magicBasic();
        } else if ( randomNum == 5 ) {
            return new fullAtkMagicTank();
        } else if ( randomNum == 6 ) {
            return new magicAtkMagicTank();
        } else if ( randomNum == 7 ) {
            return new magicTank();
        } else if ( randomNum == 8 ) {
            return new fullAtkTank();
        } else if ( randomNum == 9 ) {
            return new magicAtkTank();
        } else {
            return new tank();
        }
    }

    public static int randomMonsterAmount(){
        MAX_NUMBER  = 3;
        return rng.nextInt(MAX_NUMBER) + 1;
    }

    public static int randomMonsterAtk(int number){
        return rng.nextInt(number) + 1;
    }

    public static int randomPotionAmount(){
        MAX_NUMBER = 3;
        return rng.nextInt(MAX_NUMBER);
    }

    public static BasePotion randomDropPotion(){
        MAX_NUMBER = 4;
        int randomNum = rng.nextInt(MAX_NUMBER);
        if ( randomNum == 1 ){
            GameController.getInstance().setHealingPotion(GameController.getInstance().getHealingPotion()+1);
            return new HealingPotion();
        } else if ( randomNum == 2 ) {
            GameController.getInstance().setPill(GameController.getInstance().getPill()+1);
            return new Pill();
        } else if ( randomNum == 3 ) {
            GameController.getInstance().setStrengthPotion(GameController.getInstance().getStrengthPotion()+1);
            return new StrengthPotion();
        } else {
            GameController.getInstance().setUltimatePotion(GameController.getInstance().getUltimatePotion()+1);
            return new UltimatePotion();
        }
    }
}
