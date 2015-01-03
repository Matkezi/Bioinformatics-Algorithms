package topic5.java;

import java.util.List;

/**
 * @author Matko
 * @version 1.0
 *
 * CODE CHALLENGE: Solve the Global Alignment Problem.
Input: Two protein strings written in the single-letter amino acid alphabet.
Output: The maximum alignment score of these strings followed by an alignment achieving this
maximum score. Use the BLOSUM62 scoring matrix and indel penalty σ = 5.

Sample Input:
PLEASANTLY
MEANLY

Sample Output:
8
PLEASANTLY
-MEA--N-LY


 TIP: BLOSUM62 gives you the score for both identities (Vi == Wj) and mismatches (Vi<>Wj).
Then, the alignment recurrence should be written as:

S(i,j) = max { S(i-1,j) - sigma;           S(i, 1-j) -sigma;         S(i,j) + BLOSUM62(vi, wi) }

where BLOSUM62(vi, wii) is the BLOSUM62 score for aligning residue vi with residue wi
 */
public class GlobalAlignmentProblem extends LoadAndExecute {
    String[][] backtrack;
    int[][] s;
    String[] v;
    String[] w;

    public void outputLCS(int i, int j){
        if (i == 0 || j == 0){
            return;
        }

        switch (backtrack[i][j]){
            case "down": outputLCS(i-1,j);
                break;

            case "right": outputLCS(i,j-1);
                break;

            default:      outputLCS(i-1,j-1);
                System.out.print(v[i-1]);
                break;
        }
    }

    private void formBacktrack(){
        backtrack = new String[v.length+1][w.length+1];
        s = new int[v.length+1][w.length+1];

        for (int i =1;i<v.length+1;i++){
            for (int j = 1;j<w.length+1;j++){

                int maxTmp = Integer.max(s[i-1][j],s[i][j-1]);
                if (v[i-1].equals(w[j-1])){
                    int diagonal = s[i-1][j-1] + 1;
                    s[i][j] = Integer.max(maxTmp,diagonal);
                } else s[i][j] = maxTmp;

                if (s[i][j] == s[i-1][j]){
                    backtrack[i][j] = "down";
                } if (s[i][j] == s[i][j-1]){
                    backtrack[i][j] = "right";
                } if (v[i-1].equals(w[j-1]) && s[i][j] == s[i-1][j-1]+1){
                    backtrack[i][j] = "diagonal";
                }
            }
        }

    }

    @Override
    public void execute() {

        List<String> lines = loadFromFiles("GlobalAlignmentProblem.txt");
        v = lines.get(0).split("");
        w = lines.get(1).split("");

        formBacktrack();
        outputLCS(v.length,w.length);


    }


}
