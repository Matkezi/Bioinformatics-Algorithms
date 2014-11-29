package topic2.java;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @author Matko
 */
public class ProteinTranslation {

    static Map<String, String> codonMap = new HashMap<>();

    public static String rnaToAminoAcid(String rna){

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < rna.length()-2; i += 3) {
            sb.append(codonMap.get(rna.substring(i,i+3)));
        }

        return sb.toString();
    }
    public static void initiateCodonMap() throws  IOException{
        String codonMapPath = "C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics Algorithms\\Topic2\\src\\topic2\\resources\\RNA_codon_table_1.txt";
        List<String> lines = Files.readAllLines(Paths.get(codonMapPath));

        for (String line : lines) {
            String[] splitLine = line.split("\\s+");
            if (splitLine.length == 1) {//it's a STOP codon
                ProteinTranslation.codonMap.put(splitLine[0],"");
            } else ProteinTranslation.codonMap.put(splitLine[0],splitLine[1]);
        }
    }



    public static void  main (String[] args) throws IOException {

        ProteinTranslation.initiateCodonMap();

        String filepath = "C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics Algorithms\\Topic2\\src\\topic2\\resources\\dataset_96_5.txt";
        List<String> lines = Files.readAllLines(Paths.get(filepath));

        System.out.println(rnaToAminoAcid("CCGAGGACCGAAAUCAAC"));

    }
}
