package logic.character;

import Utils.Config;
import javafx.scene.image.ImageView;

public class Detective extends BaseCharacter{
    public Detective() {
<<<<<<< HEAD
        super(Config.DETECTIVEMAXHP , Config.DETECIVEPOWER , Config.DETECTIVEDEFENSE);
=======
        super(Config.DETECTIVEMAXHP);
>>>>>>> 6e93848cdbee085381d7ed2d6d8733c663d9286e
    }

    @Override
    public void attack() {

    }

    public String toString() {
        return "Detective";
    }

}
