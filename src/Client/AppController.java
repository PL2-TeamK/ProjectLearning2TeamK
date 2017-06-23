package Client;

/**
 * アプリのベースのクラス
 * 一番最初にこのインスタンスを生成し、その他のインスタンスを順次作成する。
 * AppViewクラス、Userクラスのインスタンスを生成する。
 * ゲーム開始時にGameModelクラスのインスタンスを生成する。
 */
public class AppController {
    private User user;
    private AppView appView;
}
