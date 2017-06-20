package Client;

/**
 * ゲームのモデル
 * ステージ選択後に生成されるような設計にする(ステージ選択の度に生成される)
 * コンストラクタでステージ番号を引数にとり、ゲームモデルを生成する。
 *
 */
public class GameModel {

    private int stageNum;


    public GameModel (int stageNum) {
        this.stageNum = stageNum;

    }
}
