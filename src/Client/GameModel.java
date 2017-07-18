package Client;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
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
                conversations.add(new Conversation(6));
                conversations.add(new Conversation(13));
                conversations.add(new Conversation(27));
                conversations.add(new Conversation(22));
                conversations.add(new Conversation(2));

                conversations.add(new Conversation(1));
                conversations.add(new Conversation(16));
                conversations.add(new Conversation(25));
                conversations.add(new Conversation(17));
                conversations.add(new Conversation(28));

                conversations.add(new Conversation(29));
                conversations.add(new Conversation(4));
                conversations.add(new Conversation(12));
                conversations.add(new Conversation(7));
                conversations.add(new Conversation(20));

                conversations.add(new Conversation(30));
                conversations.add(new Conversation(24));
                conversations.add(new Conversation(10));
                conversations.add(new Conversation(26));
                conversations.add(new Conversation(27));
                break;

            case Constants.FIRST_ENDLESS:
                for (int convNum = 1; convNum <= 30; convNum++) {
                    conversations.add(new Conversation(convNum));
                }
                break;

            case Constants.SECOND_STAGE:

                break;

            case Constants.SECOND_ENDLESS:
                for (int convNum = 1; convNum <= 50; convNum++) {
                    conversations.add(new Conversation(convNum));
                }
                break;

            case Constants.THIRD_STAGE:

                break;

            case Constants.THIRD_ENDLESS:
                for (int convNum = 1; convNum <= 70; convNum++) {
                    conversations.add(new Conversation(convNum));
                }
                break;

            case Constants.FOURTH_STAGE:

                break;

            case Constants.FOURTH_ENDLESS:
                for (int convNum = 1; convNum <= 90; convNum++) {
                    conversations.add(new Conversation(convNum));
                }
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
        int baseScore = 0;
        switch (replyNum) {
            case 1: // ふーん
                baseScore = -1;
                break;
            case 2: // へえー
                baseScore = -1;
                break;
            case 3: // そうなんだぁ
                baseScore = -1;
                break;
            case 4: // うん
                baseScore = 0;
                break;
            case 5: // すごいね
                baseScore = -2;
                break;
            case 6: // そんなことないよぉ
                baseScore = -3;
                break;
            case 7: // ありえない！
                baseScore = -3;
                break;
            case 8: // 大丈夫?
                baseScore = -3;
                break;
            case 9: // わかるぅ〜
                baseScore = -2;
                break;
            case 10:// かわいい〜
                baseScore = -2;
                break;
            case 11:// いいなぁ
                baseScore = -2;
                break;
            case 12:// 知るか
                baseScore = 3;
                break;
            default:// その他、未入力
                baseScore = 0;
                break;
        }

        hitPoint += baseScore * hpCoefficient;


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

            // インデックスを変更する前に、
            // 強制的にConversationインスタンスを初期化する
            // 2度目呼び出された場合に、困るので
            conversations.get(conversationIndex).reset();
            if (isEndless) {
                // エンドレスの場合
                // ランダムかな
                // TODO
                Random rand = new Random();
                conversationIndex = rand.nextInt(conversations.size());

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
