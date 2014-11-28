package main.java;

import com.sun.org.apache.xml.internal.utils.ThreadControllerWrapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Matko
 * @version 1.0
 */
public class CycloPeptideSequencing {

    static Map<String, String> integerMass_keyInteger = new HashMap<>();
    static Map<String, String> integerMass = new HashMap<>();

    public static void initiateIntegerrMass_keyInteger() throws  IOException{
        String codonMapPath = "C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics Algorithms\\Topic2\\src\\main\\resources\\integer_mass_table.txt";
        List<String> lines = Files.readAllLines(Paths.get(codonMapPath));

        for (String line : lines) {
            String[] splitLine = line.split("\\s+");
            CycloPeptideSequencing.integerMass_keyInteger.put(splitLine[1],splitLine[0]);
        }
    }

    public static void initiateIntegerMass() throws  IOException{
        String codonMapPath = "C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics Algorithms\\Topic2\\src\\main\\resources\\integer_mass_table.txt";
        List<String> lines = Files.readAllLines(Paths.get(codonMapPath));

        for (String line : lines) {
            String[] splitLine = line.split("\\s+");
            CycloPeptideSequencing.integerMass.put(splitLine[0],splitLine[1]);
        }
    }

    public static List expandPaptides (List<String> peptides){
        List<String> newPeptides = new ArrayList<>();
        for (String peptide : peptides){
            for (String key:integerMass_keyInteger.keySet()){
                String peptideTmp = peptide+"-"+key;
                newPeptides.add(peptideTmp);
            }
        }
        return newPeptides;
    }

    public static int mass(String peptide){

        List<String> peps = Arrays.asList(peptide.split("-"));

        int sum = 0;
        for (String pep : peps){
            sum+=Integer.parseInt(pep);
        }
        return sum;
    }

    public static boolean allPresent(List<String> spectrum, String peptide){

        List<String> peps = Arrays.asList(peptide.split("-"));

        for (String pep : peps){
            if(!spectrum.contains(pep)) return false;
        }
        return true;
    }

    public static boolean notConsistent(List<String> spectrum, String peptide){
        if (!allPresent(spectrum,peptide)) return true;

        List<String> peps = Arrays.asList(peptide.split("-"));

        for (int i = 0;i<peps.size()-1;i++){
            int sum = Integer.parseInt(peps.get(i));
            for (int j = i+1;j<peps.size();j++){
                sum += Integer.parseInt(peps.get(j));
                if (!spectrum.contains(Integer.toString(sum))) return true;
            }
        }

        return false;
    }

    public static void cycloPeptideSequencing(List<String> spectrum) {
        List<String> peptides = spectrum;
        List<String> output = new ArrayList<>();
        int parrentMass = Integer.parseInt(spectrum.get(spectrum.size()-1));
        while(!peptides.isEmpty()){
            peptides = CycloPeptideSequencing.expandPaptides(peptides);
            System.out.print("\n");
            for (int i = 0;i<peptides.size();) {
                if (mass(peptides.get(i)) == parrentMass) {
                    if (allPresent(spectrum, peptides.get(i))) {
                        //System.out.println(peptides.get(i) + " ");
                        output.add(peptides.get(i));
                    }
                    peptides.remove(i);
                } else if (CycloPeptideSequencing.notConsistent(spectrum, peptides.get(i))){
                    peptides.remove(i);
                } else i++; //else move ahead in a list
            }

            List<String> outputReduced = new ArrayList<>(new LinkedHashSet<>(output));
            for (String out : outputReduced) System.out.println(out);

        }
    }

    public static void  main (String[] args) throws IOException {

        ProteinTranslation.initiateCodonMap();
        CycloPeptideSequencing.initiateIntegerMass();
        CycloPeptideSequencing.initiateIntegerrMass_keyInteger();

        //cycloseq_data
        //spectrum test
        String filepath = "C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics Algorithms\\Topic2\\src\\main\\resources\\dataset_100_5.txt";
        List<String> lines = Files.readAllLines(Paths.get(filepath));


        List<String> spectrum = new ArrayList<>();

        String[] splitLine = lines.get(0).split("\\s+");
        for (String line : splitLine){
            if (line.equals("0")) continue;
            spectrum.add(line);
        }

        cycloPeptideSequencing(spectrum);




    }
}
