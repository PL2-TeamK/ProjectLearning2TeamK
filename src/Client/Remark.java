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

    public Remark(int remarkNum) {
        /**
         * 引数で与えられた番号の発言のインスタンスを生成する。
         * 発言番号は重複しない
         */

        if (SCORE_MAP.containsKey(remarkNum) && WORD_MAP.containsKey(remarkNum)) {
            // 定義されている場合
            word = WORD_MAP.get(remarkNum);
            score = SCORE_MAP.get(remarkNum);

        } else {
            // 単語が定義されていない場合にはエラーを発生させたい。
        }
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
     * 発言番号と返答に対応するスコア列が対応するmapと
     * 発言番号と発言文字列が対応するmapを作成する
     */

    private final static Map<Integer, HashMap<Integer, Integer>> SCORE_MAP;
    private final static Map<Integer, String> WORD_MAP;

    static {
        Map<Integer, HashMap<Integer, Integer>> tmpScoreMap = new HashMap<>();
        Map<Integer, String> tmpWordMap = new HashMap<>();
        Map<Integer, Integer> replyScoreMap;

        /**
         * 発言番号一個に対して以下の処理を行う。
         * replyScoreMap = new HashMap<>();
         * replyScoreMap.put(返答番号, スコア); でスコアを設定
         * ...
         * tmpScoreMap.put(発言番号, replyScoreMap); で発言番号とスコア列を対応させる。
         * tmpWordMap.put(発言番号, 発言文字列); で発言番号と発言文字列を対応させる。
         *
         * 上記処理を繰り返し、tmpScoreMapとtmpWordMapが完成したら定数化する。
         */



        SCORE_MAP = Collections.unmodifiableMap(tmpScoreMap);
        WORD_MAP = Collections.unmodifiableMap(tmpWordMap);

    }

}
