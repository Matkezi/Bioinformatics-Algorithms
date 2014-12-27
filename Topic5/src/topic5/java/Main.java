package topic5.java;

import java.io.IOException;

/**
 * @author Matko
 * @version 1.0
 */
public class Main {

    public static void main (String[] args) throws IOException {
        LongestPathDAG lpd = new LongestPathDAG();
        lpd.loadFromFiles();
    }
}
