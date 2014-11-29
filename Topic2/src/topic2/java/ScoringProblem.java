package topic2.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Matko
 * @version 1.0
 */
public class ScoringProblem {

    public static int score(List<Integer> theoretical, List<String> experimental1){

        List<String> experimental = new ArrayList<>(experimental1);
        experimental.addAll(experimental1);

        HashMap<Integer, Integer> theoreticalMultiplicity = new HashMap<>();
        for (int pep : theoretical){
            int occurrences = Collections.frequency(theoretical,pep);
            theoreticalMultiplicity.put(pep,occurrences);
        }

        HashMap<Integer, Integer> experimentalMultiplicity = new HashMap<>();
        for (String pep : experimental){
            int occurrences = Collections.frequency(experimental,pep);
            experimentalMultiplicity.put(Integer.parseInt(pep),occurrences);
        }

        int score = 0;

        List<String> theory = new ArrayList<String>(theoretical.size());
        for (Integer myInt : theoretical) {
            theory.add(String.valueOf(myInt));
        }

        for (String pep : theory){
            if (experimental.contains(pep)) {
                if (experimentalMultiplicity.get(Integer.parseInt(pep)) < theoreticalMultiplicity.get(Integer.parseInt(pep))) {
                    score += experimentalMultiplicity.get(Integer.parseInt(pep));
                } else score += theoreticalMultiplicity.get(Integer.parseInt(pep));
                while(experimental.remove(pep)) {}
            }
        }

        return score;
    }


    public static void main (String[] args) throws IOException {
        ProteinTranslation.initiateCodonMap();
        TheoreticalSpectrum.initiateIntegerMass();

        String filepath = "C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics Algorithms\\Topic2\\src\\topic2\\resources\\dataset_4913_1.txt";
        List<String> lines = Files.readAllLines(Paths.get(filepath));

        List<String> spectrum = new ArrayList<>();

        String[] splitLine = lines.get(0).split("\\s+");

        for (String line : splitLine){
            spectrum.add(line);
        }

        List<Integer> cyclicSpectrum = TheoreticalSpectrum.cyclicSpectrum("PEEP");
        System.out.println(cyclicSpectrum);
        int score = ScoringProblem.score(cyclicSpectrum,spectrum);
        System.out.println(score);

    }
}
