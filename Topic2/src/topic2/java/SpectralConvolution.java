package topic2.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class SpectralConvolution {

    public static void main (String[] args) throws IOException{
        ProteinTranslation.initiateCodonMap();

        String filepath = "C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics Algorithms\\Topic2\\src\\topic2\\resources\\dataset_104_4.txt";
        List<String> lines = Files.readAllLines(Paths.get(filepath));

        List<Integer> spectrum = new ArrayList<>();

        String[] splitLine = lines.get(0).split("\\s+");
        for (String line : splitLine){
            spectrum.add(Integer.parseInt(line));
        }
        Collections.sort(spectrum);
        System.out.println(spectrum);

        List<Integer> result = new ArrayList<>();
        for(int j = 1;j<spectrum.size();j++){
            for (int i = 0;i<spectrum.size()-1;i++){
                int col = spectrum.get(j);
                int row = spectrum.get(i);
                if (col > row) result.add(col - row);
                else break;
            }
        }
        System.out.println(result);
        Collections.sort(result);

        for (Integer rez : result){
            System.out.print(rez+" ");
        }
    }
}
