import java.util.ArrayList;

public class MinHeap<T extends Comparable<T>>{
    private ArrayList<T> list;

    //Constructors
    public MinHeap(){
        this.list = new ArrayList<>();
    }

    //Getter Methods
    public ArrayList<T> getMinHeap(){
        return this.list;
    }

    //Setter Methods
    public void insert(T data){
        this.list.add(data);
        int index = this.list.size() - 1;
        while (index > 0){
            T child = this.list.get(index);
            T parent = this.list.get((int) (index - 1) / 2);
            if (child.compareTo(parent) < 0){
                T temp = parent;
                this.list.set((int) (index - 1) / 2, child);
                this.list.set(index, temp);
                index = (int) (index - 1) / 2;
            }
            else
                index = -1;
        }
    }

    public void deleteMin(){
        this.list.set(0, this.list.remove(this.list.size() - 1));
        this.minHeapify(0);
    }

    public void minHeapify(int index){
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        if (leftChildIndex < this.list.size()){
            if (rightChildIndex >= this.list.size()){
                if (this.list.get(index).compareTo(this.list.get(leftChildIndex)) > 0){
                    T temp = this.list.get(index);
                    this.list.set(index, this.list.get(leftChildIndex));
                    this.list.set(leftChildIndex, temp);
                }
            }
            else{
                int minimumIndex = this.list.get(leftChildIndex).compareTo(this.list.get(rightChildIndex)) <= 0 ? leftChildIndex : rightChildIndex;
                if (this.list.get(index).compareTo(this.list.get(minimumIndex)) > 0){
                    T temp = this.list.get(index);
                    this.list.set(index, this.list.get(minimumIndex));
                    this.list.set(minimumIndex, temp);
                    this.minHeapify(minimumIndex);
                }
            }
        }
    }
}