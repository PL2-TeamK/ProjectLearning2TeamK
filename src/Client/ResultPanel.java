package Client;

import javax.swing.*;
import java.awt.*;

/**
 * ゲーム結果表示パネルs
 */
public class ResultPanel extends JLayeredPane {
    private JLabel backgroundLabel;

    public ResultPanel () {
        setLayout(null);


        // 背景ラベル
        backgroundLabel = new JLabel();
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBounds(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
        backgroundLabel.setBackground(Color.cyan);
        add(backgroundLabel);
        setLayer(backgroundLabel, DEFAULT_LAYER);
    }
}
