package logic.potion;

import logic.game.GameController;
import logic.potion.BasePotion;
import logic.usage.Healable;
import logic.usage.Upgradable;

import java.util.Arrays;

public class HealingPotion extends BasePotion implements Healable, Upgradable {
    private int level ;
    private final int MAX_LEVEL ;
    private final int[] RECOVER_PT ;

    public HealingPotion() {
        super("HealingPotion");
        setLevel(0);
        this.MAX_LEVEL = 3 ;
        this.RECOVER_PT = new int[]{10,17,21,30} ;
    }

    @Override
    public String toString() {
        return getName() + " (+" + getRecoverPoint() + " HP, Level: "+ getLevel()+")";
    }

    @Override
    public int getRecoverPoint() {
        return RECOVER_PT[level];
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        if(level < 0 || level > MAX_LEVEL){
            this.level = 0 ;
            return;
        }
        this.level = level ;
    }

    @Override
    public int getMaxLevel() {
        return MAX_LEVEL;
    }

    @Override
    public void usePotion() {
        GameController.getInstance().getCharacter().setHp(GameController.getInstance().getCharacter().getHp() + getRecoverPoint());
        GameController.getInstance().setHealingPotion(GameController.getInstance().getHealingPotion() - 1);
    }
}
