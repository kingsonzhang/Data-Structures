import java.util.ArrayList;

public class Queue{
    private ArrayList<Object> list;

    //Constructor simply initializes ArrayList list
    public Queue(){
        this.list = new ArrayList<>();
    }

    //Getter Methods
    public Object poll(){
        return this.list.getFirst();
    }

    public int size(){
        return this.list.size();
    }

    //Setter Methods
    public void enqueue(Object data){
        this.list.addLast(data);
    }

    public Object dequeue(){
        return this.list.removeFirst();
    }
}