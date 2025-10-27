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
        
        CompleteBinaryTree<Integer> smallToLarge = new CompleteBinaryTree<>();
        smallToLarge.insert(minimumTree.getMinHeap());

        CompleteBinaryTree<Integer> largeToSmall = new CompleteBinaryTree<>();
        largeToSmall.insert(maximumTree.getMaxHeap());
        largeToSmall.printBFS();
    }
}