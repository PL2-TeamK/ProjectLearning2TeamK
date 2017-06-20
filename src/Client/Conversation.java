package Client;

import java.util.ArrayList;

/**
 * 相手の発言の一連のまとまりを表すクラス
 */
public class Conversation {
    private ArrayList<Remark> remarks;
    private int remarkCounter;
    private boolean isEnd;
    private Remark currentRemark;

    public Conversation(int conversationNum) {
        // どのまとまりかを示す引数をとる。


        // 発言カウンターをセット
        remarkCounter = 0;

        // isEndフラグを初期化
        isEnd = false;
    }

    public String getRemark() {
        // 発言を取得する。
        currentRemark = remarks.get(remarkCounter);
        remarkCounter++;
        if (remarkCounter == remarks.size()) {
            // ArrayListの終端に達した場合
            isEnd = true;
        }
        return  currentRemark.getWord();
    }

    public int getScore(int replyNum) {
        return currentRemark.getScore(replyNum);
    }

    public boolean getIsEnd() {
        return isEnd;
    }


    /**
     * 以下、会話データの記述を行う。
     */

}
