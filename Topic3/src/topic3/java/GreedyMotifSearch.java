package topic3.java;

import com.sun.xml.internal.ws.util.QNameMap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


/**
 * @author Matko
 * @version 1.0
 */
public class GreedyMotifSearch {

    int k;
    double[][] profile;


    public GreedyMotifSearch(int k) {
        this.profile = new double[4][k];
        this.k = k;
    }


    private Double calculateProbability(String kMer){
        String[] nucleotides = kMer.split("");
        Double probabilty = 1.0;
        for (int j = 0;j<k;j++){
            switch (nucleotides[j]){
                case "A": probabilty *= profile[0][j]; break;
                case "C": probabilty *= profile[1][j]; break;
                case "G": probabilty *= profile[2][j]; break;
                case "T": probabilty *= profile[3][j]; break;
                default: probabilty *= 1.0;
            }
        }
        return probabilty;
    }

    private String profileMostProbableKmer (String text){

        HashMap<String,Double> probabilites = new HashMap<>();

        for (int i = 0; i < text.length()-k+1; i++) {
            String kMer = text.substring(i, i + k);
            Double probability = calculateProbability(kMer);
            probabilites.put(kMer,probability);
        }

        Double maxValueInMap = (Collections.max(probabilites.values()));  // This will return max value in the Hashmap
        for (Map.Entry<String, Double> entry : probabilites.entrySet()) {  // Itrate through hashmap
            if (entry.getValue() == maxValueInMap) {
                System.out.println(entry.getKey());
            }
        }
        return "df";
    }

    public static void main (String[] args) throws IOException{
        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic3\\src\\topic3\\resources");
        File file1 = new File(dir, "Profile-most Probable k-mer Problem.txt");
        Path filepath = file1.toPath();

        List<String> lines = Files.readAllLines(filepath);

        String text = lines.get(0);
        int k = Integer.parseInt(lines.get(1));

        GreedyMotifSearch gms = new GreedyMotifSearch(k);

        //initialiaze a profile
        for (int i = 0; i<4;i++){
            String line = lines.get(i+2);
            String[] splited = line.split("\\s+");
            for (int j = 0;j<k;j++){
                gms.profile[i][j] = Double.parseDouble(splited[j]);
            }
        }

        gms.profileMostProbableKmer(text);

    }


}
