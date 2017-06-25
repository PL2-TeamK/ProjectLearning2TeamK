package Client;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 * 全画面のベースになるクラス
 * JFrameを継承し、JLayeredPaneを載せる。
 */
public class AppView extends JFrame implements ISwitchPanel {
    private StartPanel startPanel;
    private LoginPanel loginPanel;
    private NewUserPanel newUserPanel;
    private HomePanel homePanel;
    private MyPagePanel myPagePanel;

    public AppView() {
        // macでの表示をwindowsでの表示に変える
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {

        }
        // スタートパネルだけメインスレッドで作成する。
        startPanel = new StartPanel(this);
        startPanel.setVisible(true);
        add(startPanel);
        Thread makePanelThread = new Thread(() -> {
           // その他のパネルを作成するスレッド
        });

        setSize(Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void SwitchStartPanelToLoginPanel() {
        /**
         * LoginPanelを生成
         * 画面遷移
         * StartPanelの参照を切る
         */
        loginPanel = new LoginPanel(this);
        loginPanel.setVisible(true);
        add(loginPanel);
        startPanel.setVisible(false);
        startPanel = null;
    }

    @Override
    public void SwitchStartPanelToNewUserPanel() {
        /**
         * NewUserPanelを生成
         * 画面遷移
         * StartPanelの参照を切る
         */
        newUserPanel = new NewUserPanel();
        newUserPanel.setVisible(true);
        startPanel.setVisible(false);
        startPanel = null;
    }
}
