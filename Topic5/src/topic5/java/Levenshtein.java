package topic5.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class Levenshtein extends GlobalAlignmentProblem {

    protected void formBacktrack(){
        backtrack = new String[v.length+1][w.length+1];
        s = new int[v.length+1][w.length+1];

        //initialiaze first row
        for (int j = 0;j<w.length;j++){
            s[0][j+1] = s[0][j]-1;
        }

        //initialiaze first collumn
        for (int i = 0;i<v.length;i++){
            s[i+1][0] = s[i][0]-1;
        }

        for (int i =1;i<v.length+1;i++) {
            for (int j = 1; j < w.length + 1; j++) {

                List<Integer> values = new ArrayList<>();
                values.add(s[i - 1][j] - 1);
                values.add(s[i][j - 1] - 1);

                if (v[i - 1].equals(w[j - 1])) values.add(s[i - 1][j - 1]);
                else values.add(s[i - 1][j - 1] - 1);

                s[i][j] = Collections.max(values);

                int index = 0;
                for (int k = 0; k < values.size(); k++) {
                    if (values.get(k) == s[i][j]) index = k;
                }

                switch (index) {
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

    public void execute() {

        List<String> lines = loadFromFiles("Levenshtein.txt");
        v = lines.get(0).split("");
        w = lines.get(1).split("");

        formBacktrack();
        findAlignment(v.length, w.length);
        score = s[v.length][w.length];
        System.out.println(Math.abs(score));


        Collections.reverse(vOut);
        Collections.reverse(wOut);

        for (int i = 0;i<vOut.size();i++){
            System.out.print(vOut.get(i));
        }
        System.out.println();
        for (int i = 0;i<wOut.size();i++){
            System.out.print(wOut.get(i));
        }

    }


}

