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

    public void outputLCS(int i, int j){
        if (i == 0 || j == 0){
            return;
        }


        switch (backtrack[i][j]){
            case "down": outputLCS(i-1,j);
                         break;

            case "right": outputLCS(i,j-1);
                          break;

            default:      outputLCS(i-1,j-1);
                          System.out.print(v[i-1]);
                          break;
        }
    }

    private void formBacktrack(){
        backtrack = new String[v.length+1][w.length+1];
        s = new int[v.length+1][w.length+1];

        for (int i =1;i<v.length+1;i++){
            for (int j = 1;j<w.length+1;j++){

                int maxTmp = Integer.max(s[i-1][j],s[i][j-1]);
                if (v[i-1].equals(w[j-1])){
                    int diagonal = s[i-1][j-1] + 1;
                    s[i][j] = Integer.max(maxTmp,diagonal);
                } else s[i][j] = maxTmp;

                if (s[i][j] == s[i-1][j]){
                    backtrack[i][j] = "down";
                } if (s[i][j] == s[i][j-1]){
                    backtrack[i][j] = "right";
                } if (v[i-1].equals(w[j-1]) && s[i][j] == s[i-1][j-1]+1){
                    backtrack[i][j] = "diagonal";
                }
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
        outputLCS(v.length,w.length);
    }
}
