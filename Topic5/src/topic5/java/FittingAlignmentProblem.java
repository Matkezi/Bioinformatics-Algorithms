package topic5.java;

import java.util.*;

/**
 * @author Matko
 * @version 1.0
 *


CODE CHALLENGE: Solve the Fitting Alignment Problem.
Input: Two nucleotide strings v and w, where v has length at most 1000 and w has length at most 100.
Output: A highest-scoring fitting alignment between v and w. Use the simple scoring method in which
matches count +1 and both the mismatch and indel penalties are 1.

Sample Input:
GTAGGCTTAAGGTTA
TAGATA

Sample Output:
2
TAGGCTTA
TAGA--TA
 */
public class FittingAlignmentProblem extends GlobalAlignmentProblem {

    int maxRowindex = 0, maxColumnindex = 0;

    protected void findScore(){
        for (int i = 0;i<vOut.size();i++){
            String v = vOut.get(i);
            String w = wOut.get(i);

            if (v.equals("-") || w.equals("-")) score -= sigma;
            else {
                List<String> key = new ArrayList<>();
                key.add(v);
                key.add(w);
                score += table.get(key);
            }
        }
    }

    protected void findAlignment(int i, int j){

        if (i == 0 && j == 0){//only when we reach the end of matrix we can end the reursion
            return;
        } else {//if we are in row 0 we must go left, and if we are in column 0 we must go up
            if (i == 0) backtrack[i][j] = "right";
            else if (j == 0) backtrack[i][j] = "down";
        }


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
        maxColumnindex = w.length;
        for (int i = 1; i < s.length; i++) {
            if (s[i][w.length] > maxValue) {
                maxValue = s[i][w.length];
                maxRowindex = i;
            }
        }
        s[v.length][w.length] = maxValue;
    }

    protected void formBacktrack(){
        backtrack = new String[v.length+1][w.length+1];
        s = new int[v.length+1][w.length+1];

        //initialiaze first collumn
        for (int i = 0;i<v.length;i++){
            s[i+1][0] = s[i][0]-1;
        }

        for (int i = 0;i<v.length;i++){
            backtrack[i+1][1] = "start";
        }

        for (int i =1;i<v.length+1;i++){
            for (int j = 2;j<w.length+1;j++){

                int indel = Integer.max(s[i-1][j]-sigma,s[i][j-1]-sigma);

                //form a key for diagonal
                List<String> key = new ArrayList<>();
                key.add(v[i-1]);
                key.add(w[j-1]);

                //compare diagonal and indel
                int diagonal = s[i-1][j-1] + table.get(key);
                s[i][j] = Integer.max(indel,diagonal);

                if (s[i][j] == s[i-1][j]-sigma){
                    backtrack[i][j] = "down";
                } if (s[i][j] == s[i][j-1]-sigma){
                    backtrack[i][j] = "right";
                } if (s[i][j] == s[i-1][j-1]+table.get(key)){
                    backtrack[i][j] = "diagonal";
                }
            }
        }

        //form last node of a backtrack matrix
        formLastNode();

    }

    @Override
    public void execute() {

        List<String> lines = loadFromFiles("FittingAlignmentProblem.txt");
        v = lines.get(0).split("");
        w = lines.get(1).split("");

        loadTable("PAM250_1.txt");
        formBacktrack();
        findAlignment(maxRowindex, maxColumnindex);
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
