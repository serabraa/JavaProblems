package Chapter2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Gene {

    public enum Nucleotide {
        A, C, G, T
    }


    public static class Codon implements Comparable<Codon> {
        public final Nucleotide first,second,third;
        private final Comparator<Codon> comparator = Comparator.comparing((Codon c)-> c.first).
                thenComparing((Codon c)->c.second).thenComparing((Codon c) -> c.third);

    public Codon(String codonString){
        first = Nucleotide.valueOf(codonString.substring(0,1));
        second = Nucleotide.valueOf(codonString.substring(1,2));
        third = Nucleotide.valueOf(codonString.substring(2,3));
    }

        @Override
        public int compareTo(Codon other) {
            return comparator.compare(this,other);
        }
    }
        private ArrayList<Codon> codons = new ArrayList<>();

    public Gene(String geneStr){
        for (int i = 0; i < geneStr.length() - 3; i+=3) {
            codons.add(new Codon(geneStr.substring(i,i+3))); //each 3 chars of gene string is transformed to a codon and added in the array list of codons
        }
    }

    public boolean linearSearchCodon(Codon c){
        for (Codon codon:
             codons) {
            if (codon.compareTo(c) == 0){
                return true;
            }
        }return false;
    }

    public boolean binarySearchCodon(Codon c) {
    //AS THIS IS AN ILLUSTRATIVE EXAMPLE, WE ARE NOT WORRYING ABOUT THE PERFORMANCE. FOR THE BINARY SEARCH WE NEED A SORTED DATASET, SO NOW WE SORT THIS
    ArrayList<Codon> sortedCodons = new ArrayList<>(codons);
    Collections.sort(sortedCodons);
    int low = 0;
    int high = sortedCodons.size()-1;

    while (low <= high){
        int middle = (low + high)/2; //findign middle element
        int comparisonResult = sortedCodons.get(middle).compareTo(c); //-1 if less, 0 if equal , 1 if more
            if (comparisonResult <0){
                low = middle +1;
            }
            else if (comparisonResult >0){
                high = middle -1;
            }else
                return true;

        }return false;
    }


    public static void main(String[] args) {
        String geneStr = "ACGTGCTAGCTAGGCTAACGAUG";
        Gene sergisticCreated = new Gene(geneStr);

//        Codon aug = new Codon("AUG");
        Codon acg = new Codon("ACG");
        Codon cga = new Codon("CGA");
        Codon gtc = new Codon("GTC");
//        Codon cg = new Codon("CGAL");

//        System.out.println(sergisticCreated.linearSearchCodon(acg));
//        System.out.println(sergisticCreated.linearSearchCodon(cga));

        System.out.println(sergisticCreated.binarySearchCodon(cga));
        System.out.println(sergisticCreated.binarySearchCodon(gtc));
    }
}
