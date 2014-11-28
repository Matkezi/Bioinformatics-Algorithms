package topic1.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Matko
 */
public class MinimumSkew {

    public static List findMinimumSkew(String genome){
        List<String> minimums = new ArrayList<>();
        List<Integer> skew = new ArrayList<>();

        int gCount=0, cCount=0;
        for (int i = 0;i<genome.length();i++){
            if(genome.charAt(i) == 'G'){
                gCount++;
            } else if (genome.charAt(i) == 'C'){
                cCount++;
            }
            skew.add(gCount-cCount);
        }

        int minIndex = skew.indexOf(Collections.min(skew));
        for (int index = 0; index<skew.size();index++){
            if (skew.get(index) <= skew.get(minIndex)){
                //must be +1 because of the numbering from 0
                minimums.add(Integer.toString(index+1));
            }
        }

        return minimums;
    }
}
