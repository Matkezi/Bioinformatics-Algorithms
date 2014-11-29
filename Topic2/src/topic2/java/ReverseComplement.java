package topic2.java;

/**
 * @author Matko
 */
public class ReverseComplement {

    /**
     * Makes a reverse complement of a given nucl-acid
     * @param nuclAcid text of nucleotides to be reversecompemented
     * @param type rna or dna
     * @return a reverse complement string
     */
    public static String reverseComplement(String nuclAcid, String type){

        StringBuilder complement = new StringBuilder();

        if (type.equals("dna")){
            for (int i = 0; i < nuclAcid.length();i++){
                if (nuclAcid.charAt(i) == 'A') complement.append("T");
                 else if (nuclAcid.charAt(i) == 'T') complement.append("A");
                 else if (nuclAcid.charAt(i) == 'G') complement.append("C");
                 else if (nuclAcid.charAt(i) == 'C') complement.append("G");
            }
        } else {
            for (int i = 0; i < nuclAcid.length();i++){
                if (nuclAcid.charAt(i) == 'A') complement.append("U");
                else if (nuclAcid.charAt(i) == 'U') complement.append("A");
                else if (nuclAcid.charAt(i) == 'G') complement.append("C");
                else if (nuclAcid.charAt(i) == 'C') complement.append("G");
            }
        }
        return complement.reverse().toString();
    }

}
