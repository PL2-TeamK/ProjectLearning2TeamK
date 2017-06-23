package Client;

/**
 * ユーザー情報を格納するクラス
 * サーバーで生成, クライアントで管理する
 */

import java.util.*;


public class User {
    private String name;

    private ArrayList<Integer> highScore;
    private int maxClearedStage;

    public User(String name) {
        this.name = name;
        maxClearedStage = -1;
    }



    public void setHighScore(ArrayList<Integer> argList) {
        // ハイスコア情報をコピー
        highScore = new ArrayList<Integer>(argList);
    }

    public void setMaxClearedStage(int stageNum) {
        /**
         * 最大ステージクリア数をセットする。
         * ステージは順次解放されるので、最大ステージのみ記録するので問題ない
         */
        if (maxClearedStage < stageNum)
            maxClearedStage = stageNum;
    }



    public ArrayList<Integer> getHighScore() {
        return highScore;
    }

    public int getMaxClearedStage() {
        return maxClearedStage;
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
