package Client;

import java.util.*;

/**
 * ステージごとの返答リストを示すクラス
 * コンストラクタでステージ番号を受け取ってステージに表示する返答リストを生成する。
 * 返答種類を示す定数と、それに対応する文字列を持つHashMapを得る。
 * ステージごとの返答リストをここに記載する。
 * 直接コード化することで返答を記述する。
 *
 */
public class ReplyList {
    // 返答番号と返答内容の組を保管
    private HashMap<Integer, String> replyMap;
    // 返答番号のみを格納するリスト
    private ArrayList<Integer> replyNumList;

    public ReplyList(int stageNum) {
        // ステージ番号に対応した返答リストを作成する
        // 返答番号のみを格納するリストも用意する。
    }

    public String getReply(int replyNum) {
        return replyMap.get(replyNum);
    }

    public ArrayList<Integer> getReplyNumList() {
        // 返答番号リストを返す。
        return replyNumList;
    }

    /**
     * 以下、返答データの記述を行う
     * 返答リストを生成
     */
    public final static Map<Integer, String> ALL_REPLY;
    static {
        // 一時的なmapを作成して、後で変更不可にする。
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "ふーん");
        map.put(2, "へえー");
        map.put(3, "そうなんだぁ");
        map.put(4, "うん");
        map.put(5, "すごいね");
        map.put(6, "そんなことないよぉ");
        map.put(7, "ありえない！");
        map.put(8, "大丈夫?");
        map.put(9, "分かるぅ〜");
        map.put(10, "かわいい〜");
        map.put(11, "いいなぁ");
        map.put(12, "知るか");
        // 追加


        // 以下のコードで変更不可のMapを生成する
        ALL_REPLY = Collections.unmodifiableMap(map);
    }

}
