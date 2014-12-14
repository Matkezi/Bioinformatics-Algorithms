package topic4.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class StringSpelledByGappedPatterns {


    private String StringSpelledByPatterns(List<String> dnas){
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<dnas.size();i++){
            if (i == dnas.size()-1){
                sb.append(dnas.get(i));
            } else {
                sb.append(dnas.get(i).substring(0,1));
            }
        }
        return sb.toString();
    }

    private String findString(List<String> gapped, int k,int  d){



    }


    public void execute() throws IOException{
        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\out");
        File file1 = new File(dir, "PairedDeBruijnGraphProblemOut.txt");

        Path filepath = file1.toPath();

        List<String> gappedPatterns = Files.readAllLines(filepath);

        String solution = findString(gappedPatterns,GraphProblem.k,GraphProblem.d);
        System.out.println(solution);

    }

}
