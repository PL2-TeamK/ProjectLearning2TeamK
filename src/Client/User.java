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

    public User(String name) {
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

    public void updateClearedStage(int stageNum, boolean isCleared) {
        /**
         * ステージクリア状況を更新する。
         * 基本的にfalseを渡しても意味ないよ
         */
        switch(stageNum) {
            case Constants.FIRST_STAGE:
                clearedStage.set(0, isCleared); break;
            case Constants.SECOND_STAGE:
                clearedStage.set(1, isCleared); break;
            case Constants.THIRD_STAGE:
                clearedStage.set(2, isCleared);
                break;
            case Constants.FOURTH_STAGE:
                clearedStage.set(3, isCleared); break;
        }
    }

    public boolean updateHighScore(int stageNum, int score) {
        /**
         * ハイスコアを更新するためのメソッド
         * 更新された場合はtrueを返す。
         * (戻り値は使っても使わなくてもいいよ)
         */
        boolean isUpdated = false;
        switch (stageNum) {
            case Constants.FIRST_ENDLESS:
                isUpdated = checkAndUpdateHighScore(0, score);
                break;
            case Constants.SECOND_ENDLESS:
                isUpdated = checkAndUpdateHighScore(1, score);
                break;
            case Constants.THIRD_ENDLESS:
                isUpdated = checkAndUpdateHighScore(2, score);
                break;
            case Constants.FOURTH_ENDLESS:
                isUpdated = checkAndUpdateHighScore(3, score);
                break;

        }

        return isUpdated;
    }

    private boolean checkAndUpdateHighScore(int indexOfArray, int score) {
        /**
         * updateHighScore()メソッドでの共通処理を切り出したメソッド
         * ArrayList内の値と今回のスコアを比較して、更新する
         * 更新した場合はtrueを返す。
         */


        if (score > highScore.get(indexOfArray)) {
            highScore.set(indexOfArray, score);
            return true;
        } else {
            return false;
        }
    }
}
