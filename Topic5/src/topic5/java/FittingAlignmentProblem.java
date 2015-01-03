package topic5.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class FittingAlignmentProblem extends LocalAlignmentProblem {

    List<Integer> possibleEndingsCoordinates = new ArrayList<>();

    protected void findAlignment(int i, int j){

        if (i == 0 && j == 0){//only when we reach the end of matrix we can end the reursion
            return;
        } else {//if we are in row 0 we must go left, and if we are in column 0 we must go up
            if (i == 0) backtrack[i][j] = "right";
            else if (j == 0) backtrack[i][j] = "down";
        }


        switch (backtrack[i][j]){
            case "down":
                vOut.add(v[i-1]);
                wOut.add("-");

                findAlignment(i - 1, j);
                break;

            case "right":
                vOut.add("-");
                wOut.add(w[j-1]);

                findAlignment(i, j - 1);
                break;

            case "diagonal":
                vOut.add(v[i-1]);
                wOut.add(w[j-1]);
                findAlignment(i - 1, j - 1);
                break;

            default:
                return;

        }
    }

    protected void formBacktrack(){
        backtrack = new String[v.length+1][w.length+1];
        s = new int[v.length+1][w.length+1];

        /*
        //initialiaze first row
//        for (int j = 0;j<w.length;j++){
//            s[0][j+1] = s[0][j]-sigma;
//        }
//
//        //initialiaze first collumn
//        for (int i = 0;i<v.length;i++){
//            s[i+1][0] = s[i][0]-sigma;
//        }
        */


       //start and end of substring
        for (int i =1;i<v.length+1;i++){
            //set free taxi to start of a substring v'
            if (v[i-1].equals(w[0])) {
                s[i][0] = 0;
                backtrack[i][0] = "start";
            }

            //set free taxi from end of substring v'
            if (v[i-1].equals(w[w.length-1])){

            }
        }





    }

    public void execute(){
        List<String> lines = loadFromFiles("FittingAlignmentProblem.txt");
        v = lines.get(0).split("");
        w = lines.get(1).split("");

        loadTable("PAM250_1.txt");
        formBacktrack();
        findAlignment(maxRowindex, maxColumnindex);
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
