package Client;

/**
 * AppViewからAppControllerへのコールバックメソッドの宣言を行う。
 */
public interface IViewToController {
    boolean sendNameAndPassToServer (String name, String pass, boolean isNew);
    GameModel makeGameModelAndReturnRef (int stageNum);
    void logout ();
    void sendMaxClearedStageNumToServer (int stageNum);
    void sendHighScoreToServer (int stageNum, int score);
    String[] fetchRankingFromServer();
}
