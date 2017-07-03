package Test;

import Client.Conversation;

import java.util.Timer;

/**
 * Created by yudaikichise on 2017/06/30.
 */
public class ConversationTest {
    public static void main(String[] args) {


        Conversation.ALL_CONVERSATIONS.keySet().forEach(convKey -> {
            Conversation conv = new Conversation(convKey);
            System.out.println("convKey: " + convKey);
            while(!conv.getIsEnd()) {
                System.out.println("getWord: " + conv.getRemark());
                System.out.println("scores");
                for (int replyNum = 1; replyNum <= 12; replyNum++) {
                    System.out.print("[" + replyNum + "]:" + conv.getScore(replyNum));
                }
                System.out.println("\n");
            }
        });

    }
}
