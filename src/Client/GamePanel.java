package Client;

import javax.swing.*;
import java.awt.*;

/**
 * ゲーム画面
 * ゲーム開始時に生成
 */
public class GamePanel extends JLayeredPane {
    GameModel gameModel;
    ISwitchPanel panelSwitcher;

    private JLabel backgroundLabel;
    private JLabel mainLabel;
    private ReplyButton[] replyButtons = new ReplyButton[12];
    private TimingCanvas timingCanvas;
    private GaugeCanvas hpGaugeCanvas;
    private GaugeCanvas mpGaugeCanvas;
    private JLabel remarkLabel;

    private int remarkWidth = Constants.VIEW_WIDTH / 3;
    private int remarkHeight = 40;


    public GamePanel(int stageNum) {
        /**
         * ボタンの生成
         */
        if (stageNum == Constants.FIRST_STAGE || stageNum == Constants.FIRST_ENDLESS) {
            // 有効なボタン
            int[] validButtonNum = {1, 2, 3, 4, 12};
            // 無効なボタン
            int[] invalidButtonNum = {5, 6, 7, 8, 9, 10, 11};
            int counter = 0;
            for(int i = 0; i < validButtonNum.length; i++) {
                replyButtons[counter] = new ReplyButton(validButtonNum[i], true);
                counter++;
            }
            for (int i : invalidButtonNum) {
                replyButtons[counter] = new ReplyButton(i, false);
                counter++;
            }
        } else if (stageNum == Constants.SECOND_STAGE || stageNum == Constants.SECOND_ENDLESS) {

        } else if (stageNum == Constants.THIRD_STAGE || stageNum == Constants.THIRD_ENDLESS) {

        } else if (stageNum == Constants.FOURTH_STAGE || stageNum == Constants.FOURTH_ENDLESS) {

        }
        /**
         * ボタンの描画
         */
        for (ReplyButton button: replyButtons) {
            add(button);
            setLayer(button, PALETTE_LAYER);
            button.addActionListener(e -> {
                // ボタンが押された時の処理をセット
                // 返答番号が文字列で格納されている。
                String command = e.getActionCommand();
                int replyNum = Integer.parseInt(command);
                // 押されたタイミング値を取得
                float timing = timingCanvas.replyButtonClicked();
                // GameModelの値の更新を行う。
                updateGameModelAndCanvas(replyNum, timing);

                // 再びRemarkの更新などを行う
                gameHandler();
            });
        }


        // 背景のセット
        backgroundLabel = new JLabel();
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBackground(Color.cyan);
        backgroundLabel.setBounds(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
        add(backgroundLabel);
        setLayer(backgroundLabel, DEFAULT_LAYER);

        // TimingCanvasを用意
        timingCanvas = new TimingCanvas(Constants.VIEW_HEIGHT * 8 / 20);
        // TimingCanvasがtimeoutForInputを呼べるようにする。
        timingCanvas.setBounds(Constants.VIEW_WIDTH * 1 / 20, Constants.VIEW_HEIGHT * 1 / 4 - Constants.VIEW_HEIGHT * 4 / 20, Constants.VIEW_HEIGHT * 8 /20, Constants.VIEW_HEIGHT * 8 / 20);
        add(timingCanvas);
        setLayer(timingCanvas, PALETTE_LAYER);

        // hpGaugeCanvasを用意
        hpGaugeCanvas = new GaugeCanvas(Constants.VIEW_WIDTH * 1 / 20, Constants.VIEW_HEIGHT * 4 / 10);
        hpGaugeCanvas.setBounds(Constants.VIEW_WIDTH * 8 / 10 - Constants.VIEW_WIDTH * 1 / 40, Constants.VIEW_HEIGHT * 1 / 4 - Constants.VIEW_HEIGHT * 2 / 10,
                Constants.VIEW_WIDTH * 1 / 20, Constants.VIEW_HEIGHT * 4 / 10);
        hpGaugeCanvas.setGaugeColor(new Color(Integer.parseInt("E6855E", 16)));
        hpGaugeCanvas.setValue(100.0f);
        add(hpGaugeCanvas);
        setLayer(hpGaugeCanvas, PALETTE_LAYER);

        //mpGaugeCanvasを用意
        mpGaugeCanvas = new GaugeCanvas(Constants.VIEW_WIDTH * 1 / 20, Constants.VIEW_HEIGHT * 4 / 10);
        mpGaugeCanvas.setBounds(Constants.VIEW_WIDTH * 9 / 10 - Constants.VIEW_WIDTH * 1 / 40, Constants.VIEW_HEIGHT * 1 / 4 - Constants.VIEW_HEIGHT * 2 /10,
                Constants.VIEW_WIDTH * 1 / 20, Constants.VIEW_HEIGHT * 4 / 10);
        mpGaugeCanvas.setGaugeColor(new Color(Integer.parseInt("5EC84E", 16)));
        mpGaugeCanvas.setValue(100.0f);
        add(mpGaugeCanvas);
        setLayer(mpGaugeCanvas, PALETTE_LAYER);

        // 動く発言ラベルの設定
        remarkLabel = new JLabel();
        remarkLabel.setHorizontalAlignment(JLabel.CENTER);
        remarkLabel.setVerticalAlignment(JLabel.CENTER);
        remarkLabel.setBounds(Constants.VIEW_WIDTH / 2 - remarkWidth / 2, Constants.VIEW_HEIGHT / 5 - remarkHeight / 2,
                remarkWidth, remarkHeight);
        remarkLabel.setOpaque(true);
        remarkLabel.setBackground(Color.yellow);
        add(remarkLabel);
        setLayer(remarkLabel, PALETTE_LAYER);

    }

    public void gameHandler() {
        // ボタンが押されたあと、ゲームが継続する場合呼ばれる。

        // 発言取得、remarkLabel更新
        String remarkText = gameModel.getNextRemarkText();
        if (remarkText.equals(Constants.LAST_CONV)) {
            // 通常モードの最後の発言が終わった場合
            gameFinish();

            // gameFinishを読んだ後は仕事がないので、メソッドを抜ける
            return;
        }
        // remarkLabelが動くアニメーション
        remarkLabel.setText(remarkText);
//        int labelPosX = Constants.VIEW_WIDTH / 2 - remarkWidth / 2;
//        Timer labelTimer = new Timer(40, e -> {
//            int nextPosY = (int)remarkLabel.getBounds().getY() + 10;
//            remarkLabel.setBounds(labelPosX, nextPosY, remarkWidth, remarkHeight);
//            if (nextPosY >= Constants.VIEW_HEIGHT * 2 / 5) {
//                labelTimer.stop(); // <- ここでだめ
//            }
//        });
//        labelTimer.setRepeats(true);
//        labelTimer.start();

//        repaint();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e){
//            e.printStackTrace();
//        }

        // ボタンを有効にする
        for (ReplyButton button : replyButtons) {
            button.setStateStandBy();
        }
        // タイミング円のアニメーション開始
//        timingCanvas.startListening();
        // 次はボタンアクションか時間で停止する。
        Timer timer = new Timer(1000, e -> {
            timingCanvas.startListening();
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void gameFinish() {
        // ゲーム終了時に呼ばれるメソッド
        // TODO: 実装
        // タイマー停止
        // タイマーの起動はAppView.switchChoosePanelToGamePanel()で行なっている
        gameModel.gameEnd();
        boolean clearedFlag = true;
        if (gameModel.getHitPoint() <= 0f || gameModel.getMoodPoint() <= 0f) {
            clearedFlag = false;
        }
        int score;
        if (gameModel.getStageNum() > 100) {
            score = gameModel.getPlayTime();
        } else {
            if (clearedFlag) {
                score = Constants.STAGE_CLEARED;
            } else {
                score = Constants.STAGE_FAILED;
            }
        }

        panelSwitcher.switchGamePanelToResultPanel(gameModel.getStageNum(), score);
    }

    public void setRefToGameModel (GameModel ref) {
        gameModel = ref;
    }

    public void setPanelSwitcher (ISwitchPanel switcher) {
        // 画面遷移用の参照をセットする。
        this.panelSwitcher = switcher;
    }

    /**
     * ICallBackFromTimingCanvasの実装
     */
    public void timeoutForInput() {
        // 時間切れにより、全てのボタン入力を停止する。
        for (ReplyButton button : replyButtons) {
            button.setEnabled(false);
        }
        boolean isGameValid = updateGameModelAndCanvas(-1, 0.0f);
        if (isGameValid) {
            gameHandler();
        } else {
            gameFinish();
        }

        repaint();
    }

    public boolean updateGameModelAndCanvas(int replyNum, float timing) {
        // mpかhpのどちらかが0ならfalse
        boolean hpValid = gameModel.updateHitPoint(replyNum);
        boolean mpValid = gameModel.updateMoodPoint(replyNum, timing);
        // Canvasに値を渡して更新
        hpGaugeCanvas.setValue(gameModel.getHitPoint());
        mpGaugeCanvas.setValue(gameModel.getMoodPoint());

        if (hpValid && mpValid) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * GamePanel内で用いるカスタムしたJButton
     */
    class ReplyButton extends JButton {
        /**
         * replyNumにセットした値で文字列をセット
         * replyNumの値で表示位置まで決定する
         * isValidで操作可能かを指定する
         * @param replyNum
         * @param isValid
         */
        private boolean isValid;

        ReplyButton(int replyNum, boolean isValid) {
            this.isValid = isValid;
            int xPos;
            int yPos;
            switch (replyNum) {
                case 1: // ふーん
                    xPos = 1;
                    yPos = 0;
                    break;
                case 2: // へえー
                    xPos = 2;
                    yPos = 0;
                    break;
                case 3: // そうなんだぁ
                    xPos = 1;
                    yPos = 1;
                    break;
                case 4: // うん
                    xPos = 2;
                    yPos = 1;
                    break;
                case 5: // すごいね
                    xPos = 1;
                    yPos = 2;
                    break;
                case 6: // そんなことないよぉ
                    xPos = 0;
                    yPos = 0;
                    break;
                case 7: // ありえない!
                    xPos = 3;
                    yPos = 0;
                    break;
                case 8: // 大丈夫?
                    xPos = 0;
                    yPos = 1;
                    break;
                case 9: // 分かるぅ〜
                    xPos = 3;
                    yPos = 1;
                    break;
                case 10: // かわいい〜
                    xPos = 0;
                    yPos = 2;
                    break;
                case 11: // いいなぁ
                    xPos = 3;
                    yPos = 2;
                    break;
                case 12: // 知るか
                    xPos = 2;
                    yPos = 2;
                    break;
                default:
                    xPos = 0;
                    yPos = 0;
            }
            /**
             * 描画座標のセット
             * コマンドのセット
             * テキストのセット
             * 表示設定
             */
            setBounds(Constants.VIEW_WIDTH / 14 * (1 + xPos * 3), Constants.VIEW_HEIGHT / 14 * (7 + yPos * 2), Constants.VIEW_WIDTH * 3 / 14, Constants.VIEW_HEIGHT * 2 / 14);
            setActionCommand(replyNum + ""); // replyNumがそのままコマンドになる
            if (isValid) {
                setText(ReplyList.ALL_REPLY.get(replyNum));
            } else {
                setText("今日は必要ないね");
            }
            setEnabled(isValid);
        }

        public void setStateStandBy() {
            // 有効なボタンのみ有効化する
            setEnabled(isValid);
        }


    }

    /**
     * タイミングを示すCanvas
     * 外側の円と内側から広がった円を表示
     *
     */
    class TimingCanvas extends Canvas {


        private int outerRadius;
        private int innerRadius;
        // 正方形だからwidthだけ
        private int canvasWidth;


        private Timer timer;

        public TimingCanvas(int width) {
            canvasWidth = width;
            setSize(width, width);
            outerRadius = width * 6 / 20;
            setBackground(new Color(0, 0, 0, 0));
        }

        public void startListening() {
            // 内側の円の半径を0に変更し、Timerを起動
            innerRadius = 0;
            timer = new Timer(30, e -> {
                // TimerFunc
                updateCanvas();
                if (innerRadius > canvasWidth * 9 / 20) {
                    // timing = 0fにしたい
                    timer.stop();
                    // インナークラスからはそのアウタークラスのメソッドにアクセスできる!?
                    GamePanel.this.timeoutForInput();
                }
            });
            timer.setRepeats(true);
            timer.start();
        }

        public float replyButtonClicked() {
            // ボタンが押されたら呼ばれるメソッドで、タイミングを0から1で返す。
            // Timerをストップする
            timer.stop();
            float timing;
            timing = 1.0f - Math.abs(innerRadius - outerRadius) / canvasWidth * 3 / 20;

            if (timing < 0) {
                return 0f;
            }

            return timing;
        }

        private void updateCanvas() {
            // 内側の円の半径を変更して、再描画を行う。
            innerRadius += 1;
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            // 描画メソッド
            g2d.setColor(new Color(0, 0, 0, 0));
            g2d.clearRect(0, 0, canvasWidth, canvasWidth);
            g2d.setColor(Color.yellow);
            g2d.fillOval(canvasWidth / 2 - outerRadius, canvasWidth / 2 - outerRadius,
                    2 * outerRadius, 2 * outerRadius);
            g2d.setColor(Color.CYAN);
            g2d.fillOval(canvasWidth / 2 - innerRadius, canvasWidth / 2 - innerRadius,
                    2 * innerRadius, 2 * innerRadius);

        }

    }

    /**
     * 体力値と雰囲気値表示用Canvas
     */

    class GaugeCanvas extends Canvas {
        private float value;
        private int canvasWidth;
        private int canvasHeight;
        private Color gaugeColor;

        public GaugeCanvas (int width, int height) {
            canvasWidth = width;
            canvasHeight = height;
            setSize(canvasWidth, canvasHeight);
            gaugeColor = Color.black;
        }

        public void setValue(float value) {
            this.value = value;
            repaint();
        }

        public void setGaugeColor(Color color) {
            gaugeColor = color;
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            // 描画処理の記述
            g2d.setColor(gaugeColor);
            g2d.fillRect(0, (int)(canvasHeight * (100.f - value) / 100.f), canvasWidth, canvasHeight);
        }


    }


}
