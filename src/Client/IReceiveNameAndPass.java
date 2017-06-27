package Client;

/**
 * ユーザー名とパスワードを入力させる画面からAppViewへのコールバックメソッドを宣言する。
 */
public interface IReceiveNameAndPass {
    public boolean receiveNameAndPass(String name, String pass, boolean isNewUser);
}
