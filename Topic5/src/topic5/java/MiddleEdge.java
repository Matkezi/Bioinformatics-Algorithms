package topic5.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 *

CODE CHALLENGE: Solve the Middle Edge in Linear Space Problem (for protein strings).
Input: Two amino acid strings.
Output: A middle edge in the alignment graph in the form "(i, j) (k, l)", where (i, j) connects to (k, l).
To compute scores, use the BLOSUM62 scoring matrix and a (linear) indel penalty equal to 5.

Sample Input:
PLEASANTLY
MEASNLY

Sample Output:
(4, 3) (5, 4)
 */
public class MiddleEdge extends GlobalAlignmentProblem {

    //indel penalty
    int sigma = 5;

    enum MidType{FROMSOURCE,TOSINK}

    List<Integer> lenghts = new ArrayList<>();
    List<Integer> fromSource = new ArrayList<>();
    List<Integer> toSink = new ArrayList<>();

    protected List<Integer> fillMiddleColumn(String[] v, String[] w){

        List<Integer> finalColumn = new ArrayList<>();
        int[][] s = new int[v.length+1][w.length+1];

        //initialize first row
        for (int j = 0;j<w.length;j++){
            s[0][j+1] = s[0][j]-sigma;
        }

        //initialize first collumn
        for (int i = 0;i<v.length;i++){
            s[i+1][0] = s[i][0]-sigma;
        }

        for (int i =1;i<v.length+1;i++) {
            for (int j = 1; j < w.length + 1; j++) {
                int indel = Integer.max(s[i-1][j]-sigma,s[i][j-1]-sigma);

                //form a key for diagonal
                List<String> key = new ArrayList<>();
                key.add(v[i-1]);
                key.add(w[j-1]);

                //compare diagonal and indel
                int diagonal = s[i-1][j-1] + table.get(key);
                s[i][j] = Integer.max(indel,diagonal);
            }
        }

        for (int i = 0;i<v.length+1;i++) finalColumn.add(s[i][w.length]);
        return finalColumn;
    }


    public void execute() {
        List<String> lines = loadFromFiles("MiddleEdge.txt");
        v = lines.get(0).split("");
        w = lines.get(1).split("");

        loadTable("bloSum62.txt");

        String[] currentW = new String[w.length/2];

        System.arraycopy(w,0,currentW,0,currentW.length);

        fromSource = fillMiddleColumn(v,currentW);

        System.out.println();
    }
}
