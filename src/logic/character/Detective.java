package logic.character;

import Utils.Config;
import javafx.scene.image.ImageView;
import logic.monsters.Monster;

public class Detective extends BaseCharacter{
    public Detective() {
        super(Config.DETECTIVEMAXHP, Config.DETECTIVEPOWER, Config.DETECTIVEDEFENSE,Config.DETECTIVEMAGICPOWER,Config.DETECTIVEMAGICDEFENSE);
    }

    @Override
    public String toString() {
        return "Detective";
    }


}
