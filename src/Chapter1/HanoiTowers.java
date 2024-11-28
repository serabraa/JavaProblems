package Chapter1;

import java.util.Stack;

public class HanoiTowers {

    private final int numDiscs;
    public final Stack<Integer> towerA = new Stack<>();
    public final Stack<Integer> towerB = new Stack<>();
    public final Stack<Integer> towerC = new Stack<>();

    public HanoiTowers(int numDiscs){
        this.numDiscs = numDiscs;
        for (int i = numDiscs; i >= 1; i--) {
            towerA.push(i);
        }
    }

    private void move(Stack<Integer> from,Stack<Integer>to){
        to.push(from.pop());

    }

    public void solveManual(){
        move(towerA,towerC);
        move(towerA,towerB);
        move(towerC,towerB);
        move(towerA,towerC);
        move(towerB,towerA);
        move(towerB,towerC);
        move(towerA,towerC);
    }

    public void legalMove(Stack<Integer> first, Stack<Integer> second) {
        if (!first.isEmpty() && (second.isEmpty() || first.peek() < second.peek())) {
            // Move from first to second if second is empty or first's top is smaller
            move(first, second);
        } else if (!second.isEmpty() && (first.isEmpty() || second.peek() < first.peek())) {
            // Move from second to first if first is empty or second's top is smaller
            move(second, first);
        } else {
            throw new IllegalStateException("No legal moves possible between these two towers.");
        }
    }


    public void solveIteratively(int numDiscs){
        int numberOfMoves = (int) Math.pow(2,numDiscs) -1;
        Stack<Integer> t1 = towerA;
        Stack<Integer> t2 = towerB;
        Stack<Integer> t3 = towerC;


        if (numDiscs %2 == 0){
            Stack<Integer> temp = t2;
            t2 = t3;
            t3 = temp;
        }

        for (int i = 0; i < numberOfMoves; i++) {
            if (i%3==0){
                legalMove(t1,t3);
            }
            else if(i%3==1){
                legalMove(t1,t2);
            }
            else legalMove(t2,t3);

        }
        printLastHanoiTower();
    }

    public void solveIterativelyLogical(int numDiscs){
        int numberOfMoves =  (int) Math.pow(2,numDiscs) -1;

        for (int i = 0; i < numberOfMoves; i++) {
            if(i%2 == 0){
                if (!towerA.isEmpty() && towerB.isEmpty() && towerC.isEmpty()) {
                    move(towerA,towerC);
                }else if (!towerB.isEmpty() && !towerC.isEmpty() && !towerA.isEmpty()){
                    move(towerC,towerB);
                }else if(towerA.isEmpty() && !towerB.isEmpty() && !towerC.isEmpty()){
                    move(towerB,towerA);
                }else if(!towerA.isEmpty() && towerB.isEmpty() && !towerC.isEmpty()){
                    move(towerA,towerC);
                }
            }else if(i == numberOfMoves/2){
                move(towerA,towerC);
            }else {
                if(towerB.isEmpty() && !towerC.isEmpty()){
                    move(towerA,towerB);
                }else if(!towerA.isEmpty() && !towerB.isEmpty() && !towerC.isEmpty()){
                    move(towerB,towerC);
                }
            }

        }
    printLastHanoiTower();
    }

    public void printLastHanoiTower(){
        while (!towerC.isEmpty()){
            System.out.println(towerC.pop());
        }
    }

    public static void main(String[] args) {
       HanoiTowers sergusTowers = new HanoiTowers(8);
        System.out.println(sergusTowers.towerA.peek());
        sergusTowers.solveIteratively(8);
    }
}
