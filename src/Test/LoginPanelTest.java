package Test;

import Client.*;

import javax.swing.*;

/**
 * Created by yudaikichise on 2017/06/26.
 */
public class LoginPanelTest extends JFrame implements ISwitchPanel, IReceiveNameAndPass {
    public static void main(String[] args) {
        new LoginPanelTest();
    }

    private LoginPanel loginPanel;

    public LoginPanelTest() {
        loginPanel = new LoginPanel(this, this);

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

    @Override
    public void SwitchNewUserPanelToHomePanel() {

    }

    @Override
    public void SwitchLoginPanelToHomePanel() {

    }

    @Override
    public boolean receiveNameAndPass(String name, String pass, boolean isNewUser) {
        return false;
    }
}
