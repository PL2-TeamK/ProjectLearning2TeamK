package Client;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 全画面のベースになるクラス
 * JFrameを継承し、JLayeredPaneを載せる。
 */
public class AppView extends JFrame implements ISwitchPanel, IReceiveNameAndPass, IUserDataForAppView {
    private IViewToController refToController;

    private User refToUser;

    private StartPanel startPanel;
    private LoginPanel loginPanel;
    private NewUserPanel newUserPanel;
    private HomePanel homePanel;
    private ChooseStagePanel chooseStagePanel;
    private RankingPanel rankingPanel;
    private MyPagePanel myPagePanel;
    private GamePanel gamePanel;
    private ResultPanel resultPanel;



    public AppView(IViewToController arg) {
        refToController = arg;

        // macでの表示をwindowsでの表示に変える
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {

        } catch (Exception e) {
            e.printStackTrace();
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

    public void setRefToUser(User user) {
        refToUser = user;
    }




    /**
     * 以下、ISwitchPanelの実装
     */

    @Override
    public void switchStartPanelToLoginPanel() {
        /**
         * LoginPanelを生成
         * 画面遷移
         * StartPanelの参照を切る
         */
        loginPanel = new LoginPanel(this, this);
        loginPanel.setVisible(true);
        add(loginPanel);
        startPanel.setVisible(false);
        remove(startPanel);
        startPanel = null;
    }

    @Override
    public void switchStartPanelToNewUserPanel() {
        /**
         * NewUserPanelを生成
         * 画面遷移
         * StartPanelの参照を切る
         */
        newUserPanel = new NewUserPanel(this, this);
        newUserPanel.setVisible(true);
        add(newUserPanel);
        startPanel.setVisible(false);
        remove(startPanel);
        startPanel = null;
    }

    @Override
    public void switchLoginPanelToHomePanel() {
        /**
         * HomePanelを生成
         * 画面遷移
         * LoginPanelの参照を切る
         */

        createAndVisualizeHomePanel();
        loginPanel.setVisible(false);
        remove(loginPanel);
        loginPanel = null;
    }

    /**
     *
     */
    @Override
    public void switchNewUserPanelToHomePanel() {
        createAndVisualizeHomePanel();
        newUserPanel.setVisible(false);
        remove(newUserPanel);
        newUserPanel = null;
    }

    /**
     * 重複処理の共通化
     * HomePanelの生成
     * クラス内処理のため、privateに
     */
    private void createAndVisualizeHomePanel() {
        homePanel = new HomePanel(this);
        homePanel.setVisible(true);
        add(homePanel);
    }

    @Override
    public void switchLoginPanelBackToStartPanel() {
        createAndVisualizeStartPanel();
        loginPanel.setVisible(false);
        remove(loginPanel);
        loginPanel = null;
    }

    @Override
    public void switchNewUserPanelBackToStartPanel() {
        createAndVisualizeStartPanel();
        newUserPanel.setVisible(false);
        remove(newUserPanel);
        newUserPanel = null;
    }

    /**
     * スタート画面生成と可視化処理
     */

    private void createAndVisualizeStartPanel() {
        startPanel = new StartPanel(this);
        startPanel.setVisible(true);
        add(startPanel);

    }

    @Override
    public void switchHomePanelToChooseStagePanel() {
        chooseStagePanel = new ChooseStagePanel(this, this);
        chooseStagePanel.setVisible(true);
        add(chooseStagePanel);
        unvisualizeAndRemoveHomePanel();
    }

    @Override
    public void switchHomePanelToRankingPanel() {
        rankingPanel = new RankingPanel(refToController.fetchRankingFromServer());
        rankingPanel.setPanelSwitcher(this);
        rankingPanel.setVisible(true);
        add(rankingPanel);
        unvisualizeAndRemoveHomePanel();
    }

    @Override
    public void switchHomePanelToMyPagePanel() {
        myPagePanel = new MyPagePanel();
        myPagePanel.setRefToUser(refToUser);
        myPagePanel.setSwitcher(this);
        myPagePanel.initLabels();
        myPagePanel.setVisible(true);
        add(myPagePanel);
        unvisualizeAndRemoveHomePanel();
    }

    private void unvisualizeAndRemoveHomePanel() {
        homePanel.setVisible(false);
        remove(homePanel);
        homePanel = null;
    }

    @Override
    public void switchHomePanelBackToStartPanel() {
        createAndVisualizeStartPanel();
        refToController.logout();
        unvisualizeAndRemoveHomePanel();
    }

    @Override
    public void switchChooseStagePanelBackToHomePanel() {
        createAndVisualizeHomePanel();
        chooseStagePanel.setVisible(false);
        remove(chooseStagePanel);
        chooseStagePanel = null;
    }

    @Override
    public void switchRankingPanelBackToHomePanel() {
        createAndVisualizeHomePanel();
        rankingPanel.setVisible(false);
        remove(rankingPanel);
        rankingPanel = null;
    }

    @Override
    public void switchMyPagePanelBackToHomePanel() {
        createAndVisualizeHomePanel();
        myPagePanel.setVisible(false);
        remove(myPagePanel);
        myPagePanel = null;
    }

    @Override
    public void switchChooseStagePanelToGamePanel(int stageNum) {
        /**
         * ステージ選択後
         * 指定されたステージ番号のステージ画面を生成し、遷移する。
         */
        gamePanel = new GamePanel(stageNum);
        add(gamePanel);
        gamePanel.setVisible(true);
        // gameModelを生成し取得
        GameModel gameModel = refToController.makeGameModelAndReturnRef(stageNum);
        gamePanel.setRefToGameModel(gameModel);
        gamePanel.setPanelSwitcher(this);
        chooseStagePanel.setVisible(false);
        remove(chooseStagePanel);
        chooseStagePanel = null;
        // gameModelのタイマー起動
        // 停止は、GamePanel.gameFinished()で行う。
        gameModel.gameStart();
        gamePanel.gameHandler();
    }

    @Override
    public void switchGamePanelToResultPanel(int stageNum, int score) {
        /**
         * 結果表示画面
         * 一般ステージの場合は、成否のみ
         * エンドレスモードの場合は、スコアとハイスコア更新の有無
         */
        resultPanel = new ResultPanel(stageNum, score);
        resultPanel.setPanelSwitcher(this);
        add(resultPanel);
        // Userデータの更新が発生する場合、Controllerに通知しServerに送信する必要がある。
        // UI関係ないしスレッド化する。
        new Thread(() -> {
            if (stageNum < 100) {
                if (score == Constants.STAGE_CLEARED) {
                    if (refToUser.setMaxClearedStage(stageNum)) {
                        // 最大クリア数更新したので、Controllerに投げる
                        refToController.sendMaxClearedStageNumToServer(stageNum);
                    }
                }
            } else {
                if (refToUser.updateHighScore(stageNum, score)) {
                    // ハイスコア更新したので、サーバーに通知する。
                        refToController.sendHighScoreToServer(stageNum, score);
                }
            }
        }).start();
        resultPanel.setVisible(true);
        gamePanel.setVisible(false);
        remove(gamePanel);
        gamePanel = null;
    }

    @Override
    public void switchResultPanelToHomePanel() {
//        homePanel = new HomePanel(this);
//        add(homePanel);
//        homePanel.setVisible(true);
        createAndVisualizeHomePanel();
        resultPanel.setVisible(false);
        remove(resultPanel);
        resultPanel = null;
    }

    /**
     * 以下 IReceiveNameAndPassの実装
     *
     *
     * @param name
     * @param pass
     * @param isNewUser
     * @return
     */
    @Override
    public boolean receiveNameAndPass(String name, String pass, boolean isNewUser) {

        // AppControllerに処理を依頼してServerからの返答を待つ

        return refToController.sendNameAndPassToServer(name, pass, isNewUser);
    }





    /**
     * 以下IUserDataForAppViewの実装
     */

    public int getMaxClearedStage() {
        return refToUser.getMaxClearedStage();
    }


}
