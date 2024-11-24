package Chapter1;

public class FibonnaciSequenceIterative {
    //Fibonnaci Sequence by Iterative approach, note that here counts start from 1, so that fibSequenceIter(0) is causing to an exception

    public static int fibSequenceIter(int n){
        int first = 0;  //first means the first number of fibonnaci, in the start it is fib(1)
        int second = 1; //with the same logic second means here second number of fibonnaci, in the start fib(2)


        if(n<=0){
            throw new IllegalArgumentException("The input must be higher than 0");}
        if(n==1){
            return first;}
        if(n==2 ){
            return second;}
        // if n>2, we just calculate it iteratively
        for (int i = 1; i !=n-2 ; i++) {
            second = first+ second;      // here the next one is calculated
            first = second - first;     //here happens the variable swapping
            }
        return first + second;
    }

    public static void main(String[] args) {
        System.out.println(fibSequenceIter(25));
    }
}
