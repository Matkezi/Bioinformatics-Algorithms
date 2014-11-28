package topic1.java;

import java.util.*;

public class FrequentWords {


    public static List findMostFrequentKmers(int kMerLenght, String text, int... occurances) {

        //initialiaze list and map
        List<String> words = new ArrayList<String>();
        Map<String, Integer> map = new HashMap<String, Integer>();

        //load the map
        for (int i = 0; i < text.length() - kMerLenght; i++) {
            String kMer = text.substring(i, i + kMerLenght);
            map.put(kMer, PatternCount.countPattern(kMer, text));
        }

        if (occurances.length == 0) { //get frequent words that occure max times
            //save the most frequent kmers
            int maxValueInMap = (Collections.max(map.values()));  // This will return max value in the Hashmap
            for (Map.Entry<String, Integer> entry : map.entrySet()) {  // Itrate through hashmap
                if (entry.getValue() == maxValueInMap) {
                    words.add(entry.getKey());
                }
            }
        } else { //get all frequent words that occure more the x times
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() >= occurances[0]) {
                    words.add(entry.getKey());
                }
            }
        }
        return words;
    }

    public static List neighbors(String pattern, int d){
        List<String> ret = new ArrayList<>();

        if (pattern.length() == 1){
            ret.add("A");
            ret.add("C");
            ret.add("G");
            ret.add("T");
            return ret;
        }

        List<String> neighborhood = new ArrayList<>();

        List<String> suffixNeighbors = neighbors(pattern.substring(1,pattern.length()),d);

        for (String text: suffixNeighbors){
            if (HammingDistance.findHammingDistance(pattern.substring(1,pattern.length()),text) < d){
                neighborhood.add("A"+text);
                neighborhood.add("C"+text);
                neighborhood.add("G"+text);
                neighborhood.add("T"+text);

            } else {
                neighborhood.add(pattern.substring(0,1)+text);
            }
        }
        return neighborhood;
    }

    public static List findMostFrequentKmersWithAMismatch(String genome, int kMerLenght, int d) {
        List<String> words = new ArrayList<>();
        List<String> kMers = new ArrayList<>();
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (int i = 0; i < genome.length() - kMerLenght; i++) {

            String startingKmer = genome.substring(i, i + kMerLenght);

            kMers = neighbors(startingKmer,d);

            //for every kMer variation go through text and note number of matches in a text, save it to a map
            for (String kMer : kMers) {
                int numberOfMatches = 1;
                for (int j = 0;j< genome.length() - kMerLenght+1;j++){
                    String pattern = genome.substring(j,j+kMerLenght);
                    if (HammingDistance.findHammingDistance(kMer,pattern) <= d){
                        numberOfMatches++;
                    }
                    map.put(kMer,numberOfMatches);
                }
            }
        }

        //get from map only kMers that occured max times
        int maxValueInMap = (Collections.max(map.values()));  // This will return max value in the Hashmap
        for (Map.Entry<String, Integer> entry : map.entrySet()) {  // Itrate through hashmap
            if (entry.getValue() == maxValueInMap) {
                words.add(entry.getKey());
            }
        }
        return words;
    }

    public static List findMostFrequentKmersWithAMismatchReverse(String genome, int kMerLenght, int d) {

        List<String> words = new ArrayList<>();
        List<String> kMers = new ArrayList<>();
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (int i = 0; i < genome.length() - kMerLenght; i++) {

            String startingKmer = genome.substring(i, i + kMerLenght);

            kMers = neighbors(startingKmer,d);

            //for every kMer variation go through text and note number of matches in a text, save it to a map
            for (String kMer : kMers) {
                int numberOfMatches = 1;
                for (int j = 0;j< genome.length() - kMerLenght+1;j++){
                    String pattern = genome.substring(j,j+kMerLenght);
                    if (HammingDistance.findHammingDistance(kMer,pattern) <= d){
                        numberOfMatches++;
                    }
                }
                String complement = ReverseComplement.reverseComplement(kMer);
                for (int j = 0;j< genome.length() - kMerLenght+1;j++){
                    String pattern = genome.substring(j,j+kMerLenght);
                    if (HammingDistance.findHammingDistance(complement,pattern) <= d){
                        numberOfMatches++;
                    }
                }
                map.put(kMer,numberOfMatches);
            }
        }

        //get from map only kMers that occured max times
        int maxValueInMap = (Collections.max(map.values()));  // This will return max value in the Hashmap
        for (Map.Entry<String, Integer> entry : map.entrySet()) {  // Itrate through hashmap
            if (entry.getValue() == maxValueInMap) {
                words.add(entry.getKey());
            }
        }
        return words;
    }


}

