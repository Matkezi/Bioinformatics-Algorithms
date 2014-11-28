package topic1.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Matko
 */
public class Main {

    public static void main(String[] args)throws IOException {
        Stopwatch timmer = new Stopwatch();

        String filepath = "C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics Algorithms\\Topic1\\src\\topic1\\resources\\Salmonella_enterica.txt";

        List<String> lines = Files.readAllLines(Paths.get(filepath));

        //Pattern
//        int count = PatternCount.countPattern(lines.get(0), lines.get(1));
//        System.out.println(count);

        //Frequent Words
//        List<String> words = FrequentWords.findMostFrequentKmers(Integer.parseInt(lines.get(1)),lines.get(0));
//
//        for (String word : words) {
//            System.out.print(word+" ");
//
//        }

        //Reverse complement
//        String complement = ReverseComplement.reverseComplement("CCAGATC");
//
//        System.out.println(complement);

        //Pattern Matching (starting postions)
//        List<Integer> startingPostions = PatternMatching.startsOfPatternIngenome(lines.get(0),lines.get(1));
//
//        for ( Integer position : startingPostions){
//            System.out.print(position+" ");
//        }
        //Clump finding
//        List<String> words = ClumpFinding.findClumps("GCACAAGGCCGACAATAGGACGTAGCCTTGAAGACGACGTAGCGTGGTCGCATAAGTACAGTAGATAGTACCTCCCCCGCGCATCCTATTATTAAGTTAATT",4,30,3);
//
//        for (String word : words) {
//            System.out.print(word + " ");
//        }

        //Finding minimum skew
//        List<String> minimums = MinimumSkew.findMinimumSkew(genome);
//
//        for (String min : minimums) {
//            System.out.print((min+" "));
//        }

        //Finding Hamming Distance
//        int distance = HammingDistance.findHammingDistance("TGACCCGTTATGCTCGAGTTCGGTCAGAGCGTCATTGCGAGTAGTCGTTTGCTTTCTCAAACTCC","GAGCGATTAAGCGTGACAGCCCCAGGGAACCCACAAAACGTGATCGCAGTCCATCCGATCATACA");
//        System.out.print(distance);

        //Approximate Pattern Matching and Pattern Count
//        List<Integer> startingPostions = PatternMatching.startsOfPatternIngenome("CCC","CATGCCATTCGCATTGTCCCAGTGA",2);
//
//        //the count
//        System.out.println(startingPostions.size());
//
//        for ( Integer position : startingPostions) {
//            System.out.print(position + " ");
//        }

        //Find Frequent word with mismatch
//        List<String> words = FrequentWords.findMostFrequentKmersWithAMismatch(lines.get(0),Integer.parseInt(lines.get(1)),Integer.parseInt(lines.get(2)));
//
//        for (String word : words) {
//            System.out.print(word+" ");
//
//        }


        //Find Frequent word with mismatch and reverse
//        List<String> words = FrequentWords.findMostFrequentKmersWithAMismatchReverse(genome,9,1);
//
//        for (String word : words) {
//            System.out.print(word+" ");
//
//        }


        //Final challenge
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i<lines.size();i++){
            sb.append(lines.get(i));
        }

        String genome = sb.toString();

        List<String> minimums = MinimumSkew.findMinimumSkew(genome);

        for (String min : minimums) {
            System.out.print((min+" "));
        }

        //cut genome aprox 1000 nucleotids before posible oriC till 1000 after

        genome = genome.substring(3764856-500,3764856);
        List<String> words = FrequentWords.findMostFrequentKmersWithAMismatchReverse(genome,9,0);

        for (String word : words) {
            System.out.print(word+" ");

        }

        System.out.println("Done! \n total time used: " + timmer.elapsedTime() +".");



    }
}
