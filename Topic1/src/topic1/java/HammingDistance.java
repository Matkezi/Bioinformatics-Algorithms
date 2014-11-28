package topic1.java;

/**
 * @author Matko
 */
public class HammingDistance {

    public static int findHammingDistance(String p, String q){
        int distance = 0;
        for (int i = 0;i<p.length();i++){
            if (p.charAt(i) != q.charAt(i)){
                distance++;
            }
        }
        return distance;
    }
}
