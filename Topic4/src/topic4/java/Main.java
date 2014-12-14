package topic4.java;

import java.io.IOException;

/**
 * @author Matko
 * @version 1.0
 */
public class Main {

    public static void main (String[] args) throws IOException{

        DeBruijnGraph dbg = new DeBruijnGraph();
        dbg.execute();

        Euler eu = new Euler();
        eu.execute();

        GenomePath.execute();

    }
}
