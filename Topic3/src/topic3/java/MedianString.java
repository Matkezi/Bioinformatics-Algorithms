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
}
