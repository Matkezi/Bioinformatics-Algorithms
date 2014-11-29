package topic2.java;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;

/**
 * @author Matko
 */
public class PeptideEncoding {

    public static String dnaToRna(String dna){
        String rna = dna.replace("T","U");
        return rna;
    }

    public static String rnaToDna(String rna){
        String dna = rna.replace("U","T");
        return dna;
    }

    /**
     * Based on a given peptide it finds all patterns in a text encoding the peptide
     * CASE SENSITIVE!
     * @param dna a given RNA string
     * @param peptide peptide to compare pattern to
     * @return a list of found patterns
     */
    public static List patternsEncodingPeptide(String dna, String peptide){
        int patternLenght = 3 * peptide.length();
        List<String> patterns = new ArrayList<>();

        String text = PeptideEncoding.dnaToRna(dna);

        for (int i = 0;i<text.length()-patternLenght;i++){
            String pattern = text.substring(i,i+patternLenght);
            if (ProteinTranslation.rnaToAminoAcid(pattern).equals(peptide)
               || ProteinTranslation.rnaToAminoAcid(ReverseComplement.reverseComplement(pattern,"rna")).equals(peptide)) {
                pattern = rnaToDna(pattern);
                patterns.add(pattern);
            }
        }
        return patterns;
    }

    public static void  main (String[] args) throws IOException {

        ProteinTranslation.initiateCodonMap();

        String filepath = "C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics Algorithms\\Topic2\\src\\topic2\\resources\\B_brevis.txt";
        List<String> lines = Files.readAllLines(Paths.get(filepath));

        StringBuilder sb = new StringBuilder();

        for (String line : lines){
            sb.append(line);
        }

        List<String> patterns = PeptideEncoding.patternsEncodingPeptide(sb.toString(),"VKLFPWFNQY");

        for (String pattern : patterns) System.out.println(pattern);

    }

}
