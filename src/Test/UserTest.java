package Test;
import Client.User;

import java.util.ArrayList;


/**
 * Userクラスのテスト
 */
public class UserTest {
    public static void main(String[] args) {
        System.out.println("The test for User class.");
        User user = new User("testUser");

        System.out.println("Check User.getHighScore() method");
        ArrayList<Integer> tmpHighScore = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            tmpHighScore.add(1000 * i);
        user.setHighScore(tmpHighScore);
        ArrayList<Integer> returnedHighScore = new ArrayList<>(user.getHighScore());
        returnedHighScore.forEach(i -> System.out.println(i));

        System.out.println("Check Managing cleared stage info.");
        System.out.println("maxClearedStage = " + user.getMaxClearedStage());
        System.out.println("setClearedStage with 2");
        user.setMaxClearedStage(2);
        System.out.println("maxClearedStage = " + user.getMaxClearedStage());
        System.out.println("set clearedStage with 1");
        user.setMaxClearedStage(1);
        System.out.println("maxClearedStage = " + user.getMaxClearedStage());

    }
}
