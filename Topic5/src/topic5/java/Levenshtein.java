package topic5.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class Levenshtein extends GlobalAlignmentProblem {

    int score = 0;

    protected void lScore(){
        for (int i = 0;i<vOut.size();i++) {
            String v = vOut.get(i);
            String w = wOut.get(i);

            if (v.equals(w)) score += 0;
            else {
                score -= 1;
            }

        }
    }

    public void execute() {
        super.execute();
        lScore();
        System.out.println("\n"+Math.abs(score));
        System.out.println(s[v.length][w.length]);

    }


}

