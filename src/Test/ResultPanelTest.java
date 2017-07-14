package Test;

import Client.ResultPanel;
import Client.Constants;

import javax.swing.*;

/**
 * Created by yudaikichise on 2017/07/14.
 * ResultPanelのテスト用
 */
public class ResultPanelTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("ResultPanelTest");
        ResultPanel resultPanel = new ResultPanel(Constants.FIRST_STAGE, Constants.STAGE_CLEARED);
        frame.add(resultPanel);
        resultPanel.setVisible(true);
    }
}


