package Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * ランキング閲覧画面
 */
public class RankingPanel extends JLayeredPane {
    private ISwitchPanel panelSwitcher;

    private JLabel backgroundLabel;
    private JButton backButtons;
    private JLabel panelTitleLabel;

    private CustomTabPane tabPane;
    private CustomScrollPane scrollPanes[] = new CustomScrollPane[4];
    

    public RankingPanel(String[] rankingInfo) {
        tabPane = new CustomTabPane();
        for (int i = 0; i < 4; i++) {
            scrollPanes[i] = new CustomScrollPane(rankingInfo[i]);
            tabPane.addTab("ステージ" + (i + 1), scrollPanes[i]);
        }
        tabPane.setBounds(Constants.VIEW_WIDTH * (10 - 9) / 20, Constants.VIEW_HEIGHT * 3 / 10,
                Constants.VIEW_WIDTH * 18 / 20, Constants.VIEW_HEIGHT * 6 / 10);
        add(tabPane);
        setLayer(tabPane, PALETTE_LAYER);
    }
}

class CustomTabPane extends JTabbedPane {


    public CustomTabPane () {
        setSize(Constants.VIEW_WIDTH * 9 / 10, Constants.VIEW_HEIGHT * 6 / 10);
    }
}

class CustomScrollPane extends JScrollPane {
    private JLayeredPane panel;
    private JLabel titleLabel;
    private JLabel backgroundLabel;
    private JLabel rankLabels[] = new JLabel[10];
    private JLabel nameLabels[] = new JLabel[10];
    private JLabel scoreLabels[] = new JLabel[10];

    private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 24);

    private int rowHeight = 50;
    private int spaceHeight = 30;

    private int rankColumnWidth = Constants.VIEW_WIDTH / 10;
    private int nameColumnWidth = Constants.VIEW_WIDTH * 3 / 10;
    private int scoreColumnWidth = Constants.VIEW_WIDTH * 3 / 10;


    public CustomScrollPane(String rankInfo) {
        //System.out.println("CustomScrollPane():" + rankInfo);

        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        panel = new JLayeredPane();
        panel.setSize(Constants.VIEW_WIDTH * 9 / 10, rowHeight * 11 + spaceHeight * 11);
        setViewportView(panel);

        panel.setVisible(true);
        backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon("./resource/image/background/ranking.png"));
        backgroundLabel.setOpaque(true);
        panel.add(backgroundLabel);
        panel.setLayer(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        String[] nameScores = rankInfo.split("/");
        ArrayList<String> nameArray = new ArrayList<>();
        ArrayList<Integer> scoreArray = new ArrayList<>();

        for (String nameScore : nameScores) {
            if (!nameScore.equals("null")) {
                String[] values = nameScore.split(" ");
                nameArray.add(values[0]);
                scoreArray.add(Integer.parseInt(values[1]));
            }
        }

        for (int i = 0; i < nameArray.size(); i++) {
            // 表示するランキング一覧以上の作業は行わない。

            rankLabels[i] = new JLabel((i + 1) + "");
            rankLabels[i].setBounds(Constants.VIEW_WIDTH * 1 / 10 - rankColumnWidth / 2,
                    (i + 1) * (rowHeight + spaceHeight),
                    rankColumnWidth, rowHeight);
            rankLabels[i].setVerticalAlignment(JLabel.CENTER);
            rankLabels[i].setHorizontalAlignment(JLabel.CENTER);
            rankLabels[i].setFont(font);
            panel.add(rankLabels[i]);
            panel.setLayer(rankLabels[i], JLayeredPane.PALETTE_LAYER);


            nameLabels[i] = new JLabel(nameArray.get(i));
            nameLabels[i].setBounds(Constants.VIEW_WIDTH * 4 / 10 - nameColumnWidth / 2,
                    (i + 1) * (rowHeight + spaceHeight),
                    nameColumnWidth, rowHeight);
            nameLabels[i].setHorizontalAlignment(JLabel.CENTER);
            nameLabels[i].setVerticalAlignment(JLabel.CENTER);
            nameLabels[i].setFont(font);
            panel.add(nameLabels[i]);
            panel.setLayer(nameLabels[i], JLayeredPane.PALETTE_LAYER);

            int score = scoreArray.get(i);
            int hour = score / 3600;
            int minute = (score % 3600) / 60;
            int second = score % 60;

            String scoreText = hour + ":" + minute + ":" + second;
            scoreLabels[i] = new JLabel(scoreText);
            scoreLabels[i].setBounds(Constants.VIEW_WIDTH * 6 / 10 - scoreColumnWidth / 2,
                    (i + 1) * (rowHeight + spaceHeight),
                    scoreColumnWidth, rowHeight);
            scoreLabels[i].setHorizontalAlignment(JLabel.CENTER);
            scoreLabels[i].setVerticalAlignment(JLabel.CENTER);
            scoreLabels[i].setFont(font);
            panel.add(scoreLabels[i]);
            panel.setLayer(scoreLabels[i], JLayeredPane.PALETTE_LAYER);

        }

        setVisible(true);
    }
}


