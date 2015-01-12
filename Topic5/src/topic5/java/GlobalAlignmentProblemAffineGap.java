package topic5.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * @author Matko
 * @version 1.0
CODE CHALLENGE: Solve the Alignment with Affine Gap Penalties Problem.
Input: Two amino acid strings v and w (each of length at most 100).
Output: The maximum alignment score between v and w, followed by an alignment of v and w
achieving this maximum score. Use the BLOSUM62 scoring matrix, a gap opening penalty of 11, and
a gap extension penalty of 1.

Sample Input:
PRTEINS
PRTWPSEIN

Sample Output:
8
PRT---EINS
PRTWPSEIN-
 */
public class GlobalAlignmentProblemAffineGap extends LoadAndExecute {
    String[][] backtrack;
    int[][] s;
    String[] v;
    String[] w;

    int sigma = 11;
    int epsilon = 1;
    HashMap<List,Integer> table = new HashMap<>();

    List<String> vOut = new ArrayList<>();
    List<String> wOut = new ArrayList<>();
    int score = 0;

    int[][] lower, upper, middle;


    protected void findScore(){
        score = middle[v.length][w.length];
    }

    protected void findAlignment(int i, int j){

    }

    protected void formBacktrack(){
        lower = new int[v.length+1][w.length+1];
        upper = new int[v.length+1][w.length+1];
        middle = new int[v.length+1][w.length+1];

        backtrack = new String[v.length+1][w.length+1];
        s = new int[v.length+1][w.length+1];

//        //initialize upper
//        for (int i = 0;i<v.length+1;i++) {
//            for (int j = 0; j < w.length; j++) {
//                upper[i][j+1] = upper[i][j]-epsilon;
//            }
//        }
//
//        //initialize lower
//        for (int j = 0;j<w.length+1;j++){
//            for (int i = 0;i<v.length;i++){
//                lower[i+1][j] = lower[i][j]-epsilon;
//            }
//        }

        for (int i =1;i<v.length+1;i++) {
            for (int j = 1; j < w.length + 1; j++) {

                //form a key for diagonal
                List<String> key = new ArrayList<>();
                key.add(v[i-1]);
                key.add(w[j-1]);

                lower[i][j] = Integer.max(lower[i-1][j]-epsilon,middle[i-1][j]-sigma);
                upper[i][j] = Integer.max(upper[i][j-1]-epsilon,middle[i][j-1]-sigma);
                int temp = Integer.max(lower[i][j],upper[i][j]);
                middle[i][j] = Integer.max(temp,middle[i-1][j-1]+table.get(key));

            }
        }
    }


    /**
     * Forms HasMap bloSum62, key is list of 2 alphabet letters and value is i"weight"
     */
    protected void loadTable(String filename){
        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic5\\src\\topic5\\resources");
        File file = new File(dir,filename);
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> alphabet = new LinkedList<>(Arrays.asList(lines.get(0).split("\\s+")));
        alphabet.remove(0);

        for (int i = 1;i<lines.size();i++){
            String[] currentLine = lines.get(i).split("\\s+");
            String firstLetter = currentLine[0];
            for (int j = 0;j<alphabet.size();j++){
                String secondLetter = alphabet.get(j);

                List<String> key = new ArrayList<>();
                key.add(firstLetter);
                key.add(secondLetter);

                Integer value = Integer.parseInt(currentLine[j+1]);

                table.put(key,value);
            }
        }
    }

    @Override
    public void execute() {

        List<String> lines = loadFromFiles("GlobalAlignmentProblemAffineGap.txt");
        v = lines.get(0).split("");
        w = lines.get(1).split("");

        loadTable("bloSum62.txt");
        formBacktrack();
        findAlignment(v.length, w.length);
        findScore();

        Collections.reverse(vOut);
        Collections.reverse(wOut);

        //printing
        System.out.println(score);

        for (int i = 0;i<vOut.size();i++){
            System.out.print(vOut.get(i));
        }
        System.out.println();
        for (int i = 0;i<wOut.size();i++){
            System.out.print(wOut.get(i));
        }

    }


}
