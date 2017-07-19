package Client;


import java.util.*;

/**
 * 相手の発言の一連のまとまりを表すクラス
 */
public class Conversation {
    private ArrayList<Remark> remarks;
    private int remarkCounter;
    private boolean isEnd;
    private Remark currentRemark;

    public Conversation(int conversationNum) {
        // どのまとまりかを示す引数をとる。


        // 発言カウンターをセット
        remarkCounter = 0;

        // isEndフラグを初期化
        isEnd = false;

        // remarksを取得しArrayListを生成
        remarks = new ArrayList<>();
        ALL_CONVERSATIONS.get(conversationNum).forEach(remarkNum -> {
            remarks.add(new Remark(remarkNum));
        });
    }

    public String getRemark() {
        // 発言を取得する。
        currentRemark = remarks.get(remarkCounter);
        // 次の発言取得のためにカウンタだけ進めとく
        remarkCounter++;
        if (remarkCounter == remarks.size()) {
            // ArrayListの終端に達した場合
            isEnd = true;
        }
        return  currentRemark.getWord();
    }

    public int getScore(int replyNum) {
        // 先ほど取得した発言のスコアを得る
        return currentRemark.getScore(replyNum);
    }

    public boolean getIsEnd() {
        return isEnd;
    }

    public void reset() {
        // 初期化のために行う。
        // 再び呼び出された場合に備えて、インデックスを初期化しておく
        remarkCounter = 0;
        isEnd = false;
    }

    public void playRemark(int speed) {
       currentRemark.playSound(speed);
    }


    /**
     * 以下、会話データの記述を行う。
     * 文脈番号をキーにして、発言番号のリストをオブジェクトとするMapを作成
     */
    public final static Map<Integer, ArrayList<Integer>> ALL_CONVERSATIONS;
    static {
        Map<Integer, ArrayList<Integer>> tmpmap;
        ArrayList<Integer> tmpRemarkList;
        tmpmap = new HashMap<>();
        /**
         * 文脈一個に対して以下の処理を行う
         * 単語リストの初期化
         * tmpRemarkList = new ArrayList<>();
         * tmpRemarkList.add(追加したい単語番号);
         *
         * tmpmap.add(文脈番号, tmpRemarkList);
         * 文脈番号と単語リストの対応付け
         *
         * 文脈の個数だけ、上記処理を繰り返す。
         * 最後に
         * ALL_CONVERSATIONS = Collections.unmodifiablemap(tmpmap);
         * を実行し、定数化する。
         */

        /**
         * 文脈番号1
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(101);
        tmpRemarkList.add(102);
        tmpRemarkList.add(103);
        tmpmap.put(1, tmpRemarkList);

        /**
         * 文脈番号2
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(201);
        tmpRemarkList.add(202);
        tmpmap.put(2, tmpRemarkList);

        /**
         * 文脈番号3
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(301);
        tmpRemarkList.add(302);
        tmpRemarkList.add(303);
        tmpmap.put(3, tmpRemarkList);

        /**
         * 文脈番号4
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(401);
        tmpRemarkList.add(402);
        tmpmap.put(4, tmpRemarkList);

        /**
         * 文脈番号5
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(501);
        tmpRemarkList.add(502);
        tmpRemarkList.add(503);
        tmpmap.put(5, tmpRemarkList);

        /**
         * 文脈番号6
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(601);
        tmpRemarkList.add(602);
        tmpRemarkList.add(603);
        tmpmap.put(6, tmpRemarkList);

        /**
         * 文脈番号7
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(701);
        tmpRemarkList.add(702);
        tmpmap.put(7, tmpRemarkList);

        /**
         * 文脈番号8
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(801);
        tmpRemarkList.add(802);
        tmpmap.put(8, tmpRemarkList);

        /**
         * 文脈番号9
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(901);
        tmpRemarkList.add(902);
        tmpRemarkList.add(903);
        tmpmap.put(9, tmpRemarkList);

        /**
         * 文脈番号10
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(1001);
        tmpRemarkList.add(1002);
        tmpRemarkList.add(1003);
        tmpmap.put(10, tmpRemarkList);
        
        /**
         * 文脈番号11
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(1101);
        tmpRemarkList.add(1102);
        tmpRemarkList.add(1103);
        tmpmap.put(11, tmpRemarkList);

        /**
         * 文脈番号12
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(1201);
        tmpRemarkList.add(1202);
        tmpRemarkList.add(1203);
        tmpmap.put(12, tmpRemarkList);
        
        /**
         * 文脈番号13
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(1301);
        tmpRemarkList.add(1302);
        tmpRemarkList.add(1303);
        tmpmap.put(13, tmpRemarkList);
        
        /**
         * 文脈番号14
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(1401);
        tmpRemarkList.add(1402);
        tmpRemarkList.add(1403);
        tmpmap.put(14, tmpRemarkList);
        
        /**
         * 文脈番号15
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(1501);
        tmpRemarkList.add(1502);
        tmpmap.put(15, tmpRemarkList);
        
        /**
         * 文脈番号16
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(1601);
        tmpRemarkList.add(1602);
        tmpRemarkList.add(1603);
        tmpmap.put(16, tmpRemarkList);
        
        /**
         * 文脈番号17
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(1701);
        tmpRemarkList.add(1702);
        tmpRemarkList.add(1703);
        tmpmap.put(17, tmpRemarkList);
        
        /**
         * 文脈番号18
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(1801);
        tmpRemarkList.add(1802);
        tmpRemarkList.add(1803);
        tmpmap.put(18, tmpRemarkList);
        
        /**
         * 文脈番号19
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(1901);
        tmpRemarkList.add(1902);
        tmpRemarkList.add(1903);
        tmpmap.put(19, tmpRemarkList);
        
        /**
         * 文脈番号20
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(2001);
        tmpRemarkList.add(2002);
        tmpRemarkList.add(2003);
        tmpmap.put(20, tmpRemarkList);
        
        /**
         * 文脈番号21
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(2101);
        tmpRemarkList.add(2102);
        tmpmap.put(21, tmpRemarkList);
        
        /**
         * 文脈番号22
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(2201);
        tmpRemarkList.add(2202);
        tmpRemarkList.add(2203);
        tmpmap.put(22, tmpRemarkList);
        
        /**
         * 文脈番号23
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(2301);
        tmpRemarkList.add(2302);
        tmpRemarkList.add(2303);
        tmpmap.put(23, tmpRemarkList);
        
        /**
         * 文脈番号24
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(2401);
        tmpRemarkList.add(2402);
        tmpmap.put(24, tmpRemarkList);
        
        /**
         * 文脈番号25
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(2501);
        tmpRemarkList.add(2502);
        tmpmap.put(25, tmpRemarkList);
        
        /**
         * 文脈番号26
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(2601);
        tmpRemarkList.add(2602);
        tmpmap.put(26, tmpRemarkList);
        
        /**
         * 文脈番号27
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(2701);
        tmpRemarkList.add(2702);
        tmpmap.put(27, tmpRemarkList);
        
        /**
         * 文脈番号28
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(2801);
        tmpRemarkList.add(2802);
        tmpRemarkList.add(2803);
        tmpmap.put(28, tmpRemarkList);
        
        /**
         * 文脈番号29
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(2901);
        tmpRemarkList.add(2902);
        tmpmap.put(29, tmpRemarkList);
        
        /**
         * 文脈番号30
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(3001);
        tmpmap.put(30, tmpRemarkList);
        
        /**
         * 文脈番号31
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(3101);
        tmpRemarkList.add(3102);
        tmpRemarkList.add(3103);
        tmpmap.put(31, tmpRemarkList);
        
        /**
         * 文脈番号32
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(3201);
        tmpRemarkList.add(3202);
        tmpRemarkList.add(3203);
        tmpmap.put(32, tmpRemarkList);
        
        
        /**
         * 文脈番号33
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(3301);
        tmpRemarkList.add(3302);
        tmpRemarkList.add(3303);
        tmpmap.put(33, tmpRemarkList);
        
        /**
         * 文脈番号34
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(3401);
        tmpRemarkList.add(3402);
        tmpmap.put(34, tmpRemarkList);
        
        /**
         * 文脈番号35
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(3501);
        tmpRemarkList.add(3502);
        tmpRemarkList.add(3503);
        tmpmap.put(35, tmpRemarkList);
        
        /**
         * 文脈番号36
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(3601);
        tmpRemarkList.add(3602);
        tmpmap.put(36, tmpRemarkList);
        
        /**
         * 文脈番号37
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(3701);
        tmpRemarkList.add(3702);
        tmpRemarkList.add(3703);
        tmpmap.put(37, tmpRemarkList);
        
        /**
         * 文脈番号38
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(3801);
        tmpmap.put(38, tmpRemarkList);
        
        /**
         * 文脈番号39
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(3901);
        tmpRemarkList.add(3902);
        tmpmap.put(39, tmpRemarkList);
        
        /**
         * 文脈番号40
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(4001);
        tmpRemarkList.add(4002);
        tmpmap.put(40, tmpRemarkList);
        
        /**
         * 文脈番号41
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(4101);
        tmpRemarkList.add(4102);
        tmpmap.put(41, tmpRemarkList);
        
        /**
         * 文脈番号42
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(4201);
        tmpRemarkList.add(4202);
        tmpRemarkList.add(4203);
        tmpmap.put(42, tmpRemarkList);
        
        /**
         * 文脈番号43
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(4301);
        tmpRemarkList.add(4302);
        tmpRemarkList.add(4303);
        tmpmap.put(43, tmpRemarkList);
        
        /**
         * 文脈番号44
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(4401);
        tmpRemarkList.add(4402);
        tmpRemarkList.add(4403);
        tmpmap.put(44, tmpRemarkList);
        
        /**
         * 文脈番号45
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(4501);
        tmpmap.put(45, tmpRemarkList);
        
        /**
         * 文脈番号46
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(4601);
        tmpRemarkList.add(4602);
        tmpRemarkList.add(4603);
        tmpmap.put(46, tmpRemarkList);
        
        /**
         * 文脈番号47
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(4701);
        tmpmap.put(47, tmpRemarkList);
        
        /**
         * 文脈番号48
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(4801);
        tmpRemarkList.add(4802);
        tmpmap.put(48, tmpRemarkList);
        
        /**
         * 文脈番号49
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(4901);
        tmpRemarkList.add(4902);
        tmpmap.put(49, tmpRemarkList);
        
        /**
         * 文脈番号50
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(5001);
        tmpRemarkList.add(5002);
        tmpRemarkList.add(5003);
        tmpmap.put(50, tmpRemarkList);
        
        /**
         * 文脈番号51
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(5101);
        tmpRemarkList.add(5102);
        tmpRemarkList.add(5103);
        tmpmap.put(51, tmpRemarkList);
        
        /**
         * 文脈番号52
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(5201);
        tmpRemarkList.add(5202);
        tmpRemarkList.add(5203);
        tmpmap.put(52, tmpRemarkList);
        
        /**
         * 文脈番号53
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(5301);
        tmpRemarkList.add(5302);
        tmpRemarkList.add(5303);
        tmpmap.put(53, tmpRemarkList);
        
        /**
         * 文脈番号54
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(5401);
        tmpRemarkList.add(5402);
        tmpRemarkList.add(5403);
        tmpmap.put(54, tmpRemarkList);
        
        /**
         * 文脈番号55
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(5501);
        tmpRemarkList.add(5502);
        tmpmap.put(55, tmpRemarkList);
        
        /**
         * 文脈番号56
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(5601);
        tmpRemarkList.add(5602);
        tmpRemarkList.add(5603);
        tmpmap.put(56, tmpRemarkList);
        
        /**
         * 文脈番号57
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(5701);
        tmpRemarkList.add(5702);
        tmpmap.put(57, tmpRemarkList);
        
        /**
         * 文脈番号58
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(5801);
        tmpRemarkList.add(5802);
        tmpRemarkList.add(5803);
        tmpmap.put(58, tmpRemarkList);
        
        /**
         * 文脈番号59
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(5901);
        tmpRemarkList.add(5902);
        tmpmap.put(59, tmpRemarkList);
        
        /**
         * 文脈番号60
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(6001);
        tmpRemarkList.add(6002);
        tmpRemarkList.add(6003);
        tmpmap.put(60, tmpRemarkList);
        
        /**
         * 文脈番号61
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(6101);
        tmpRemarkList.add(6102);
        tmpRemarkList.add(6103);
        tmpmap.put(61, tmpRemarkList);
        
        /**
         * 文脈番号62
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(6201);
        tmpRemarkList.add(6202);
        tmpRemarkList.add(6203);
        tmpmap.put(62, tmpRemarkList);
        
        /**
         * 文脈番号63
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(6301);
        tmpRemarkList.add(6302);
        tmpmap.put(63, tmpRemarkList);
        
        /**
         * 文脈番号64
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(6401);
        tmpRemarkList.add(6402);
        tmpmap.put(64, tmpRemarkList);
        
        /**
         * 文脈番号65
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(6501);
        tmpRemarkList.add(6502);
        tmpmap.put(65, tmpRemarkList);
        
        /**
         * 文脈番号66
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(6601);
        tmpmap.put(66, tmpRemarkList);
        
        /**
         * 文脈番号67
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(6701);
        tmpRemarkList.add(6702);
        tmpmap.put(67, tmpRemarkList);
        
        /**
         * 文脈番号68
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(6801);
        tmpmap.put(68, tmpRemarkList);
        
        /**
         * 文脈番号69
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(6901);
        tmpRemarkList.add(6902);
        tmpRemarkList.add(6903);
        tmpmap.put(69, tmpRemarkList);
        
        /**
         * 文脈番号70
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(7001);
        tmpRemarkList.add(7002);
        tmpRemarkList.add(7003);
        tmpmap.put(70, tmpRemarkList);
        
        /**
         * 文脈番号71
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(7101);
        tmpRemarkList.add(7102);
        tmpRemarkList.add(7103);
        tmpmap.put(71, tmpRemarkList);
        
        /**
         * 文脈番号72
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(7201);
        tmpRemarkList.add(7202);
        tmpRemarkList.add(7203);
        tmpmap.put(72, tmpRemarkList);
        
        /**
         * 文脈番号73
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(7301);
        tmpRemarkList.add(7302);
        tmpmap.put(73, tmpRemarkList);
        
        /**
         * 文脈番号74
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(7401);
        tmpRemarkList.add(7402);
        tmpmap.put(74, tmpRemarkList);
        
        /**
         * 文脈番号75
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(7501);
        tmpRemarkList.add(7502);
        tmpmap.put(75, tmpRemarkList);
        
        /**
         * 文脈番号76
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(7601);
        tmpRemarkList.add(7602);
        tmpmap.put(76, tmpRemarkList);
        
        /**
         * 文脈番号77
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(7701);
        tmpmap.put(77, tmpRemarkList);
        
        /**
         * 文脈番号78
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(7801);
        tmpRemarkList.add(7802);
        tmpRemarkList.add(7803);
        tmpmap.put(78, tmpRemarkList);
        
        /**
         * 文脈番号79
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(7901);
        tmpRemarkList.add(7902);
        tmpRemarkList.add(7903);
        tmpmap.put(79, tmpRemarkList);
        
        /**
         * 文脈番号80
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(8001);
        tmpRemarkList.add(8002);
        tmpRemarkList.add(8003);
        tmpmap.put(80, tmpRemarkList);
        
        /**
         * 文脈番号81
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(8101);
        tmpmap.put(81, tmpRemarkList);
        
        /**
         * 文脈番号82
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(8201);
        tmpRemarkList.add(8202);
        tmpmap.put(82, tmpRemarkList);
        
        /**
         * 文脈番号83
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(8301);
        tmpRemarkList.add(8302);
        tmpRemarkList.add(8303);
        tmpmap.put(83, tmpRemarkList);
        
        /**
         * 文脈番号84
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(8401);
        tmpRemarkList.add(8402);
        tmpmap.put(84, tmpRemarkList);
        
        /**
         * 文脈番号85
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(8501);
        tmpRemarkList.add(8502);
        tmpRemarkList.add(8503);
        tmpmap.put(85, tmpRemarkList);
        
        /**
         * 文脈番号86
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(8601);
        tmpRemarkList.add(8602);
        tmpRemarkList.add(8603);
        tmpmap.put(86, tmpRemarkList);
        
        /**
         * 文脈番号87
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(8701);
        tmpRemarkList.add(8702);
        tmpmap.put(87, tmpRemarkList);
        
        /**
         * 文脈番号88
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(8801);
        tmpRemarkList.add(8802);
        tmpmap.put(88, tmpRemarkList);
        
        /**
         * 文脈番号89
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(8901);
        tmpRemarkList.add(8902);
        tmpRemarkList.add(8903);
        tmpmap.put(89, tmpRemarkList);
        
        /**
         * 文脈番号90
         */

        tmpRemarkList = new ArrayList<>();
        tmpRemarkList.add(9001);
        tmpRemarkList.add(9002);
        tmpmap.put(90, tmpRemarkList);
     
        
        ALL_CONVERSATIONS = Collections.unmodifiableMap(tmpmap);
    }

}
