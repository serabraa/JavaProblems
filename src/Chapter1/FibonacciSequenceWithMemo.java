package Chapter1;

import java.util.Map;
import java.util.HashMap;

public class FibonacciSequenceWithMemo {

    static Map<Integer, Integer> memo = new HashMap<>(Map.of(0, 0, 1, 1)); //this represents our base values of fib(0)=0 and fib(1)=1

    public static int fibSequenceMemo(int n){
        if(!memo.containsKey(n)){
            memo.put(n,fibSequenceMemo(n-2) + fibSequenceMemo(n-1));
        }
        return memo.get(n);
    }


    public static void main(String[] args) {
        System.out.println(fibSequenceMemo(24));

    }
}
