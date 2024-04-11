package logic.character;

import Utils.Config;
import logic.monsters.Monster;

public class Boy extends BaseCharacter{
    public Boy() {
        super(Config.BOYMAXHP , Config.BOYPOWER , Config.BOYDEFENSE,Config.BOYMAGICPOWER,Config.BOYMAGICDEFENSE);
    }

    @Override
    public String toString() {
        return "Boy";
    }
}
