package topic3.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class GreedyMotifSearch {

    double[][] profile;

    public GreedyMotifSearch(int k) {
        this.profile = new double[4][k];
    }

    //    private String profileMostProbableKmer (String text, int k){
//
//    }


    public static void main (String[] args) throws IOException{
        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic3\\src\\topic3\\resources");
        File file1 = new File(dir, "Profile-most Probable k-mer Problem.txt");
        Path filepath = file1.toPath();

        List<String> lines = Files.readAllLines(filepath);

        String text = lines.get(0);
        int k = Integer.parseInt(lines.get(1));

        GreedyMotifSearch gms = new GreedyMotifSearch(k);

        for (int i = 0; i<4;i++){
            String line = lines.get(i+2);
            String[] splited = line.split("\\s+");
            for (int j = 0;j<k;j++){
                gms.profile[i][j] = Double.parseDouble(splited[j]);
            }
        }

        System.out.print(gms.profile);
    }


}
