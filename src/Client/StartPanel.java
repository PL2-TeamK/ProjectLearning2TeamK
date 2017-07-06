package Client;

import javax.swing.*;
import java.awt.*;

/**
 * スタート画面
 */
public class StartPanel extends JLayeredPane {
    // 画面遷移のためのAppViewへの参照
    private ISwitchPanel refToAppView;

    private JButton buttonToLogin;
    private JButton buttonToNew;
    private JLabel backgroundLabel;
    private JLabel titleLabel;

    private final int buttonWidth = 230;
    private final int buttonHeight =  75;

//    private final int titleWidth = 320;
//    private final int titleHeight = 180;



    public StartPanel(AppView arg) {
        refToAppView = arg;

        setLayout(null);

        // ログインボタン
        buttonToLogin = new JButton();
        buttonToLogin.setIcon(new ImageIcon("./resource/image/buttons/loginBtn.png"));
        buttonToLogin.setBorderPainted(false);
        buttonToLogin.setContentAreaFilled(false);
        buttonToLogin.setBounds(Constants.VIEW_WIDTH * 1 / 3 - buttonWidth / 2, Constants.VIEW_HEIGHT * 2 / 3, buttonWidth, buttonHeight);
        add(buttonToLogin);
        setLayer(buttonToLogin, JLayeredPane.PALETTE_LAYER, 10);
        buttonToLogin.addActionListener(e -> {
            // ログイン画面への遷移
            refToAppView.switchStartPanelToLoginPanel();
        });
        buttonToLogin.setOpaque(false);

        // 新規ユーザーボタン
        buttonToNew = new JButton();
        buttonToNew.setIcon(new ImageIcon("./resource/image/buttons/signUpBtn.png"));
        buttonToNew.setBorderPainted(false);
        buttonToNew.setContentAreaFilled(false);
        buttonToNew.setBounds(Constants.VIEW_WIDTH * 2 / 3 - buttonWidth / 2, Constants.VIEW_HEIGHT * 2 / 3, buttonWidth, buttonHeight);
        add(buttonToNew);
        setLayer(buttonToNew, JLayeredPane.PALETTE_LAYER, 10);
        buttonToNew.addActionListener(e -> {
            // 新規登録画面への遷移
            refToAppView.switchStartPanelToNewUserPanel();
        });

//        // タイトルラベル
//        titleLabel = new JLabel("タイトル");
//        titleLabel.setBounds(Constants.VIEW_WIDTH / 2 - titleWidth / 2, Constants.VIEW_HEIGHT * 1 / 3 - titleHeight / 2, titleWidth, titleHeight);
//        titleLabel.setOpaque(true);
//        titleLabel.setBackground(Color.magenta);
//        titleLabel.setHorizontalAlignment(JLabel.CENTER);
//        add(titleLabel);
//        setLayer(titleLabel, JLayeredPane.DEFAULT_LAYER, 10);


        // 背景ラベル
        backgroundLabel = new JLabel();
//        backgroundLabel.setBackground(Color.cyan);
        backgroundLabel.setBounds(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
//        backgroundLabel.setOpaque(true);
        backgroundLabel.setIcon(new ImageIcon("./resource/image/background/start.png"));
        add(backgroundLabel);
        setLayer(backgroundLabel, JLayeredPane.DEFAULT_LAYER, 100);


        setSize(Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
    }
}
