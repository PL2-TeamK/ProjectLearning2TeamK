package Client;

import javax.swing.*;

/**
 * 全画面のベースになるクラス
 * JFrameを継承し、JLayeredPaneを載せる。
 */
public class AppView extends JFrame {
    private StartPanel startPanel;
    private LoginPanel loginPanel;
    private NewUserPanel newUserPanel;
    private HomePanel homePanel;
    private MyPagePanel myPagePanel;

    public AppView() {
        // スタートパネルだけメインスレッドで作成する。
        startPanel = new StartPanel();
        startPanel.setVisible(true);

        Thread makePanelThread = new Thread(() -> {
           // その他のパネルを作成するスレッド
        });
    }
}
