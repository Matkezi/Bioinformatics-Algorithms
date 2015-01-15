package topic5.java;

import java.io.IOException;

/**
 * @author Matko
 * @version 1.0
 */
public class Main {

    public static void main (String[] args) throws IOException {
//        DpChange dp = new DpChange();
//        dp.loadFromFiles();
//        System.out.println(dp.getMinNumCoins());

        LongestPathDAG lpd = new LongestPathDAG();
        lpd.executeLongestPathDAG();

//        LCSBacktrack lcb = new LCSBacktrack();
//        lcb.loadFromFiles();
    }
}
