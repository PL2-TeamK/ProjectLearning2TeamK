package Client;

/**
 * コールバックインタフェース
 * 全てのJLayeredPaneを継承するクラスがAppViewクラスを参照するため
 */

public interface ISwitchPanel {
    public void SwitchStartPanelToLoginPanel();
    public void SwitchStartPanelToNewUserPanel();
}