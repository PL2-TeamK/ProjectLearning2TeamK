package Client;

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
        user = new User(name);
        appView.setRefToUser(user);
        return true;
    }

    @Override
    public GameModel makeGameModelAndReturnRef (int stageNum) {
        gameModel = new GameModel(stageNum);
        return  gameModel;
    }




}
