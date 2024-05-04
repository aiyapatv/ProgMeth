package Utils;

import logic.game.GameController;
import logic.monsters.Monster;
import logic.monsters.basic.Basic;
import logic.monsters.basic.Buff;
import logic.monsters.basic.FullBasic;
import logic.monsters.basic.MagicBasic;
import logic.monsters.magictank.FullAtkMagicTank;
import logic.monsters.magictank.MagicAtkMagicTank;
import logic.monsters.magictank.MagicTank;
import logic.monsters.tank.FullAtkTank;
import logic.monsters.tank.MagicAtkTank;
import logic.monsters.tank.Tank;
import logic.potion.*;

public class Random {
    private static final java.util.Random rng = new java.util.Random();
    private static int MAX_NUMBER;

    public static Monster randomMonsterImage(){
        MAX_NUMBER  = 10;
        int randomNum = rng.nextInt(MAX_NUMBER) + 1;
        if ( randomNum == 1 ){
            return new Basic();
        } else if ( randomNum == 2 ) {
            return new Buff();
        } else if ( randomNum == 3 ) {
            return new FullBasic();
        } else if ( randomNum == 4 ) {
            return new MagicBasic();
        } else if ( randomNum == 5 ) {
            return new FullAtkMagicTank();
        } else if ( randomNum == 6 ) {
            return new MagicAtkMagicTank();
        } else if ( randomNum == 7 ) {
            return new MagicTank();
        } else if ( randomNum == 8 ) {
            return new FullAtkTank();
        } else if ( randomNum == 9 ) {
            return new MagicAtkTank();
        } else {
            return new Tank();
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
