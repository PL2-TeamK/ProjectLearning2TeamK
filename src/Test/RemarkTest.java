package Test;

import Client.Remarks;

/**
 * Created by yudaikichise on 2017/06/30.
 */
public class RemarkTest {
    public static void main(String[] args) {
        Remarks remarks;
        remarks = new Remarks(103);
        System.out.println(remarks.getWord());
        for (int i = 1; i <= 12; i++) {
            System.out.println("replyNum = " + i + " score = " + remarks.getScore(i));
        }
    }
}
