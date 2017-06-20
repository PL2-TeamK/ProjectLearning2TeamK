package Client;

import java.util.*;

/**
 * 相手の発言の最小単位を表すクラス
 * 発言文字列と、返答内容による得点を格納するHashMapを用意する。
 * HashMapのkeyは返答を表す定数とし、valueはその得点とする。
 * key値が存在するかを事前に、containsKey(Object key)で確認し、存在しない場合にはデフォルト値を返すような実装も可能
 * フィールドの値をどこで設定するかが問題(RemarkかConversationか)
 */
public class Remark {
    private String word;
    private HashMap<Integer, Integer> score;

    public Remark() {
        // TODO: 発言内容を記録する仕組みの実装
    }

    public String getWord() {
        return word;
    }

    public int getScore(int replyNum) {
        if (score.containsKey(replyNum)) {
            // key値が存在した場合
            return score.get(replyNum);
        } else {
            // key値が存在しない場合、デフォルト値を返す
            return 0;
        }
    }

    /**
     * 以下発言データの記述を行う。
     * staticデータとすることにする
     */

}
