import java.util.Random;
import java.util.ArrayList;

public class Tester{
    public static void main(String args[]){
        MinHeap<Integer> minimumTree = new MinHeap<>();
        MaxHeap<Integer> maximumTree = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++){
            minimumTree.insert(Math.abs(random.nextInt() % 1000000));
            maximumTree.insert(Math.abs(random.nextInt() % 1000000));
        }
        
        Tree<Integer> smallToLarge = new Tree<>();
        smallToLarge.insert(minimumTree.getMinHeap());

        Tree<Integer> largeToSmall = new Tree<>();
        largeToSmall.insert(maximumTree.getMaxHeap());
        largeToSmall.printBFS();
    }
}