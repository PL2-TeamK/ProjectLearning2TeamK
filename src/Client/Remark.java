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
            return -1;
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
        HashMap<Integer, Integer> replyScoreMap;

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

        /**
         * 文脈番号1
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(101, replyScoreMap);
        tmpWordMap.put(101, "ゆうきが");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(102, replyScoreMap);
        tmpWordMap.put(102, "機械工の人と");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(103, replyScoreMap);
        tmpWordMap.put(103, "付き合ってるらしいよ(^ ^)");

        /**
         * 文脈番号2
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(201, replyScoreMap);
        tmpWordMap.put(201, "Y大の先生って");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(202, replyScoreMap);
        tmpWordMap.put(202, "教えるのすっごい上手らしいよー");

        /**
         * 文脈番号3
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(301, replyScoreMap);
        tmpWordMap.put(301, "みずきが");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(302, replyScoreMap);
        tmpWordMap.put(302, "最近ブリーチに");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(303, replyScoreMap);
        tmpWordMap.put(303, "はまったらしいよー");

        /**
         * 文脈番号4
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(401, replyScoreMap);
        tmpWordMap.put(401, "情報工の人たちって");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(402, replyScoreMap);
        tmpWordMap.put(402, "みんな陰キャらしいよー");

        /**
         * 文脈番号5
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(501, replyScoreMap);
        tmpWordMap.put(501, "C棟2階の");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(502, replyScoreMap);
        tmpWordMap.put(502, "女子トイレって");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(503, replyScoreMap);
        tmpWordMap.put(503, "貞子でるらしいよー");

        /**
         * 文脈番号6
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(601, replyScoreMap);
        tmpWordMap.put(601, "A子ってー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(602, replyScoreMap);
        tmpWordMap.put(602, "きっせのこと");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(603, replyScoreMap);
        tmpWordMap.put(603, "好きらしいよー");

        /**
         * 文脈番号7
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(701, replyScoreMap);
        tmpWordMap.put(701, "ディズニーランドって");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(702, replyScoreMap);
        tmpWordMap.put(702, "カラスがいないらしいよー");

        /**
         * 文脈番号8
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(801, replyScoreMap);
        tmpWordMap.put(801, "きな粉牛乳って");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(802, replyScoreMap);
        tmpWordMap.put(802, "便秘にいいらしいよー");

        /**
         * 文脈番号9
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(901, replyScoreMap);
        tmpWordMap.put(901, "唐辛子食べると-");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(902, replyScoreMap);
        tmpWordMap.put(902, "脂肪燃焼されて");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(903, replyScoreMap);
        tmpWordMap.put(903, "痩せるらしいよー");

        /**
         * 文脈番号10
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1001, replyScoreMap);
        tmpWordMap.put(1001, "今季ってー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1002, replyScoreMap);
        tmpWordMap.put(1002, "花柄のレースのガウンが");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(1003, replyScoreMap);
        tmpWordMap.put(1003, "流行らしいよー");

        SCORE_MAP = Collections.unmodifiableMap(tmpScoreMap);
        WORD_MAP = Collections.unmodifiableMap(tmpWordMap);

    }

}
