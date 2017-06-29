package Client;

import Client.Remark;

/**
 * Created by yudaikichise on 2017/06/30.
 */
public class RemarkTest {
    public static void main(String[] args) {
        Remark remark;
        remark = new Remark(103);
        System.out.println(remark.getWord());
        for (int i = 1; i <= 12; i++) {
            System.out.println("replyNum = " + i + " score = " + remark.getScore(i));
        }
    }
}
