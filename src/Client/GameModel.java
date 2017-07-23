package Client;

import javax.sound.sampled.Clip;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
//import java.util.Timer;
//import java.util.TimerTask;
import javax.swing.Timer;

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

    /**
     * ゲームスピードをコントロールするための変数を用意する
     */


    private Timer speedUpTimer;
    private int gameSpeed = Constants.SPEED_100_PERCENT;

    /**
     * BGMをここから流すのでClipを用意
     */
    private Clip BGMClip;

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
                int convList1[] = {6,13,27,22,2,1,16,25,17,28,29,4,12,7,20,30,24,10,26,27};
                for (int convNum: convList1) {
                    conversations.add(new Conversation(convNum));
                }
//                conversations.add(new Conversation(6));
//                conversations.add(new Conversation(13));
//                conversations.add(new Conversation(27));
//                conversations.add(new Conversation(22));
//                conversations.add(new Conversation(2));
//
//                conversations.add(new Conversation(1));
//                conversations.add(new Conversation(16));
//                conversations.add(new Conversation(25));
//                conversations.add(new Conversation(17));
//                conversations.add(new Conversation(28));
//
//                conversations.add(new Conversation(29));
//                conversations.add(new Conversation(4));
//                conversations.add(new Conversation(12));
//                conversations.add(new Conversation(7));
//                conversations.add(new Conversation(20));
//
//                conversations.add(new Conversation(30));
//                conversations.add(new Conversation(24));
//                conversations.add(new Conversation(10));
//                conversations.add(new Conversation(26));
//                conversations.add(new Conversation(27));
                break;

            case Constants.FIRST_ENDLESS:
                for (int convNum = 1; convNum <= 30; convNum++) {
                    conversations.add(new Conversation(convNum));
                }
                break;

            case Constants.SECOND_STAGE:
                int convList2[] = {43,31,5,2,39,45,29,11,22,15,32,42,37,1,50,20,14,30,47,27};
                for (int convNum: convList2) {
                    conversations.add(new Conversation(convNum));
                }
                break;

            case Constants.SECOND_ENDLESS:
                for (int convNum = 1; convNum <= 50; convNum++) {
                    conversations.add(new Conversation(convNum));
                }
                break;

            case Constants.THIRD_STAGE:
                int convList3[] = {8,60,61,24,64,32,19,36,58,9,70,26,41,44,53,40,19,55,21,66};
                for (int convNum: convList3) {
                    conversations.add(new Conversation(convNum));
                }
                break;

            case Constants.THIRD_ENDLESS:
                for (int convNum = 1; convNum <= 70; convNum++) {
                    conversations.add(new Conversation(convNum));
                }
                break;

            case Constants.FOURTH_STAGE:
                int convList4[] = {71,81,73,59,46,67,87,34,28,3,51,74,23,62,83,18,36,75,85,48};
                for (int convNum: convList4) {
                    conversations.add(new Conversation(convNum));
                }
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
        new Thread(() -> {
           playBGM();
        }).start();

        playTime = 0;

        timer = new Timer(1000, e -> {
            playTime++;
        });
        timer.start();

        if (isEndless) {
            // スピードアップに関わるメソッドをここからタイマーで呼ぶ
            speedUpTimer = new Timer(60000, e -> {
                upGameSpeed();
                new Thread(() -> {
                    playBGM();
                }).start();
                if (gameSpeed != Constants.SPEED_150_PERCENT) {
                    speedUpTimer.stop();
                    speedUpTimer.start();
                }
            });
            speedUpTimer.setRepeats(false);
            speedUpTimer.start();
        }
    }

    public void gameEnd() {
        // タイマー停止
        timer.stop();
        if (speedUpTimer != null && speedUpTimer.isRunning()) {
            speedUpTimer.stop();
        }

        if (BGMClip != null && BGMClip.isRunning()) {
            BGMClip.stop();
            BGMClip = null;
        }
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
        String returnText = conversations.get(conversationIndex).getRemark();
        conversations.get(conversationIndex).playRemark(gameSpeed);
        return returnText;
    }

    public int getStageNum() {
        return stageNum;
    }

    public void playBGM() {
        // ゲームステージと、ゲームスピードにより流す音楽を決定する。

        if (BGMClip != null && BGMClip.isRunning()) {
            BGMClip.stop();
            BGMClip = null;
        }
        String filePath = "./resource/music/stage";
        switch (stageNum) {
            case Constants.FIRST_STAGE:
                filePath += "1";
                break;
            case Constants.FIRST_ENDLESS:
                filePath += "1";
                break;
            case Constants.SECOND_STAGE:
                filePath += "2";
                break;
            case Constants.SECOND_ENDLESS:
                filePath += "2";
                break;
            case Constants.THIRD_STAGE:
                filePath += "3";
                break;
            case Constants.THIRD_ENDLESS:
                filePath += "3";
                break;
            case Constants.FOURTH_STAGE:
                filePath += "4";
                break;
            case Constants.FOURTH_ENDLESS:
                filePath += "4";
                break;
        }

        filePath += "BGM-";

        switch (gameSpeed) {
            case Constants.SPEED_100_PERCENT:
                filePath += "1";
                break;
            case Constants.SPEED_125_PERCENT:
                filePath += "2";
                break;
            case Constants.SPEED_150_PERCENT:
                filePath += "3";
                break;
        }

        filePath += ".wav";

        BGMClip = Music.getClipFromFilePath(filePath);
        Music.volumeControlByLinerScaler(BGMClip, 0.2);
        BGMClip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void upGameSpeed() {
        // エンドレスモードのスピードをあげる
        switch (gameSpeed) {
            case Constants.SPEED_100_PERCENT:
                gameSpeed = Constants.SPEED_125_PERCENT;
                break;
            case Constants.SPEED_125_PERCENT:
                gameSpeed = Constants.SPEED_150_PERCENT;
                break;
            default:
                // 何もしない
        }

        return;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

}
