package Chapter1;

// here we want to understand how the compressing works, and we provide an example of compression from String to BitString

import java.util.BitSet;
// we have 4 type of nucleotides that can represent a gene in DNA, it is "A", "C", "G" and "T"
// DNA gene examples ACGT, GTCA and so on, so it can be represented with the collection of bits for example A can be 00
// C can be 01, G 10 and T 11
public class CompressedGene {
    private BitSet bitSet;
    private int length;

    public CompressedGene(String gene){
        compress(gene);
    }

    public void compress (String gene){
        length = gene.length();

        //reserving enough capcacity for all of the bits
        bitSet = new BitSet(length * 2); // for each char 2 bits here.
        final String upperGene = gene.toUpperCase(); // converting to upper case letters
        //finally converting string into bit representation
        for (int i = 0; i < length; i++) {
            final int firstLocation = 2 * i;
            final int secondLocation = 2 * i+1;
            switch (upperGene.charAt(i)){
                case'A': // we want 00 for "A"
                    bitSet.set(firstLocation, false);
                    bitSet.set(secondLocation, false);
                    break;
                case'C':// C = 01
                    bitSet.set(firstLocation, false);
                    bitSet.set(secondLocation, true);
                    break;
                case 'G':
                    bitSet.set(firstLocation, true);
                    bitSet.set(secondLocation, false);
                    break;
                case 'T':
                    bitSet.set(firstLocation,true);
                    bitSet.set(secondLocation,true);
                    break;
                default:
                    throw new IllegalArgumentException("The provided gene String contains characters other than ACGT!");
            }
        }
    }

    public String decompress(){
        //if 00 ->A,01->C,10->G, and 11->T
        if(bitSet == null){
            return "";
        }
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length*2; i+=2) {
            final int firstBit = (bitSet.get(i) ? 1 : 0);
            final int secondBit = (bitSet.get(i+1) ? 1 : 0);
            final int lastBits = firstBit<<1 | secondBit;

            switch (lastBits){
                case 0b00:
                    builder.append('A');
                    break;
                case 0b01:
                    builder.append('C');
                    break;
                case 0b10:
                    builder.append('G');
                    break;
                case 0b11:
                    builder.append('T');
                    break;
            }
        }return builder.toString();
    }

    public void printCompress(){
        System.out.println(bitSet);
    }

    public static void main(String[] args) {
        final String original = "gatcctccatatacaacggtatctccacctcaggtttagatctcaacaacggaaccattg";
        CompressedGene compressed = new CompressedGene(original);
        final String decompressed = compressed.decompress();
        System.out.println(decompressed);
        System.out.println("original is the same as decompressed: " + original.equalsIgnoreCase(decompressed));
        System.out.println(decompressed);
        compressed.printCompress();

    }
}
