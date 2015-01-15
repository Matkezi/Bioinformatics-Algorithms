package topic6.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 * CODE CHALLENGE: Implement GREEDYSORTING.
Input: A permutation P.
Output: The sequence of permutations corresponding to applying GREEDYSORTING to P, ending with
the identity permutation.

Sample Input:
(-3 +4 +1 +5 -2)

Sample Output:
(-1 -4 +3 +5 -2)
(+1 -4 +3 +5 -2)
(+1 +2 -5 -3 +4)
(+1 +2 +3 +5 +4)
(+1 +2 +3 -4 -5)
(+1 +2 +3 +4 -5)
(+1 +2 +3 +4 +5)

 Algorithm:
GREEDYSORTING(P)
    approxReversalDistance ← 0
    for k = 1 to |P|
        if element k is not sorted
            apply the k-sorting reversal to P
        approxReversalDistance ← approxReversalDistance + 1
        if k-th element of P is −k
            apply the k-sorting reversal to P
            approxReversalDistance ← approxReversalDistance + 1
    return approxReversalDistance
 */
public class GreedySorting extends LoadAndExecute {

    private List<Integer> p = new ArrayList<>();


    private void reverseP(int k){

        List<Integer> tempList = p.subList(k,p.indexOf(k+1)+1);
        Collections.reverse(tempList);

        List<Integer> reverseSignList = new ArrayList<>();
        for (Integer num : tempList) reverseSignList.add(num * -1);

        List<Integer> finalList = new ArrayList<>();

        finalList.addAll(p.subList(0,k));
        finalList.addAll(k,reverseSignList);
        finalList.addAll(p.subList(k,p.size()));
        System.out.println(finalList);
    }

    private void sort(){

        for (int k = 0;k<p.size();k++){
            if (Math.abs(p.get(k)) != k+1) reverseP(k);

            if (p.get(k) < 0){

            }
        }
    }


    @Override
    public void execute(String fileName) {
        List<String> lines = loadFromFiles(fileName);

        //fill list p
        String inputLine = lines.get(0);
        inputLine = inputLine.replaceAll("\\(","");
        inputLine = inputLine.replaceAll("\\)","");
        String[] inputLineArray = inputLine.split(" ");
        for (String lineComp : inputLineArray) p.add(Integer.parseInt(lineComp));

       sort();

    }

    public List<Integer> getP() {
        return p;
    }
}
