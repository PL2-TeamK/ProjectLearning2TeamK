package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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

    private boolean isGameFinishMethodCalled = false;

    // 内心モードであるか
    private boolean isMindMode = false;
    private boolean isNextButtonShuffle = false;

    // 内心モード用Randomインスタンス
    private Random random;


    public GamePanel(int stageNum) {
        /**
         * ボタンの生成
         */

        if (stageNum == Constants.FIRST_STAGE || stageNum == Constants.FIRST_ENDLESS) {
            // 有効なボタン
            int[] validButtonNum = {1, 2, 3, 4, 9, 12};
            // 無効なボタン
            int[] invalidButtonNum = {5, 6, 7, 8, 10, 11};
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
            // 有効なボタン
            int[] validButtonNum = {1, 2, 3, 4, 7, 8, 9, 12};
            // 無効なボタン
            int[] invalidButtonNum = {5, 6, 10, 11};
            int counter = 0;
            for(int i = 0; i < validButtonNum.length; i++) {
                replyButtons[counter] = new ReplyButton(validButtonNum[i], true);
                counter++;
            }
            for (int i : invalidButtonNum) {
                replyButtons[counter] = new ReplyButton(i, false);
                counter++;
            }
        } else if (stageNum == Constants.THIRD_STAGE || stageNum == Constants.THIRD_ENDLESS) {
            // 有効なボタン
            int[] validButtonNum = {1, 2, 3, 4, 5, 7, 8, 9, 11, 12};
            // 無効なボタン
            int[] invalidButtonNum = {6, 10};
            int counter = 0;
            for(int i = 0; i < validButtonNum.length; i++) {
                replyButtons[counter] = new ReplyButton(validButtonNum[i], true);
                counter++;
            }
            for (int i : invalidButtonNum) {
                replyButtons[counter] = new ReplyButton(i, false);
                counter++;
            }
        } else if (stageNum == Constants.FOURTH_STAGE || stageNum == Constants.FOURTH_ENDLESS) {
            // 全てのボタンが有効
            for (int i = 0; i < 12; i++) {
                replyButtons[i] = new ReplyButton(i + 1, true);
            }
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
                for (ReplyButton but: replyButtons) {
                    but.setEnabled(false);
                }
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
        backgroundLabel.setIcon(new ImageIcon("./resource/image/background/playBackground.png"));
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

        // Randomインスタンスの生成
        random = new Random();

    }

    public void gameHandler() {
        // ボタンが押されたあと、ゲームが継続する場合呼ばれる。
        if (gameModel.getMoodPoint() <= 0 || gameModel.getHitPoint() <= 0) {
            gameFinish();
        }

        // isNextButtonShuffle = trueの場合、ボタンの位置を変更させる
        if (isNextButtonShuffle) {
            shuffleReplyButtons();
        }
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



        // タイミング円のアニメーション開始
//        timingCanvas.startListening();


        // 文脈の終端時に特定のモードに入る可能性がある
        isMindMode = false;
        isNextButtonShuffle = false;

        if (gameModel.getConvIsEnd()) {
            float value = random.nextFloat();
            if (value <= 0.1) {
                // 内心モード
                isMindMode = true;
            } else if (value <= 0.2) {
                // ボタンシャッフルモード
                isNextButtonShuffle = true;
            }
        }


        // ボタンが動くまでの時間をゲームスピードによって変更する
        int delay = 1000;
        switch (gameModel.getGameSpeed()) {
            case Constants.SPEED_100_PERCENT:
                delay = 1000;
                break;
            case Constants.SPEED_125_PERCENT:
                delay = 800;
                break;
            case Constants.SPEED_150_PERCENT:
                delay = 660;
                break;
        }


        // 次はボタンアクションか時間で停止する。
        Timer timer = new Timer(delay, e -> {
            // ボタンを有効にする
            for (ReplyButton button : replyButtons) {
                button.setStateStandBy();
            }
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
        if (isGameFinishMethodCalled) {
            return;
        }

        isGameFinishMethodCalled = true;

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
        if (!isMindMode) {
            // 内心モード時には雰囲気の更新はしない
            mpGaugeCanvas.setValue(gameModel.getMoodPoint());
        }

        if (hpValid && mpValid) {
            return true;
        } else {
            return false;
        }
    }

    public void shuffleReplyButtons() {
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= replyButtons.length; i++) {
            nums.add(i);
        }

        Collections.shuffle(nums);

        for (int i = 0; i < replyButtons.length; i++) {
            replyButtons[i].changePosition(nums.get(i));
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
            setIcon(new ImageIcon("./resource/image/buttons/replyBtn" + replyNum + ".png"));
            //setBorderPainted(false);
            //setContentAreaFilled(false);
            setBounds(Constants.VIEW_WIDTH / 14 * (1 + xPos * 3), Constants.VIEW_HEIGHT / 14 * (7 + yPos * 2), Constants.VIEW_WIDTH * 3 / 14, Constants.VIEW_HEIGHT * 2 / 14);
            setActionCommand(replyNum + ""); // replyNumがそのままコマンドになる
            if (isValid) {
                //setText(ReplyList.ALL_REPLY.get(replyNum));
            } else {
                //setText("今日は必要ないね");
            }
            setEnabled(isValid);
            setContentAreaFilled(false);
            setBorderPainted(false);
        }

        public void setStateStandBy() {
            // 有効なボタンのみ有効化する
            setEnabled(isValid);
        }

        public void changePosition(int position) {
            int xPos;
            int yPos;
            switch (position) {
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
            setBounds(Constants.VIEW_WIDTH / 14 * (1 + xPos * 3), Constants.VIEW_HEIGHT / 14 * (7 + yPos * 2),
                    Constants.VIEW_WIDTH * 3 / 14, Constants.VIEW_HEIGHT * 2 / 14);
            repaint();
        }


    }

    /**
     * タイミングを示すCanvas
     * 外側の円と内側から広がった円を表示
     *
     */
    class TimingCanvas extends Canvas {


        private float outerRadius;
        private float innerRadius;
        // 正方形だからwidthだけ
        private int canvasWidth;


        // 色
        private Color bgColor = new Color(Integer.parseInt("100000", 16));
        private Color outerColor = new Color(Integer.parseInt("FFFFFF", 16));//CE7754
        private Color normalColor = new Color(Integer.parseInt("FFFF00", 16));//3E90BA
        private Color mindModeColor = new Color(Integer.parseInt("1E90FF", 16));//B94E8A
        private Color shuffleColor = new Color(Integer.parseInt("FF0000", 16));//E5D64C



        private Timer timer;

        public TimingCanvas(int width) {
            canvasWidth = width;
            setSize(width, width);
            outerRadius = width * 6 / 20;
            setBackground(bgColor);
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
            innerRadius += 2.2;
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            // 描画メソッド

            // アンチエイリアス処理
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(bgColor);
            g2d.clearRect(0, 0, canvasWidth, canvasWidth);

            // 線の太さ指定
            g2d.setStroke(new BasicStroke(12));

            // 外側の円の描画
            g2d.setColor(outerColor);
            g2d.drawOval(canvasWidth / 2 - (int)outerRadius, canvasWidth / 2 - (int)outerRadius,
                    2 * (int)outerRadius, 2 * (int)outerRadius);

            // 内側の円の描画
            if (isMindMode) {
                // 内心モード有効時には色を変更する
                g2d.setColor(mindModeColor);
            } else if (isNextButtonShuffle) {
                g2d.setColor(shuffleColor);
            } else {
                g2d.setColor(normalColor);
            }
            g2d.drawOval(canvasWidth / 2 - (int)innerRadius, canvasWidth / 2 - (int)innerRadius,
                    2 * (int)innerRadius, 2 * (int)innerRadius);

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
        private String text;

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

        public void setText(String arg) {
            text = arg;
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
