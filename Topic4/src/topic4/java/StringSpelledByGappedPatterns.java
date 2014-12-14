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
