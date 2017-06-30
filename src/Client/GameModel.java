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
                for (int convNum = 1; convNum <= 30; convNum++) {
                    conversations.add(new Conversation(convNum));
                }
                break;

            case Constants.FIRST_ENDLESS:
                for (int convNum = 1; convNum <= 30; convNum++) {
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
}
