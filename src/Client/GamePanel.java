package Client;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * ゲーム画面
 * ゲーム開始時に生成
 */
public class GamePanel extends JLayeredPane {

    private JLabel backgroundLabel;
    private JLabel mainLabel;
    private ReplyButton[] replyButtons = new ReplyButton[12];


    public GamePanel(int stageNum) {
        /**
         * ボタンの生成
         */
        if (stageNum == Constants.FIRST_STAGE || stageNum == Constants.FIRST_ENDLESS) {
            int[] validButtonNum = {1, 2, 3, 4, 12};
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
        ReplyButton(int replyNum, boolean isValid) {
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

            setBounds(Constants.VIEW_WIDTH / 14 * (1 + xPos * 3), Constants.VIEW_WIDTH / 14 * (7 + yPos * 2), Constants.VIEW_WIDTH * 3 / 14, Constants.VIEW_HEIGHT * 2 / 14);
            setActionCommand(replyNum + ""); // replyNumがそのままコマンドになる
            setText(ReplyList.ALL_REPLY.get(replyNum));
            setEnabled(isValid);
        }


    }
}
