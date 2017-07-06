package Client;

import javax.swing.*;
import java.awt.*;

/**
 * 新規登録画面
 */
public class NewUserPanel extends JLayeredPane {
    private ISwitchPanel panelSwitcher;
    private IReceiveNameAndPass senderToAppView;

    private JLabel backgroundLabel;
    private JLabel panelTitleLabel;
    private JTextField nameField;
    private JPasswordField passField1;
    private JPasswordField passField2;
    private JButton confirmButton;
    private JLabel nameInfoLabel;
    private JLabel passInfoLabel1;
    private JLabel passInfoLabel2;
    private JLabel emptyAlertLabel;
    private JLabel passIncorrectAlertLabel;
    private JLabel inputUsernameRefusedAlertLabel;
    private JLabel unusableCharAlertLabel;
    private JButton backButton;

    public NewUserPanel(ISwitchPanel switcher, IReceiveNameAndPass sender) {
        panelSwitcher = switcher;
        senderToAppView = sender;

        setLayout(null);
        setSize(Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);

        // 背景ラベル
        backgroundLabel = new JLabel();
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBounds(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
//        backgroundLabel.setBackground(Color.cyan);
        backgroundLabel.setIcon(new ImageIcon("./resource/image/background/signup.png"));
        add(backgroundLabel);
        setLayer(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

//        // パネルラベルタイトル
//        panelTitleLabel = new JLabel("新規ユーザー登録");
//        panelTitleLabel.setHorizontalAlignment(JLabel.CENTER);
//        panelTitleLabel.setBounds(Constants.VIEW_WIDTH / 2 - 80, Constants.VIEW_HEIGHT / 5, 160, 50);
//        add(panelTitleLabel);
//        setLayer(panelTitleLabel, JLayeredPane.PALETTE_LAYER);

        // テキストフィールドを示すラベル
        nameInfoLabel = new JLabel("ユーザー名を登録");
        nameInfoLabel.setBounds(Constants.VIEW_WIDTH / 4, Constants.VIEW_HEIGHT / 3, Constants.VIEW_WIDTH / 3, 20);
        add(nameInfoLabel);
        setLayer(nameInfoLabel, JLayeredPane.PALETTE_LAYER);

        // テキストフィールド
        nameField = new JTextField(32);
        nameField.setBounds(Constants.VIEW_WIDTH / 3, Constants.VIEW_HEIGHT / 3 + 20, Constants.VIEW_WIDTH / 3, 20);
        add(nameField);
        setLayer(nameField, JLayeredPane.PALETTE_LAYER);

        // 1つめのパスワードフィールドを示すラベル
        passInfoLabel1 = new JLabel("ログインのためのパスワードを設定してください");
        passInfoLabel1.setBounds(Constants.VIEW_WIDTH / 4, Constants.VIEW_HEIGHT / 3 + 50, Constants.VIEW_WIDTH / 3, 20);
        add(passInfoLabel1);
        setLayer(passInfoLabel1, JLayeredPane.PALETTE_LAYER);

        // 1つめのワスワードフィールド
        passField1 = new JPasswordField(32);
        passField1.setBounds(Constants.VIEW_WIDTH / 3, Constants.VIEW_HEIGHT / 3 + 70, Constants.VIEW_WIDTH / 3,20);
        add(passField1);
        setLayer(passField1, JLayeredPane.PALETTE_LAYER);

        // 2つめのパスワードフィールドを示すラベル
        passInfoLabel2 = new JLabel("確認のためもう一度入力してください");
        passInfoLabel2.setBounds(Constants.VIEW_WIDTH / 4, Constants.VIEW_HEIGHT / 3 + 100, Constants.VIEW_WIDTH / 3, 20);
        add(passInfoLabel2);
        setLayer(passInfoLabel2, JLayeredPane.PALETTE_LAYER);

        // 2つめのパスワードフィールド
        passField2 = new JPasswordField(32);
        passField2.setBounds(Constants.VIEW_WIDTH / 3, Constants.VIEW_HEIGHT / 3 + 120, Constants.VIEW_WIDTH / 3, 20);
        add(passField2);
        setLayer(passField2, JLayeredPane.PALETTE_LAYER);

        // 確定ボタン
        confirmButton = new JButton();
        confirmButton.setIcon(new ImageIcon("./resource/image/buttons/registBtn.png"));
        confirmButton.setHorizontalAlignment(JButton.CENTER);
        confirmButton.setBounds(Constants.VIEW_WIDTH / 2 - 160, Constants.VIEW_HEIGHT * 4 / 5, 120, 40);
        add(confirmButton);
        setLayer(confirmButton, JLayeredPane.PALETTE_LAYER);
        confirmButton.addActionListener(e -> {
            /**
             * 処理の追加
             * アラートを全て非表示にする。
             * ボタンを無効にする
             */
            String name = new String(nameField.getText());
            String pass1 = new String(passField1.getPassword());
            String pass2 = new String(passField2.getPassword());

            emptyAlertLabel.setVisible(false);
            passIncorrectAlertLabel.setVisible(false);
            inputUsernameRefusedAlertLabel.setVisible(false);
            unusableCharAlertLabel.setVisible(false);
            confirmButton.setEnabled(false);


            if (name.length() == 0 || pass1.length() == 0 || pass2.length() == 0) {
                // 入力欄に入力がなされていない場合
                emptyAlertLabel.setVisible(true);
                confirmButton.setEnabled(true);
            } else if (name.contains(" ") || name.contains(",") || pass1.contains(" ") || pass1.contains(",") || pass2.contains(" ") || pass2.contains(",")){
                // 入力欄にスペースかカンマが含まれている場合
                unusableCharAlertLabel.setVisible(true);
                confirmButton.setEnabled(true);
                nameField.setText("");
                passField1.setText("");
                passField2.setText("");
            } else if (!pass1.equals(pass2)) {
                // 2つのパスワード入力が一致していない場合
                passIncorrectAlertLabel.setVisible(true);
                confirmButton.setEnabled(true);
                passField1.setText("");
                passField2.setText("");
            } else if (!senderToAppView.receiveNameAndPass(name, pass1, true)) {
                // 新規ユーザ登録に失敗
                inputUsernameRefusedAlertLabel.setVisible(true);
                confirmButton.setEnabled(true);
                nameField.setText("");
            } else {
                // 新規ユーザー登録に成功
                panelSwitcher.switchNewUserPanelToHomePanel();
            }
        });

        // 未入力アラートラベル
        emptyAlertLabel = new JLabel("空欄があります");
        emptyAlertLabel.setForeground(Color.red);
        emptyAlertLabel.setBounds(Constants.VIEW_WIDTH / 2 - 160, Constants.VIEW_HEIGHT * 4 / 5 - 30, 320, 20);
        emptyAlertLabel.setHorizontalAlignment(JLabel.CENTER);
        emptyAlertLabel.setVisible(false);
        add(emptyAlertLabel);
        setLayer(emptyAlertLabel, JLayeredPane.PALETTE_LAYER);

        // 2つの入力パスが不一致のアラートラベル
        passIncorrectAlertLabel = new JLabel("入力された2つのパスワードが一致しません");
        passIncorrectAlertLabel.setHorizontalAlignment(JLabel.CENTER);
        passIncorrectAlertLabel.setForeground(Color.red);
        passIncorrectAlertLabel.setBounds(Constants.VIEW_WIDTH / 2 - 160, Constants.VIEW_HEIGHT * 4 / 5 - 30, 320, 20);
        passIncorrectAlertLabel.setVisible(false);
        add(passIncorrectAlertLabel);
        setLayer(passIncorrectAlertLabel, JLayeredPane.PALETTE_LAYER);

        // 入力された名前が既にサーバー上に存在する場合
        inputUsernameRefusedAlertLabel = new JLabel("入力されたユーザー名は既に使用されています");
        inputUsernameRefusedAlertLabel.setHorizontalAlignment(JLabel.CENTER);
        inputUsernameRefusedAlertLabel.setForeground(Color.red);
        inputUsernameRefusedAlertLabel.setBounds(Constants.VIEW_WIDTH / 2 - 160, Constants.VIEW_HEIGHT * 4 / 5 - 30, 160, 20);
        inputUsernameRefusedAlertLabel.setVisible(false);
        add(inputUsernameRefusedAlertLabel);
        setLayer(inputUsernameRefusedAlertLabel, JLayeredPane.PALETTE_LAYER);

        // 使用禁止文字アラートラベル
        unusableCharAlertLabel = new JLabel("スペースとカンマは入力できません");
        unusableCharAlertLabel.setForeground(Color.red);
        unusableCharAlertLabel.setBounds(Constants.VIEW_WIDTH / 2 - 160, Constants.VIEW_HEIGHT * 4 / 5 - 50, 320, 20);
        unusableCharAlertLabel.setHorizontalAlignment(JLabel.CENTER);
        add(unusableCharAlertLabel);
        setLayer(unusableCharAlertLabel, JLayeredPane.PALETTE_LAYER, 50);
        unusableCharAlertLabel.setVisible(false);

        // 前の画面に戻る
        backButton = new JButton("前の画面に戻る");
        backButton.setBounds(32, 18, 160, 20);
        backButton.setHorizontalAlignment(JButton.CENTER);
        add(backButton);
        setLayer(backButton, JLayeredPane.PALETTE_LAYER);
        backButton.addActionListener(e -> {
            panelSwitcher.switchNewUserPanelBackToStartPanel();
        });
    }


}
