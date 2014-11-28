package topic3.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

                int counter = 0;
                for (String dna : dnas){
                    boolean contains = false;

                    for (String kmer : kMers){
                        if (dna.contains(kmer)){
                            contains = true;
                            break;
                        }
                    }
                    if (contains) counter++;;
                }
                if (counter == dnas.size()) patterns.add(startingKmer);
            }
        }

        Set<String> dedupedPatterns = new LinkedHashSet<>(patterns);
        patterns.clear();
        patterns.addAll(dedupedPatterns);

    }

    public static void main(String[] args) throws IOException{

        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic3\\src\\topic3\\resources");
        File file1 = new File(dir, "motifEnnumerate.txt");
        Path filepath = file1.toPath();

        List<String> lines = Files.readAllLines(filepath);

        List<String> dnas = lines.subList(2,lines.size());

        MotifEnnumeraation motifEnum = new MotifEnnumeraation(dnas,Integer.parseInt(lines.get(0)),Integer.parseInt(lines.get(1)));
        System.out.println(motifEnum.patterns);

    }
}
