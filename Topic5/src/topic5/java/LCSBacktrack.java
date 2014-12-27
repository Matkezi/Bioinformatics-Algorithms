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
    int[][] s;
    String[] v;
    String[] w;

    public String outputLCS(String v, int i, int j){
        return "hello";
    }

    private void formBacktrack(){
        backtrack = new String[w.length][v.length];
        s = new int[v.length][w.length];

        for (int i =1;i<v.length;i++){
            for (int j = 1;j<w.length;j++){
                int maxTmp = Integer.max(s[i-1][j],s[i][j-1]);
                if (v[i].equals(w[j])){
                    int diagonal = s[i-1][j-1] + 1;
                    s[i][j] = Integer.max(maxTmp,diagonal);
                } else s[i][j] = maxTmp;



            }
        }

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
