package topic2.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Matko
 * @version 1.0
 */
public class TheoreticalSpectrum {

    static Map<String, String> integerMass = new HashMap<>();

    public static void initiateIntegerMass() throws  IOException{
        String codonMapPath = "C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics Algorithms\\Topic2\\src\\topic2\\resources\\integer_mass_table.txt";
        List<String> lines = Files.readAllLines(Paths.get(codonMapPath));

        for (String line : lines) {
            String[] splitLine = line.split("\\s+");
            TheoreticalSpectrum.integerMass.put(splitLine[0],splitLine[1]);
        }
    }

    public static List linearSpectrum(String peptide){
        StringBuilder sb = new StringBuilder(peptide);

        List<Integer> prefixMass = new ArrayList<>();
        prefixMass.add(0);

        for (int i = 0;i<peptide.length();i++){
            prefixMass.add(prefixMass.get(i)+Integer.parseInt(integerMass.get(sb.substring(i,i+1))));
        }

        List<Integer> linearSpectrum = new ArrayList<>();
        linearSpectrum.add(0);

        for (int i = 0;i<peptide.length();i++){
            for (int j = i+1;j<peptide.length()+1;j++){
                linearSpectrum.add(prefixMass.get(j)-prefixMass.get(i));
            }
        }

        Collections.sort(linearSpectrum);

        return linearSpectrum;

    }

    public static void initiateCodonMap() throws  IOException{
        String codonMapPath = "C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics Algorithms\\Topic2\\src\\topic2\\resources\\experimental_spectrum.txt";
        List<String> lines = Files.readAllLines(Paths.get(codonMapPath));


    }

    public static List cyclicSpectrum(String peptide){
        StringBuilder sb = new StringBuilder(peptide);

        List<Integer> prefixMass = new ArrayList<>();
        prefixMass.add(0);

        for (int i = 0;i<peptide.length();i++){
            prefixMass.add(prefixMass.get(i)+Integer.parseInt(integerMass.get(sb.substring(i,i+1))));
        }

        Integer peptideMass = (prefixMass.get(peptide.length()));

        List<Integer> cyclicSpectrum = new ArrayList<>();
        cyclicSpectrum.add(0);

        for (int i = 0;i<peptide.length();i++){
            for (int j = i+1;j<peptide.length()+1;j++){
                cyclicSpectrum.add(prefixMass.get(j)-prefixMass.get(i));
                if (i > 0 && j < peptide.length()) cyclicSpectrum.add( peptideMass - (prefixMass.get(j)-prefixMass.get(i)) );
            }
        }

        Collections.sort(cyclicSpectrum);

        return cyclicSpectrum;

    }

    public static void  main (String[] args) throws IOException {

        ProteinTranslation.initiateCodonMap();
        TheoreticalSpectrum.initiateIntegerMass();

        String filepath = "C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics Algorithms\\Topic2\\src\\topic2\\resources\\dataset_102_3.txt";
        List<String> lines = Files.readAllLines(Paths.get(filepath));


        StringBuilder sb = new StringBuilder();

        //for experimental
        List<Integer> experimentalSpectrum  = new ArrayList<>();


        String[] splitLine = lines.get(0).split("\\s+");
        for (String entry : splitLine){
            experimentalSpectrum.add(Integer.parseInt(entry));
        }

        //end of experimental

//        for (String line : lines){
//            sb.append(line);
//        }


        List<Integer> cyclicSpectrum = cyclicSpectrum("MAIT");
        List<Integer> linearSpectrum = linearSpectrum("MAIT");


        for (Integer value : cyclicSpectrum) System.out.print(value + " ");
        System.out.print("\n");

        List<Integer> common = new ArrayList<Integer>(cyclicSpectrum);
        common.retainAll(experimentalSpectrum);

        System.out.println(common.size());

    }
}
