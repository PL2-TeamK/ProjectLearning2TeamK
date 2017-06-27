package Client;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class LoginPanel extends JLayeredPane {
    // 画面遷移メソッドを参照するためのインタフェース
    private ISwitchPanel refToAppView;
    private IReceiveNameAndPass senderToAppView;

    private JLabel backgroundLabel;
    private JLabel panelTitleLabel;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton confirmButton;
    private JLabel nameInfoLabel;
    private JLabel passInfoLabel;
    private JLabel emptyAlertLabel;
    private JLabel refusedAlertLabel;

    public LoginPanel(ISwitchPanel reference, IReceiveNameAndPass ref2) {
        setLayout(null);

        refToAppView = reference;
        senderToAppView = ref2;

        // 背景ラベル
        backgroundLabel = new JLabel();
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBounds(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_WIDTH);
        backgroundLabel.setBackground(Color.cyan);
        add(backgroundLabel);
        setLayer(backgroundLabel, JLayeredPane.DEFAULT_LAYER, 20);

        // パネルタイトルラベル
        panelTitleLabel = new JLabel("ログイン");
        panelTitleLabel.setOpaque(false);
        panelTitleLabel.setBackground(Color.magenta);
        panelTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        panelTitleLabel.setBounds(Constants.VIEW_WIDTH / 2 - 80, Constants.VIEW_HEIGHT / 4 - 45, 160, 90);
        add(panelTitleLabel);
        setLayer(panelTitleLabel, JLayeredPane.PALETTE_LAYER, 50);

        // テキストフィールドを示すラベル
        nameInfoLabel = new JLabel("ユーザー名");
        nameInfoLabel.setOpaque(false);
        nameInfoLabel.setBounds(Constants.VIEW_WIDTH / 4, Constants.VIEW_HEIGHT / 2 - 10, 160, 20);
        add(nameInfoLabel);
        setLayer(nameInfoLabel, PALETTE_LAYER, 10);

        // テキストフィールド
        nameField = new JTextField(16);
        nameField.setBounds(Constants.VIEW_WIDTH / 3, Constants.VIEW_HEIGHT / 2 + 10, Constants.VIEW_WIDTH / 3, 20);
        add(nameField);
        setLayer(nameField, JLayeredPane.PALETTE_LAYER, 50);

        // パスワードフィールドを示すラベル
        passInfoLabel = new JLabel("パスワード");
        passInfoLabel.setOpaque(false);
        passInfoLabel.setBounds(Constants.VIEW_WIDTH / 4, Constants.VIEW_HEIGHT / 2 + 50, 160, 20);
        add(passInfoLabel);
        setLayer(passInfoLabel, JLayeredPane.PALETTE_LAYER, 10);

        // パスワードフィールド
        passwordField = new JPasswordField(32);
        passwordField.setBounds(Constants.VIEW_WIDTH / 3, Constants.VIEW_HEIGHT / 2 + 70, Constants.VIEW_WIDTH / 3, 20);
        add(passwordField);
        setLayer(passwordField, JLayeredPane.PALETTE_LAYER, 50);

        // 確定ボタン
        confirmButton = new JButton("決定");
        confirmButton.setHorizontalAlignment(JButton.CENTER);
        confirmButton.addActionListener(e -> {
            // ボタンが押されたので、とりあえず停止
            confirmButton.setEnabled(false);

            String name = new String(nameField.getText());
            String pass = new String(passwordField.getPassword());
            if (name.length() != 0 && pass.length() != 0) {
                /** 名前とパスワードが入力されていればAppControllerクラスに渡して結果を待つ
                 *  アラートを消去
                 */
                emptyAlertLabel.setVisible(false);
                refusedAlertLabel.setVisible(false);
                boolean isLoginSucceeded = senderToAppView.receiveNameAndPass(name, pass, false);
                if (isLoginSucceeded) {
                    // ログイン成功
                    // 画面遷移
                    refToAppView.SwitchLoginPanelToHomePanel();
                } else {
                    /**
                     * ログイン失敗
                     * アラートを表示し、テキストフィールドをクリアする(?)
                     */
                    refusedAlertLabel.setVisible(true);
                    nameField.setText("");
                    passwordField.setText("");
                }

            } else {
                // 名前とパスワードが入力されていないので、ボタンを有効化
                confirmButton.setEnabled(true);
                // アラートを表示
                emptyAlertLabel.setVisible(true);
                return;
            }
        });
        confirmButton.setBounds(Constants.VIEW_WIDTH / 2 - 80, Constants.VIEW_HEIGHT * 4 / 5 - 15, 160, 30);
        add(confirmButton);
        setLayer(confirmButton, JLayeredPane.PALETTE_LAYER, 50);

        // 未入力アラートラベル
        emptyAlertLabel = new JLabel("名前とパスワードをそれぞれ入力してください");
        emptyAlertLabel.setForeground(Color.red);
        emptyAlertLabel.setBounds(Constants.VIEW_WIDTH / 2 - 160, Constants.VIEW_HEIGHT * 4 / 5 - 50, 320, 30);
        emptyAlertLabel.setHorizontalAlignment(JLabel.CENTER);
        add(emptyAlertLabel);
        setLayer(emptyAlertLabel, JLayeredPane.PALETTE_LAYER, 50);
        emptyAlertLabel.setVisible(false);

        // 値の不一致アラートラベル
        refusedAlertLabel = new JLabel("ユーザー名とパスワードのいずれかが異なっています");
        refusedAlertLabel.setForeground(Color.red);
        refusedAlertLabel.setBounds(Constants.VIEW_WIDTH / 2 - 160, Constants.VIEW_HEIGHT * 4 / 5 - 50, 320, 30);
        refusedAlertLabel.setHorizontalAlignment(JLabel.CENTER);
        add(refusedAlertLabel);
        setLayer(refusedAlertLabel, JLayeredPane.PALETTE_LAYER, 50);
        refusedAlertLabel.setVisible(false);
    }
}
