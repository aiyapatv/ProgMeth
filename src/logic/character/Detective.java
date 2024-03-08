package logic.character;

import Utils.Config;
import javafx.scene.image.ImageView;

public class Detective extends BaseCharacter{
    public Detective() {
        super(Config.DETECTIVEMAXHP);
    }

    @Override
    public int getPower() {
        return Config.DETECIVEPOWER;
    }

    @Override
    public int getDefense() {
        return Config.DETECTIVEDEFENSE;
    }

    @Override
    public void attack() {

    }

    public String toString() {
        return "Detective";
    }

}
