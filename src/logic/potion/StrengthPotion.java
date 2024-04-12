package logic.potion;


import logic.game.GameController;
import logic.usage.AttBuffable;
import logic.usage.Upgradable;

import java.util.Arrays;

public class StrengthPotion extends BasePotion implements AttBuffable, Upgradable {
    private int level ;
    private final int  MAX_LEVEL ;
    private final int[] ATT_BUFF ;
    private final int BUFF_TURN ;

    public StrengthPotion() {
        super("StrengthPotion");
        setLevel(0);
        this.MAX_LEVEL = 3 ;
        this.BUFF_TURN = 3 ;
        this.ATT_BUFF = new int[]{3,5,7,10} ;
    }

    @Override
    public String toString() {
        return getName() + " (+" + getAttBuff() +  " Att for next " +
                getBuffTurn()+ " turns, Level: "+ getLevel()+")";
    }

    @Override
    public int getAttBuff() {
        return ATT_BUFF[getLevel()];
    }

    @Override
    public int getBuffTurn() {
        return BUFF_TURN;
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
        GameController.getInstance().getCharacter().setPower(GameController.getInstance().getCharacter().getPower() + getAttBuff());
        GameController.getInstance().setStrengthPotion(GameController.getInstance().getStrengthPotion() - 1);

    }
}
