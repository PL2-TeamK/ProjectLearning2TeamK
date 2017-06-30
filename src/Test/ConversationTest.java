package Test;

import Client.Conversation;

import java.util.Timer;

/**
 * Created by yudaikichise on 2017/06/30.
 */
public class ConversationTest {
    public static void main(String[] args) {

        Conversation conv;
        for (int convNum = 1; convNum <= 10; convNum++) {
            System.out.println("convNum: " + convNum);
            conv = new Conversation(convNum);
            while (!conv.getIsEnd()) {
                System.out.println("getWord: " + conv.getRemark() );
                System.out.println("scores");
                for (int replyNum = 1; replyNum <= 12; replyNum++) {
                    System.out.print(" [" + replyNum + "]:" + conv.getScore(replyNum));
                }
                System.out.println();
                System.out.println();
            }
        }

    }
}
