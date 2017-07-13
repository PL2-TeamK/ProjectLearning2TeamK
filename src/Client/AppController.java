package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * アプリのベースのクラス
 * 一番最初にこのインスタンスを生成し、その他のインスタンスを順次作成する。
 * AppViewクラス、Userクラスのインスタンスを生成する。
 * ゲーム開始時にGameModelクラスのインスタンスを生成する。
 */
public class AppController implements IViewToController {
    private User user;
    private AppView appView;
    private GameModel gameModel;

    // 通信関連
    private String serverAddless = "localhost";
    private int port = 4231;
    private Socket socket;
    private DataInputStream reader;
    private DataOutputStream writer;

    public AppController() {
        // 画面の用意
        appView = new AppView(this);
    }

    @Override
    public boolean sendNameAndPassToServer (String name, String pass, boolean isNew) {
        /**
         * サーバーに名前、パスワードを送信する。
         * isNewフラグがtrueの場合新規ユーザー登録
         * falseの場合はlogin操作
         * 新規ユーザー登録に成功、またはログイン成功時にreturnを返す
         * それ以外の場合にはfalseを返す
         */
        // TODO: 未実装: Serverが完成した段階で記述を行う。
        // 接続を確立
        makeConnectionToServer();

        String sendingMessage;
        if (isNew) {
            // 新規登録時
            sendingMessage = "SignUp," + name + " " + pass;
        } else {
            // ログイン
            sendingMessage = "Login," + name + " " + pass;
        }

        String recievedMessage = "";
        try {
            writer.writeUTF(sendingMessage);
            recievedMessage = reader.readUTF();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        String[] tokens = recievedMessage.split(",");
        if (tokens[1].equals("success")){
            // 画像の
            user = new User(name);

            try {
                writer.writeUTF("CfmStage,null");
                recievedMessage = reader.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            user.setMaxClearedStage(Integer.parseInt(recievedMessage.split(",")[1]));

            ArrayList<Integer> scoreList = new ArrayList<>();
            for (int i = 1; i <= 4; i++) {

                try {
                    writer.writeUTF("CfmScore," + i);
                    recievedMessage = reader.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                scoreList.add(Integer.parseInt(recievedMessage.split(",")[1]));
            }
            user.setHighScore(scoreList);

            appView.setRefToUser(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public GameModel makeGameModelAndReturnRef (int stageNum) {
        gameModel = new GameModel(stageNum);
        return  gameModel;
    }

    // 接続を確立する
    private void makeConnectionToServer () {
        // 接続先情報
        InetSocketAddress socketAddress = new InetSocketAddress(serverAddless, port);
        // socket
        socket = new Socket();

        try {
            socket.connect(socketAddress, port);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        InetAddress inetAddress;
        if ((inetAddress = socket.getInetAddress()) != null) {
            System.out.println("connect to " + inetAddress);
        } else {
            System.out.println("connection failed");
            return;
        }

        // 書き込み
        try {
            // autoFlush
            writer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        // 読み込み
        try {
            reader = new DataInputStream(socket.getInputStream());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return;
    }




}
