package Client;

import java.util.ArrayList;
import java.util.HashMap;

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
     */
}
