package Test;
import Client.User;

import java.util.ArrayList;


/**
 * Userクラスのテスト
 */
public class UserTest {
    public static void main(String[] args) {
        User user = new User("testUser");

        System.out.println("Check User.getHighScore() method");
        ArrayList<Integer> tmpHighScore = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            tmpHighScore.add(1000 * i);
        ArrayList<Integer> returnedHighScore = new ArrayList<Integer>(user.getHighScore());
        returnedHighScore.forEach(i -> System.out.println(i));

    }
}
