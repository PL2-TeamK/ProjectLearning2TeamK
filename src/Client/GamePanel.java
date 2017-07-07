package Client;

import javax.swing.*;
import java.awt.*;

/**
 * ゲーム画面
 * ゲーム開始時に生成
 */
public class GamePanel extends JLayeredPane {
    GameModel gameModel;

    private JLabel backgroundLabel;
    private JLabel mainLabel;
    private ReplyButton[] replyButtons = new ReplyButton[12];
    private TimingCanvas timingCanvas;
    private GaugeCanvas hpGaugeCanvas;
    private GaugeCanvas mpGaugeCanvas;


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

    }

    public void setRefToGameModel (GameModel ref) {
        gameModel = ref;
    }

    /**
     * ICallBackFromTimingCanvasの実装
     */
    public void timeoutForInput() {
        updateGameModelAndCanvas(-1, 0.0f);
    }

    public void updateGameModelAndCanvas(int replyNum, float timing) {
        gameModel.updateHitPoint(replyNum);
        gameModel.updateMoodPoint(replyNum, timing);
        // Canvasに値を渡して更新
        hpGaugeCanvas.setValue(gameModel.getHitPoint());
        mpGaugeCanvas.setValue(gameModel.getMoodPoint());
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
            outerRadius = width * 8 / 20;
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
        }

        public float replyButtonClicked() {
            // ボタンが押されたら呼ばれるメソッドで、タイミングを0から1で返す。
            // Timerをストップする
            timer.stop();
            float timing;

            return 1.0f;
        }

        private void updateCanvas() {
            // 内側の円の半径を変更して、再描画を行う。
            innerRadius += outerRadius / 60;
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
