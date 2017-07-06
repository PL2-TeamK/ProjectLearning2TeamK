package Client;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * ゲーム画面
 * ゲーム開始時に生成
 */
public class GamePanel extends JLayeredPane {
    GameModel gameModel;

    private JLabel backgroundLabel;
    private JLabel mainLabel;
    private ReplyButton[] replyButtons = new ReplyButton[12];


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
        }


        // 背景のセット
        backgroundLabel = new JLabel();
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBackground(Color.cyan);
        backgroundLabel.setBounds(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
        add(backgroundLabel);
        setLayer(backgroundLabel, DEFAULT_LAYER);

    }

    public void setRefToGameModel (GameModel ref) {
        gameModel = ref;
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
    class timingCanvas extends Canvas {
        private int outerRadius;
        private int innerRadius;
        private int canvasWidth;
        private int canvasHeight;

        public void startListening() {
            // 内側の円の半径を0に変更し、Timerを起動
        }

        public float stopListening() {
            // ボタンが押されたら呼ばれるメソッドで、タイミングを0から1で返す。
            // Timerをストップする

            return 1.0f;
        }

        private void updateCanvas() {
            // 内側の円の半径を変更して、再描画を行う。
        }

        @Override
        public void paint(Graphics g) {
            // 描画メソッド
        }
    }

    
}
