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
//        backButton.setIcon(new ImageIcon("./resource/image/buttons/"));
        backButton.addActionListener(e -> {
            panelSwitcher.switchResultPanelToHomePanel();
        });
        backButton.setBounds(Constants.VIEW_WIDTH / 2 - 80, Constants.VIEW_HEIGHT / 2 - 30,
                160, 60);
        add(backButton);
        setLayer(backButton, PALETTE_LAYER);
    }

    public void setPanelSwitcher(ISwitchPanel switcher) {
        this.panelSwitcher = switcher;
    }
}
