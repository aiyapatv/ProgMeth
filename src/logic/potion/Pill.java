package logic.potion;


import logic.game.GameController;
import logic.usage.Healable;

public class Pill extends BasePotion implements Healable {
    private final int RECOVERY_PT ;

    public Pill() {
        super("Pill");
        this.RECOVERY_PT = 7 ;
    }

    @Override
    public String toString() {
        return getName() + " (+" + getRecoverPoint() + " HP)";
    }

    @Override
    public int getRecoverPoint() {
        return RECOVERY_PT;
    }

    @Override
    public void usePotion() {
        GameController.getInstance().getCharacter().setHp(GameController.getInstance().getCharacter().getHp() + getRecoverPoint());
        GameController.getInstance().setPill(GameController.getInstance().getPill() - 1);
    }
}
