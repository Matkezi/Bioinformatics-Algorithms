package topic5.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 *
 * CODE CHALLENGE: Solve the Global Alignment Problem.
Input: Two protein strings written in the single-letter amino acid alphabet.
Output: The maximum alignment score of these strings followed by an alignment achieving this
maximum score. Use the BLOSUM62 scoring matrix and indel penalty σ = 5.

Sample Input:
PLEASANTLY
MEANLY

Sample Output:
8
PLEASANTLY
-MEA--N-LY


 TIP: BLOSUM62 gives you the score for both identities (Vi == Wj) and mismatches (Vi<>Wj).
Then, the alignment recurrence should be written as:

S(i,j) = max { S(i-1,j) - sigma;           S(i, 1-j) -sigma;         S(i,j) + BLOSUM62(vi, wi) }

where BLOSUM62(vi, wii) is the BLOSUM62 score for aligning residue vi with residue wi
 */
public class GlobalAlignmentProblem extends LoadAndExecute {


    @Override
    public void execute() throws IOException{

        List<String> lines = Files.readAllLines(super.loadFromFiles("GlobalAlignmentProblem.txt").toPath());
    }


}
