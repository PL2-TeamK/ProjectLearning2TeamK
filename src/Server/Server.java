


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server{

    private int port; // 待ち受けポート番号を引数
    private File rankfile;
    private File accountfile;
    private List<String> playerNameList = new ArrayList<>();

    private Server(int port) throws IOException{
        this.port = port;
        rankfile= new File("ServerFile/rankfile.txt");//logfileは　playername,status,time,opponent
        accountfile = new File("ServerFile/accountfile.txt"); //accountfileは　playername password,point

    }




    class Receiver extends Thread {


        private DataInputStream dataInputStream;
        private Socket socket;
        private String name;
        private String[] command;

        Receiver (Socket socket){

            this.socket = socket;

            try{
                dataInputStream = new DataInputStream(socket.getInputStream());


            } catch (IOException e) {
                System.err.println("データ受信時にエラーが発生しました: " + e);
            }

        }

        public void run(){
            boolean isStop = false;
            try{
                while(!isStop) {
                    String inputLine = dataInputStream.readUTF();

                    if (inputLine != null){
                        System.out.println();
                        System.out.println(inputLine);
                        command = inputLine.split(",", 0);

                        switch (command[0]){
                            case "Login":
                                commandLogin();
                                break;
                            case "SignUp":
                                commandSignUp();
                                break;
                            case "CfmStage":
                                commandCfmStage();
                                break;
                            case "SendClearStage":
                                commandSendClearStage();
                                break;
                            case "SendScore":
                                commandSendScore();
                                break;
                            case "CfmScore":
                                commandCfmScore();
                                break;
                            case "CfmRank":
                                commandCfmRank();
                                break;
                            default:
                                System.err.println("想定されていないコマンド:" + command[0]);
                                isStop = true;
                        }

                    }


                }
            } catch (IOException e2){
                System.out.println(getname() + "の接続が切れました");

                playerNameList.remove(getname());
            }

        }

        //set系

        public void setname(String name) {this.name = name;}

        //get系

        public String getname(){
            return name;
        }

        public Socket getSocket(){
            return socket;
        }

        //クライアントからの命令に一対一対応するcommandメソッドとそれで用いるメソッド


        public void commandLogin(){//ログイン試行

            try {
                DataOutputStream out = new DataOutputStream(getSocket().getOutputStream());
                BufferedReader bufferedReader = new BufferedReader(new FileReader(accountfile));
                String line = bufferedReader.readLine();
                if(isOnlyLogin()) {
                    while (line != null) {
                        if (line.split(",", 0)[0].equals(command[1])) {
                            out.writeUTF("LoginJudge,success");
                            out.flush();


                            setname(command[1].split(" ", 0)[0]);
                            playerNameList.add(getname());

                            System.out.println("アカウントが存在したためログインに成功:" + getname());

                            return;
                        }
                        line = bufferedReader.readLine();
                    }
                }

                System.out.println("ログインに失敗");//test
                out.writeUTF("LoginJudge,error");
                out.flush();


            }catch (IOException e){
                System.err.println("ログイン時にファイルがありません、もしくはソケット接続失敗");
            }

        }

        public boolean isOnlyLogin(){//同一アカウント複数ログインのチェック
            return !playerNameList.contains(command[1].split(" ",0)[0]);
        }


        public void commandSignUp(){//新規登録時


            try {
                DataOutputStream out = new DataOutputStream(getSocket().getOutputStream());

                if(isOnlySignUp()) {
                    out.writeUTF("SignJudge,success");
                    out.flush();
                }else {
                    out.writeUTF("SignJudge,error");
                    out.flush();
                    System.out.println("同一名のアカウントが存在したため、新規登録失敗");//test
                    return;
                }

                System.out.println("新規登録成功:" + getname());
                setname(command[1].split(" ", 0)[0]);
                playerNameList.add(getname());

                BufferedWriter bufferedWriter;

                synchronized (accountfile) {
                    bufferedWriter = new BufferedWriter(new FileWriter(accountfile, true));
                    bufferedWriter.write(command[1] + ",0,0 0 0 0");
                    bufferedWriter.newLine();
                }

                bufferedWriter.flush();
                bufferedWriter.close();


            }catch (IOException e){
                System.err.println("新規登録時にファイルがありません、もしくはソケット接続失敗");
            }
        }

        public boolean isOnlySignUp(){//同名アカウントを作成していないかチェック

            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(accountfile));
                String line = bufferedReader.readLine();

                while (line != null) {
                    if (line.split(" ", 0)[0].equals(command[1].split(" ", 0)[0])) {

                        bufferedReader.close();
                        return false;
                    }
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
            }catch (IOException e){
                e.getMessage();
            }
            return true;
        }


        public void commandCfmStage(){//クリアしたステージをサーバに問い合わせる（ログイン時が予想される）
            try {
                DataOutputStream out = new DataOutputStream(getSocket().getOutputStream());

                BufferedReader bufferedReader = new BufferedReader(new FileReader(accountfile));
                String line = bufferedReader.readLine();
                String stage = "";

                while (line != null) {

                    if (line.split(" ", 0)[0].equals(name)) {
                        stage = line.split(",",0)[1];
                    }
                    line = bufferedReader.readLine();

                }

                bufferedReader.close();

                if(stage != null) {
                    out.writeUTF("CfmStage," + stage);
                    out.flush();
                }else {
                    System.err.println("クリアステージ問い合わせ時にアカウントが見つからないです。");
                }

            }catch (IOException e){
                e.getMessage();
            }
        }

        public void commandSendClearStage(){//ステージクリア状況の送信

            try {
                synchronized (accountfile) {

                    System.out.println("ステージクリア状況の送信");//test
                    BufferedReader bufferedReader;
                    BufferedWriter bufferedWriter;
                    bufferedReader = new BufferedReader(new FileReader(accountfile));
                    String line = bufferedReader.readLine();
                    //line = bufferedReader.readLine();
                    List<String> accountList = new ArrayList();

                    while (line != null) {

                        if (name.equals(line.split(" ", 0)[0])) {
                            accountList.add(name + " " + line.split(",",0)[0].split(" ",0)[1]+ "," + command[1] + "," + line.split(",",0)[2]);
                        } else {
                            accountList.add(line);
                        }

                        line = bufferedReader.readLine();
                    }

                    bufferedReader.close();

                    bufferedWriter = new BufferedWriter(new FileWriter(accountfile, false));

                    for (int i = 0; i < accountList.size(); i++) {

                        bufferedWriter.write(accountList.get(i));
                        bufferedWriter.newLine();

                    }

                    bufferedWriter.close();
                }
            }catch (FileNotFoundException e){
                e.getMessage();
            }catch (IOException e2) {
                e2.getMessage();
            }
        }

        public void commandSendScore(){//自己ハイスコア受信、全体スコア更新
            try {


                System.out.println("自己ハイスコアを受信・更新");//test
                BufferedReader bufferedReader;
                BufferedWriter bufferedWriter;
                String line;
                synchronized (accountfile) {
                    bufferedReader = new BufferedReader(new FileReader(accountfile));
                    //String line = bufferedReader.readLine();
                    line = bufferedReader.readLine();
                    List<String> accountList = new ArrayList();

                    while (line != null) {

                        if (name.equals(line.split(" ", 0)[0])) {
                            String newScore = "";
                            for (int i = 1; i < 5; i++) {
                                if ((Integer.parseInt(command[1].split(" ", 0)[0]) == i) && Integer.parseInt(command[1].split(" ", 0)[1]) > Integer.parseInt(line.split(",", 0)[2].split(" ", 0)[i - 1])) {
                                    newScore += command[1].split(" ", 0)[1];
                                } else {
                                    newScore += line.split(",", 0)[2].split(" ", 0)[i - 1];
                                }
                                if (i < 4) {
                                    newScore += " ";
                                }
                            }
                            accountList.add(name + " " + line.split(",", 0)[0].split(" ", 0)[1] + "," + line.split(",", 0)[1] + "," + newScore);
                        } else {
                            accountList.add(line);
                        }

                        line = bufferedReader.readLine();
                    }

                    bufferedWriter = new BufferedWriter(new FileWriter(accountfile, false));

                    for (int i = 0; i < accountList.size(); i++) {
                        bufferedWriter.write(accountList.get(i));
                        bufferedWriter.newLine();
                    }
                }

                bufferedWriter.flush();

                synchronized (rankfile){
                    List<String> rankList = new ArrayList<>();
                    bufferedReader = new BufferedReader(new FileReader(rankfile));
                    //line = bufferedReader.readLine();
                    line = bufferedReader.readLine();
                    int lookStage = 1;
                    List<String> lookStageHighScoreInfo = new ArrayList<>();

                    while (line!=null){


                        if(line.equals("<<<")){
                            if(lookStage == Integer.parseInt(command[1].split(" ",0)[0])){
                                for(String s:lookStageHighScoreInfo){
                                    rankList.add(s);
                                }
                            }
                            lookStage++;
                            rankList.add(line);
                        }else if(lookStage == Integer.parseInt(command[1].split(" ",0)[0])){
                            if(Integer.parseInt(command[1].split(" ",0)[1])>Integer.parseInt(line.split(" ",0)[1])){

                                lookStageHighScoreInfo.add(name + " " + command[1].split(" ",0)[1]);

                                if(lookStageHighScoreInfo.size()<10){//10位更新後は付け足さない
                                    lookStageHighScoreInfo.add(line);
                                }

                            }else {
                                if(lookStageHighScoreInfo.size()<10){
                                    lookStageHighScoreInfo.add(line);
                                }
                            }
                        }else {
                            rankList.add(line);
                        }
                        line = bufferedReader.readLine();
                    }

                    bufferedWriter = new BufferedWriter(new FileWriter(rankfile,false));

                    for (int i = 0; i < rankList.size(); i++) {

                        bufferedWriter.write(rankList.get(i));
                        bufferedWriter.newLine();
                    }
                }

                bufferedReader.close();
                bufferedWriter.close();

            }catch (FileNotFoundException e){
                e.getMessage();
            }catch (IOException e2){
                e2.getMessage();
            }
        }

        public void commandCfmScore(){//自己ハイスコア確認
            try {
                DataOutputStream out = new DataOutputStream(getSocket().getOutputStream());

                BufferedReader bufferedReader = new BufferedReader(new FileReader(accountfile));
                String line = bufferedReader.readLine();
                String score = null;

                while (line != null) {
                    if (line.split(" ", 0)[0].equals(name)) {
                        score = line.split(",",0)[2].split(" ",0)[Integer.parseInt(command[1])-1];
                    }
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();

                if(!score.equals(null)) {
                    out.writeUTF("ScoreInfo," + score);
                    out.flush();
                }else {
                    System.err.println("スコア問い合わせ時にアカウントが見つからないです。");
                }

            }catch (IOException e){
                e.getMessage();
            }
        }

        public void commandCfmRank(){//全体ハイスコア確認
            try {
                DataOutputStream out = new DataOutputStream(getSocket().getOutputStream());

                BufferedReader bufferedReader = new BufferedReader(new FileReader(rankfile));
                String line = bufferedReader.readLine();
                String rankInfo = "";
                int lookStage = 1;
                int count = 0;//10人未満ならnullをつける

                while (line != null) {

                    if(line.equals("<<<")){
                        lookStage++;
                    }else if(lookStage == Integer.parseInt(command[1])){
                        rankInfo += line + "/";
                        count++;
                    }

                    line = bufferedReader.readLine();
                }
                if(count<10){
                    rankInfo += "null";
                }else {
                    rankInfo = rankInfo.substring(0, rankInfo.length() - 1);//最後の/を取り除く
                }
                bufferedReader.close();

                out.writeUTF("RankInfo," + rankInfo);
                out.flush();


            }catch (IOException e){
                e.getMessage();
            }
        }
    }





    //サーバ起動　クライアント受付
    public void acceptClient(){
        try {
            System.out.println("サーバが起動しました．");//test
            ServerSocket ss = new ServerSocket(port);
            List<Receiver> receivers = new ArrayList<>();
            while (true) {
                Socket socket = ss.accept();
                System.out.println("クライアントと接続しました．");//test
                receivers.add(new Receiver(socket));
                receivers.get(receivers.size()-1).start();
            }
        } catch (Exception e) {
            System.err.println("ソケット作成時にエラーが発生しました: " + e);
        }
    }


    public static void main(String[] args) { //main

        try {
            Server server = new Server(4231);
            server.acceptClient();
        }catch (IOException e){
            System.err.println("ファイルが見つかりません");
        }
    }
}