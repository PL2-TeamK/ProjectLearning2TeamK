package Client;

import javax.swing.*;
import java.awt.*;

/**
 * ゲーム結果表示パネルs
 */
public class ResultPanel extends JLayeredPane {
    private JLabel backgroundLabel;
    private JButton backButton;
    private ISwitchPanel panelSwitcher;
    private JLabel scoreLabel;
    private int endlessScore;

    // ステージ番号とスコアによって表示内容を変更する。
    public ResultPanel (int stageNum, int score) {
        setLayout(null);


        // 背景ラベル
        backgroundLabel = new JLabel();
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBounds(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
//        backgroundLabel.setBackground(Color.cyan);
        add(backgroundLabel);
        setLayer(backgroundLabel, DEFAULT_LAYER);


        // ホームに戻るボタン
        backButton = new JButton();
        backButton.setIcon(new ImageIcon("./resource/image/buttons/menuBackBtn.png"));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(e -> {
            panelSwitcher.switchResultPanelToHomePanel();
        });
        backButton.setBounds(Constants.VIEW_WIDTH / 2 - 80, Constants.VIEW_HEIGHT * 3/ 4 - 30,
                160, 60);
        add(backButton);
        setLayer(backButton, PALETTE_LAYER);

        if (stageNum < 100) {
            if (score == Constants.STAGE_CLEARED) {
                stageCleared();
            } else {
                stageFailed();
            }
        } else {
            endlessScore = score;
            endlessEnded();

        }
    }

    public void setPanelSwitcher(ISwitchPanel switcher) {
        this.panelSwitcher = switcher;
    }

    private void stageCleared() {
        backgroundLabel.setIcon(new ImageIcon("./resource/image/background/clear.png"));
    }

    private void stageFailed() {
        backgroundLabel.setIcon(new ImageIcon("./resource/image/background/over.png"));
    }

    private void endlessEnded() {
        backgroundLabel.setIcon(new ImageIcon("./resource/image/background/clear.png"));
        scoreLabel = new JLabel();
        int hour = endlessScore / 3600;
        int minute = (endlessScore % 3600) / 60;
        int second = endlessScore % 60;
        String text = "<html>あなたは、<br>" + hour + ":" + minute + ":" + second + "<br>耐えました!</html>";
        scoreLabel.setText(text);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setVerticalAlignment(JLabel.CENTER);
        scoreLabel.setBounds(Constants.VIEW_WIDTH * (5 - 1) / 10,
                Constants.VIEW_HEIGHT * (5 - 1) / 10,
                Constants.VIEW_WIDTH * 2 / 10,
                Constants.VIEW_HEIGHT * 2 / 10);
        add(scoreLabel);
        setLayer(scoreLabel, PALETTE_LAYER);
    }
}
