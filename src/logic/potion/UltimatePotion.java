package logic.potion;

import logic.game.GameController;
import logic.usage.AttBuffable;
import logic.usage.Healable;

public class UltimatePotion extends BasePotion implements AttBuffable, Healable{

    private final int ATT_BUFF;
    private final int BUFF_TURN;
    private final int RECOVER_PT;

    public UltimatePotion() {
        super("UltimatePotion");
        ATT_BUFF = 7;
        BUFF_TURN = 3;
        RECOVER_PT = 10;
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

    @Override
    public void usePotion() {
        GameController.getInstance().getCharacter().setPower(GameController.getInstance().getCharacter().getPower() + getAttBuff());
        GameController.getInstance().getCharacter().setHp(GameController.getInstance().getCharacter().getHp() + getRecoverPoint());
        GameController.getInstance().setUltimatePotion(GameController.getInstance().getUltimatePotion() - 1);
    }
}
