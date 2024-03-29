package topic5.java;

import java.util.*;

/**
 * @author Matko
 * @version 1.0
 *
CODE CHALLENGE: Solve the Local Alignment Problem.
Input: Two protein strings written in the single-letter amino acid alphabet.
Output: The maximum score of a local alignment of the strings, followed by a local alignment of these
strings achieving the maximum score. Use the PAM250 scoring matrix and indel penalty σ = 5.

Sample Input:
MEANLY
PENALTY

Sample Output:
15
EANL-Y
ENALTY
 */
public class LocalAlignmentProblem extends GlobalAlignmentProblem {

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

        for (int i = 0; i < s.length; i++)
            for (int j = 0; j < s[i].length; j++)
                if (s[i][j] > maxValue) {
                    maxValue = s[i][j];
                    maxRowindex = i;
                    maxColumnindex = j;
                }

        s[v.length][w.length] = maxValue;
    }

    protected void formBacktrack(){
        backtrack = new String[v.length+1][w.length+1];
        s = new int[v.length+1][w.length+1];

        //initialiaze first row
        for (int j = 0;j<w.length;j++){
            s[0][j+1] = s[0][j]-sigma;
        }

        //initialiaze first collumn
        for (int i = 0;i<v.length;i++){
            s[i+1][0] = s[i][0]-sigma;
        }

        for (int i =1;i<v.length+1;i++){
            for (int j = 1;j<w.length+1;j++){

                int indel = Integer.max(s[i-1][j]-sigma,s[i][j-1]-sigma);

                //form a key for diagonal
                List<String> key = new ArrayList<>();
                key.add(v[i-1]);
                key.add(w[j-1]);

                //compare diagonal and indel
                int diagonal = s[i-1][j-1] + table.get(key);
                s[i][j] = Integer.max(indel,diagonal);

                //compare current to 0 (starting node)
                s[i][j] = Integer.max(s[i][j],0);

                if (s[i][j] == 0) {
                    backtrack[i][j] = "start";
                } if (s[i][j] == s[i-1][j]-sigma){
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

        List<String> lines = loadFromFiles("LocalAlignmentProblem.txt");
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
