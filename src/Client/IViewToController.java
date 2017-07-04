package Client;

/**
 * AppViewからAppControllerへのコールバックメソッドの宣言を行う。
 */
public interface IViewToController {
    public boolean sendNameAndPassToServer (String name, String pass, boolean isNew);
    public GameModel makeGameModelAndReturnRef (int stageNum);
}
