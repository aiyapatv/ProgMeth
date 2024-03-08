package logic.character;

import Utils.Config;

public class Wizard extends BaseCharacter{
    public Wizard(){
<<<<<<< HEAD
        super(Config.WIZARDMAXHP , Config.WIZARDPOWER , Config.WIZARDDEFENSE);
    }
=======
        super(Config.WIZARDMAXHP);
    }


>>>>>>> 6e93848cdbee085381d7ed2d6d8733c663d9286e

    @Override
    public void attack() {
    }


    public String toString() {
        return "Wizard";
    }
}
