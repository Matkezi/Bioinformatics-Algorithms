package topic5.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * @author Matko
 * @version 1.0
CODE CHALLENGE: Solve the Alignment with Affine Gap Penalties Problem.
Input: Two amino acid strings v and w (each of length at most 100).
Output: The maximum alignment score between v and w, followed by an alignment of v and w
achieving this maximum score. Use the BLOSUM62 scoring matrix, a gap opening penalty of 11, and
a gap extension penalty of 1.

Sample Input:
PRTEINS
PRTWPSEIN

Sample Output:
8
PRT---EINS
PRTWPSEIN-
 */
public class GlobalAlignmentProblemAffineGap extends GlobalAlignmentProblem {

    int sigma = 11;
    int epsilon = 1;
    int[][] lower, upper, middle;

    @Override
    protected void findScore() {
        score = middle[v.length][w.length];
    }

    @Override
    protected void formBacktrack(){
        lower = new int[v.length+1][w.length+1];
        upper = new int[v.length+1][w.length+1];
        middle = new int[v.length+1][w.length+1];

        backtrack = new String[v.length+1][w.length+1];


        for (int i =1;i<v.length+1;i++) {
            for (int j = 1; j < w.length + 1; j++) {

                //form a key for diagonal
                List<String> key = new ArrayList<>();
                key.add(v[i-1]);
                key.add(w[j-1]);

                lower[i][j] = Integer.max(lower[i-1][j]-epsilon,middle[i-1][j]-sigma);
                upper[i][j] = Integer.max(upper[i][j-1]-epsilon,middle[i][j-1]-sigma);
                int temp = Integer.max(lower[i][j],upper[i][j]);
                middle[i][j] = Integer.max(temp,middle[i-1][j-1]+table.get(key));

                List<Integer> values = new ArrayList<>();
                values.add(lower[i][j]);
                values.add(upper[i][j]);
                values.add(middle[i-1][j-1]+table.get(key));

                int index = 0;
                for (int k = 0;k<values.size();k++){
                    if (values.get(k) == middle[i][j]) index = k;
                }

                switch (index){
                    case 0: {
                        backtrack[i][j] = "down";
                        break;
                    }
                    case 1: {
                        backtrack[i][j] = "right";
                        break;
                    }
                    default:
                        backtrack[i][j] = "diagonal";
                }


            }
        }
    }

    @Override
    public void execute() {

        List<String> lines = loadFromFiles("GlobalAlignmentProblemAffineGap.txt");
        v = lines.get(0).split("");
        w = lines.get(1).split("");

        loadTable("bloSum62.txt");
        formBacktrack();
        findAlignment(v.length, w.length);
        findScore();

        Collections.reverse(vOut);
        Collections.reverse(wOut);

        //printing
        System.out.println(score);

        for (int i = 0;i<vOut.size();i++){
            System.out.print(vOut.get(i));
        }
        System.out.println();
        for (int i = 0;i<wOut.size();i++){
            System.out.print(wOut.get(i));
        }

    }


}
