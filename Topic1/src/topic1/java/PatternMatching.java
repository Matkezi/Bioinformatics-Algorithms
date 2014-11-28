package topic1.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matko
 */
public class PatternMatching {

    public static List startsOfPatternIngenome(String pattern, String genome, int ... d) {

        List<Integer> startingPostions = new ArrayList<>();

        if (d.length == 0) {
            for (int i = -1; (i = genome.indexOf(pattern, i + 1)) != -1; ) {
                startingPostions.add(i);
            }
        } else { //patterns with a mismatches of d or less
            for (int i = 0;i<genome.length()-pattern.length()+1;i++){
                String slice = genome.substring(i,i+pattern.length());
                if (HammingDistance.findHammingDistance(slice,pattern) <= d[0]){
                    startingPostions.add(i);
                }
            }
        }
        return startingPostions;
    }
}
