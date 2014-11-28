package topic3.java;

import topic1.java.HammingDistance;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

/**
 * @author Matko
 * @version 1.0
 */
public class MedianString {

    String median;
    private List<String> dnas = new ArrayList<>();
    private int k;

    public MedianString(List<String> dnas, int k) {
        this.dnas = dnas;
        this.k = k;
        findMedianString();
    }

    private void findMedianString(){
        int distance = 32000; //must be a very large number -> infinity

        for (int i = 0; i<Math.pow(4,k)-1;i++){
            String pattern = numberToPattern(i,k);
        }

    }

    private int distanceBetweenPatternsAndString(String pattern, List<String> dnas){
        int k = pattern.length();
        int distance = 0;

        for (String dna : dnas){
            int hammingDistance = 32000; //->infinity

            for (int i = 0; i < dna.length()-k+1; i++) {
                String possiblePattern = dna.substring(i, i + k);
                if (hammingDistance > HammingDistance.findHammingDistance(pattern,possiblePattern) ){
                    hammingDistance = HammingDistance.findHammingDistance(pattern,possiblePattern);
                }
            }
            distance += hammingDistance;
        }
        return distance;
    }

    private String numberToSymbol(int index){
        switch (index){
            case 0: return "A";
            case 1: return "C";
            case 2: return "G";
            case 3: return "T";
            default: return "ERROR";
        }
    }

    private String numberToPattern(int index, int k){
        if (k == 1){
            return numberToSymbol(index);
        }
        int r = index % 4;
        int prefixIndex = index / 4;

        String prefixPattern = numberToPattern(prefixIndex,k-1);
        String symbol = numberToSymbol(r);
        return prefixPattern+symbol;
    }

    public static void main(String[] args) throws IOException{

        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic3\\src\\topic3\\resources");
        File file1 = new File(dir, "medianString.txt");
        Path filepath = file1.toPath();

        List<String> lines = Files.readAllLines(filepath);

        List<String> dnas = lines.subList(1,lines.size());

        MedianString ms = new MedianString(dnas,Integer.parseInt(lines.get(0)));

        List<String> test = new ArrayList<>();

    }
}
