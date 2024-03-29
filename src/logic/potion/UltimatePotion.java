package logic.potion;

import logic.usage.AttBuffable;
import logic.usage.Healable;

public class UltimatePotion extends BasePotion implements AttBuffable, Healable{

    private final int ATT_BUFF;
    private final int BUFF_TURN;
    private final int RECOVER_PT;

    public UltimatePotion() {
        super("UltimatePotion");
        ATT_BUFF = 5;
        BUFF_TURN = 3;
        RECOVER_PT = 5;
    }

    @Override
    public int getAttBuff() {
        return ATT_BUFF;
    }

    @Override
    public int getBuffTurn() {
        return BUFF_TURN;
    }

    @Override
    public int getRecoverPoint() {
        return RECOVER_PT;
    }

    @Override
    public String toString() {
        return getName() + " (+" + getAttBuff() + " Att for next 3 turns, +" + getRecoverPoint() + " HP)";
    }

}
