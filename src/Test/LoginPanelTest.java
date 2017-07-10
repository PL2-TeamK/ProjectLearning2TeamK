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
    public void switchStartPanelToNewUserPanel() {

    }

    @Override
    public void switchStartPanelToLoginPanel() {

    }

    @Override
    public void switchNewUserPanelToHomePanel() {

    }

    @Override
    public void switchLoginPanelToHomePanel() {

    }

    @Override
    public void switchLoginPanelBackToStartPanel() {

    }

    @Override
    public void switchNewUserPanelBackToStartPanel() {

    }

    @Override
    public void switchHomePanelToChooseStagePanel(){

    }

    @Override
    public void switchHomePanelToRankingPanel(){

    }

    @Override
    public void switchHomePanelToMyPagePanel() {

    }

    @Override
    public void switchHomePanelBackToStartPanel() {

    }

    @Override
    public void switchChooseStagePanelBackToHomePanel() {

    }

    @Override
    public void switchRankingPanelBackToHomePanel() {

    }

    @Override
    public void switchMyPagePanelBackToHomePanel() {

    }

    @Override
    public void switchChooseStagePanelToGamePanel(int stageNum) {

    }

    @Override
    public boolean receiveNameAndPass(String name, String pass, boolean isNewUser) {
        return false;
    }

    @Override
    public void switchGamePanelToResultPanel() {

    }
}
