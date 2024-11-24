package Chapter1;
import java.util.stream.IntStream;

public class FibonacciSequenceStream {
    private int last = 0;
    private int next = 1;

    public IntStream stream(){
        return IntStream.generate(() -> {
            int oldLast = last;
            last = next;
            next = oldLast + next;
            return oldLast;
        });
    }


    public static void main(String[] args) {
        FibonacciSequenceStream fibSeq = new FibonacciSequenceStream();
        fibSeq.stream().limit(39).forEachOrdered(System.out::println);
    }

}
