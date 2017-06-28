package Client;

import javax.swing.*;
import java.awt.*;

/**
 * ステージ選択画面
 */
public class ChooseStagePanel extends JLayeredPane {
    private ISwitchPanel panelSwitcher;
    private IUserDataForAppView refToUserData;

    private JLabel backgroundLabel;
    private JButton backButton;
    private JLabel panelTitleLabel;
    private JButton normalStageButtons[] = new JButton[4];
    private JButton endlessStageButtons[] = new JButton[4];


    public ChooseStagePanel(ISwitchPanel switcher, IUserDataForAppView refToUserData) {
        panelSwitcher = switcher;
        this.refToUserData = refToUserData;
        // 背景ラベル
        backgroundLabel = new JLabel();
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBackground(Color.cyan);
        backgroundLabel.setBounds(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
        add(backgroundLabel);
        setLayer(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        // 戻るボタン
        backButton = new JButton("前の画面に戻る");
        backButton.setBounds(32, 18, 160, 20);
        add(backButton);
        setLayer(backButton, JLayeredPane.PALETTE_LAYER);
        // ラムダ式内に1つの式しかないときは{}がいらないらしい。
        backButton.addActionListener(e ->
            panelSwitcher.switchChooseStagePanelBackToHomePanel()
        );

        int maxClearedStage = refToUserData.getMaxClearedStage();
        for (int i = 0; i < 4; i++) {
            normalStageButtons[i] = new JButton();
            normalStageButtons[i].setBounds(Constants.VIEW_WIDTH * (i + 1) / 5 - Constants.VIEW_WIDTH * 1 / 12, Constants.VIEW_HEIGHT / 3 - Constants.VIEW_HEIGHT / 8, Constants.VIEW_WIDTH * 1 / 6, Constants.VIEW_HEIGHT / 4);
            normalStageButtons[i].setActionCommand("normal," + (i + 1));
            add(normalStageButtons[i]);
            setLayer(normalStageButtons[i], JLayeredPane.PALETTE_LAYER);
            if (maxClearedStage >= i) {
                normalStageButtons[i].setText("ステージ1");
                normalStageButtons[i].setEnabled(true);
            } else {
                normalStageButtons[i].setText("未開放");
                normalStageButtons[i].setEnabled(false);
                normalStageButtons[i].setBackground(Color.gray);
            }
            normalStageButtons[i].addActionListener(e -> {
                String actionCommand = e.getActionCommand();
                distinguishWhichButtonClicked(actionCommand);
            });

            endlessStageButtons[i] = new JButton();
            endlessStageButtons[i].setBounds(Constants.VIEW_WIDTH * (i + 1) / 5 - Constants.VIEW_WIDTH * 1 / 12, Constants.VIEW_HEIGHT * 2 / 3 - Constants.VIEW_HEIGHT / 8, Constants.VIEW_WIDTH * 1 / 6, Constants.VIEW_HEIGHT / 4);
            normalStageButtons[i].setActionCommand("endless," + (i + 1));
            add(endlessStageButtons[i]);
            setLayer(endlessStageButtons[i], JLayeredPane.PALETTE_LAYER);
            if (maxClearedStage > i) {
                endlessStageButtons[i].setText("<html>ステージ1<br>∞</html>");
                endlessStageButtons[i].setEnabled(true);
            } else {
                endlessStageButtons[i].setText("未開放");
                endlessStageButtons[i].setEnabled(false);
                endlessStageButtons[i].setBackground(Color.gray);

            }
            endlessStageButtons[i].addActionListener(e -> {
                String actionCommand = e.getActionCommand();
                distinguishWhichButtonClicked(actionCommand);
            });
        }

    }

    /**
     * 配列でボタンを用意しているため、どのボタンが押されたかを判別するメソッド
     * @param actionCommand
     */

    private void distinguishWhichButtonClicked(String actionCommand) {
        String[] tokens = actionCommand.split(",");
    }
    

}
