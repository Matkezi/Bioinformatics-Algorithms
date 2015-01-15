package topic6.java;

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

    @Override
    public void execute(String fileName) {
        List<String> lines = loadFromFiles(fileName);

    }
}
