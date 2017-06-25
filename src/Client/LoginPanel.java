package Client;

import javax.swing.*;

/**
 *
 */
public class LoginPanel extends JLayeredPane {
    // 画面遷移メソッドを参照するためのインタフェース
    private ISwitchPanel refToAppView;

    private JLabel backgroundLabel;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton confirmButton;

    public LoginPanel(ISwitchPanel reference) {
        refToAppView = reference;

        // 背景ラベル

    }
}
