package Test;

import Client.*;

import javax.swing.*;

/**
 * Created by yudaikichise on 2017/06/26.
 */
public class LoginPanelTest extends JFrame implements ISwitchPanel {
    public static void main(String[] args) {
        new LoginPanelTest();
    }

    private LoginPanel loginPanel;

    public LoginPanelTest() {
        loginPanel = new LoginPanel(this);

        add(loginPanel);

        setSize(Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void SwitchStartPanelToNewUserPanel() {

    }

    @Override
    public void SwitchStartPanelToLoginPanel() {

    }
}
