package Client;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ゲームのモデル
 * ステージ選択後に生成されるような設計にする(ステージ選択の度に生成される)
 * コンストラクタでステージ番号を引数にとり、ゲームモデルを生成する。
 *
 */
public class GameModel {

    private int stageNum;

    /**
     * 体力値
     * 雰囲気値
     * 体力減少係数
     * 雰囲気減少係数
     * デフォルト値を設定しておく
     */
    private float hitPoint;
    private float moodPoint;
    private float hpCoefficient;
    private float mpCoefficient;
    private boolean isEndless;

    /**
     * Conversationsインスタンス列
     */
    private ArrayList<Conversation> conversations;

    /**
     * 時間計測用のタイマー
     * Timerクラスとint型の変数で管理する。
     */

    private int playTime;
    private Timer timer;

    /**
     * conversations中のどの文脈に注目しているかを示す変数を用意
     */

    private int conversationIndex = 0;


    public GameModel (int stageNum) {
        /**
         * GameModel()の処理
         * 体力値等のデフォルト値設定
         * ステージ毎に体力・雰囲気値減少係数を設定
         * Convesation列の設定
         *
         */

        hitPoint = 100.0f;
        moodPoint = 100.0f;
        hpCoefficient = 5.0f;
        mpCoefficient = 5.0f;

        this.stageNum = stageNum;
        conversations = new ArrayList<>();
        switch (stageNum){
            case Constants.FIRST_STAGE:
                for (int convNum = 1; convNum <= 10; convNum++) {
                    conversations.add(new Conversation(convNum));
                }
                break;

            case Constants.FIRST_ENDLESS:
                for (int convNum = 1; convNum <= 10; convNum++) {
                    conversations.add(new Conversation(convNum));
                }
                break;

            case Constants.SECOND_STAGE:

                break;

            case Constants.SECOND_ENDLESS:

                break;

            case Constants.THIRD_STAGE:

                break;

            case Constants.THIRD_ENDLESS:

                break;

            case Constants.FOURTH_STAGE:

                break;

            case Constants.FOURTH_ENDLESS:

                break;

            default:
                // このパターンはやばい
        }

        if (stageNum >= 100) {
            // エンドレスモードかを検査
            isEndless = true;
        } else {
            isEndless = false;
        }

//        System.out.println(stageNum);

    }

    /**
     *  体力値、雰囲気値の取得
     */

    public float getHitPoint() {
        return hitPoint;
    }

    public float getMoodPoint() {
        return moodPoint;
    }

    public void gameStart() {
        /**
         * 1秒ごとにplayTimeが1ずつ増えてく
         */
        playTime = 0;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                    playTime++;
            }
        }, 1000, 1000);
    }

    public void gameEnd() {
        // タイマー停止
        timer.cancel();
    }

    public int getPlayTime() {
        return playTime;
    }

    /**
     *  注意
     *  以下、特にゲームバランスに関わる部分である
     *  計算式の検討は慎重に行うべき
     */

    public boolean updateMoodPoint (int replyNum, float timing) {
        /**
         * 0 <= timing <= 1
         */

        int score = conversations.get(conversationIndex).getScore(replyNum);
        if (score < 0) {
            moodPoint += (2.0f - timing) * (float)score * mpCoefficient;
        } else if (score > 0){
            moodPoint += timing * (float)score * mpCoefficient;
        } else {
            moodPoint += (timing - 1.0f);
        }

        if (moodPoint > 100.0f) {
            moodPoint = 100.0f;
        }

        if (moodPoint > 0) {
            return true;
        } else {
            // moodPointが0になったらゲームオーバー
            return false;
        }


    }

    public boolean updateHitPoint (int replyNum) {
        // TODO: 実装
        if (hitPoint > 100.0f) {
            hitPoint = 100.0f;
        }

        if (hitPoint > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getNextRemarkText() {
        if (conversations.get(conversationIndex).getIsEnd()) {
            // 現在着目している会話が終了点の場合、次の会話を選択する。
//            System.out.println("conv end");
//            System.out.println(isEndless);
            if (isEndless) {
                // エンドレスの場合
                // ランダムかな
                // TODO
            } else {
                // 通常ステージの場合
                // 順番に会話を並べとこう
//                System.out.println("index updated");
                conversationIndex++;
            }

        }
        if (conversationIndex == conversations.size()) {
            // 通常モードで最後に到達した場合
            return Constants.LAST_CONV;
        }
        // 現在着目している会話の発言を返す。
        // 自動でRemarkインスタンスの発言カウンタは進む
        return conversations.get(conversationIndex).getRemark();


    }

    public int getStageNum() {
        return stageNum;
    }
}
