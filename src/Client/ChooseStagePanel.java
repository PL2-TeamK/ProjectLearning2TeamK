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
    private JButton tutorialButton;


    public ChooseStagePanel(ISwitchPanel switcher, IUserDataForAppView refToUserData) {
        panelSwitcher = switcher;
        this.refToUserData = refToUserData;
        // 背景ラベル
        backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon("./resource/image/background/Background.png"));
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBackground(Color.cyan);
        backgroundLabel.setBounds(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
        add(backgroundLabel);
        setLayer(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        // 戻るボタン
        backButton = new JButton();
        backButton.setIcon(new ImageIcon("./resource/image/buttons/backBtn.png"));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
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
            normalStageButtons[i].setIcon(new ImageIcon("./resource/image/buttons/stage" + (i+1) + "Btn.png"));
            normalStageButtons[i].setBorderPainted(false);
            normalStageButtons[i].setContentAreaFilled(false);
            normalStageButtons[i].setBounds(Constants.VIEW_WIDTH * (i + 1) / 5 - Constants.VIEW_WIDTH * 1 / 12, Constants.VIEW_HEIGHT / 3 - Constants.VIEW_HEIGHT / 8, Constants.VIEW_WIDTH * 1 / 6, Constants.VIEW_HEIGHT / 4);
            normalStageButtons[i].setActionCommand("normal," + (i + 1));
            add(normalStageButtons[i]);
            setLayer(normalStageButtons[i], JLayeredPane.PALETTE_LAYER);
            if (maxClearedStage >= i) {
                //normalStageButtons[i].setText("ステージ1");
                normalStageButtons[i].setEnabled(true);
            } else {
                //normalStageButtons[i].setText("未開放");
                normalStageButtons[i].setDisabledIcon(new ImageIcon("./resource/image/buttons/stage" + (i+1) + "Lock.png"));
                normalStageButtons[i].setEnabled(false);
                //normalStageButtons[i].setBackground(Color.gray);
            }
            normalStageButtons[i].addActionListener(e -> {
                String actionCommand = e.getActionCommand();
                int stageNum = distinguishWhichButtonClicked(actionCommand);
                panelSwitcher.switchChooseStagePanelToGamePanel(stageNum);
            });

            endlessStageButtons[i] = new JButton();
            endlessStageButtons[i].setIcon(new ImageIcon("./resource/image/buttons/stage" + (i+1) + "EndlessBtn.png"));
            endlessStageButtons[i].setBorderPainted(false);
            endlessStageButtons[i].setContentAreaFilled(false);
            endlessStageButtons[i].setBounds(Constants.VIEW_WIDTH * (i + 1) / 5 - Constants.VIEW_WIDTH * 1 / 12, Constants.VIEW_HEIGHT * 2 / 3 - Constants.VIEW_HEIGHT / 8, Constants.VIEW_WIDTH * 1 / 6, Constants.VIEW_HEIGHT / 4);
            endlessStageButtons[i].setActionCommand("endless," + (i + 1));
            add(endlessStageButtons[i]);
            setLayer(endlessStageButtons[i], JLayeredPane.PALETTE_LAYER);
            if (maxClearedStage > i) {
                //endlessStageButtons[i].setText("<html>ステージ1<br>∞</html>");
                endlessStageButtons[i].setEnabled(true);
            } else {
                //endlessStageButtons[i].setText("未開放");
                endlessStageButtons[i].setEnabled(false);
                endlessStageButtons[i].setDisabledIcon(new ImageIcon("./resource/image/buttons/stage" + (i+1) + "EndlessLock.png"));
                //endlessStageButtons[i].setBackground(Color.gray);

            }
            endlessStageButtons[i].addActionListener(e -> {
                String actionCommand = e.getActionCommand();
                int stageNum = distinguishWhichButtonClicked(actionCommand);
                panelSwitcher.switchChooseStagePanelToGamePanel(stageNum);
            });
        }

        tutorialButton = new JButton();
        tutorialButton.setIcon(new ImageIcon("./resource/image/buttons/tutorialBtn.png"));
        tutorialButton.setBounds(Constants.VIEW_WIDTH * 9 / 10 - 120, Constants.VIEW_HEIGHT / 10 - 30,
                200, 60);
        tutorialButton.setContentAreaFilled(false);
        tutorialButton.setBorderPainted(false);
        tutorialButton.addActionListener(e -> {
            switcher.switchChoosePanelToTutorialPanel();
        });
        add(tutorialButton);
        setLayer(tutorialButton, PALETTE_LAYER);
    }

    /**
     * 配列でボタンを用意しているため、どのボタンが押されたかを判別するメソッド
     * @param actionCommand
     */

    private int distinguishWhichButtonClicked(String actionCommand) {
        String[] tokens = actionCommand.split(",");
        String gameMode = tokens[0];
        int tmpStageNum = Integer.parseInt(tokens[1]);

        if (gameMode.equals("normal")) {
            switch (tmpStageNum) {
                case 1: return Constants.FIRST_STAGE;
                case 2: return Constants.SECOND_STAGE;
                case 3: return Constants.THIRD_STAGE;
                case 4: return Constants.FOURTH_STAGE;
                default: return -1;
            }
        } else if (gameMode.equals("endless")) {
            switch (tmpStageNum) {
                case 1: return Constants.FIRST_ENDLESS;
                case 2: return Constants.SECOND_ENDLESS;
                case 3: return Constants.THIRD_ENDLESS;
                case 4: return Constants.FOURTH_ENDLESS;
                default: return -1;
            }
        } else {
            return -1;
        }

    }
    

}
