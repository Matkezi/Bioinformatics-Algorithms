package topic5.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class LCSBacktrack {

    String[][] backtrack;
    String[] v;
    String[] w;

    public String outputLCS(String v, int i, int j){
        return "hello";
    }

    private void formBacktrack(){
        backtrack = new String[w.length][v.length];

    }

    public void loadFromFiles() throws IOException {

        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic5\\src\\topic5\\resources");
        File file = new File(dir, "LCSBacktrack.txt");

        List<String> lines = Files.readAllLines(file.toPath());

        v = lines.get(0).split("");
        w = lines.get(1).split("");

        formBacktrack();
    }
}
