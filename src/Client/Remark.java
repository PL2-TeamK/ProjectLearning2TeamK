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
            return -3;
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
        tmpWordMap.put(1002, "花柄とかレースのガウンが");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(1003, replyScoreMap);
        tmpWordMap.put(1003, "流行らしいよー");

        /**
         * 文脈番号11
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1101, replyScoreMap);
        tmpWordMap.put(1101, "スポドリとー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1102, replyScoreMap);
        tmpWordMap.put(1102, "お酒を一緒に飲むとー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(1103, replyScoreMap);
        tmpWordMap.put(1103, "酔いが早く回るらしいよー");

        /**
         * 文脈番号12
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1201, replyScoreMap);
        tmpWordMap.put(1201, "ごはんは");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1202, replyScoreMap);
        tmpWordMap.put(1202, "野菜から食べると");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(1203, replyScoreMap);
        tmpWordMap.put(1203, "痩せるんだよー");

        /**
         * 文脈番号13
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1301, replyScoreMap);
        tmpWordMap.put(1301, "ラブホってー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1302, replyScoreMap);
        tmpWordMap.put(1302, "男だけだとー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(1303, replyScoreMap);
        tmpWordMap.put(1303, "入れないんだってー");

        /**
         * 文脈番号14
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1401, replyScoreMap);
        tmpWordMap.put(1401, "ワイシャツの「ワイ」って");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1402, replyScoreMap);
        tmpWordMap.put(1402, "Ｙじゃなくてー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(1403, replyScoreMap);
        tmpWordMap.put(1403, "「White(白)」のワイなんだよー");

        /**
         * 文脈番号15
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1501, replyScoreMap);
        tmpWordMap.put(1501, "はちみつって");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1502, replyScoreMap);
        tmpWordMap.put(1502, "腐らないんだってー");

        /**
         * 文脈番号16
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1601, replyScoreMap);
        tmpWordMap.put(1601, "ハト時計で");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1602, replyScoreMap);
        tmpWordMap.put(1602, "出てくる鳥は");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(1603, replyScoreMap);
        tmpWordMap.put(1603, "カッコウなんだってー");

        /**
         * 文脈番号17
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1701, replyScoreMap);
        tmpWordMap.put(1701, "ドラえもんってー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1702, replyScoreMap);
        tmpWordMap.put(1702, "体内で原子力発電して");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(1703, replyScoreMap);
        tmpWordMap.put(1703, "動いてるんだってー");

        /**
         * 文脈番号18
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1801, replyScoreMap);
        tmpWordMap.put(1801, "カルボナーラってー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1802, replyScoreMap);
        tmpWordMap.put(1802, "イタリア語で");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(1803, replyScoreMap);
        tmpWordMap.put(1803, "炭焼き職人って意味なんだよー");

        /**
         * 文脈番号19
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1901, replyScoreMap);
        tmpWordMap.put(1901, "朝にー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(1902, replyScoreMap);
        tmpWordMap.put(1902, "二度寝するとー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(1903, replyScoreMap);
        tmpWordMap.put(1903, "太るんだよー");

        /**
         * 文脈番号20
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(2001, replyScoreMap);
        tmpWordMap.put(2001, "ダイエットはー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(2002, replyScoreMap);
        tmpWordMap.put(2002, "春に始めるのがー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(2, 1);    // へぇー
        replyScoreMap.put(1, 1);    // ふーん
        replyScoreMap.put(3, 1);    // そうなんだぁ
        tmpScoreMap.put(2003, replyScoreMap);
        tmpWordMap.put(2003, "いいんだよー");

        /**
         * 文脈番号21
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(2101, replyScoreMap);
        tmpWordMap.put(2101, "スクリュードライバーってお酒さぁ");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(2102, replyScoreMap);
        tmpWordMap.put(2102, "春に始めるのがー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(9, 1);    // わかるー
        tmpScoreMap.put(2103, replyScoreMap);
        tmpWordMap.put(2103, "めっちゃ強そうじゃない？(笑)");

        /**
         * 文脈番号22
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(2201, replyScoreMap);
        tmpWordMap.put(2201, "情報工のー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(2202, replyScoreMap);
        tmpWordMap.put(2202, "牛島君ってー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(9, 2);    // わかるー
        tmpScoreMap.put(2203, replyScoreMap);
        tmpWordMap.put(2203, "すっごいイケメンだよねー");

        /**
         * 文脈番号23
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(2301, replyScoreMap);
        tmpWordMap.put(2301, "ディズニーってー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(2302, replyScoreMap);
        tmpWordMap.put(2302, "いるだけでー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(9, 1);    // わかるー
        tmpScoreMap.put(2303, replyScoreMap);
        tmpWordMap.put(2303, "楽しいよねー(*^^)");

        /**
         * 文脈番号24
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(2401, replyScoreMap);
        tmpWordMap.put(2401, "理系男子ってー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(9, 1);    // わかるー
        tmpScoreMap.put(2402, replyScoreMap);
        tmpWordMap.put(2402, "なんか頭よさそうだよねー");

        /**
         * 文脈番号25
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(2501, replyScoreMap);
        tmpWordMap.put(2501, "抹茶苦手な人ってー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(9, 1);    // わかるー
        tmpScoreMap.put(2502, replyScoreMap);
        tmpWordMap.put(2502, "人生の３割は損してるよねー(笑)");

        /**
         * 文脈番号26
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(2601, replyScoreMap);
        tmpWordMap.put(2601, "A子ってー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        replyScoreMap.put(9, 2);    // わかるー
        tmpScoreMap.put(2602, replyScoreMap);
        tmpWordMap.put(2602, "ぶりっ子だよねー");

        /**
         * 文脈番号27
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(2701, replyScoreMap);
        tmpWordMap.put(2701, "Y大学の最寄りってー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        replyScoreMap.put(9, 1);    // わかるー
        tmpScoreMap.put(2702, replyScoreMap);
        tmpWordMap.put(2702, "最寄りじゃなくない？？");

        /**
         * 文脈番号28
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(2801, replyScoreMap);
        tmpWordMap.put(2801, "A子ってー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(2802, replyScoreMap);
        tmpWordMap.put(2802, "男の前だと");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        replyScoreMap.put(9, 2);    // わかるー
        tmpScoreMap.put(2803, replyScoreMap);
        tmpWordMap.put(2803, "態度変わるよねー(－－〆)");

        /**
         * 文脈番号29
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(2901, replyScoreMap);
        tmpWordMap.put(2901, "女性の幽霊ってー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        replyScoreMap.put(9, 1);    // わかるー
        tmpScoreMap.put(2902, replyScoreMap);
        tmpWordMap.put(2902, "黒髪ロング&白ワンピのイメージあるよねー");

        /**
         * 文脈番号30
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        replyScoreMap.put(9, 1);    // わかるー
        tmpScoreMap.put(3001, replyScoreMap);
        tmpWordMap.put(3001, "かわいいって正義だよねー(^^♪");

        /**
         * 文脈番号31
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(3101, replyScoreMap);
        tmpWordMap.put(3101, "昨日");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(3102, replyScoreMap);
        tmpWordMap.put(3102, "先生がー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(7, 1);    // ありえない
        tmpScoreMap.put(3103, replyScoreMap);
        tmpWordMap.put(3103, "すっごいいっぱい課題だしてきたんだよねー");

        /**
         * 文脈番号32
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(3201, replyScoreMap);
        tmpWordMap.put(3201, "Y大の男子とー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(3202, replyScoreMap);
        tmpWordMap.put(3202, "デートしてあげたのにー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(7, 1);    // ありえない
        tmpScoreMap.put(3203, replyScoreMap);
        tmpWordMap.put(3203, "全然おごってくれなかったー");

        /**
         * 文脈番号33
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(3301, replyScoreMap);
        tmpWordMap.put(3301, "A子がー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(3302, replyScoreMap);
        tmpWordMap.put(3302, "彼氏にー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(7, 2);    // ありえない
        tmpScoreMap.put(3303, replyScoreMap);
        tmpWordMap.put(3303, "ちょっかい出してきてんだよねー(怒)");

        /**
         * 文脈番号34
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(3401, replyScoreMap);
        tmpWordMap.put(3401, "レポートの再提出うけてー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(7, 2);    // ありえない
        tmpScoreMap.put(3403, replyScoreMap);
        tmpWordMap.put(3403, "まじむかつくー");

        /**
         * 文脈番号35
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(3501, replyScoreMap);
        tmpWordMap.put(3501, "バイトにー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(3502, replyScoreMap);
        tmpWordMap.put(3502, "ちょっと遅刻したらー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(7, 1);    // ありえない
        tmpScoreMap.put(3503, replyScoreMap);
        tmpWordMap.put(3503, "減給されたー( ;∀;)");

        /**
         * 文脈番号36
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(3601, replyScoreMap);
        tmpWordMap.put(3601, "彼氏が女の子と");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(7, 2);    // ありえない
        tmpScoreMap.put(3602, replyScoreMap);
        tmpWordMap.put(3602, "浮気してた(怒)");

        /**
         * 文脈番号37
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(3701, replyScoreMap);
        tmpWordMap.put(3701, "この前ー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(3702, replyScoreMap);
        tmpWordMap.put(3702, "飲み会でー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(7, 1);    // ありえない
        tmpScoreMap.put(3703, replyScoreMap);
        tmpWordMap.put(3703, "服にお酒こぼされたんよねー");

        /**
         * 文脈番号38
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(7, 1);    // ありえない
        tmpScoreMap.put(3801, replyScoreMap);
        tmpWordMap.put(3801, "めちゃくちゃバイトのシフト削られてた( ;∀;)");

        /**
         * 文脈番号39
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(3901, replyScoreMap);
        tmpWordMap.put(3901, "B子がー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(7, 2);    // ありえない
        tmpScoreMap.put(3902, replyScoreMap);
        tmpWordMap.put(3902, "うちの陰口言ってるらしいんだよねー");

        /**
         * 文脈番号40
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(4001, replyScoreMap);
        tmpWordMap.put(4001, "お父さんがー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(7, 1);    // ありえない
        tmpScoreMap.put(4002, replyScoreMap);
        tmpWordMap.put(4002, "門限定めてきたー(怒)");

        /**
         * 文脈番号41
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(4101, replyScoreMap);
        tmpWordMap.put(4101, "彼氏が女の子とー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(8, 1);    // 大丈夫？
        replyScoreMap.put(7, 1);    // ありえない
        replyScoreMap.put(2, -4);    // へぇー
        replyScoreMap.put(1, -4);    // ふーん
        replyScoreMap.put(3, -4);    // そうなんだぁ
        tmpScoreMap.put(4102, replyScoreMap);
        tmpWordMap.put(4102, "浮気してるの見ちゃった(泣)");

        /**
         * 文脈番号42
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(4201, replyScoreMap);
        tmpWordMap.put(4201, "この前ー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(4202, replyScoreMap);
        tmpWordMap.put(4202, "電車でー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(8, 1);    // 大丈夫？
        replyScoreMap.put(7, 1);    // ありえない
        replyScoreMap.put(2, -4);    // へぇー
        replyScoreMap.put(1, -4);    // ふーん
        replyScoreMap.put(3, -4);    // そうなんだぁ
        tmpScoreMap.put(4203, replyScoreMap);
        tmpWordMap.put(4203, "痴漢にあったの、、");

        /**
         * 文脈番号43
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(4301, replyScoreMap);
        tmpWordMap.put(4301, "最近、");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(4302, replyScoreMap);
        tmpWordMap.put(4302, "彼氏が、");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(8, 1);    // 大丈夫？
        replyScoreMap.put(2, -4);    // へぇー
        replyScoreMap.put(1, -4);    // ふーん
        replyScoreMap.put(3, -4);    // そうなんだぁ
        tmpScoreMap.put(4303, replyScoreMap);
        tmpWordMap.put(4303, "全然相手にしてくれない(泣)");

        /**
         * 文脈番号44
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(4401, replyScoreMap);
        tmpWordMap.put(4401, "私なんもしてないのに");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(4402, replyScoreMap);
        tmpWordMap.put(4402, "B子にー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(8, 1);    // 大丈夫？
        replyScoreMap.put(7, 1);    // ありえない
        replyScoreMap.put(2, -4);    // へぇー
        replyScoreMap.put(1, -4);    // ふーん
        replyScoreMap.put(3, -4);    // そうなんだぁ
        tmpScoreMap.put(4403, replyScoreMap);
        tmpWordMap.put(4403, "はぶられる(泣)");

        /**
         * 文脈番号45
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(8, 1);    // 大丈夫？
        replyScoreMap.put(2, -4);    // へぇー
        replyScoreMap.put(1, -4);    // ふーん
        replyScoreMap.put(3, -4);    // そうなんだぁ
        tmpScoreMap.put(4501, replyScoreMap);
        tmpWordMap.put(4501, "病み気なう、、");

        /**
         * 文脈番号46
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(4601, replyScoreMap);
        tmpWordMap.put(4601, "彼氏がー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(4602, replyScoreMap);
        tmpWordMap.put(4602, "留学行くからー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(8, 1);    // 大丈夫？
        replyScoreMap.put(2, -4);    // へぇー
        replyScoreMap.put(1, -4);    // ふーん
        replyScoreMap.put(3, -4);    // そうなんだぁ
        tmpScoreMap.put(4603, replyScoreMap);
        tmpWordMap.put(4603, "会えなくなっちゃう、、(泣)");

        /**
         * 文脈番号47
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(8, 1);    // 大丈夫？
        replyScoreMap.put(2, -4);    // へぇー
        replyScoreMap.put(1, -4);    // ふーん
        replyScoreMap.put(3, -4);    // そうなんだぁ
        tmpScoreMap.put(4701, replyScoreMap);
        tmpWordMap.put(4701, "最近体調悪いんだよね、、");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(8, 1);    // 大丈夫？
        replyScoreMap.put(2, -4);    // へぇー
        replyScoreMap.put(1, -4);    // ふーん
        replyScoreMap.put(3, -4);    // そうなんだぁ
        tmpScoreMap.put(4702, replyScoreMap);
        tmpWordMap.put(4702, "風邪ひいちゃったかも、、");

        /**
         * 文脈番号48
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(4801, replyScoreMap);
        tmpWordMap.put(4801, "昨日");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(8, 2);    // 大丈夫？
        replyScoreMap.put(2, -4);    // へぇー
        replyScoreMap.put(1, -4);    // ふーん
        replyScoreMap.put(3, -4);    // そうなんだぁ
        tmpScoreMap.put(4802, replyScoreMap);
        tmpWordMap.put(4802, "フラれちゃった(泣)");

        /**
         * 文脈番号49
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(4901, replyScoreMap);
        tmpWordMap.put(4901, "殺される夢見て");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(8, 1);    // 大丈夫？
        tmpScoreMap.put(4902, replyScoreMap);
        tmpWordMap.put(4902, "気味悪い、、");

        /**
         * 文脈番号50
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(5001, replyScoreMap);
        tmpWordMap.put(5001, "久しぶりにー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(5002, replyScoreMap);
        tmpWordMap.put(5002, "運動したらー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(8, 1);    // 大丈夫？
        replyScoreMap.put(2, -4);    // へぇー
        replyScoreMap.put(1, -4);    // ふーん
        replyScoreMap.put(3, -4);    // そうなんだぁ
        tmpScoreMap.put(5003, replyScoreMap);
        tmpWordMap.put(5003, "足怪我しちゃった、");

        /**
         * 文脈番号51
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(5101, replyScoreMap);
        tmpWordMap.put(5101, "夏休みにー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(5102, replyScoreMap);
        tmpWordMap.put(5102, "ハワイにー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(11, 1);    // いいなぁ
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(5103, replyScoreMap);
        tmpWordMap.put(5103, "旅行に行くんだー(^^♪");

        /**
         * 文脈番号52
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(5201, replyScoreMap);
        tmpWordMap.put(5201, "昨日");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(5202, replyScoreMap);
        tmpWordMap.put(5202, "ヒルトンホテルのー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(11, 1);    // いいなぁ
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(5203, replyScoreMap);
        tmpWordMap.put(5203, "いちごビュッフェ行ってきたのー(^^♪");

        /**
         * 文脈番号53
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(5301, replyScoreMap);
        tmpWordMap.put(5301, "この前ー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(5302, replyScoreMap);
        tmpWordMap.put(5302, "シャネルのー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(11, 1);    // いいなぁ
        replyScoreMap.put(5, 1);    // すごいねー！
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(5303, replyScoreMap);
        tmpWordMap.put(5303, "チーク買ったんだー(^^♪");

        /**
         * 文脈番号54
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(5401, replyScoreMap);
        tmpWordMap.put(5401, "昨日");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(5402, replyScoreMap);
        tmpWordMap.put(5402, "新しくー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(11, 1);    // いいなぁ
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(5403, replyScoreMap);
        tmpWordMap.put(5403, "彼氏できたー(*^^)v");

        /**
         * 文脈番号55
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(5501, replyScoreMap);
        tmpWordMap.put(5501, "この前ー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(11, 1);    // いいなぁ
        replyScoreMap.put(5, 1);    // すごいねー！
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(5503, replyScoreMap);
        tmpWordMap.put(5503, "リムジン女子会やったのー");

        /**
         * 文脈番号56
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(5601, replyScoreMap);
        tmpWordMap.put(5601, "サークルのー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(5602, replyScoreMap);
        tmpWordMap.put(5602, "ボーリング大会でー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(11, 1);    // いいなぁ
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(5603, replyScoreMap);
        tmpWordMap.put(5603, "ディズニーのペアチケもらったのー(^^♪");

        /**
         * 文脈番号57
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(11, 1);    // いいなぁ
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(5701, replyScoreMap);
        tmpWordMap.put(5701, "叙々苑のー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(11, 1);    // いいなぁ
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(5702, replyScoreMap);
        tmpWordMap.put(5702, "焼肉おいしかった!(^^)!");

        /**
         * 文脈番号58
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(5801, replyScoreMap);
        tmpWordMap.put(5801, "この前ー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(5802, replyScoreMap);
        tmpWordMap.put(5802, "サイズ測ったらー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(11, 1);    // いいなぁ
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(5803, replyScoreMap);
        tmpWordMap.put(5803, "胸が大きくなってた(*^^)v");

        /**
         * 文脈番号59
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(5901, replyScoreMap);
        tmpWordMap.put(5901, "誕プレでー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(11, 1);    // いいなぁ
        replyScoreMap.put(5, 1);    // すごいねー！
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(5902, replyScoreMap);
        tmpWordMap.put(5902, "GUCCIの財布もらったー(*^^)v");

        /**
         * 文脈番号60
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(6001, replyScoreMap);
        tmpWordMap.put(6001, "K大の人とー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(6002, replyScoreMap);
        tmpWordMap.put(6002, "2人で遊んだらー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(11, 1);    // いいなぁ
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(6003, replyScoreMap);
        tmpWordMap.put(6003, "全部ごちそうになっちゃった(笑)");

        /**
         * 文脈番号61
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(6101, replyScoreMap);
        tmpWordMap.put(6101, "この前ー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(6102, replyScoreMap);
        tmpWordMap.put(6102, "駅前でー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(5, 1);    // すごいねー！
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(6103, replyScoreMap);
        tmpWordMap.put(6103, "ナンパされちゃったー");

        /**
         * 文脈番号62
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(6201, replyScoreMap);
        tmpWordMap.put(6201, "渋谷でー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(6202, replyScoreMap);
        tmpWordMap.put(6202, "歩いてたらー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(5, 1);    // すごいねー！
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(6203, replyScoreMap);
        tmpWordMap.put(6203, "スカウトされたのー( *´艸｀) ");

        /**
         * 文脈番号63
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(6301, replyScoreMap);
        tmpWordMap.put(6301, "昨日");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(5, 1);    // すごいねー！
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(6302, replyScoreMap);
        tmpWordMap.put(6302, "カットモデルの撮影したんだー(^_-)-☆");

        /**
         * 文脈番号64
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(5, 1);    // すごいねー！
        replyScoreMap.put(11, 1);    // いいなぁ
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(6401, replyScoreMap);
        tmpWordMap.put(6401, "３人から告白されちゃった(^^♪");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(5, 1);    // すごいねー！
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(6402, replyScoreMap);
        tmpWordMap.put(6402, "すごくない？？");

        /**
         * 文脈番号65
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(6501, replyScoreMap);
        tmpWordMap.put(6501, "先月ー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(5, 1);    // すごいねー！
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(6502, replyScoreMap);
        tmpWordMap.put(6502, "15万も稼いじゃった(笑)");

        /**
         * 文脈番号66
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(5, 1);    // すごいねー！
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(6601, replyScoreMap);
        tmpWordMap.put(6601, "1ヶ月で２kgも痩せたんだー");

        /**
         * 文脈番号67
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(6701, replyScoreMap);
        tmpWordMap.put(6701, "スクラッチでー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(5, 1);    // すごいねー！
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(6702, replyScoreMap);
        tmpWordMap.put(6702, "1万円当てちゃった(^^♪");

        /**
         * 文脈番号68
         */

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(5, 1);    // すごいねー！
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(6801, replyScoreMap);
        tmpWordMap.put(6801, "嵐のライブに当選しちゃった(^^♪");

        /**
         * 文脈番号69
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(6901, replyScoreMap);
        tmpWordMap.put(6901, "体引き締めようと思ってー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(6902, replyScoreMap);
        tmpWordMap.put(6902, "筋トレしてたらー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(5, 1);    // すごいねー！
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(6903, replyScoreMap);
        tmpWordMap.put(6903, "腹筋割れてた(笑)");

        /**
         * 文脈番号70
         */

        replyScoreMap = new HashMap<>();
        replyScoreMa.put(4, 0);
        tmpScoreMap.put(7001, replyScoreMap);
        tmpWordMap.put(7001, "彼氏の誕生日に");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(4, 0);
        tmpScoreMap.put(7002, replyScoreMap);
        tmpWordMap.put(7002, "自分でー");

        replyScoreMap = new HashMap<>();
        replyScoreMap.put(5, 1);    // すごいねー！
        replyScoreMap.put(2, -2);    // へぇー
        replyScoreMap.put(1, -2);    // ふーん
        replyScoreMap.put(3, -2);    // そうなんだぁ
        replyScoreMa.put(4, -2);	// うん
        tmpScoreMap.put(7003, replyScoreMap);
        tmpWordMap.put(7003, "ローストビーフつくったのー( *´艸｀) ");

        SCORE_MAP = Collections.unmodifiableMap(tmpScoreMap);
        WORD_MAP = Collections.unmodifiableMap(tmpWordMap);

    }

}
