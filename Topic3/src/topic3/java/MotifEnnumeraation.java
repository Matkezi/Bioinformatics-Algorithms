package topic3.java;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static topic1.java.FrequentWords.neighbors;

/**
 * @author Matko
 * @version 1.0
 */
public class MotifEnnumeraation {

    List<String> patterns = new ArrayList<>();
    List<String> dnas = new ArrayList<>();

    int k, d;

    public MotifEnnumeraation(List<String> dnas, int k, int d) {
        this.dnas = dnas;
        this.k = k;
        this.d = d;
        fillPatterns();
    }

    private void fillPatterns(){


        for (String genome : dnas) {
            for (int i = 0; i < genome.length() - k; i++) {

                String startingKmer = genome.substring(i, i + k);
                List<String> kMers = neighbors(startingKmer, d);

                for (String kmer : kMers){
                    int counter = 0;
                    for (String dna : dnas){
                        if (dna.contains(kmer)) {
                            counter++;
                        }
                    }
                    if (counter == dnas.size()) patterns.add(kmer);
                }
            }
        }

        Set<String> dedupedPatterns = new LinkedHashSet<>(patterns);
        patterns.clear();
        patterns.addAll(dedupedPatterns);

    }


}
