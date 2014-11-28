package topic3.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class MedianString {

    String median;
    private List<String> dnas = new ArrayList<>();
    private int k;

    public MedianString(List<String> dnas, int k) {
        this.dnas = dnas;
        this.k = k;
        findMedianString();
    }

    private void findMedianString(){


    }

    private String numberToSymbol(int index){
        switch (index){
            case 0: return "A";
            case 1: return "C";
            case 2: return "G";
            case 3: return "T";
            default: return "ERROR";
        }
    }

    private String numberToPattern(int index, int k){
        if (k == 1){
            return numberToSymbol(index);
        }
    }
}
