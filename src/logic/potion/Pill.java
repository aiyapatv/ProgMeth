package logic.potion;


import logic.usage.Healable;

public class Pill extends BasePotion implements Healable {
    private final int RECOVERY_PT ;

    public Pill() {
        super("Pill");
        this.RECOVERY_PT = 2 ;
    }

    @Override
    public String toString() {
        return getName() + " (+" + getRecoverPoint() + " HP)";
    }

    @Override
    public int getRecoverPoint() {
        return RECOVERY_PT;
    }
}
