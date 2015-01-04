package topic5.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 *
 *
CODE CHALLENGE: Solve the Overlap Alignment Problem.
Input: Two strings v and w, each of length at most 1000.
Output: The score of an optimal overlap alignment of v and w, followed by an alignment of a suffix v' of
v and a prefix w' of w achieving this maximum score. Use an alignment score in which matches count
+1 and both the mismatch and indel penalties are 2.

Sample Input:
PAWHEAE
HEAGAWGHEE

Sample Output:
1
HEAE
HEAG
 */
public class OverlapAlingmentProblem extends GlobalAlignmentProblem{

    int maxRowindex = 0, maxColumnindex = 0;


    protected void findAlignment(int i, int j){

        if (j == 0) return;

        switch (backtrack[i][j]){
            case "down":
                vOut.add(v[i-1]);
                wOut.add("-");

                findAlignment(i - 1, j);
                break;

            case "right":
                vOut.add("-");
                wOut.add(w[j-1]);

                findAlignment(i, j - 1);
                break;

            case "diagonal":
                vOut.add(v[i-1]);
                wOut.add(w[j-1]);
                findAlignment(i - 1, j - 1);
                break;

            default:
                return;

        }
    }

    private void formLastNode(){
        int maxValue = 0;
        maxRowindex = v.length;
        for (int j = 1; j < w.length; j++) {
            if (s[v.length][j] > maxValue) {
                maxValue = s[v.length][j];
                maxColumnindex = j;
            }
        }
    }

    protected void formBacktrack(){
        backtrack = new String[v.length+1][w.length+1];
        s = new int[v.length+1][w.length+1];

        for (int i =1;i<v.length+1;i++){
            for (int j = 1;j<w.length+1;j++){

                List<Integer> scores = new ArrayList<>();
                scores.add(s[i-1][j]-2);
                scores.add(s[i][j-1]-2);

                if (v[i-1].equals(w[j-1])) scores.add(s[i-1][j-1] + 1);
                else scores.add(s[i-1][j-1] - 2);

                s[i][j] = Collections.max(scores);

                int index = 0;
                for (int k = 0;k<scores.size();k++){
                    if (scores.get(k) == s[i][j]) index = k;
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


        //form last node of a backtrack matrix
        formLastNode();

    }

    @Override
    public void execute() {

        List<String> lines = loadFromFiles("OverlapAlignmentProblem.txt");
        v = lines.get(0).split("");
        w = lines.get(1).split("");

        formBacktrack();
        findAlignment(maxRowindex, maxColumnindex);
        score = s[maxRowindex][maxColumnindex];

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
