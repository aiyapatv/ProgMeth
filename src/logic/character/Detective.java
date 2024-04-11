package logic.character;

import Utils.Config;
import javafx.scene.image.ImageView;

public class Detective extends BaseCharacter{
    public Detective() {
        super(Config.DETECTIVEMAXHP, Config.DETECIVEPOWER, Config.DETECTIVEDEFENSE);
    }

    @Override
    public void attack() {

    }

    public String toString() {
        return "Detective";
    }

}
