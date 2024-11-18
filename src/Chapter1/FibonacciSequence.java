package Chapter1;

public class FibonacciSequence {
        //recursive version of Fibonnaci sequence, without memoization, that means the nym

        public static int fibSequence(int n){
            if (n<2){

                return n;

            }else{
                return fibSequence(n-1)+fibSequence(n-2);
            }
        }


        public static void main(String[] args) {
            System.out.println(fibSequence(10));
        }
}



