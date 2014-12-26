package topic4.java;

import topic1.java.Stopwatch;

import java.io.IOException;

/**
 * @author Matko
 * @version 1.0
 */
public class Main {

    public static void main (String[] args) throws IOException{

        Stopwatch timer = new Stopwatch();
        DeBruijnGraph dbg = new DeBruijnGraph();
        dbg.execute();
        double time1 = timer.elapsedTime();
        System.out.println("Made DeBruijnGraph after: "+time1+" seconds.");

        Euler eu = new Euler();
        eu.execute();
        double time2 = timer.elapsedTime();
        System.out.println("Made EulerPath after: "+(time2-time1)+" seconds.");

        StringSpelledByGappedPatterns ssgp = new StringSpelledByGappedPatterns();
        ssgp.execute();
        System.out.println("Finished after: "+(timer.elapsedTime()-time2-time1)+" seconds.");

        //GenomePath.execute();

    }
}
