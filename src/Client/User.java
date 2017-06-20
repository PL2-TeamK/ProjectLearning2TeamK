package Client;

/**
 * ユーザー情報を格納するクラス
 * サーバーで生成, クライアントで管理する
 */

import java.util.*;


public class User {
    private String name;
    private ArrayList<Boolean> clearedStage;
    private ArrayList<Integer> highScore;

    User(String name) {
        this.name = name;
    }

    public void setClearedStage(ArrayList<Boolean> argList) {
        // ArrayList<T>のコンストラクタにArrayList<T>のインスタンスを渡すとディープコピーされる。
        // クリア状況をコピー
        clearedStage = new ArrayList<Boolean>(argList);
    }

    public void setHighScore(ArrayList<Integer> argList) {
        // ハイスコア情報をコピー
        highScore = new ArrayList<Integer>(argList);
    }

    public ArrayList<Boolean> getClearedStege() {
        return clearedStage;
    }

    public ArrayList<Integer> getHighScore() {
        return highScore;
    }
}
