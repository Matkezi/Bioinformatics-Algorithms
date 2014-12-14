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
        List<String> firstPatterns = new ArrayList<>();
        List<String> secondPatterns = new ArrayList<>();
        for (int i = 0;i<gapped.size();i++){
            String[] line = gapped.get(i).split("\\|");
            firstPatterns.add(line[0]);
            secondPatterns.add(line[1]);
        }

        String prefixString = StringSpelledByPatterns(firstPatterns);
        String suffixString = StringSpelledByPatterns(secondPatterns);

        StringBuilder prefix = new StringBuilder(prefixString);
        StringBuilder suffix = new StringBuilder(suffixString);

        for (int i = k+d+1;i<prefix.length();i++){
            if (prefix.charAt(i) != suffix.charAt(i-k-d)){
                return  "there is no string spelled by the gapped patterns";
            }
        }

        prefix.append(suffix.subSequence(suffixString.length()-k-d,suffixString.length()));
        return prefix.toString();
    }


    public void execute() throws IOException{
        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\out");
        File file1 = new File(dir, "EulerPathOut.txt");

        Path filepath = file1.toPath();

        List<String> gappedPatterns = Files.readAllLines(filepath);

        String solution = findString(gappedPatterns,GraphProblem.k,GraphProblem.d);
        System.out.println(solution);

    }

}
