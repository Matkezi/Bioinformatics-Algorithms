package topic2.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Matko
 * @version 1.0
 */
public class Leaderboard {

    static Map<String, String> integerMass_keyInteger = new HashMap<>();
    static Map<String, String> integerMass = new HashMap<>();

    public static void initiateIntegerrMass_keyInteger() throws  IOException{
        String codonMapPath = "C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics Algorithms\\Topic2\\src\\topic2\\resources\\integer_mass_table.txt";
        List<String> lines = Files.readAllLines(Paths.get(codonMapPath));

        for (String line : lines) {
            String[] splitLine = line.split("\\s+");
            CycloPeptideSequencing.integerMass_keyInteger.put(splitLine[1],splitLine[0]);
        }
    }

    public static void initiateIntegerMass() throws  IOException{
        String codonMapPath = "C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics Algorithms\\Topic2\\src\\topic2\\resources\\integer_mass_table.txt";
        List<String> lines = Files.readAllLines(Paths.get(codonMapPath));

        for (String line : lines) {
            String[] splitLine = line.split("\\s+");
            CycloPeptideSequencing.integerMass.put(splitLine[0],splitLine[1]);
        }
    }

//    public static List expandPaptides (List<String> peptides){
//        List<String> newPeptides = new ArrayList<>();
//        for (String peptide : peptides){
//            for (String key:integerMass_keyInteger.keySet()){
//                String peptideTmp = peptide + "-" + key;
//                newPeptides.add(peptideTmp);
//            }
//        }
//        return newPeptides;
//    }

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

    static class Entity implements Comparable<Entity> {
        String name;
        int num;
        Entity(String name, int num) {
            this.name = name;
            this.num = num;
        }
        @Override
        public int compareTo(Entity o) {
            if (this.num < o.num)
                return 1;
            else if (this.num > o.num)
                return -1;
            return 0;
        }
    }

    public static List<String> trim (List<String> leaderboard, List<String> spectrum, int n){
        List<String> lBoard = new ArrayList<>();
        List<Integer> linearScores = new ArrayList<>(leaderboard.size());

        for (int j = 0;j<leaderboard.size();j++){
            //make a list of integers
            List<String> peptideS = Arrays.asList(leaderboard.get(j).split("-"));
            List<Integer> peptide = new ArrayList<Integer>(peptideS.size());
            for (String my : peptideS) {
                peptide.add(Integer.parseInt(my));
            }
            linearScores.add(ScoringProblem.score(peptide,spectrum));
        }

        List<Entity> entities = new ArrayList<Entity>();

        for (int i = 0;i<leaderboard.size();i++){
            entities.add(new Entity(leaderboard.get(i),linearScores.get(i)));
        }
        Collections.sort(entities);

        for (int i = 0;i<leaderboard.size();i++){
            lBoard.add(entities.get(i).name);
        }


        for (int i = n+1;i<leaderboard.size();i++){
            if (entities.get(i).num < entities.get(n).num){
                List<String> lBoard2 = lBoard.subList(0,i);
                return lBoard2;
            }
        }

        return lBoard;

    }

    public static void cycloPeptideSequencing(List<String> spectrum, int n) {
        List<String> peptides = spectrum;

        List<Integer> leaderPeptide = new ArrayList<>();
        leaderPeptide.add(0);

        int parrentMass = Integer.parseInt(spectrum.get(spectrum.size()-1));
        while(!peptides.isEmpty()){
            peptides = CycloPeptideSequencing.expandPaptides(peptides);
            System.out.print("\n");
            for (int i = 0;i<peptides.size();) {
                if (mass(peptides.get(i)) == parrentMass) {

                    //make a list of integers
                    List<String> peptideS = Arrays.asList(peptides.get(i).split("-"));
                    List<Integer> peptide = new ArrayList<Integer>(peptideS.size());
                    for (String my : peptideS) {
                        peptide.add(Integer.parseInt(my));
                    }

                    if (ScoringProblem.score(peptide,spectrum) > ScoringProblem.score(leaderPeptide,spectrum)){
                        leaderPeptide.clear();
                        leaderPeptide.addAll(peptide);
                    }
                    i++;
                } else if (mass(peptides.get(i)) > parrentMass  || !allPresent(spectrum,peptides.get(i))){
                    peptides.remove(i);
                } else i++;
            }
            peptides = trim(peptides,spectrum,n);
            System.out.println(leaderPeptide);
        }
    }

    public static void  main (String[] args) throws IOException {

        ProteinTranslation.initiateCodonMap();
        initiateIntegerMass();
        initiateIntegerrMass_keyInteger();

        //cycloseq_data
        //spectrum test
        String filepath = "C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics Algorithms\\Topic2\\src\\topic2\\resources\\spectrum test.txt";
        List<String> lines = Files.readAllLines(Paths.get(filepath));


        List<String> spectrum = new ArrayList<>();

        String[] splitLine = lines.get(0).split("\\s+");
        for (String line : splitLine){
            if (line.equals("0")) continue;
            spectrum.add(line);
        }

        cycloPeptideSequencing(spectrum, 325);




    }
}
