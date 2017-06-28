package Client;

import javax.swing.*;
import java.awt.*;

/**
 * ステージ選択画面
 */
public class ChooseStagePanel extends JLayeredPane {
    private ISwitchPanel panelSwitcher;

    private JLabel backgroundLabel;
    private JButton backButton;
    private JLabel panelTitleLabel;
    private JButton normalStageButtons[] = new JButton[4];
    private JButton endlessStageButtons[] = new JButton[4];


    public ChooseStagePanel(ISwitchPanel switcher) {
        panelSwitcher = switcher;

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


    }
    

}
