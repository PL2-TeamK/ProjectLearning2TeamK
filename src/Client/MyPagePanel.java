package Client;

import javax.swing.*;

/**
 * マイページ画面
 */
public class MyPagePanel extends JLayeredPane {
    private JLabel backgroundLabel;
    private JLabel stageLabels[] = new JLabel[4];
    private JLabel scoreLabels[] = new JLabel[4];
    private JLabel clearedStageLabel;
    private User refToUser;
    private JButton backButton;
    private ISwitchPanel panelSwitcher;

    public MyPagePanel() {
        setLayout(null);

        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0,
                Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
        backgroundLabel.setOpaque(true);
        backgroundLabel.setIcon(new ImageIcon("./resource/image/background/ranking.png"));
        add(backgroundLabel);
        setLayer(backgroundLabel, DEFAULT_LAYER);

        backButton = new JButton();
        backButton.setIcon(new ImageIcon("./resource/image/buttons/backBtn.png"));
        backButton.setBounds(Constants.VIEW_WIDTH * 1 / 10 - 115, Constants.VIEW_HEIGHT * 1 / 10 - 30,
                230, 75);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.addActionListener(e -> {
            panelSwitcher.switchMyPagePanelBackToHomePanel();
        });
        add(backButton);
        setLayer(backButton, PALETTE_LAYER);

    }

    public void setRefToUser (User user) {
        refToUser = user;
    }

    public void setSwitcher (ISwitchPanel switcher) {
        panelSwitcher = switcher;
    }

    public void initLabels() {
        // 結果の表示を行う
        for (int i = 0; i < 4; i++) {
            // 左側のラベル
            String text = "ステージ" + (i + 1) + "∞";
            stageLabels[i] = new JLabel(text);
            stageLabels[i].setBounds(Constants.VIEW_WIDTH / 4, Constants.VIEW_HEIGHT * (i + 5) / 10,
                    Constants.VIEW_WIDTH / 4, 30);
            stageLabels[i].setVerticalAlignment(JLabel.CENTER);
            stageLabels[i].setHorizontalAlignment(JLabel.CENTER);
            add(stageLabels[i]);
            setLayer(stageLabels[i], PALETTE_LAYER);

            // 右側のスコア表示
            scoreLabels[i] = new JLabel(secondToText(refToUser.getHighScore().get(i)));
            scoreLabels[i].setBounds(Constants.VIEW_WIDTH / 2, Constants.VIEW_HEIGHT * (i + 5) / 10,
                    Constants.VIEW_WIDTH / 4, 30);
            scoreLabels[i].setHorizontalAlignment(JLabel.CENTER);
            scoreLabels[i].setVerticalAlignment(JLabel.CENTER);
            add(scoreLabels[i]);
            setLayer(scoreLabels[i], PALETTE_LAYER);
        }
    }

    private String secondToText (int second) {
        int hour = second / 3600;
        int minute = (second % 3600) / 60;
        int sec = second % 60;
        return hour + ":" + minute + ":" + sec;
    }




}
