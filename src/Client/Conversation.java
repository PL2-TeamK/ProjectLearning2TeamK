package Client;

import java.util.*;

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
     * 文脈番号をキーにして、発言番号のリストをオブジェクトとするMapを作成
     */
    private final static Map<Integer, ArrayList<Integer>> ALL_CONVERSATIONS;
    static {
        Map<Integer, ArrayList<Integer>> tmpmap;
        ArrayList<Integer> tmpRemarkList;
        tmpmap = new HashMap<>();
        /**
         * 文脈一個に対して以下の処理を行う
         * 単語リストの初期化
         * tmpRemarkList = new ArrayList<>();
         * tmpRemarkList.add(追加したい単語番号);
         *
         * tmpmap.add(文脈番号, tmpRemarkList);
         * 文脈番号と単語リストの対応付け
         *
         * 文脈の個数だけ、上記処理を繰り返す。
         * 最後に
         * ALL_CONVERSATIONS = Collections.unmodifiablemap(tmpmap);
         * を実行し、定数化する。
         */






        ALL_CONVERSATIONS = Collections.unmodifiableMap(tmpmap);
    }

}
