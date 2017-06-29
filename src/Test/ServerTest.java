import com.sun.org.apache.regexp.internal.RE;
import sun.tools.tree.ShiftRightExpression;

import javax.sound.midi.Receiver;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class serverDrive{

	private Server1 serverS;
	private Receiver1 receiverS;

	// コンストラクタ
	public serverDrive(){

	}

	// メソッド
	public void connectServer(String ipAddress, int port){	// サーバに接続

		try {
			Socket socket = new Socket(ipAddress,port);
			serverS = new Server1(socket);
			receiverS = new Receiver1(socket);

			serverS.start();
			receiverS.start();
		}catch (IOException e){
			e.getMessage();
		}

	}

	public void sendMessage(String msg,DataOutputStream out){	// サーバに操作情報を送信
        try {
            out.writeUTF(msg);//送信データをバッファに書き出す
            out.flush();//送信データを送る
        } catch (IOException e) {
            e.printStackTrace();
        }

		System.out.println("メッセージ " + msg + " を送信しました"); //テスト標準出力
	}

	// データ受信用スレッド(内部クラス)
	class Receiver1 extends Thread {
		private DataInputStream sisr; //受信データ用文字ストリーム
        private Socket socket;

		// 内部クラスReceiverのコンストラクタ
		Receiver1 (Socket socket){
		    this.socket = socket;
			try{
				sisr = new DataInputStream(socket.getInputStream()); //受信したバイトデータを文字ストリームに
			} catch (IOException e) {
				System.err.println("データ受信時にエラーが発生しました: " + e);
			}
		}
		// 内部クラス Receiverのメソッド
		public void run(){
			try{
				while(true) {//データを受信し続ける
					String inputLine =sisr.readUTF();//受信データを一行分読み込む
					if (inputLine != null){//データを受信したら
						receiveMessage(inputLine);//データ受信用メソッドを呼び出す
					}
				}
			} catch (IOException e){
				System.err.println("データ受信時にエラーが発生しました: " + e);
			}
		}
	}


	
	// データ送信用スレッド(内部クラス)
		class Server1 extends Thread {

		    Socket socket;
		    DataOutputStream out;

		    Server1(Socket socket){
		        this.socket = socket;
		        try{
                    out = new DataOutputStream(socket.getOutputStream()); //データ送信用オブジェクトの用意
                 } catch (UnknownHostException e) {
                    System.err.println("ホストのIPアドレスが判定できません: " + e);
                    System.exit(-1);
                } catch (IOException e) {
                    System.err.println("サーバ接続時にエラーが発生しました: " + e);
                    System.exit(-1);
		        }
		    }

        // 内部クラス Server1のメソッド
        public void run() {

            try {

                BufferedReader bf = new BufferedReader(new FileReader("testYuki.txt"));
                String line;
                line = bf.readLine();


                    while (line != null) {
                        sendMessage(line, out);
                        line = bf.readLine();
                        try {
							sleep(100);
						}catch (InterruptedException e){
                        	e.getMessage();
						}
                    }

            } catch (IOException e) {
                // TODO 自動生成さた catch ブロック
                e.getMessage();
            }
        }
    }


	public void receiveMessage(String msg){	// メッセージの受信
		System.out.println("サーバからメッセージ " + msg + " を受信しました"); //テスト用標準出力
	}

	//テスト用のmain
	public static void main(String args[]){ 
		serverDrive oclient = new serverDrive();
		oclient.connectServer("localhost", 4231);

	}
}

