package Client;

import javax.swing.*;
import java.awt.*;

/**
 * ホーム画面
 */
public class HomePanel extends JLayeredPane {
    private ISwitchPanel panelSwitcher;

    private JLabel backgroundLabel;
    private JLabel panelTitleLabel;
    private JButton logoutButton;
    private JButton chooseStageButton;
    private JButton rankingButton;
    private JButton mypageButton;
    private JLabel maskLabel;
    private JButton confirmButton;
    private JButton refuseButton;
    private JLabel infoLabel;



    public HomePanel(ISwitchPanel switcher) {
        panelSwitcher = switcher;

        // 背景ラベル
        backgroundLabel = new JLabel();
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBounds(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
//        backgroundLabel.setBackground(Color.cyan);
        backgroundLabel.setIcon(new ImageIcon("./resource/image/background/menu.png"));
        add(backgroundLabel);
        setLayer(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

//        // パネルラベルタイトル
//        panelTitleLabel = new JLabel("ホーム");
//        panelTitleLabel.setHorizontalAlignment(JLabel.CENTER);
//        panelTitleLabel.setBounds(Constants.VIEW_WIDTH / 2 - 160, Constants.VIEW_HEIGHT / 4 - 20, 320, 40);
//        add(panelTitleLabel);
//        setLayer(panelTitleLabel, JLayeredPane.PALETTE_LAYER);

        // ステージ選択画面に遷移するボタン
        chooseStageButton = new JButton();
        chooseStageButton.setIcon(new ImageIcon("./resource/image/buttons/stageSelectBtn.png"));
        chooseStageButton.setBorderPainted(false);
        chooseStageButton.setContentAreaFilled(false);
        chooseStageButton.setHorizontalAlignment(JButton.CENTER);
        chooseStageButton.setBounds(Constants.VIEW_WIDTH / 4 - 80, Constants.VIEW_HEIGHT * 3 / 5 - 80, 120, 120);
        add(chooseStageButton);
        setLayer(chooseStageButton, JLayeredPane.PALETTE_LAYER);
        chooseStageButton.addActionListener(e -> {
            panelSwitcher.switchHomePanelToChooseStagePanel();
        });

        // ランキング画面に遷移するボタン
        rankingButton = new JButton();
        rankingButton.setIcon(new ImageIcon("./resource/image/buttons/rankingBtn.png"));
        rankingButton.setBorderPainted(false);
        rankingButton.setContentAreaFilled(false);
        rankingButton.setHorizontalAlignment(JButton.CENTER);
        rankingButton.setBounds(Constants.VIEW_WIDTH / 2 - 80, Constants.VIEW_HEIGHT * 3 / 5 - 80, 120, 120);
        add(rankingButton);
        setLayer(rankingButton, JLayeredPane.PALETTE_LAYER);
        rankingButton.addActionListener(e -> {
            panelSwitcher.switchHomePanelToRankingPanel();
        });

        // マイページに遷移するボタン
        mypageButton = new JButton();
        mypageButton.setIcon(new ImageIcon("./resource/image/buttons/myPageBtn.png"));
        mypageButton.setBorderPainted(false);
        mypageButton.setContentAreaFilled(false);
        mypageButton.setHorizontalAlignment(JButton.CENTER);
        mypageButton.setBounds(Constants.VIEW_WIDTH * 3 / 4 - 80, Constants.VIEW_HEIGHT * 3 / 5 - 80, 120, 120);
        add(mypageButton);
        setLayer(mypageButton, PALETTE_LAYER);
        mypageButton.addActionListener(e -> {
            panelSwitcher.switchHomePanelToMyPagePanel();
        });

        // ログアウト
        logoutButton = new JButton();
        logoutButton.setIcon(new ImageIcon("./resource/image/buttons/backTitleBtn.png"));
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setBounds(32, 18, 160, 20);
        logoutButton.setHorizontalAlignment(JButton.CENTER);
        add(logoutButton);
        setLayer(logoutButton, JLayeredPane.PALETTE_LAYER);
        logoutButton.addActionListener(e -> {
            maskLabel.setVisible(true);
            confirmButton.setVisible(true);
            confirmButton.setEnabled(true);
            refuseButton.setVisible(true);
            refuseButton.setEnabled(true);
            infoLabel.setVisible(true);
        });

        // ログアウト確認画面
        // 画面マスク
        maskLabel = new JLabel();
        maskLabel.setIcon(new ImageIcon("./resource/image/background/menu.png"));
        maskLabel.setOpaque(true);
        maskLabel.setBackground(new Color(33, 33, 33, 100));
        maskLabel.setBounds(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
        add(maskLabel);
        setLayer(maskLabel, JLayeredPane.MODAL_LAYER);
        maskLabel.setVisible(false);



        // 決定ボタン
        confirmButton = new JButton();
        confirmButton.setIcon(new ImageIcon("./resource/image/buttons/confirmBtn.png"));
        confirmButton.setBorderPainted(false);
        confirmButton.setContentAreaFilled(false);
        confirmButton.setHorizontalAlignment(JButton.CENTER);
        confirmButton.setBounds(Constants.VIEW_WIDTH / 3 - 80, Constants.VIEW_HEIGHT * 2 / 3 - 20, 160, 40);
        add(confirmButton);
        setLayer(confirmButton, JLayeredPane.POPUP_LAYER);
        confirmButton.setVisible(false);
        confirmButton.setEnabled(false);
        confirmButton.addActionListener(e -> {
            panelSwitcher.switchHomePanelBackToStartPanel();
        });

        // キャンセルボタン
        refuseButton = new JButton();
        refuseButton.setIcon(new ImageIcon("./resource/image/buttons/refuseBtn.png"));
        refuseButton.setBorderPainted(false);
        refuseButton.setContentAreaFilled(false);
        refuseButton.setHorizontalAlignment(JButton.CENTER);
        refuseButton.setBounds(Constants.VIEW_WIDTH * 2 / 3 - 80, Constants.VIEW_HEIGHT * 2 / 3 - 20, 160, 40);
        add(refuseButton);
        setLayer(refuseButton, JLayeredPane.POPUP_LAYER);
        refuseButton.setVisible(false);
        refuseButton.setEnabled(false);
        refuseButton.addActionListener(e -> {
            maskLabel.setVisible(false);
            confirmButton.setVisible(false);
            confirmButton.setEnabled(false);
            refuseButton.setVisible(false);
            refuseButton.setEnabled(false);
            infoLabel.setVisible(false);

            chooseStageButton.setEnabled(true);
            rankingButton.setEnabled(true);
            mypageButton.setEnabled(true);
            logoutButton.setEnabled(true);

        });

        // infoラベル
        infoLabel = new JLabel("本当にタイトルに戻りますか?");
        infoLabel.setBounds(Constants.VIEW_WIDTH / 2 - 160, Constants.VIEW_HEIGHT / 3 - 20, 320 , 40);
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        add(infoLabel);
        setLayer(infoLabel, JLayeredPane.POPUP_LAYER);
        infoLabel.setVisible(false);

        // テスト

    }

}
