import java.util.ArrayList;

public class Stack{
    private ArrayList<Object> list;

    //Constructors
    public Stack(){
        this.list = new ArrayList<>();
    }

    //Getter Methods
    public Object peek(){
        return this.list.getLast();
    }

    public int size(){
        return this.list.size();
    }

    //Setter Methods
    public void push(Object element){
        this.list.add(element);
    }

    public Object pop(){
        return this.list.removeLast();
    }
}