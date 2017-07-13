package Client;

/**
 * コールバックインタフェース
 * 全てのJLayeredPaneを継承するクラスがAppViewクラスを参照するため
 */

public interface ISwitchPanel {
    void switchStartPanelToLoginPanel();
    void switchStartPanelToNewUserPanel();
    void switchLoginPanelToHomePanel();
    void switchNewUserPanelToHomePanel();
    void switchLoginPanelBackToStartPanel();
    void switchNewUserPanelBackToStartPanel();
    void switchHomePanelToChooseStagePanel();
    void switchHomePanelToRankingPanel();
    void switchHomePanelToMyPagePanel();
    void switchHomePanelBackToStartPanel();
    void switchChooseStagePanelBackToHomePanel();
    void switchRankingPanelBackToHomePanel();
    void switchMyPagePanelBackToHomePanel();
    void switchChooseStagePanelToGamePanel(int stageNum);
    void switchGamePanelToResultPanel(int stageNum, int score);
    void switchResultPanelToHomePanel();

}