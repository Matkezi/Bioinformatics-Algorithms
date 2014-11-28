package topic1.java;

/**
 * @author Matko
 */
public class ReverseComplement {

    public static String reverseComplement(String dna){

        StringBuilder complement = new StringBuilder();

        for (int i = 0; i < dna.length();i++){
            if (dna.charAt(i) == 'A'){
                complement.append("T");
            } else if (dna.charAt(i) == 'T'){
                complement.append("A");
            } else if (dna.charAt(i) == 'G'){
                complement.append("C");
            } else if (dna.charAt(i) == 'C'){
                complement.append("G");
            }
        }

        return complement.reverse().toString();
    }

}
