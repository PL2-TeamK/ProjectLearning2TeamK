package Client;

import javax.swing.*;

/**
 *
 */
public class LoginPanel extends JLayeredPane {
    // 画面遷移メソッドを参照するためのインタフェース
    private ISwitchPanel refToAppView;

    public LoginPanel(ISwitchPanel reference) {
        refToAppView = reference;
    }
}
