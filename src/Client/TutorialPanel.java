package Client;

import javax.swing.*;

public class TutorialPanel extends JLayeredPane {
    private int pageNum;
    private JLabel imageLabel;
    private JButton windowButton;
    private ISwitchPanel panelSwitcher;

    public TutorialPanel(ISwitchPanel panelSwitcher) {
        this.panelSwitcher = panelSwitcher;

        createPage1();
    }

    private void createPage1() {
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setIcon(new ImageIcon("./resource/image/background/tutorial.PNG"));
        imageLabel.setBounds(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
        add(imageLabel);
        setLayer(imageLabel, PALETTE_LAYER);

        windowButton = new JButton();
        windowButton.setContentAreaFilled(false);
        windowButton.setBorderPainted(false);
        windowButton.addActionListener(e -> {
            createPage2();
        });
        windowButton.setBounds(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
        add(windowButton);
        setLayer(windowButton, MODAL_LAYER);

    }

    private void createPage2() {
        remove(imageLabel);
        remove(windowButton);

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setBounds(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
        imageLabel.setIcon(new ImageIcon("./resource/image/background/tutorial2.png"));
        add(imageLabel);
        setLayer(imageLabel, PALETTE_LAYER);

        windowButton = new JButton();
        windowButton.setContentAreaFilled(false);
        windowButton.setBorderPainted(false);
        windowButton.addActionListener(e -> {
            createPage3();
        });
        windowButton.setBounds(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
        add(windowButton);
        setLayer(windowButton, MODAL_LAYER);
    }

    private void createPage3() {
        remove(imageLabel);
        remove(windowButton);

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setBounds(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
        imageLabel.setIcon(new ImageIcon("./resource/image/background/tutorial3.png"));
        add(imageLabel);
        setLayer(imageLabel, PALETTE_LAYER);

        windowButton = new JButton();
        windowButton.setContentAreaFilled(false);
        windowButton.setBorderPainted(false);
        windowButton.addActionListener(e -> {
            panelSwitcher.switchTutorialPanelBackToChoosePanel();
        });
        windowButton.setBounds(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
        add(windowButton);
        setLayer(windowButton, MODAL_LAYER);
    }
}
