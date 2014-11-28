package topic1.java;

import java.util.ArrayList;
import java.util.List;

/**
 *@author Matko
 */
public class ClumpFinding {

    public static List findClumps (String genome, int k, int L, int t){
        List<String> words = new ArrayList<>();

        for (int i = 0; i < genome.length() - L;i++){
            String slice = genome.substring(i,i+L);
            List<String> foundKmers = FrequentWords.findMostFrequentKmers(k,slice,t);
            for (String kMer : foundKmers){
                if (!words.contains(kMer)) {
                    words.add(kMer);
                }
            }
        }


        return words;
    }

}
