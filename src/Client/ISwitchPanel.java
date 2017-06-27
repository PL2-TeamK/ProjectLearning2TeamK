package Client;

/**
 * コールバックインタフェース
 * 全てのJLayeredPaneを継承するクラスがAppViewクラスを参照するため
 */

public interface ISwitchPanel {
    public void switchStartPanelToLoginPanel();
    public void switchStartPanelToNewUserPanel();
    public void switchLoginPanelToHomePanel();
    public void switchNewUserPanelToHomePanel();
    public void switchLoginPanelBackToStartPanel();
    public void switchNewUserPanelBackToStartPanel();
}