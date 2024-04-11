package logic.character;

import Utils.Config;
import logic.monsters.Monster;

public class Blonde extends BaseCharacter{
    public Blonde() {
        super(Config.BLONDEMAXHP , Config.BLONDPOWER , Config.BLONDEDEFENSE, Config.BLONDEMAGICPOWER,Config.BLONDEMAGICDEFENSE);
    }

    @Override
    public String toString() {
        return "Blonde";
    }
}
