package Client;

import javax.swing.*;

/**
 * ホーム画面
 */
public class HomePanel extends JLayeredPane {
    private ISwitchPanel refToSwitch;


    public HomePanel(ISwitchPanel iSwitch) {
        refToSwitch = iSwitch;
    }
}
