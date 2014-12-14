package topic4.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class UniversalString {

    int k;

    public UniversalString(int k) {
        this.k = k;
    }


    public void printUniversalString() throws IOException{
        PrintWriter writer = new PrintWriter("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\out\\universalStringKmersOut.txt", "UTF-8");
        for (int i = 0;i<(int) Math.pow(2, k);i++) {
            writer.println(String.format("%9s", Integer.toBinaryString(i)).replace(' ', '0'));
        }
        writer.close();
    }
}
