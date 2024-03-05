package Utils;

import java.util.Random;

public class RandomMonster {
    private static final Random rng = new Random();
    private static int MAX_NUMBER;

    public static int drawMonsterImage(){
        MAX_NUMBER  = 22;
        return rng.nextInt(MAX_NUMBER) + 1;
    }

    public static int drawMonsterNumber(){
        MAX_NUMBER  = 3;
//        return rng.nextInt(MAX_NUMBER) + 1;
        return 1;
    }
}
