package topic5.java;

import java.util.List;

/**
 * @author Matko
 * @version 1.0
 * CODE CHALLENGE: Implement LINEARSPACEALIGNMENT to solve the Global Alignment Problem for a large dataset.
Input: Two long (10000 amino acid) protein strings written in the single-letter amino acid alphabet.
Output: The maximum alignment score of these strings, followed by an alignment achieving this
maximum score. Use the BLOSUM62 scoring matrix and indel penalty σ = 5.

Sample Input:
PLEASANTLY
MEANLY

Sample Output:
8
PLEASANTLY
-MEA--N-LY

 Pseudocode:
LINEARSPACEALIGNMENT(top, bottom, left, right)
    if left = right
        return alignment formed by bottom − top vertical edges
    if top = bottom
        return alignment formed by right − left horizontal edges
    middle ← ⌊ (left + right)/2⌋
    midNode ← MiddleNode(top, bottom, left, right)
    midEdge ← MiddleEdge(top, bottom, left, right)
    LINEARSPACEALIGNMENT(top, midNode, left, middle)
    output midEdge
    if midEdge = "→" or midEdge = "↘"
        middle ← middle + 1
    if midEdge = "↓" or midEdge ="↘"
        midNode ← midNode + 1
    LINEARSPACEALIGNMENT(midNode, bottom, middle, right)
 */
public class LinearSpaceAlingment extends GlobalAlignmentProblem {



    @Override
    public void execute() {
        List<String> lines = loadFromFiles("LinearSpaceAlingment.txt");
        v = lines.get(0).split("");
        w = lines.get(1).split("");

        loadTable("bloSum62.txt");


    }
}
